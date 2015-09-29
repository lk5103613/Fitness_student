package com.like.fragments;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.like.adapter.InfoAdapter;
import com.like.customview.pulltorefresh.PullToRefreshBase;
import com.like.customview.pulltorefresh.PullToRefreshListView;
import com.like.entity.Category;
import com.like.entity.CoachInfo;
import com.like.entity.Coaches;
import com.like.fitness.student.CoachDetailActivity;
import com.like.fitness.student.MapActivity;
import com.like.fitness.student.R;
import com.like.listener.SearchListener;
import com.like.network.APIS;
import com.like.network.DataFetcher;
import com.like.network.GsonUtil;
import com.like.network.MyNetworkUtil;

public class IndexFragment extends BaseFragment implements OnClickListener{

	private Context mContext;

	private PullToRefreshListView mCoachList;
	private TextView mTxtRedu;
	private TextView mTxtPingjia;
	private TextView mTxtJuli;

	private InfoAdapter mAdapter;
	private List<CoachInfo> mCoaches;
	private DataFetcher mDataFetcher;
	private ViewGroup mCategoryContainer;

	private TextView[] mSorts;
	private List<TextView> mTextViews;
	private int mCurrentCatIndex = -1;
	private int mCurrentPage = 0;
	private int mCurrentSort = 0;

	private Coaches coaches = new Coaches();

	private PopupWindow mSXWindow;
	private RelativeLayout mShaiXuanLayout;
	private SearchListener mListener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_index, container, false);

		this.mContext = getActivity();
		mListener = (SearchListener) getActivity();
		mDataFetcher = DataFetcher.getInstance(mContext);

		mCoachList = (PullToRefreshListView) view.findViewById(R.id.coach_list);
		mCategoryContainer = (ViewGroup) view.findViewById(R.id.categories_container);
		mShaiXuanLayout = (RelativeLayout) view.findViewById(R.id.shaixuan_container);

		mTxtRedu = (TextView) view.findViewById(R.id.redu);
		mTxtPingjia = (TextView) view.findViewById(R.id.pingjia);
		mTxtJuli = (TextView) view.findViewById(R.id.juli);
		mSorts = new TextView[] { mTxtRedu, mTxtPingjia, mTxtJuli };
		
		mTxtRedu.setOnClickListener(this);
		mTxtJuli.setOnClickListener(this);
		mTxtPingjia.setOnClickListener(this);

		mAdapter = new InfoAdapter(mContext, coaches.list, MyNetworkUtil
				.getInstance(mContext).getImageLoader());
		mCoachList.setAdapter(mAdapter);
		mCoachList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
					@Override
					public void onRefresh(
							PullToRefreshBase<ListView> refreshView) {
						String key = mListener.getSearchKey();
						updateData(mCurrentCatIndex, mCurrentPage,
								mCurrentSort, key);
					}
				});

		

		mCoachList
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Intent intent = new Intent(mContext,
								CoachDetailActivity.class);
						CoachInfo coachInfo = mAdapter.getItem(position - 1);
						intent.putExtra("id", coachInfo.coachid);
						startActivity(intent);
					}
				});

		mDataFetcher.fetchCategory(APIS.GET_CATEGORY,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Type type = new TypeToken<List<Category>>() {
						}.getType();
						List<Category> categories = GsonUtil.gson.fromJson(
								response.toString(), type);
						addCategories(categories);

						updateData(mCurrentPage, 0, 0, mListener.getSearchKey());
					}
				}, null);

		return view;
	}
	
	private void addCategories(List<Category> categories) {
		mTextViews = new ArrayList<TextView>();
		for (int i = 0; i < categories.size(); i++) {
			TextView textView = new TextView(mContext);
			textView.setTextSize(13);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.MATCH_PARENT);
			textView.setLayoutParams(lp);
			textView.setGravity(Gravity.CENTER);
			textView.setPadding(30, 0, 30, 0);
			textView.setTextColor(0xff555555);
			if (i == 0) {
				textView.setTextColor(0xffbf4722);
				mCurrentCatIndex = 0;
			}
			textView.setText(categories.get(i).catname);
			textView.setId(categories.get(i).catid);
			final int finalI = i;
			textView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (mCurrentCatIndex != -1) {
						mTextViews.get(mCurrentCatIndex).setTextColor(
								0xff555555);
						mTextViews.get(finalI).setTextColor(0xffBF4722);
					}
					mCurrentCatIndex = finalI;
					updateData(mCurrentCatIndex, mCurrentPage, 0, mListener.getSearchKey());
				}
			});
			mTextViews.add(textView);
			mCategoryContainer.addView(textView);
		}
	}

	private void updateData(int cat, int page, int sort, String key) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("currentPage", page + "");
		params.put("catid", cat + "");
		params.put("sort", sort + "");
        try {
            key =  URLEncoder.encode(key, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }

        if (!TextUtils.isEmpty(key)) {
			params.put("key", key);
		}

		mDataFetcher.fetchCoachListByParams(APIS.GET_COACH_LIST, params,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						
						System.out.println("response " + response);
						Coaches tempCoaches = GsonUtil.gson.fromJson(
								response.toString(), Coaches.class);
						coaches.list.clear();
						coaches.list.addAll(tempCoaches.list);
						mAdapter.notifyDataSetChanged();
                        mCoachList.onRefreshComplete();
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						System.out.println("error");
					}
				});
	}

	public void showShaiXuan(View v) {
		if (mShaiXuanLayout.getVisibility() == View.VISIBLE) {
			mShaiXuanLayout.setVisibility(View.GONE);
		} else {
			mShaiXuanLayout.setVisibility(View.VISIBLE);
		}

	}

	public void backgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = ((Activity)mContext).getWindow().getAttributes();
		lp.alpha = bgAlpha;
		((Activity)mContext).getWindow().setAttributes(lp);
	}

	@Override
	public void updateData(String key){
		System.err.println("update");
		updateData(mCurrentCatIndex, mCurrentPage, 0, key);
	}

	@Override
	public void onClick(View v) {
		mSorts[mCurrentSort].setTextColor(0xff555555);
        ((TextView)v).setTextColor(getResources().getColor(R.color.text_selected_color));
		switch (v.getId()) {
			case R.id.redu:
				mCurrentSort = 0;
				break;
			case R.id.pingjia:
				mCurrentSort = 1;
				break;
			case R.id.juli:
				mCurrentSort = 2;
				break;
		}
		String search = mListener.getSearchKey();
		updateData(mCurrentCatIndex, 0, mCurrentSort, search);
	}
}

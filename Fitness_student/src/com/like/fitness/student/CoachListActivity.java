package com.like.fitness.student;

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
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
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
import com.like.network.APIS;
import com.like.network.DataFetcher;
import com.like.network.GsonUtil;
import com.like.network.MyNetworkUtil;

public class CoachListActivity extends Activity {

	private Context mContext;
	private Context mApplicationContext;

	private PullToRefreshListView mCoachList;
	private EditText mTxtSearch;
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

	private Coaches coaches = new Coaches();

	private PopupWindow mSXWindow;
	private RelativeLayout mShaiXuanLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coach_list);

		this.mContext = this;
		this.mApplicationContext = getApplicationContext();
		mDataFetcher = DataFetcher.getInstance(mApplicationContext);

		mCoachList = (PullToRefreshListView) findViewById(R.id.coach_list);
		mTxtSearch = (EditText) findViewById(R.id.txt_search_content);
		mCategoryContainer = (ViewGroup) findViewById(R.id.categories_container);
		mShaiXuanLayout = (RelativeLayout) findViewById(R.id.shaixuan_container);


		mTxtRedu = (TextView) findViewById(R.id.redu);
		mTxtPingjia = (TextView) findViewById(R.id.pingjia);
		mTxtJuli = (TextView) findViewById(R.id.juli);
		mSorts = new TextView[]{mTxtRedu, mTxtPingjia, mTxtJuli};

		mAdapter = new InfoAdapter(mContext, coaches.list, MyNetworkUtil
				.getInstance(mApplicationContext).getImageLoader());
		mCoachList.setAdapter(mAdapter);
        mCoachList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String key = mTxtSearch.getText().toString();
                updateData(mCurrentCatIndex, mCurrentPage, mCurrentSort, key);
            }
        });

        mTxtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return false;
            }
        });
		mTxtSearch.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String searchText = s.toString();
				updateData(mCurrentCatIndex, mCurrentPage, 0, searchText);
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		mCoachList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Intent intent = new Intent(mContext,CoachDetailActivity.class);
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
						
						updateData(mCurrentPage,0,0,"");
					}
				}, null);

	}

	private void addCategories(List<Category> categories) {
		mTextViews = new ArrayList<>();
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
					updateData(mCurrentCatIndex, mCurrentPage, 0, "");
				}
			});
			mTextViews.add(textView);
			mCategoryContainer.addView(textView);
		}
	}

	private void updateData(int cat, int page, int sort, String key) {
		Map<String, String> params = new HashMap<>();
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
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = bgAlpha;
		getWindow().setAttributes(lp);
	}

	private int mCurrentSort = 0;
	public void sort(View v) {
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
		String search = mTxtSearch.getText().toString();
		updateData(mCurrentCatIndex, 0, mCurrentSort, search);
	}

    public void toMap(View v) {
        Intent intent = new Intent(mContext, MapActivity.class);
        startActivity(intent);
    }

}

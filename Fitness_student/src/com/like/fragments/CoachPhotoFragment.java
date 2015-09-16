package com.like.fragments;

import java.lang.reflect.Type;
import java.util.List;

import org.json.JSONArray;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.google.gson.reflect.TypeToken;
import com.like.adapter.PhotoGridAdapter;
import com.like.customview.PageControlView;
import com.like.customview.ScrollLayout;
import com.like.entity.Photo;
import com.like.fitness.student.R;
import com.like.network.APIS;
import com.like.network.DataFetcher;
import com.like.network.GsonUtil;
import com.like.network.MyNetworkUtil;

public class CoachPhotoFragment extends Fragment {

	private List<Photo> photos;
	private ScrollLayout mScrollLayout;
	private static final float APP_PAGE_SIZE = 9f;
	private Context mContext;
	private PageControlView pageControl;
	public int n=0;
	private DataFetcher mFetcher;
	 private int coachId;
	private ImageLoader mImageLoader;

	public CoachPhotoFragment() {
		mFetcher = DataFetcher.getInstance(getActivity());
	}
	
	public void setCoachId(int coachId) {
        this.coachId = coachId;
        
        mFetcher.fetchPhotoById(APIS.GET_COACH_PHOTO, coachId, new Response.Listener<JSONArray>() {
			@Override
			public void onResponse(JSONArray response) {
				Type type = new TypeToken<List<Photo>>(){}.getType();
				photos = GsonUtil.gson.fromJson(response.toString(), type);
				System.out.println("photos " + photos.size());
				initData();
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				System.out.println(error.getMessage());
			}
		});
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = getActivity();
		View view = inflater.inflate(R.layout.fragment_coach_photo, container,false);
		mScrollLayout = (ScrollLayout)view.findViewById(R.id.scroll_layout);
		pageControl = (PageControlView) view.findViewById(R.id.pageControl);
		mImageLoader = MyNetworkUtil.getInstance(getActivity().getApplicationContext()).getImageLoader();
		return view;
	}

	public void initData() {
		
		int pageNo = (int)Math.ceil( photos.size()/APP_PAGE_SIZE);
		for (int i = 0; i < pageNo; i++) {
			GridView appPage = new GridView(mContext);
			appPage.setGravity(Gravity.CENTER);

			appPage.setAdapter(new PhotoGridAdapter(mContext, photos, i, mImageLoader));
			appPage.setNumColumns(3);
			mScrollLayout.addView(appPage);
		}
		
		//加载分页
		pageControl.bindScrollViewGroup(mScrollLayout);

	}

}

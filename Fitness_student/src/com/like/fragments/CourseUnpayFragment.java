package com.like.fragments;

import java.lang.reflect.Type;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response.Listener;
import com.google.gson.reflect.TypeToken;
import com.like.adapter.CourseListAdapter;
import com.like.customview.pulltorefresh.PullToRefreshBase;
import com.like.customview.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.like.customview.pulltorefresh.PullToRefreshListView;
import com.like.entity.Course;
import com.like.entity.ListResult;
import com.like.fitness.student.R;
import com.like.network.GsonUtil;

public class CourseUnpayFragment extends BaseFragment {
	
	private PullToRefreshListView mList;
	private int mStatus = 0;
	private CourseListAdapter mAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_nopay, container, false);
		initView(view);
		updateList();
		return view;
	}
	
	private void initView(View view) {
		mList = (PullToRefreshListView) view.findViewById(R.id.unpay_list);
		mList.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				updateList();
			}
		});
	}
	
	private void updateList() {
		mDataFetcher.fetchMyCourse(mLoginUser.uid, mStatus+"", new Listener<String>(){
			@Override
			public void onResponse(String response) {
				Type type = new TypeToken<ListResult<Course>>(){}.getType();
				ListResult<Course> courseResult = GsonUtil.gson.fromJson(response, type);
				if(mAdapter == null) {
					mAdapter = new CourseListAdapter(mContext, courseResult.list);
					mList.setAdapter(mAdapter);
				} else {
					mAdapter.update(courseResult.list);
				}
				if(mList.isRefreshing())
					mList.onRefreshComplete();
			}}, mErrorListener);
	}

}
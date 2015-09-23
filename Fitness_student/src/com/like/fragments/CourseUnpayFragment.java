package com.like.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response.Listener;
import com.like.customview.pulltorefresh.PullToRefreshBase;
import com.like.customview.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.like.customview.pulltorefresh.PullToRefreshListView;
import com.like.fitness.student.R;

public class CourseUnpayFragment extends BaseFragment {
	
	private PullToRefreshListView mList;
	private int mStatus = 0;
	
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
			}}, mErrorListener);
	}

}

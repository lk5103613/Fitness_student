package com.like.fragments;

import com.like.adapter.CollectListAdapter;
import com.like.customview.pulltorefresh.PullToRefreshListView;
import com.like.fitness.student.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CollectHotFragment extends BaseFragment {

	private PullToRefreshListView mList;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_collect_hot, container, false);
		initView(view);
		return view;
	}
	
	private void initView(View view) {
		mList = (PullToRefreshListView) view.findViewById(R.id.collect_list);
		mList.setAdapter(new CollectListAdapter(mContext));
	}

}

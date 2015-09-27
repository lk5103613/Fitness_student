package com.like.fragments;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.google.gson.reflect.TypeToken;
import com.like.adapter.CollectListAdapter;
import com.like.customview.pulltorefresh.PullToRefreshBase;
import com.like.customview.pulltorefresh.PullToRefreshListView;
import com.like.customview.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.like.entity.CollectItem;
import com.like.entity.ListResult;
import com.like.fitness.student.R;
import com.like.network.GsonUtil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class CollectStartingFragment extends BaseFragment {
	private PullToRefreshListView mList;
	private List<CollectItem> mCollectItems = new ArrayList<>();
	private CollectListAdapter mAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		View view = inflater.inflate(R.layout.fragment_collect_starting, container, false);
		initView(view);
		getData();
		return view;
	}
	
	private void initView(View view) {
		mList = (PullToRefreshListView) view.findViewById(R.id.collect_list);
		mAdapter = new CollectListAdapter(mContext,mCollectItems);
		mList.setAdapter(mAdapter);
		//刷新列表
		mList.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				System.out.println("refresh");
				getData();
			}
		});
	}
	
	private void getData(){
		showLoading(true);
		mDataFetcher.fetchCollection(mLoginUser.uid, "2",  new Listener<String>() {
			@Override
			public void onResponse(String response) {
				Type type = new TypeToken<ListResult<CollectItem>>(){}.getType();
				ListResult<CollectItem> result = GsonUtil.gson.fromJson(response, type);
				if (!result.list.isEmpty()) {
					mCollectItems.clear();
					mCollectItems.addAll(result.list);
					mAdapter.notifyDataSetChanged();
				}
				mList.onRefreshComplete();
				showLoading(false);
			}
		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(mContext, "请检查网络", Toast.LENGTH_SHORT).show();
				mList.onRefreshComplete();
				showLoading(false);
				return;
			}
		});
	}

}

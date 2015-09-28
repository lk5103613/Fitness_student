package com.like.fitness.student;

import java.lang.reflect.Type;
import java.util.List;

import android.os.Bundle;

import com.android.volley.Response.Listener;
import com.google.gson.reflect.TypeToken;
import com.like.adapter.WithdrawAdapter;
import com.like.customview.pulltorefresh.PullToRefreshListView;
import com.like.entity.WithdrawHistory;
import com.like.network.GsonUtil;

public class WithdrawHistoryActivity extends BaseActivity {
	
	private WithdrawAdapter mAdapter;
	private PullToRefreshListView mList;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_withdraw_history);
		
		mList = (PullToRefreshListView) findViewById(R.id.history_list);
		fetchHis();
	}
	
	private void fetchHis() {
		mDataFetcher.fetchWidthdrawHis(mLoginUser.uid, new Listener<String>() {
			@Override
			public void onResponse(String response) {
				Type type = new TypeToken<List<WithdrawHistory>>(){}.getType();
				List<WithdrawHistory> histories = GsonUtil.gson.fromJson(response, type);
				if(mAdapter == null) {
					mAdapter = new WithdrawAdapter(mContext, histories);
					mList.setAdapter(mAdapter);
				} else{
					
				}
			}
		}, mErrorListener);
	}

}

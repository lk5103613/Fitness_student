package com.like.fitness.student;

import com.like.adapter.WithdrawAdapter;
import com.like.customview.pulltorefresh.PullToRefreshListView;

import android.os.Bundle;

public class WithdrawHistoryActivity extends BaseActivity {
	
	private WithdrawAdapter mAdapter;
	private PullToRefreshListView mList;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_withdraw_history);
		
		mList = (PullToRefreshListView) findViewById(R.id.history_list);
		mAdapter = new WithdrawAdapter(mContext);
		mList.setAdapter(mAdapter);
	}

}

package com.like.fitness.student;

import android.os.Bundle;

import com.like.adapter.MyMsgAdapter;
import com.like.customview.pulltorefresh.PullToRefreshListView;

public class MyMsgActivity extends BaseActivity {
	
	private PullToRefreshListView mList;
	private MyMsgAdapter mAdapter;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_sys_msg);
		
		mList = (PullToRefreshListView) findViewById(R.id.msg_list);
		mAdapter = new MyMsgAdapter(mContext);
		mList.setAdapter(mAdapter);
	}
	
}

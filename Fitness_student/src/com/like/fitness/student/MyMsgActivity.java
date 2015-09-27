package com.like.fitness.student;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.google.gson.reflect.TypeToken;
import com.like.adapter.MyMsgAdapter;
import com.like.customview.pulltorefresh.PullToRefreshListView;
import com.like.entity.CollectItem;
import com.like.entity.ListResult;
import com.like.entity.MessageItem;
import com.like.network.GsonUtil;

public class MyMsgActivity extends BaseActivity {
	
	private PullToRefreshListView mList;
	private MyMsgAdapter mAdapter;
	private List<MessageItem> items = new ArrayList<>();
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_sys_msg);
		
		mList = (PullToRefreshListView) findViewById(R.id.msg_list);
		mAdapter = new MyMsgAdapter(mContext,items);
		mList.setAdapter(mAdapter);
		getMsgs();
	}
	
	private void getMsgs(){
		mDataFetcher.fetchMsgs(mLoginUser.uid, new Listener<String>() {
			@Override
			public void onResponse(String response) {
				Type type = new TypeToken<List<MessageItem>>(){}.getType();
				List<MessageItem> result = GsonUtil.gson.fromJson(response, type);
				items.clear();
				items.addAll(result);
				mList.onRefreshComplete();
			}
		},  new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(mContext, "请检查网络", Toast.LENGTH_SHORT).show();
				mList.onRefreshComplete();
				return;
			}
		});
	}
	
}

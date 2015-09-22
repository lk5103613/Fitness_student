package com.like.fitness.student;

import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.like.network.DataFetcher;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class BaseActivity extends FragmentActivity {
	
	protected Context mContext;
	protected DataFetcher mDataFetcher;
	protected ErrorListener mErrorListener;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		this.mContext = this;
		mDataFetcher = DataFetcher.getInstance(mContext);
		mErrorListener = new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError arg0) {
				Toast.makeText(mContext, "请检查网络", Toast.LENGTH_SHORT).show();
				return;
			}
		};
	}

}

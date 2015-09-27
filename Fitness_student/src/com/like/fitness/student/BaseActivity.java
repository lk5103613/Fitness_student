package com.like.fitness.student;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.like.entity.LoginResult;
import com.like.network.DataFetcher;
import com.like.network.GsonUtil;

public class BaseActivity extends FragmentActivity {
	
	public final static String USER_PROPERTIES = "login_properties";
	public final static String LOGIN_USER = "login_user";
	
	protected Context mContext;
	protected DataFetcher mDataFetcher;
	protected ErrorListener mErrorListener;
	
	protected LoginResult mLoginUser;
	protected SharedPreferences mLoginSharef;
	protected ProgressDialog mLoadingDialog;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		this.mContext = this;
		mLoginSharef = getSharedPreferences(USER_PROPERTIES, Context.MODE_PRIVATE);
		String loginJson = mLoginSharef.getString(LOGIN_USER, "");
		if(TextUtils.isEmpty(loginJson)) {
			mLoginUser = null;
		} else {
			mLoginUser = GsonUtil.gson.fromJson(loginJson, LoginResult.class);
		}
		mDataFetcher = DataFetcher.getInstance(mContext);
		mErrorListener = new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(mContext, "请检查网络", Toast.LENGTH_SHORT).show();
				return;
			}
		};
	}
	
	public void back(View v) {
		finish();
	}
	
	protected void showLoading(boolean show) {
		if(show) {
			if(mLoadingDialog == null) {
				mLoadingDialog = new ProgressDialog(this);
				mLoadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				mLoadingDialog.setMessage("请等待");
			}
			if (!mLoadingDialog.isShowing()) {
				mLoadingDialog.show();
			}
			
		} else {
			if(mLoadingDialog == null)
				return;
			if(mLoadingDialog.isShowing()) 
				mLoadingDialog.dismiss();
		}
	}

}

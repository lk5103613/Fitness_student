package com.like.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response.ErrorListener;
import com.like.entity.LoginResult;
import com.like.network.DataFetcher;
import com.like.network.GsonUtil;

public class BaseFragment extends Fragment {
	
	public final static String USER_PROPERTIES = "login_properties";
	public final static String LOGIN_USER = "login_user";
	
	protected Context mContext;
	protected DataFetcher mDataFetcher;
	protected ErrorListener mErrorListener;
	
	protected LoginResult mLoginUser;
	protected SharedPreferences mLoginSharef;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = getActivity();
		mDataFetcher = DataFetcher.getInstance(mContext);
		mLoginSharef = mContext.getSharedPreferences(USER_PROPERTIES, Context.MODE_PRIVATE);
		String loginJson = mLoginSharef.getString(LOGIN_USER, "");
		if(TextUtils.isEmpty(loginJson)) {
			mLoginUser = null;
		} else {
			mLoginUser = GsonUtil.gson.fromJson(loginJson, LoginResult.class);
		}
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	

}

package com.like.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response.Listener;
import com.like.customview.RoundImageView;
import com.like.entity.LoginResult;
import com.like.fitness.student.R;
import com.like.network.APIS;
import com.like.network.GsonUtil;

public class MyInfoFragment extends BaseFragment {
	
	private RoundImageView mHeadIcon;
	private TextView mLblNickname;
	private TextView mLblAllTranCnt;
	private TextView mLblAllTranMoney;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_my, container, false);
		initView(view);
		initUserInfo();
		return view;
	}
	
	private void initView(View view) {
		mHeadIcon = (RoundImageView) view.findViewById(R.id.header_icon);
		mLblNickname = (TextView) view.findViewById(R.id.nick_name);
		mLblAllTranCnt = (TextView) view.findViewById(R.id.all_tran_cnt);
		mLblAllTranMoney = (TextView) view.findViewById(R.id.all_tran_money);
	}

	private void initUserInfo() {
		mDataFetcher.fetchMyInfo(mLoginUser.uid, new Listener<String>() {
			@Override
			public void onResponse(String response) {
				System.out.println(response);
				LoginResult result = GsonUtil.gson.fromJson(response, LoginResult.class);
				if(result.allTranMoney == null || TextUtils.equals(result.allTranMoney, "null")) 
					result.allTranMoney = "0";
				if(result.allTranCnt == null || TextUtils.equals(result.allTranCnt, "null")) 
					result.allTranCnt = "0";
				if(!TextUtils.isEmpty(result.nickname))
					mLblNickname.setText("昵称：" + result.nickname);
				if(!TextUtils.isEmpty(result.truename))
					mLblNickname.setText("昵称：" + result.truename);
				mLblAllTranCnt.setText(result.allTranCnt + "小时");
				mLblAllTranMoney.setText(result.allTranMoney + "元");
				mDataFetcher.loadImg(APIS.BASE_URL + result.avatar, mHeadIcon, R.drawable.icon_01, R.drawable.icon_01);
			}
		}, mErrorListener);
	}
}

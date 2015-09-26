package com.like.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response.Listener;
import com.like.customview.RoundImageView;
import com.like.entity.LoginResult;
import com.like.fitness.student.MyCollectActivity;
import com.like.fitness.student.MyCouponActivity;
import com.like.fitness.student.MyCourseActivity;
import com.like.fitness.student.MySettingActivity;
import com.like.fitness.student.MyWalletActivity;
import com.like.fitness.student.R;
import com.like.network.APIS;
import com.like.network.GsonUtil;

public class MyInfoFragment extends BaseFragment implements OnClickListener {
	
	private RoundImageView mHeadIcon;
	private TextView mLblNickname;
	private TextView mLblAllTranCnt;
	private TextView mLblAllTranMoney;
	private ViewGroup mBtnMyCourse;
	private ViewGroup mBtnCoupon;
	private ViewGroup mBtnCollect;
	private ViewGroup mBtnMyWallet;
	private ViewGroup mBtnMySetting;
	
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
		mBtnMyCourse = (ViewGroup) view.findViewById(R.id.my_course);
		mBtnCoupon = (ViewGroup) view.findViewById(R.id.btn_coupon);
		mBtnMyWallet = (ViewGroup) view.findViewById(R.id.my_wallet);
		mBtnCollect = (ViewGroup) view.findViewById(R.id.btn_collect);
		mBtnMySetting = (ViewGroup) view.findViewById(R.id.my_setting);
		mBtnMyCourse.setOnClickListener(this);
		mBtnCoupon.setOnClickListener(this);
		mBtnCollect.setOnClickListener(this);
		mBtnMyWallet.setOnClickListener(this);
		mBtnMySetting.setOnClickListener(this);
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

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.my_course:
			intent = new Intent(mContext, MyCourseActivity.class);
			break;
		case R.id.btn_coupon:
			intent = new Intent(mContext, MyCouponActivity.class);
			break;
		case R.id.btn_collect:
			intent = new Intent(mContext, MyCollectActivity.class);
			break;
		case R.id.my_wallet:
			intent = new Intent(mContext, MyWalletActivity.class);
			break;
		case R.id.my_setting:
			intent = new Intent(mContext, MySettingActivity.class);
			break;
		default:
			return;
		}
		startActivity(intent);
	}
}

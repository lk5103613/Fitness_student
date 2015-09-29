package com.like.fragments;

import java.io.File;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response.Listener;
import com.like.customview.RoundImageView;
import com.like.entity.LoginResult;
import com.like.fitness.student.MyCollectActivity;
import com.like.fitness.student.MyCouponActivity;
import com.like.fitness.student.MyCourseActivity;
import com.like.fitness.student.MySettingActivity;
import com.like.fitness.student.MyShareActivity;
import com.like.fitness.student.MyWalletActivity;
import com.like.fitness.student.R;
import com.like.fitness.student.MyMsgActivity;
import com.like.network.APIS;
import com.like.network.GsonUtil;
import com.like.utils.BitmapUtil;
import com.like.utils.UploadUtil;

public class MyInfoFragment extends BaseFragment implements OnClickListener {
	private final static int REQUEST_TAKE_PHOTO = 0;
	private final static int REQUEST_FROM_FILE = 1;
	private RoundImageView mHeadIcon;
	private TextView mLblNickname;
	private TextView mLblAllTranCnt;
	private TextView mLblAllTranMoney;
	private ViewGroup mBtnMyCourse;
	private ViewGroup mBtnCoupon;
	private ViewGroup mBtnCollect;
	private ViewGroup mBtnMyWallet;
	private ViewGroup mBtnMySetting;
	private ViewGroup mBtnMsg;
	private ViewGroup mBtnShare;
	private Dialog mDialog;
	private String mAvatar;
	
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
		mBtnMsg = (ViewGroup) view.findViewById(R.id.my_msg);
		mBtnShare = (ViewGroup) view.findViewById(R.id.my_share);
		mBtnMyCourse.setOnClickListener(this);
		mBtnCoupon.setOnClickListener(this);
		mBtnCollect.setOnClickListener(this);
		mBtnMyWallet.setOnClickListener(this);
		mBtnMySetting.setOnClickListener(this);
		mBtnMsg.setOnClickListener(this);
		mBtnShare.setOnClickListener(this);
		mHeadIcon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog();
			}
		});
	}
	
	private void showDialog() {
		if(mDialog == null) {
			mDialog = new Dialog(mContext, R.style.Theme_dialog);
			View view = LayoutInflater.from(mContext).inflate(R.layout.choice_photo_dialog, null);
			Button btnCamera = (Button)view.findViewById(R.id.take_from_camert);
			btnCamera.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mDialog.dismiss();
					Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
					startActivityForResult(intent, REQUEST_TAKE_PHOTO);
				}
			});
			Button btnGalley = (Button) view.findViewById(R.id.take_from_file);
			btnGalley.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mDialog.dismiss();
					Intent intent = new Intent(Intent.ACTION_PICK);
					intent.setType("image/*");
					startActivityForResult(intent, REQUEST_FROM_FILE);
				}
			});
			mDialog.setContentView(view);
		}
		mDialog.show();
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
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == getActivity().RESULT_OK) {
			Uri uri = BitmapUtil.getUriByData(data, getActivity().getContentResolver());
			File file = BitmapUtil.getFileByUri(getActivity().getContentResolver(), uri);
			Bitmap bmp = BitmapUtil.getResizeBitmap(file);
			mHeadIcon.setImageBitmap(bmp);
			new AsyncTask<File, Void, String>() {
				@Override
				protected String doInBackground(File... params) {
					File uploadFile = params[0];
					final String serverImgName = UploadUtil.getImgName();
					try {
						UploadUtil.post(uploadFile, APIS.UPLOAD,
								serverImgName);
						mAvatar = "/upload/" + serverImgName + UploadUtil.getExtensionName(uploadFile.getAbsolutePath());
					} catch(Exception e) {
						e.printStackTrace();
					}
					return null;
				}
			}.execute(file);
		}
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
		case R.id.my_msg:
			intent = new Intent(mContext, MyMsgActivity.class);
			break;
		case R.id.my_share:
			intent = new Intent(mContext, MyShareActivity.class);
			break;
		default:
			return;
		}
		startActivity(intent);
	}
}

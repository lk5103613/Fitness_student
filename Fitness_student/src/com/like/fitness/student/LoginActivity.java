package com.like.fitness.student;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response.Listener;
import com.like.utils.DeviceUtil;
import com.like.utils.ValidateUtil;

public class LoginActivity extends BaseActivity {
	
	private EditText mTxtPhone;
	private EditText mTxtPwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
	}
	
	private void initView() {
		mTxtPhone = (EditText) findViewById(R.id.txt_phone);
		mTxtPwd = (EditText) findViewById(R.id.txt_pwd);
	}
	
	public void login(View v) {
		String phone = mTxtPhone.getText().toString();
		String pwd = mTxtPwd.getText().toString();
		if(TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd)) {
			Toast.makeText(mContext, "请填写用户名和密码", Toast.LENGTH_SHORT).show();
			return;
		}
		if(!ValidateUtil.isMobileNO(phone)) {
			Toast.makeText(mContext, "手机号码格式错误", Toast.LENGTH_SHORT).show();
			return;
		}
		String imei = DeviceUtil.getIMEI(mContext);
		mDataFetcher.fetchLogin(phone, pwd, imei, new Listener<String>() {
			@Override
			public void onResponse(String response) {
				System.out.println(response);
//				CommonResult result = GsonUtil.gson.fromJson(response, CommonResult.class);
//				if(result.code == 1) {
//					Toast.makeText(mContext, "登陆成功", Toast.LENGTH_SHORT).show();
//				}
			}
		}, mErrorListener);
	}
	
	public void toReg(View v) {
		Intent intent = new Intent(mContext, RegActivity.class);
		startActivity(intent);
	}


}

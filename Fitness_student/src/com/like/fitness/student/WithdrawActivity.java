package com.like.fitness.student;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response.Listener;
import com.like.entity.CommonResult;
import com.like.network.GsonUtil;

public class WithdrawActivity extends BaseActivity {
	private TextView mAvailable;
	private EditText mMoney;
	private String mAvaildMoney;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_withdraw);
		mAvailable = (TextView) findViewById(R.id.available);
		mMoney = (EditText) findViewById(R.id.money);
		mAvaildMoney = getIntent().getExtras().getString("available");
		mAvailable.setText(mAvaildMoney);
	}

	/**
	 * 提现历史点击事件
	 * @param view
	 */
	public void toHistory(View view){
		Intent intent = new Intent(mContext, WithdrawHistoryActivity.class);
		startActivity(intent);
	}
	
	/**
	 * 确认提现点击事件
	 * 
	 * @param view
	 */
	public void confirm(View view){
		if (TextUtils.isEmpty(mMoney.getText().toString())) {
			Toast.makeText(this, "请输入提现金额", Toast.LENGTH_SHORT).show();
			return;
		}
		if (Float.parseFloat(mAvaildMoney) < Float.parseFloat(mMoney.getText().toString())) {
			Toast.makeText(this, "超出可提现金额", Toast.LENGTH_SHORT).show();
		} else {
			final float money = Float.parseFloat(mMoney.getText().toString());
			mDataFetcher.fetchWithdraw(mLoginUser.uid, money+"", new Listener<String>() {
			@Override
			public void onResponse(String response) {
				CommonResult result = GsonUtil.gson.fromJson(response, CommonResult.class);
				if(result.code == 1) {
					Toast.makeText(mContext, "提现成功", Toast.LENGTH_SHORT).show();
					float leftMoney = Float.parseFloat(mAvaildMoney) - money;
					mAvailable.setText(leftMoney + "");
					mMoney.setText("");
				} else {
					Toast.makeText(mContext, "提现失败", Toast.LENGTH_SHORT).show();
				}
				System.out.println("response " + response);
			}
		}, mErrorListener);
		}
	}
}

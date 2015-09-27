package com.like.fitness.student;

import com.android.volley.Response.Listener;
import com.like.entity.AvailableMoney;
import com.like.network.GsonUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
		if (Float.parseFloat(mAvaildMoney) < Float.parseFloat(mMoney.getText().toString())) {
			Toast.makeText(this, "超出可提现金额", Toast.LENGTH_SHORT).show();
		} else {
			mDataFetcher.fetchWithdraw(mLoginUser.uid, new Listener<String>() {
			@Override
			public void onResponse(String response) {
				System.out.println("response " + response);
			}
		}, mErrorListener);
		}
	}
}

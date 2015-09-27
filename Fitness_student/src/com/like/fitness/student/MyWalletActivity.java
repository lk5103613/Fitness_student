package com.like.fitness.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response.Listener;
import com.like.entity.AvailableMoney;
import com.like.network.GsonUtil;

public class MyWalletActivity extends BaseActivity {
	private TextView mMoney;
	private String mAvailable;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_my_wallet);
		mMoney = (TextView) findViewById(R.id.lbl_money);
		fetchData();
	}
	
	public void toRecharge(View v) {
		Intent intent = new Intent(mContext, RechargeActivity.class);
		startActivity(intent);
	}
	
	public void toWithdraw(View v) {
		Bundle bundle = new Bundle();
		//for test
		
		bundle.putString("available", "22.5");//mAvailable
		Intent intent = new Intent(mContext, WithdrawActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
	private void fetchData(){
		mDataFetcher.fetchAvailable(mLoginUser.uid, new Listener<String>() {
			@Override
			public void onResponse(String response) {
				System.out.println(response);
				AvailableMoney result = GsonUtil.gson.fromJson(response, AvailableMoney.class);
				mAvailable = result.money;
				mMoney.setText(result.money);
				
			}
		}, mErrorListener);
	}

}

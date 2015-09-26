package com.like.fitness.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyWalletActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_my_wallet);
	}
	
	public void toRecharge(View v) {
		Intent intent = new Intent(mContext, RechargeActivity.class);
		startActivity(intent);
	}
	
	public void toWithdraw(View v) {
		Intent intent = new Intent(mContext, WithdrawActivity.class);
		startActivity(intent);
	}

}

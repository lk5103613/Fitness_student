package com.like.fitness.student;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RechargeActivity extends BaseActivity {
	private EditText mMoney;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recharge);
		mMoney = (EditText) findViewById(R.id.money);
	}
	
	/**
	 * 下一步 点击事件
	 * @param view
	 */
	public void nextStep(View view) {
		
	}

	
}

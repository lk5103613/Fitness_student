package com.like.fitness.student;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class WithdrawActivity extends BaseActivity {
	private TextView mAvailable;
	private EditText mMoney;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_withdraw);
		mAvailable = (TextView) findViewById(R.id.available);
		mMoney = (EditText) findViewById(R.id.money);
	}

	/**
	 * 提现历史点击事件
	 * @param view
	 */
	public void history(View view){
		
	}
	
	/**
	 * 确认提现点击事件
	 * 
	 * @param view
	 */
	public void confirm(View view){
		
	}
}

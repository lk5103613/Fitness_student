package com.like.fitness.student;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConfirmOrderActivity extends Activity {
	private TextView mName;
	private TextView mMoney;
	private TextView mCourse;
	private TextView mAddress;
	private TextView mType;
	private TextView mTeacher;
	private TextView mCoupon;
	private TextView mMoneyTotal1;
	private TextView mMoneyTotal2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirmorder);
		mName = (TextView) findViewById(R.id.name);
		mMoney = (TextView) findViewById(R.id.money);
		mCourse = (TextView) findViewById(R.id.address);
		mType = (TextView) findViewById(R.id.type);
		mTeacher = (TextView) findViewById(R.id.teacher);
		mCoupon = (TextView) findViewById(R.id.coupon);
		mMoneyTotal1 = (TextView) findViewById(R.id.money_total1);
		mMoneyTotal2 = (TextView) findViewById(R.id.money_total2);
	}

	/**
	 * 订单确认按钮点击事件
	 * 
	 * @param v
	 */
	public void confirmOrder(View v) {
		
	}
	
	/**
	 * back按钮点击事件
	 * @param v
	 */
	public void back(View v) {
		finish();
	}
	
}

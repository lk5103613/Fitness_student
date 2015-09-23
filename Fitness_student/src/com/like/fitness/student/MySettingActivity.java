package com.like.fitness.student;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MySettingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mysetting);
	}

	public void setting(View v) {
		switch (v.getId()) {
		case R.id.email:

			break;
		case R.id.pwd:

			break;
		case R.id.weixin:

			break;
		case R.id.weibo:

			break;
		case R.id.phone:

			break;
		}
	}

}

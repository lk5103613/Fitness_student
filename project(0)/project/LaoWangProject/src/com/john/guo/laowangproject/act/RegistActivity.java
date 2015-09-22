package com.john.guo.laowangproject.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.john.guo.laowangproject.R;
import com.john.guo.laowangproject.titlebar.TitleArgBuilder;
import com.john.guo.laowangproject.titlebar.TitleBuilder;

public class RegistActivity extends BaseActivity {
	
	//====================================================
	//
	//	页面控件
	//
	//====================================================
	
	private Button loginBTN;
	
	//====================================================
	//
	//	页面参数
	//
	//====================================================
	
	//====================================================
	//
	//	生命周期
	//
	//====================================================
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
	}
	
	//====================================================
	//
	//	逻辑方法
	//
	//====================================================
	
	@Override
	protected void initView() {
		
		new TitleBuilder(context, TitleArgBuilder.getTitle("注册"));
		
		loginBTN = (Button) findViewById(R.id.btn1);
		loginBTN.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(context, NewbieRegistActivity.class));
			}
		});
	}
	
	//====================================================
	//
	//	接口实现，函数回调
	//
	//====================================================
	
}

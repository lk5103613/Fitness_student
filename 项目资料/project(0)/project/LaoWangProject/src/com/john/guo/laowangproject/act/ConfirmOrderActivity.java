package com.john.guo.laowangproject.act;

import com.john.guo.laowangproject.R;
import com.john.guo.laowangproject.titlebar.TitleArgBuilder;
import com.john.guo.laowangproject.titlebar.TitleBuilder;

import android.os.Bundle;

public class ConfirmOrderActivity extends BaseActivity {
	
	//====================================================
	//
	//	页面控件
	//
	//====================================================
	
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
		setContentView(R.layout.activity_confirmorder);
	}
	
	//====================================================
	//
	//	逻辑方法
	//
	//====================================================
	
	@Override
	protected void initView() {
		new TitleBuilder(context, TitleArgBuilder.getBackBtnTitle(context, "确认订单"));
	}
	
	//====================================================
	//
	//	接口实现，函数回调
	//
	//====================================================
	
}

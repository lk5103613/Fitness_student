package com.john.guo.laowangproject.act;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.honestwalker.androidutils.window.ToastHelper;
import com.john.guo.laowangproject.R;
import com.john.guo.laowangproject.titlebar.TitleArgBuilder;
import com.john.guo.laowangproject.titlebar.TitleBuilder;

public class WithdrawActivity extends BaseActivity {
	
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
		setContentView(R.layout.activity_withdraw);
	}
	
	//====================================================
	//
	//	逻辑方法
	//
	//====================================================
	
	@Override
	protected void initView() {
		new TitleBuilder(context, TitleArgBuilder.getBackAndRightTVTitle(context, "提现", "提现历史", titleRightBtnClick));
	}
	
	//====================================================
	//
	//	接口实现，函数回调
	//
	//====================================================
	
	private OnClickListener titleRightBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			ToastHelper.alert(context, "提现历史");
		}
	};
	
}

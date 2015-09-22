package com.john.guo.laowangproject.act;

import com.honestwalker.androidutils.window.ToastHelper;
import com.john.guo.laowangproject.R;
import com.john.guo.laowangproject.titlebar.TitleArgBuilder;
import com.john.guo.laowangproject.titlebar.TitleBuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class UserCenterActivity extends BaseActivity implements OnClickListener {
	
	//====================================================
	//
	//	页面控件
	//
	//====================================================
	
	private RelativeLayout kechengRL;
	private RelativeLayout xiaoxiRL;
	private RelativeLayout shoucangRL;
	private RelativeLayout qianbaoRL;
	private RelativeLayout youhuiquanRL;
	private RelativeLayout shezhiRL;
	private RelativeLayout fenxiangRL;
	private RelativeLayout jiaolianRL;
	
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
		setContentView(R.layout.activity_usercenter);
	}
	
	//====================================================
	//
	//	逻辑方法
	//
	//====================================================
	
	@Override
	protected void initView() {
		new TitleBuilder(context, TitleArgBuilder.getTitle("我的"));
		kechengRL = (RelativeLayout) findViewById(R.id.layout1);
		xiaoxiRL = (RelativeLayout) findViewById(R.id.layout2);
		shoucangRL = (RelativeLayout) findViewById(R.id.layout3);
		qianbaoRL = (RelativeLayout) findViewById(R.id.layout4);
		youhuiquanRL = (RelativeLayout) findViewById(R.id.layout5);
		shezhiRL = (RelativeLayout) findViewById(R.id.layout6);
		fenxiangRL = (RelativeLayout) findViewById(R.id.layout7);
		jiaolianRL = (RelativeLayout) findViewById(R.id.layout8);
		
		kechengRL.setOnClickListener(this);
		xiaoxiRL.setOnClickListener(this);
		shoucangRL.setOnClickListener(this);
		qianbaoRL.setOnClickListener(this);
		youhuiquanRL.setOnClickListener(this);
		shezhiRL.setOnClickListener(this);
		fenxiangRL.setOnClickListener(this);
		jiaolianRL.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.layout1:
//			startActivity(new Intent(context, ));
			ToastHelper.alert(context, "我的课程");
			break;
		case R.id.layout2:
//			startActivity(new Intent(context, ));
			ToastHelper.alert(context, "我的消息");
			break;
		case R.id.layout3:
//			startActivity(new Intent(context, ));
			ToastHelper.alert(context, "我的收藏");
			break;
		case R.id.layout4:
//			startActivity(new Intent(context, ));
			ToastHelper.alert(context, "我的钱包");
			break;
		case R.id.layout5:
			startActivity(new Intent(context, CouponActivity.class));
			break;
		case R.id.layout6:
			startActivity(new Intent(context, MySettingActivity.class));
			break;
		case R.id.layout7:
//			startActivity(new Intent(context, ));
			ToastHelper.alert(context, "我的分享");
			break;
		case R.id.layout8:
//			startActivity(new Intent(context, ));
			ToastHelper.alert(context, "我要成为教练");
			break;

		default:
			break;
		}
	}
	
	//====================================================
	//
	//	接口实现，函数回调
	//
	//====================================================
	
}

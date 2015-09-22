package com.john.guo.laowangproject.act;

import java.util.ArrayList;
import java.util.List;

import com.john.guo.laowangproject.R;
import com.john.guo.laowangproject.adapter.MyCouponAdapter;
import com.john.guo.laowangproject.bean.MyCouponBean;
import com.john.guo.laowangproject.titlebar.TitleArgBuilder;
import com.john.guo.laowangproject.titlebar.TitleBuilder;

import android.os.Bundle;
import android.widget.ListView;

public class CouponActivity extends BaseActivity {
	
	//====================================================
	//
	//	页面控件
	//
	//====================================================
	
	private ListView myCouponLV;
	
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
		setContentView(R.layout.activity_coupon);
	}
	
	//====================================================
	//
	//	逻辑方法
	//
	//====================================================
	
	@Override
	protected void initView() {
		new TitleBuilder(context, TitleArgBuilder.getBackBtnTitle(context, "我的优惠券"));
		myCouponLV = (ListView) findViewById(R.id.listview1);
		myCouponLV.setAdapter(new MyCouponAdapter(context, initData()));
	}
	
	private List<MyCouponBean> initData(){
		List<MyCouponBean> data = new ArrayList<MyCouponBean>();
		data.add(new MyCouponBean("健身50元优惠券", "2015.08.08", "2015.09.08", "362348832472902387"));
		data.add(new MyCouponBean("健身100元优惠券", "2015.08.10", "2015.09.10", "362348832472902387"));
		data.add(new MyCouponBean("健身1500元优惠券", "2015.07.08", "2015.07.08", "362348832472902387"));
		return data;
	}
	
	//====================================================
	//
	//	接口实现，函数回调
	//
	//====================================================
	
}

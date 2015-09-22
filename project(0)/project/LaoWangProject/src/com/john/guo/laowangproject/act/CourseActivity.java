package com.john.guo.laowangproject.act;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.honestwalker.androidutils.window.ToastHelper;
import com.john.guo.laowangproject.R;
import com.john.guo.laowangproject.fragment.NoPayfragment;
import com.john.guo.laowangproject.titlebar.TitleArgBuilder;
import com.john.guo.laowangproject.titlebar.TitleBuilder;
import com.john.guo.laowangproject.views.TitleNavigate;
import com.john.guo.laowangproject.views.TitleNavigate.OnShowItemListener;

public class CourseActivity extends BaseActivity {
	
	//====================================================
	//
	//	页面控件
	//
	//====================================================
	
	private TitleNavigate indicator;
	private FrameLayout contentFL;
	private FragmentTransaction transaction;
	
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
		setContentView(R.layout.activity_course);
	}
	
	//====================================================
	//
	//	逻辑方法
	//
	//====================================================
	
	@Override
	protected void initView() {
		new TitleBuilder(context, TitleArgBuilder.getBackBtnTitle(context, "我的课程"));
		indicator = (TitleNavigate) findViewById(R.id.indicator);
		contentFL = (FrameLayout) findViewById(R.id.content);
		
		indicator.setTabString("未付款", "未消费", "已取消");
		indicator.setOnShowItemListener(mOnShowItemListener);
		
		transaction = getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.content, new NoPayfragment()).commit();
		
	}
	
	//====================================================
	//
	//	接口实现，函数回调
	//
	//====================================================
	
	private OnShowItemListener mOnShowItemListener = new OnShowItemListener() {
		
		@Override
		public void ShowItem(int position) {
			ToastHelper.alert(context, "选中位置："+position);
		}
	};
	
}

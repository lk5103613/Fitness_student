package com.john.guo.laowangproject.act;

import com.honestwalker.androidutils.window.ToastHelper;
import com.john.guo.laowangproject.R;
import com.john.guo.laowangproject.titlebar.TitleArgBuilder;
import com.john.guo.laowangproject.titlebar.TitleBuilder;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

public class CourseDetailsActivity extends BaseActivity {
	
	//====================================================
	//
	//	页面控件
	//
	//====================================================
	
	private Button byNowBTN;
	
	//====================================================
	//
	//	页面参数
	//
	//====================================================
	
	private PopupWindow popupWindow;
	
	//====================================================
	//
	//	生命周期
	//
	//====================================================
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coursedetails);
	}
	
	//====================================================
	//
	//	逻辑方法
	//
	//====================================================
	
	@Override
	protected void initView() {
		new TitleBuilder(context, TitleArgBuilder.getBackAndRightTVTitle(context, "课程详情", "分享", titleRightBtnClick));
		byNowBTN = (Button) findViewById(R.id.btn1);
		initPopupWindow();
		
		byNowBTN.setOnClickListener(byNowBTNClick);
	}
	
	/**
	 * 初始化pop
	 */
	private void initPopupWindow(){
		View popView = inflater.inflate(R.layout.view_popupwindow, null);
		popupWindow = new PopupWindow(popView,LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
	}
	
	//====================================================
	//
	//	接口实现，函数回调
	//
	//====================================================
	
	private OnClickListener byNowBTNClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
		}
	};
	
	private OnClickListener titleRightBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			ToastHelper.alert(context, "分享");
		}
	};
	
}

package com.honestwalker.androidutils.activity.fragment.menubar;

import java.lang.reflect.Method;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

import com.honestwalker.androidutils.EventAction.ActionClick;
import com.honestwalker.androidutils.ViewUtils.ViewSizeHelper;
import com.honestwalker.androidutils.equipment.DisplayUtil;
import com.honestwalker.androidutils.exception.ExceptionUtil;

public class BaseMenuFragment extends Fragment {
	
	protected Activity context;
	protected int screenWidth;
	protected int screenHeight;
	protected ViewSizeHelper viewSizeHelper;
	protected DisplayUtil displayUtil;
	
	public void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this.getActivity();
		viewSizeHelper = ViewSizeHelper.getInstance(context);
		displayUtil = DisplayUtil.getInstance(context);
		screenWidth = displayUtil.getWidth();
		screenHeight = displayUtil.getHeight();
	};
	
	protected void setTitle() {}
	
	/** 登录取消回调 */
	public void loginCancleCallback(){};
	
	/** 登录成功回调 */
	public void loginSuccessCallback(Object userInfoBean){
	};
	
	/** 设置按钮事件 */
	protected void setClick(final View view ,final String clickMethod) {
		if(view != null) {
			view.setClickable(true);
			try {
				// 获得方法
			    final Method method = this.getClass().getMethod(clickMethod);
			    if(method != null) {
					view.setOnClickListener(new ActionClick(this,method,null));
			    }
			} catch (Exception e) {
				ExceptionUtil.showException(e);
			}
		}
	}
}

package com.like.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class DisplayUtil {
	

	private static Display display;
	private static Activity act;
	private static DisplayUtil displayUtil;

	private DisplayUtil(){}

	/**
	 * 获取DisplayUtil实例
	 * @param activity
	 * @return
	 */
	public static DisplayUtil getInstance(Context activity){
		act = (Activity)activity;
		display = act.getWindowManager().getDefaultDisplay();
		if(displayUtil == null){
			displayUtil = new DisplayUtil();
		}
		return displayUtil;
	}
	
	public void setListViewHeightBasedOnChildren(Context context, ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight()+ DisplayUtil.dip2px(context, 20);
			System.out.println("item " + listItem.getMeasuredHeight());
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}
	
	public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
  
    /** 
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
     */  
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    } 
    
    /**
	 * 获取屏幕宽度
	 * @return
	 */
	public  int getWidth(){
		return display.getWidth();
	}

	/**
	 * 获取屏幕高度
	 * @return
	 */
	public int getHeight(){
		return display.getHeight();
	}
}

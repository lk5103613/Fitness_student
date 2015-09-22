package com.like.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DeviceUtil {

	public static int getScreenWidht(Context context) {
		WindowManager wmManager=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		wmManager.getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}
	
	public static int getScreenHeight(Context context) {
		WindowManager wmManager=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		wmManager.getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}
	
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	
	public static String getIMEI(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);    
		return tm.getDeviceId();
	}
	
}

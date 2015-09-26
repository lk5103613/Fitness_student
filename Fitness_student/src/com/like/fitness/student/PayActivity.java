package com.like.fitness.student;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

import com.like.utils.DeviceUtil;

public class PayActivity extends BaseActivity {
	
	private Dialog mDialog;
	
	private Handler mHandler;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_pay);
		
		mHandler = new Handler();
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				showDialog();
			}
		}, 3000);
	}
	
	
	private void showDialog() {
		if(mDialog == null) {
			mDialog = new Dialog(mContext, R.style.Theme_dialog);
			View v = LayoutInflater.from(mContext).inflate(R.layout.dialog_pay_failed, null, false);
			v.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if(mDialog.isShowing())
						mDialog.dismiss();
				}
			});
			mDialog.setContentView(v);
			int screenWidth = DeviceUtil.getScreenWidht(mContext);
			int screenHeight = DeviceUtil.getScreenHeight(mContext);
			Window dialogWindow = mDialog.getWindow();
	        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//	        dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
	        lp.width = screenWidth;
	        lp.height = screenHeight;
	        dialogWindow.setAttributes(lp);
		}
        mDialog.show();
	}

}

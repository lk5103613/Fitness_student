package com.honestwalker.androidutils.ViewUtils;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class LinearLayoutParamsGetter {
	public static LinearLayout.LayoutParams getLayoutParams(Context context,View view,int width,int height) {
		LinearLayout.LayoutParams lp = null;
		try {
			lp = (LayoutParams) view.getLayoutParams();
		} catch (Exception e) {
			if(lp == null) {
				lp = new LinearLayout.LayoutParams(width,height);
			} else {
				lp.width  = width;
				lp.height = height;
			}
		}
		return lp;
	}
}

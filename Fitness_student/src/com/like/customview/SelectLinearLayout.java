package com.like.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class SelectLinearLayout extends LinearLayout{
	
	public SelectLinearLayout(Context context) {
		super(context);
	}
	public SelectLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@SuppressLint("NewApi")
	public SelectLinearLayout(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	
	@Override
	public void setSelected(boolean selected) {
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			getChildAt(i).setSelected(selected);
		}
		super.setSelected(selected);
	}

}

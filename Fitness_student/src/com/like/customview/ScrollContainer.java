package com.like.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class ScrollContainer extends RelativeLayout {
	private int orgY;
	
	public ScrollContainer(Context context) {
		super(context);
	}

	public ScrollContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ScrollContainer(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean result = super.onTouchEvent(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			orgY = (int)event.getY();

			break;
		case MotionEvent.ACTION_MOVE:
			int dif = (int)event.getY() - orgY;
			System.out.println("dif " + dif);
			break;
		default:
			break;
		}
		return result;
	}

}

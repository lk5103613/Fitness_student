package com.like.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class ControlledScrollView extends ScrollView {

    private boolean mCanScroll = false;

    public ControlledScrollView(Context context) {
        super(context);
    }


    public ControlledScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ControlledScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void setCanScroll(boolean canScroll) {
        this.mCanScroll = canScroll;
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return mCanScroll;
//    }
}

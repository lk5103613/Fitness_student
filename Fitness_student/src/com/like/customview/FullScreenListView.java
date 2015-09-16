package com.like.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.like.utils.PhoneUtil;

public class FullScreenListView extends ListView {

    public FullScreenListView(Context context) {
        super(context);
    }

    public FullScreenListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullScreenListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int screenWidth =  PhoneUtil.getScreenWidthPX(getContext());
        int toolbarHeight = PhoneUtil.dip2px(getContext(), 45f);
        int titleBarHeight = PhoneUtil.getStatuBarHeight(getContext());
        int screenHeight =  PhoneUtil.getScreenHeightPX(getContext()) - toolbarHeight - titleBarHeight;
        setMeasuredDimension(screenWidth, screenHeight);
    }

}

package com.honestwalker.androidutils.views;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.honestwalker.androidutils.IO.LogCat;
import com.honestwalker.androidutils.ViewUtils.ViewSizeHelper;
import com.honestwalker.androidutils.equipment.DisplayUtil;

public class MarginViewFlow extends HorizontalScrollView {

	/*********************************************
	 * 
	 * 					属性
	 * 
	 **********************************************/
	
	private Context context;
	
	/**
	 * scrollView中的父线性布局
	 */
	private LinearLayout parentLinearLayout; 
	
	/**
	 * 去除borderView的内容view
	 */
	private ArrayList<View> contentViewsWithoutBorder = new ArrayList<View>();
	/**
	 * 记录内容项索引和可见性的映射
	 */
	private SparseBooleanArray indexVisibleMap = new SparseBooleanArray();
	
	/**
	 * 记录每个显示位置的索引和坐标映射
	 */
	private SparseIntArray indexCoordinatesMap = new SparseIntArray();
	
	/**
	 * 滑动emulator
	 */
	private Scroller mScroller;
	
	/**
	 * 每个内容项的宽度
	 */
	private int contentWidth;
	private int oneThirdContentWidth;
	/**
	 * borderView的宽度
	 */
	private int borderWidth;
	private int halfBorderWidth;
	/**
	 * 项移动的距离
	 */
	private int travelWidth;
	
	/**
	 * 为处理fling事件
	 */
	private GestureDetector gestureDetector;
	/**
	 * true是fling事件，就跳过Action_up事件
	 */
	private boolean onFling;
	
	private MarginViewFlowIndicator mIndicator;
	
	private String TAG = "scroll";
	
	/**
	 * 项移动距离占总宽度的比例
	 */
	private float travelRate;
	
	/*********************************************
	 * 
	 * 					方法
	 * 
	 **********************************************/
	
	public MarginViewFlow(Context context) {
		this(context,null);
	}

	public MarginViewFlow(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		init();
	}

	public MarginViewFlow(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		contentWidth = (int)(DisplayUtil.getInstance(context).getWidth() * 0.8);
		oneThirdContentWidth = contentWidth / 3;
		borderWidth = (int)(DisplayUtil.getInstance(context).getWidth() * 0.1);
		halfBorderWidth = borderWidth / 2;
		travelRate = 0.85f;
		travelWidth = (int)(DisplayUtil.getInstance(context).getWidth() * travelRate);
		
		initParentLinearLayout();
		
		mScroller = new Scroller(context);
		gestureDetector = new GestureDetector(context, onFlingGestureListener);
	}

	private void initParentLinearLayout(){	
		parentLinearLayout = new LinearLayout(context);
		parentLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
		super.addView(parentLinearLayout);
		setHorizontalScrollBarEnabled(false);
	}
	
	/**
	 * 初始化所有子View
	 * @param views
	 */
	public void initViews(ArrayList<View> views,int contentHeight) {
		
		for (int i = 0; i < views.size(); i++) {
			
			//左边的border
			View borderLeftView = new View(context);
			parentLinearLayout.addView(borderLeftView);
			if (i == 0) {
				ViewSizeHelper.getInstance(context).setSize(borderLeftView, 
						borderWidth, getHeight());
			}else {
				ViewSizeHelper.getInstance(context).setSize(borderLeftView, 
						halfBorderWidth, getHeight());
			}
			
			//中间内容
			View contentView = views.get(i);
			contentView.setTag("content");//添加标签识别
			android.widget.RelativeLayout.LayoutParams params = new android.widget.RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 
					LayoutParams.WRAP_CONTENT);
			params.addRule(android.widget.RelativeLayout.CENTER_IN_PARENT);
			parentLinearLayout.addView(contentView, params);
			ViewSizeHelper.getInstance(context).setSize(views.get(i),contentWidth,contentHeight);
			
			//右边的border
			if (i == views.size() - 1) {
				View borderRightView = new View(context);
				parentLinearLayout.addView(borderRightView);
				ViewSizeHelper.getInstance(context).setSize(borderRightView, 
						borderWidth, getHeight());
			}
		}
		
	}
	
	/**
	 * 获得horizontalScrollView的子view个数
	 * @return
	 */
	public int getCount(){
		return  contentViewsWithoutBorder.size();
	}
	
	/**
	 * 获得中央控件的宽度
	 * @return
	 */
	public int getContentWidth() {
		return contentWidth;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		
		if (changed) {
			
			//获得加载的内容View
			for (int j = 0; j < parentLinearLayout.getChildCount(); j++) {
				View view = parentLinearLayout.getChildAt(j);
				if (view.getTag() != null && view.getTag().equals("content")){
					contentViewsWithoutBorder.add(view);
				}
			}

			//记录要移动到的坐标值
			for (int i = 0; i < contentViewsWithoutBorder.size(); i++) {
				indexCoordinatesMap.put(i, travelWidth * i);
			}
			
			//设置可见属性
			for (int i = 0; i < contentViewsWithoutBorder.size(); i++) {
				//第一个默认可见，其余不可见
				if (i == 0) {
					indexVisibleMap.put(i, true);
					LogCat.d(TAG, "visibleIndex="+indexVisibleMap.keyAt(i));
				}else {
					indexVisibleMap.put(i, false);
				}
			}
		}
	}
	
	private int downX;
	private int tempX;
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downX = tempX = (int) ev.getRawX();
			break;
		case MotionEvent.ACTION_MOVE:
			int moveX = (int) ev.getRawX();
			if (Math.abs(moveX - downX) > ViewConfiguration.get(context).getScaledTouchSlop()) {
				return true;
			}
			break;
		}
		return super.onInterceptTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		
		onFling = false;
		gestureDetector.onTouchEvent(ev);
		
		switch (ev.getAction()) {
		case MotionEvent.ACTION_MOVE:
			int moveX = (int) ev.getRawX();
			int deltaX = tempX - moveX;
			tempX = moveX;
			if (Math.abs(ev.getRawX() - downX) > ViewConfiguration.get(context).getScaledTouchSlop()) {
				scrollBy(deltaX, 0);
				invalidate();
			}
			break;
		case MotionEvent.ACTION_UP:
			
			if (onFling) return true;
			
			int visibleIndex = -1;
			for (int i = 0; i < indexVisibleMap.size(); i++) {
				if (indexVisibleMap.valueAt(i)) {
					visibleIndex = indexVisibleMap.keyAt(i);
					break;
				}
			}
			
			int scrollX = (int)(ev.getRawX() - downX);
			if (scrollX >= oneThirdContentWidth) {//如果滑动超过内容项长度的三分之一(左滑)
				//第一项可见时，不能向左滑动
				if (visibleIndex == 0) break;
				//设置其向左一项为可见
				indexVisibleMap.put(visibleIndex, false);
				indexVisibleMap.put(visibleIndex - 1, true);
				visibleIndex--;
				scrollLeft(visibleIndex);
			
			}else if (scrollX <= -oneThirdContentWidth) {//如果滑动超过内容项长度的三分之一（右滑）
				//最后一项可见时，不能向右滑动
				if (visibleIndex == indexVisibleMap.size() - 1) break;
				//设置其向右一项为可见
				indexVisibleMap.put(visibleIndex, false);
				indexVisibleMap.put(visibleIndex + 1, true);
				visibleIndex++;
				scrollRight(visibleIndex);
				
			}else {
				scrollOrigin(visibleIndex);
			}
			break;
		}
		
		return true;
	}
	
	/**
	 * 向右滑动
	 */
	private void scrollRight(int visibleIndex) {
		LogCat.d(TAG, "scrollRight");
		
		int toCoordinate = indexCoordinatesMap.get(visibleIndex);
		
		final int delta = toCoordinate - getScrollX();
		
		//负数为向右滑动
		mScroller.startScroll(-getScrollX(), 0, -delta, 0,(int) (Math.abs(delta)));
		invalidate();
	}

	
	/**
	 * 向左滑动
	 */
	private void scrollLeft(int visibleIndex) {
		LogCat.d(TAG, "scrollLeft");
		
		int toCoordinate = indexCoordinatesMap.get(visibleIndex);
		
		final int delta = toCoordinate - getScrollX();
		
		//正数为向左滑动
		mScroller.startScroll(getScrollX(), 0, delta , 0,(int) (Math.abs(delta)));
		invalidate();
	}

	/**
	 * 滑回原位置
	 */
	private void scrollOrigin(int visibleIndex) {
		
		LogCat.d(TAG, "scrollOrigin");
		
		int toCoordinate = indexCoordinatesMap.get(visibleIndex);
		
		final int delta = getScrollX() - toCoordinate;
		
		if (delta > 0 ) {//向右滑动，返回要向左
			
			mScroller.startScroll(getScrollX(), 0, -delta, 0,
					(int) (Math.abs(delta) * 4));
		}else {//返回要向右
			mScroller.startScroll(-getScrollX(), 0, delta, 0,
					(int) (Math.abs(delta) * 4));
		}
		invalidate();
	}
	
	/**
	 * scroller 滑动回调
	 */
	@Override
	public void computeScroll() {
		
		if (mScroller.computeScrollOffset()) {
			if (mScroller.getCurrX() < 0) {
				smoothScrollTo(-mScroller.getCurrX(), mScroller.getCurrY());
			}else {
				smoothScrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			}
			invalidate();
		}
	}
	
	/**
	 * 主要处理fling事件
	 */
	private OnGestureListener onFlingGestureListener = new OnGestureListener() {
		
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			return false;
		}
		
		@Override
		public void onShowPress(MotionEvent e) {
		}
		
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
				float distanceY) {
			return false;
		}
		
		@Override
		public void onLongPress(MotionEvent e) {
		}
		
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			
			int visibleIndex = -1;
			for (int i = 0; i < indexVisibleMap.size(); i++) {
				if (indexVisibleMap.valueAt(i)) {
					visibleIndex = indexVisibleMap.keyAt(i);
					break;
				}
			}
			
			if (velocityX > 5000) {//左猛滑
				
				//第一项可见时，不能向左滑动
				if (visibleIndex == 0) return true;
				//设置其向左一项为可见
				indexVisibleMap.put(visibleIndex, false);
				indexVisibleMap.put(visibleIndex - 1, true);
				visibleIndex--;
				scrollLeft(visibleIndex);

				onFling = true;
				LogCat.d(TAG, "左猛滑"+velocityX);
			}else if (velocityX < -5000) {//右猛滑

				//最后一项可见时，不能向右滑动
				if (visibleIndex == indexVisibleMap.size() - 1) return true;
				//设置其向右一项为可见
				indexVisibleMap.put(visibleIndex, false);
				indexVisibleMap.put(visibleIndex + 1, true);
				visibleIndex++;
				scrollRight(visibleIndex);

				onFling = true;
				LogCat.d(TAG, "右猛滑"+velocityX);
			}
			
			return true;
		}
		
		@Override
		public boolean onDown(MotionEvent e) {
			return false;
		}
	}; 
	
	/**
	 * 设置导向indicator
	 */
	public void setFlowIndicator(MarginViewFlowIndicator flowIndicator) {
		mIndicator = flowIndicator;
		mIndicator.setViewPager(this);
	}
	
	@Override
	protected void onScrollChanged(int h, int v, int oldh, int oldv) {
		super.onScrollChanged(h, v, oldh, oldv);
		if (mIndicator != null) {
			mIndicator.onScrolled((int)(h/travelRate), (int)(v/travelRate), (int)(oldh/travelRate), (int)(oldv/travelRate));
		}
	}
	
}

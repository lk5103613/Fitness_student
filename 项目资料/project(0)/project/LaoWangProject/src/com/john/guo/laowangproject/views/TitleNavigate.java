package com.john.guo.laowangproject.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.honestwalker.androidutils.views.BaseMyViewLinearLayout;
import com.john.guo.laowangproject.R;

public class TitleNavigate extends BaseMyViewLinearLayout {
	
	private LinearLayout tabOneLL;
	private LinearLayout tabTwoLL;
	private LinearLayout tabThreeLL;
	
	private TextView tabOneTV;
	private TextView tabTwoTV;
	private TextView tabThreeTV;
	
	private int currentPosition;
	private OnShowItemListener mOnShowItemListener;

	public TitleNavigate(Context context) {
		super(context);
		init();
	}
	
	public TitleNavigate(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public TitleNavigate(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	private void init(){
		inflater.inflate(R.layout.view_title_navigate, this);
		tabOneLL = (LinearLayout) findViewById(R.id.layout1);
		tabTwoLL = (LinearLayout) findViewById(R.id.layout2);
		tabThreeLL = (LinearLayout) findViewById(R.id.layout3);
		
		tabOneTV = (TextView) findViewById(R.id.textview1);
		tabTwoTV = (TextView) findViewById(R.id.textview2);
		tabThreeTV = (TextView) findViewById(R.id.textview3);
		
		tabOneLL.setOnClickListener(tabOneLLClick);
		tabTwoLL.setOnClickListener(tabTwoLLClick);
		tabThreeLL.setOnClickListener(tabThreeLLClick);
		
		currentPosition = 0;	//默认选中第一项
		tabOneLL.setSelected(true);
		
	}
	
	/**
	 * 设置三个标签的内容
	 */
	public void setTabString(String tabOneStr, String tabTwoStr, String tabThreeStr){
		tabOneTV.setText(tabOneStr == null?"":tabOneStr);
		tabTwoTV.setText(tabTwoStr == null?"":tabTwoStr);
		tabThreeTV.setText(tabThreeStr == null?"":tabThreeStr);
	}
	
	
	/**
	 * 设置当前选中的项
	 * @param selectedPosition
	 */
	private void setCurrentItem(int selectedPosition){
		switch (selectedPosition) {
		case 0:
			if(currentPosition != 0){
				tabOneLL.setSelected(true);
				canclePreItemState();
			}
			break;
			
		case 1:
			if(currentPosition != 1){
				tabTwoLL.setSelected(true);
				canclePreItemState();
			}
			break;
			
		case 2:
			if(currentPosition != 2){
				tabThreeLL.setSelected(true);
				canclePreItemState();
			}
			break;

		default:
			break;
		}
		currentPosition = selectedPosition;
	}
	
	/**
	 * 清除上一个item的状态
	 */
	private void canclePreItemState(){
		switch (currentPosition) {
		case 0:
			tabOneLL.setSelected(false);
			break;
			
		case 1:
			tabTwoLL.setSelected(false);
			break;
			
		case 2:
			tabThreeLL.setSelected(false);
			break;

		default:
			break;
		}
	}
	
	
	private OnClickListener tabOneLLClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			setCurrentItem(0);
			if(mOnShowItemListener != null){
				mOnShowItemListener.ShowItem(0);
			}
		}
	};
	
	private OnClickListener tabTwoLLClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			setCurrentItem(1);
			if(mOnShowItemListener != null){
				mOnShowItemListener.ShowItem(1);
			}
		}
	};
	
	private OnClickListener tabThreeLLClick = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			setCurrentItem(2);
			if(mOnShowItemListener != null){
				mOnShowItemListener.ShowItem(2);
			}
		}
	};
	
	/**
	 * 设置回调监听
	 * @param mOnShowItemListener
	 */
	public void setOnShowItemListener(OnShowItemListener mOnShowItemListener){
		this.mOnShowItemListener = mOnShowItemListener;
	}
	
	public interface OnShowItemListener{
		public void ShowItem(int position);
	}

}

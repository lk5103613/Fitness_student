package com.honestwalker.androidutils.commons.adapter;

import com.honestwalker.androidutils.equipment.DisplayUtil;

import android.content.Context;
import android.view.View;

public class BaseViewHolder {
	
	private View baseView;
	protected Context mContext;
	protected int screenWidth;
	protected int screenHeight;
	
	private boolean isViewHolderLoaded = false;
	
	public BaseViewHolder(View baseView) {
		this.baseView = baseView;
		this.mContext = baseView.getContext();
		this.screenWidth = DisplayUtil.getInstance(baseView.getContext()).getWidth();
		this.screenHeight = DisplayUtil.getInstance(baseView.getContext()).getHeight();
	}
	
	public View findViewById(int id) {
		return baseView.findViewById(id);
	}
	
	public View findViewById(View view,int id) {
		if(this.baseView != null) {
			if(view == null) {
				view = baseView.findViewById(id);
			}
			return view;
		}
		return null;
	}
	
	/**
	 * viewholder 是否已经加载过（是否是view.getTag 获得的）
	 * @return
	 */
	public boolean isViewHolderLoaded() {
		return isViewHolderLoaded;
	}
	
	public void isViewHolderLoaded(boolean isLoaded) {
		this.isViewHolderLoaded = isLoaded;
	}
	
}

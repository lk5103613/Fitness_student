package com.honestwalker.androidutils.commons.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.honestwalker.androidutils.R;
import com.honestwalker.androidutils.equipment.DisplayUtil;

public abstract class BaseArrayAdapter<T> extends ArrayAdapter<T> {
	
	//================================
	//
	//		   参数声明
	//
	//================================
	
	protected List<T> data;
	protected LayoutInflater inflater;
	protected Context mContext;
	protected int itemResId;
	protected int screenWidth = 0;
	protected int screenHeight = 0;
	private boolean useCache = false;
	
	public ViewHolderBuilder viwHolderBuilder;
	
	public BaseArrayAdapter(Context context, int itemResId ,List<T> data) {
		this(context, itemResId, data, false);
	}
	
	public BaseArrayAdapter(Context context, int itemResId ,List<T> data , boolean userCache) {
		super(context, itemResId, 0, data);
		this.data = data;
		this.useCache = userCache;
		this.itemResId = itemResId;
		this.mContext = context;
		this.inflater = ((Activity)context).getLayoutInflater();
		this.viwHolderBuilder = new ViewHolderBuilder(this.getClass() , this);
		screenWidth = DisplayUtil.getInstance(mContext).getWidth();
		screenHeight = DisplayUtil.getInstance(mContext).getHeight();
	}
	
	protected Map<Integer,View> viewMap = new HashMap<Integer, View>();
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		T item = data.get(position);
		
		if(useCache) {
			return loadWithCache(position, convertView, item);
		} else {
			if(convertView == null) {
				convertView = inflater.inflate(itemResId, null);
			}
			BaseViewHolder baseViewCache = (BaseViewHolder) convertView.getTag();
			if(baseViewCache == null) {
				baseViewCache = new BaseViewHolder(convertView);
			}
			addItemData(convertView , item , position);
			return convertView;
		}
		
	}
	
	private View loadWithCache(int position , View convertView, T item) {
		if(!viewMap.containsKey(position)) {
			
			convertView = inflater.inflate(itemResId, null);
			
			addItemData(convertView , item , position);
			
			viewMap.put(position, convertView);
		}
		
		return viewMap.get(position);
	}
	
	//==============================================
	//	
	//					控件事件监听
	//
	//==============================================
	
	//==============================================
	//	
	//					业务逻辑方法
	//
	//==============================================
	
	public void clearViewCache() {
		viewMap.clear();
	}
	public void removeViewCache(int position) {
		viewMap.remove(position);
	}
	protected Map<Integer,View> getViewMap() {
		return viewMap;
	}
	
	private ViewHolderBuilder getViewHolderBuilder() {
		return this.viwHolderBuilder;
	}
	public <T> T getViewHolder(View convertView , Class<? extends BaseViewHolder> viewHolder) {
		return getViewHolderBuilder().getViewHolder(convertView, viewHolder);
	}
	
	protected abstract void addItemData(View convertView , T item , int position);
	
	protected String getString(int resId) {
		return mContext.getResources().getString(resId);
	}
	
}
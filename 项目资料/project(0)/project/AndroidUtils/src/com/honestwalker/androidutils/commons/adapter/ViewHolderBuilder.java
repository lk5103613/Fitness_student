package com.honestwalker.androidutils.commons.adapter;

import java.lang.reflect.Constructor;

import android.view.View;

import com.honestwalker.androidutils.IO.LogCat;
import com.honestwalker.androidutils.exception.ExceptionUtil;

public class ViewHolderBuilder {
	
	private Class viewHolderParent;
	private BaseArrayAdapter adapter;
	
	public ViewHolderBuilder(Class viewHolderParent , BaseArrayAdapter adapter) {
		this.viewHolderParent = viewHolderParent;
		this.adapter = adapter;
	}
	
	public <T> T getViewHolder(View convertView , Class<? extends BaseViewHolder> viewHolder) {
		if(convertView.getTag() == null) {
			try {
				
				boolean isViewHolderInnerClass = false;
				
				LogCat.d("Holder", "创建 viewHolderParent=" + viewHolderParent);
				LogCat.d("Holder", "viewHolder=" + viewHolder.toString());
				if(viewHolder.toString().indexOf("$") > -1) {
					isViewHolderInnerClass = true;
				} else {
					isViewHolderInnerClass = false;
				}
				
				if(isViewHolderInnerClass) {	// viewHolder时内部类时
					Constructor c = viewHolder.getDeclaredConstructor(viewHolderParent , View.class);
					c.setAccessible(true);
					convertView.setTag((T) c.newInstance(adapter , convertView));
				} else {
					Constructor c = viewHolder.getDeclaredConstructor(View.class);
					c.setAccessible(true);
					convertView.setTag((T) c.newInstance(convertView));
				}
				return (T) convertView.getTag();
			} catch (Exception e) {
				ExceptionUtil.showException(e);
			}
			return null;
		} else {
			LogCat.d("Holder", "复用");
			((BaseViewHolder) convertView.getTag()).isViewHolderLoaded(true);
			return (T) convertView.getTag();
		}
	}
	
}

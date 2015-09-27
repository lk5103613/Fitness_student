package com.like.adapter;

import com.like.fitness.student.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CollectListAdapter extends BaseAdapter {
	
	private LayoutInflater mInflater;
	
	public CollectListAdapter(Context context) {
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return 10;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.collect_item, parent, false);
		}
		return convertView;
	}
	
	public class ViewHolder {
//		public ImageView sImg
	}

}

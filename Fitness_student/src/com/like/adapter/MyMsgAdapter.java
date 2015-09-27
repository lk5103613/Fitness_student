package com.like.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.like.entity.MessageItem;
import com.like.fitness.student.R;

public class MyMsgAdapter extends BaseAdapter {
	
	private LayoutInflater mInflater;
	private List<MessageItem> mMsgs;
	
	public MyMsgAdapter(Context context, List<MessageItem> msgs) {
		mInflater = LayoutInflater.from(context);
		mMsgs = msgs;
	}

	@Override
	public int getCount() {
		return mMsgs.size();
	}

	@Override
	public MessageItem getItem(int position) {
		return mMsgs.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.item_sys_msg, parent, false);
			vh = new ViewHolder(convertView);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		if(position % 2 == 0) {
			vh.sLblIcon.setBackgroundResource(R.drawable.circle_text_coloe_dark_green);
		} else {
			vh.sLblIcon.setBackgroundResource(R.drawable.circle_text_coloe_blue);
		}
		
		MessageItem item = getItem(position);
		vh.sLblTitle.setText(item.title);
		vh.sLblTime.setText(item.addTime);
		vh.sLblDes.setText(item.content);
		return convertView;
	}
	
	public class ViewHolder {
		public TextView sLblIcon;
		public TextView sLblTitle;
		public TextView sLblTime;
		public TextView sLblDes;
		
		public ViewHolder(View view) {
			sLblIcon = (TextView) view.findViewById(R.id.icon);
			sLblTitle = (TextView) view.findViewById(R.id.title);
			sLblTime = (TextView) view.findViewById(R.id.time);
			sLblDes = (TextView) view.findViewById(R.id.des);
		}
	}

}

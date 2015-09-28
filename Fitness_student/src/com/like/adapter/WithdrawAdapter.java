package com.like.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.like.entity.WithdrawHistory;
import com.like.fitness.student.R;

public class WithdrawAdapter extends BaseAdapter {
	
	private LayoutInflater mInflater;
	private List<WithdrawHistory> mHistories;
	
	public WithdrawAdapter(Context context, List<WithdrawHistory> histories) {
		mInflater = LayoutInflater.from(context);
		this.mHistories = histories;
	}
	
	@Override
	public int getCount() {
		return mHistories.size();
	}

	@Override
	public Object getItem(int position) {
		return mHistories.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		WithdrawHistory history = (WithdrawHistory) getItem(position);
		ViewHolder vh = null;
		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.item_withdraw_history, parent, false);
			vh = new ViewHolder(convertView);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		vh.sLblMoney.setText(history.money);
		vh.sLblTime.setText(history.addTime);
		return convertView;
	}
	
	public void updateList(List<WithdrawHistory> histories) {
		this.mHistories = histories;
		notifyDataSetChanged();
	}
	
	public class ViewHolder {
		public TextView sLblMoney;
		public TextView sLblTime;
		
		public ViewHolder(View v) {
			sLblMoney = (TextView) v.findViewById(R.id.money);
			sLblTime = (TextView) v.findViewById(R.id.add_time);
		}
	}
}

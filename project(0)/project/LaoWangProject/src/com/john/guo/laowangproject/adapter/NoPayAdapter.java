package com.john.guo.laowangproject.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.honestwalker.androidutils.commons.adapter.BaseArrayAdapter;
import com.honestwalker.androidutils.commons.adapter.BaseViewHolder;
import com.honestwalker.androidutils.window.ToastHelper;
import com.john.guo.laowangproject.R;
import com.john.guo.laowangproject.bean.NoPayBean;

public class NoPayAdapter extends BaseArrayAdapter<NoPayBean> {

	public NoPayAdapter(Context context, List<NoPayBean> data) {
		super(context, R.layout.item_nopay, data);
	}

	@Override
	protected void addItemData(View convertView, NoPayBean item, final int position) {
		ViewHolder holder = getViewHolder(convertView, ViewHolder.class);
		holder.nameTV.setText(item.getName());
		if("female".equals(item.getSex())){
			holder.sexIV.setImageResource(R.drawable.female);
		}else{
			holder.sexIV.setImageResource(R.drawable.male);
		}
		holder.timeTV.setText(item.getTime());
		holder.courseTypeTV.setText(item.getCourseType());
		holder.payNowBTN.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ToastHelper.alert(mContext, "position = "+ position);
			}
		});
	}
	
	private class ViewHolder extends BaseViewHolder{
		
		private TextView nameTV;
		private TextView timeTV;
		private TextView courseTypeTV;
		private ImageView sexIV;
		private Button payNowBTN;

		public ViewHolder(View baseView) {
			super(baseView);
			nameTV = (TextView) findViewById(R.id.textview1);
			timeTV = (TextView) findViewById(R.id.textview2);
			courseTypeTV = (TextView) findViewById(R.id.textview3);
			sexIV = (ImageView) findViewById(R.id.imageview1);
			payNowBTN = (Button) findViewById(R.id.btn1);
		}
		
	}

}

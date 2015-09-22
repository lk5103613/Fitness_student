package com.john.guo.laowangproject.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.honestwalker.androidutils.commons.adapter.BaseArrayAdapter;
import com.honestwalker.androidutils.commons.adapter.BaseViewHolder;
import com.john.guo.laowangproject.R;
import com.john.guo.laowangproject.bean.MyCouponBean;

public class MyCouponAdapter extends BaseArrayAdapter<MyCouponBean> {

	public MyCouponAdapter(Context context, List<MyCouponBean> data) {
		super(context, R.layout.item_coupon, data);
	}

	@Override
	protected void addItemData(View convertView, MyCouponBean item, int position) {
		ViewHolder holder = getViewHolder(convertView, ViewHolder.class);
		holder.titleTV.setText(item.getTitle());
		holder.timeTV.setText("有效期至："+item.getStartTime()+"-"+item.getEndTime());
		holder.idTV.setText(item.getCouponID());
		if("健身50元优惠券".equals(item.getTitle())){
			holder.itemLL.setBackgroundResource(R.drawable.image_couponyellow);
		}else if("健身100元优惠券".equals(item.getTitle())){
			holder.itemLL.setBackgroundResource(R.drawable.image_couponblue);
		}else{
			holder.itemLL.setBackgroundResource(R.drawable.image_couponred);
		}
	}
	
	private class ViewHolder extends BaseViewHolder{
		
		private LinearLayout itemLL;
		private TextView titleTV;
		private TextView timeTV;
		private TextView idTV;

		public ViewHolder(View baseView) {
			super(baseView);
			itemLL = (LinearLayout) findViewById(R.id.layout1);
			titleTV = (TextView) findViewById(R.id.textview1);
			timeTV = (TextView) findViewById(R.id.textview2);
			idTV = (TextView) findViewById(R.id.textview3);
		}
		
	}

}

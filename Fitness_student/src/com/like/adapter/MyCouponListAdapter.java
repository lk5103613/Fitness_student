package com.like.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;

import com.like.entity.Coupon;
import com.like.fitness.student.R;

public class MyCouponListAdapter extends SimpleAdapter<Coupon>{

	public MyCouponListAdapter(Context context, List<Coupon> datas) {
		super(context, datas);
	}

	@Override
	public int getItemResourceId() {
		return R.layout.item_coupon;
	}

	@Override
	public void bindData(int position, View convertView,ViewHolder holder) {
		
	}

}

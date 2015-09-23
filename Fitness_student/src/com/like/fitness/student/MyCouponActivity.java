package com.like.fitness.student;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.like.adapter.MyCouponListAdapter;
import com.like.entity.Coupon;

public class MyCouponActivity extends Activity {
	
	private ListView mCouponListView;
	private MyCouponListAdapter mAdapter;
	private List<Coupon> mCoupons = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coupon);
		mCouponListView = (ListView) findViewById(R.id.my_coupon_list);
		mAdapter = new MyCouponListAdapter(this, mCoupons);
		mCouponListView.setAdapter(mAdapter);
		initData();
	}
	
	private void initData(){
		for (int i = 0; i < 10; i++) {
			Coupon coupon = new Coupon();
			mCoupons.add(coupon);
		}
		mAdapter.notifyDataSetChanged();
	}

	
}

package com.like.fitness.student;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Response.Listener;
import com.like.adapter.MyCouponListAdapter;
import com.like.entity.Coupon;

public class MyCouponActivity extends BaseActivity {
	
	private ListView mCouponListView;
	private MyCouponListAdapter mAdapter;
	private List<Coupon> mCoupons = new ArrayList<Coupon>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coupon);
		mCouponListView = (ListView) findViewById(R.id.my_coupon_list);
		mAdapter = new MyCouponListAdapter(this, mCoupons);
		mCouponListView.setAdapter(mAdapter);
		initData();
		updateList();
	}
	
	private void updateList() {
		mDataFetcher.fetchMyCoupon(mLoginUser.uid, new Listener<String>() {
			@Override
			public void onResponse(String response) {
				System.out.println(response);
			}
		}, mErrorListener);
	}
	
	private void initData(){
		for (int i = 0; i < 10; i++) {
			Coupon coupon = new Coupon();
			mCoupons.add(coupon);
		}
		mAdapter.notifyDataSetChanged();
	}

}

package com.like.fitness.student;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Response.Listener;
import com.google.gson.reflect.TypeToken;
import com.like.adapter.MyCouponListAdapter;
import com.like.entity.Category;
import com.like.entity.Coupon;
import com.like.network.GsonUtil;

public class MyCouponActivity extends BaseActivity {
	
	private ListView mCouponListView;
	private MyCouponListAdapter mAdapter;
	private List<Coupon> mCoupons = new ArrayList<Coupon>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coupon);
		mCouponListView = (ListView) findViewById(R.id.my_coupon_list);
		updateList();
	}
	
	private void updateList() {
		mDataFetcher.fetchMyCoupon(mLoginUser.uid, new Listener<String>() {
			@Override
			public void onResponse(String response) {
				Type type = new TypeToken<List<Coupon>>() {}.getType();
				List<Coupon> coupons = GsonUtil.gson.fromJson(
						response.toString(), type);
				mCoupons.clear();
				mCoupons.addAll(coupons);
				mAdapter = new MyCouponListAdapter(mContext, mCoupons);
				mCouponListView.setAdapter(mAdapter);
			}
		}, mErrorListener);
	}
	
}

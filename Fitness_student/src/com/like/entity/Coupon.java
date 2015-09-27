package com.like.entity;

import com.google.gson.annotations.SerializedName;

public class Coupon {
	public String name;
	@SerializedName("dead_time")
	public String deadTime;
	@SerializedName("coupon_no")
	public String CouponNo;
}

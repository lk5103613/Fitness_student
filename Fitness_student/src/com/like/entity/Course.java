package com.like.entity;

import com.google.gson.annotations.SerializedName;

public class Course {
	
	public final static int FEMALE = 2;
	public final static int MALE = 1;

	@SerializedName("order_id")
	public String orderId;
	@SerializedName("order_no")
	public String orderNo;
	@SerializedName("add_time")
	public String addTime;
	public String username;
	public String status;
	public String uid;
	@SerializedName("course_duration")
	public String courseDuration;
	@SerializedName("category_id")
	public String categoryId;
	@SerializedName("course_name")
	public String courseName;
	@SerializedName("course_id")
	public String courseId;
	public String money;
	public String coachid;
	public String avatar;
	public String addtime2;
	public int gender;

}

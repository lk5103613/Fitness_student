package com.like.entity;

import com.google.gson.annotations.SerializedName;

public class CollectItem {
	@SerializedName("course_id")
	public String courseId;
	@SerializedName("course_name")
	public String courseName;
	public String price;
	@SerializedName("coach_id")
	public String coachId;
	@SerializedName("add_time")
	public String addTime;
	public String cnt;
	@SerializedName("from_time")
	public String fromTime;
	@SerializedName("to_time")
	public String toTime;
	public String flag;
	@SerializedName("course_description")
	public String courseDescription;
	@SerializedName("category_id")
	public String categoryId;
	public String area;
	@SerializedName("course_duration")
	public String courseDuration;
	@SerializedName("fromDay")
	public String fromday;
	@SerializedName("toDay")
	public String today;
	@SerializedName("detail_address")
	public String detailAddress;
	public String tuikuan;
	@SerializedName("min_students")
	public String minStudents;
	@SerializedName("timeFlag")
	public String timeflag;
	@SerializedName("week_days")
	public String weekDays;
	@SerializedName("hand_select_days")
	public String handSelectDays;
	public String gender;
	public String truename;
	@SerializedName("work_years")
	public String workYears;

}

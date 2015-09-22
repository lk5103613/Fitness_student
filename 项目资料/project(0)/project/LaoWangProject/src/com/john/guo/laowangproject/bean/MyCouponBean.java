package com.john.guo.laowangproject.bean;

import java.io.Serializable;

public class MyCouponBean implements Serializable {

	private String title;
	private String startTime;
	private String endTime;
	private String couponID;

	public MyCouponBean() {
		super();
	}

	public MyCouponBean(String title, String startTime, String endTime,
			String couponID) {
		super();
		this.title = title;
		this.startTime = startTime;
		this.endTime = endTime;
		this.couponID = couponID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCouponID() {
		return couponID;
	}

	public void setCouponID(String couponID) {
		this.couponID = couponID;
	}

}

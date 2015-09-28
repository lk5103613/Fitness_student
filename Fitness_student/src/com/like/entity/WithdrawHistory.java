package com.like.entity;

import com.google.gson.annotations.SerializedName;

public class WithdrawHistory {

	public String id;
	public String money;
	public String uid;
	@SerializedName("add_time")
	public String addTime;
	public String account;
	public String flag;
	public String status;

}

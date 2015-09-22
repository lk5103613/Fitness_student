package com.like.entity;

import com.google.gson.annotations.SerializedName;

public class LoginResult {

	public String uid;
	public String truename;
	public String nickname;
	public String mp;
	public String pwd;
	public String avatar;
	public String gender;
	@SerializedName("userType")
	public String usertype;
	public String status;
	@SerializedName("all_tran_money")
	public String allTranMoney;
	@SerializedName("all_tran_cnt")
	public String allTranCnt;
	@SerializedName("last_login_time")
	public String lastLoginTime;
	@SerializedName("continuous_login_days")
	public String continuousLoginDays;
	public String district;
	public String province;
	public String imei;
	public int code;

}

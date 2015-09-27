package com.like.entity;

import com.google.gson.annotations.SerializedName;

public class MessageItem {
	public String msgid;
	public String title;
	public String content;
	@SerializedName("add_time")
	public String addTime;
	public String uid;
}

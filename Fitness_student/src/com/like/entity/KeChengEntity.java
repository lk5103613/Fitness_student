package com.like.entity;

public class KeChengEntity  extends BaseEntity{
	public KeChengEntity(){
		this.type = TYPE_KECHENG;
	}
	
	public int course_id;
	public String course_name;
	public float price;
	public int coach_id;
	public String add_time;
	public int cnt;
	public String from_time;
	public String to_time;
	public String course_description;
}

package com.john.guo.laowangproject.bean;

import java.io.Serializable;

public class NoPayBean implements Serializable {
	private String name;
	private String sex;
	private String time;
	private String courseType;
	private String level;
	private String price;
	
	public NoPayBean(String name, String sex, String time, String courseType, String level, String price) {
		super();
		this.name = name;
		this.sex = sex;
		this.time = time;
		this.courseType = courseType;
		this.level = level;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
}

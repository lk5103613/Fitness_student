package com.like.entity;

import java.util.ArrayList;
import java.util.List;

public class CoachDetailEntity {

	public int coachid;
	public String truename;
	public int weight;
	public String avatar;
	public String mp;
	public String pwd;
	public int gender;
	public String nickname;
	public String height; // int 如果未空的话报错
	public int status;
//	public List<String> tag;
	public String idcard;
	public int score;
	public int click_cnt;
	public String skill;
	
	public List<KeChengEntity> courList = new ArrayList<>();
	public List<PingYuEntity> commentList = new ArrayList<>();
	@Override
	public String toString() {
		return "CoachDetailEntity [coachid=" + coachid + ", truename="
				+ truename + ", weight=" + weight + ", avatar=" + avatar
				+ ", mp=" + mp + ", pwd=" + pwd + ", gender=" + gender
				+ ", nickname=" + nickname + ", height=" + height + ", status="
//				+ status + ", tag=" + tag + ", idcard=" + idcard + ", score="
				+ score + ", click_cnt=" + click_cnt + ", courList=" + courList
				+ ", commentList=" + commentList + "]";
	}
	
	
}

package com.like.entity;

public class CoachInfo extends BaseEntity {

    public int coachid;
    public String truename;
    public int weight;
    public String avatar;
    public String mp;
    public String pwd;
    public int gender;
    public String nickname;
    public String height; //int 如果未空的话报错
    public int status;
//    public List<String> tag;
    public String idcard;
    public int score;
    public int click_cnt;
    public String skill;
    public String coach_description;
    
    public CoachInfo() {
        this.type = TYPE_HEADER;

    }

	@Override
	public String toString() {
		return "CoachInfo [coachid=" + coachid + ", truename=" + truename
				+ ", weight=" + weight + ", avatar=" + avatar + ", mp=" + mp
				+ ", pwd=" + pwd + ", gender=" + gender + ", nickname="
				+ nickname + ", height=" + height + ", status=" + status
//				+ ", tag=" + tag + ", idcard=" + idcard + ", score=" + score
				+ ", click_cnt=" + click_cnt + ", skill=" + skill + "]";
	}


}

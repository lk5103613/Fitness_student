package com.like.entity;

import java.util.List;

public class CoachStringInfo extends BaseEntity {

    public int coachid;
    public String truename;
    public int weight;
    public String avatar;
    public String mp;
    public String pwd;
    public int gender;
    public String nickname;
    public String height;
    public int status;
    public String tag;
    public String idcard;
    public int score;
    public int click_cnt;
    public String skill;

    public CoachStringInfo() {
        this.type = TYPE_HEADER;

    }

    @Override
    public String toString() {
        return "CoachInfo [coachid=" + coachid + ", truename=" + truename
                + ", weight=" + weight + ", avatar=" + avatar + ", mp=" + mp
                + ", pwd=" + pwd + ", gender=" + gender + ", nickname="
                + nickname + ", height=" + height + ", status=" + status
                + ", tag=" + tag + ", idcard=" + idcard + ", score=" + score
                + ", click_cnt=" + click_cnt + ", skill=" + skill + "]";
    }


}

package com.like.network;

public class APIS {
    public final static String BASE_URL = "http://121.41.85.232";
//    public final static String GET_COACH_LIST = BASE_URL + "/index.php/coach/findPageList?currentPage=%1&catid=%2&sort=%3";
    public final static String GET_COACH_DETAIL = BASE_URL + "/index.php/coach/detail?coachid=";
    public final static String GET_CATEGORY = BASE_URL + "/index.php/home/categoryFn";
    public final static String GET_COACH_LIST = BASE_URL + "/index.php/coach/findPageList";
    public final static String GET_COACH_PHOTO = BASE_URL + "/index.php/Coach/photoList?coachid=";
    
    public final static String LOGIN = BASE_URL + "/index.php/AppUser/login?mp=%1&pwd=%2&imei=%3";
    public static final String REG = BASE_URL + "/index.php/AppUser/reg?nickname=%1&mp=%2&pwd=%3&imei=%4&avatar=%5";
    
    public final static String UPLOAD = BASE_URL + "/yw_uploadify.php";
    public static final String SEND_CODE = "http://222.73.117.158/msg/HttpBatchSendSM?account=vip_lb_dcjd&pswd=vip_lb_dcjd001&mobile=%1&msg=%2&needstatus=true"; 
    
    public static final String GET_MY_INFO = BASE_URL + "/index.php/AppUser/loadUserInfo?uid=%1";
    
    public static final String GET_MY_COURSE = BASE_URL + "/index.php/AppI/orderPage?uid=%1&status=%2";
   //我的优惠券
    public static final String GET_MY_COUPON = BASE_URL + "/index.php/appCoupon/findCouponList?uid=%1";
    
//我的消息
    public static final String GET_MY_MSG = BASE_URL+"/index.php/appMsg/index?uid=%1";
//我的收藏, flag: 0:热门    1:离我最近    2:即将开始
    public static final String GET_MY_COLLECTION = BASE_URL + "/index.php/AppFav/findFavCoachList?uid=%1&flag=%2";

 //获取可用金额
    public static final String GET_VALID_MONEY = BASE_URL+"/index.php/AppI/loadValidMoneyFn?uid=%1";
 //提现
    public static final String WITHDRAW = BASE_URL+"/index.php/AppI/withdraw?uid=%1&money=%2";
    public static final String WITHDRAW_HISTORY = BASE_URL + "/index.php/AppI/findWithdrawHistory?uid=%1";
    
    public static final String GET_COURSE_DETAIL = BASE_URL + "/index.php/AppCourse/detailFn?course_id=%1";
}

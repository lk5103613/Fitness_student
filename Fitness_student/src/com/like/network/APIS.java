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
    public static final String GET_MY_COUPON = BASE_URL + "/index.php/appCoupon/findCouponList?uid=%1";
    
}

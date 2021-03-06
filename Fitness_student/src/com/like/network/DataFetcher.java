package com.like.network;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

public class DataFetcher {

	private Context mApplicationContext;
	private RequestQueue mQueue;
	private ImageLoader mImgLoader;

	private static DataFetcher mFetcher;
	

	private DataFetcher(Context applicationContext) {
		this.mApplicationContext = applicationContext;
		mQueue = MyNetworkUtil.getInstance(mApplicationContext)
				.getRequestQueue();
		mImgLoader = MyNetworkUtil.getInstance(mApplicationContext).getImageLoader();
	}

	public static DataFetcher getInstance(Context applicationContext) {
		if (mFetcher == null)
			mFetcher = new DataFetcher(applicationContext);
		return mFetcher;
	}

	private JSONArray getJsonArrayParam(Map<String, String> params) {
		if (params == null)
			return null;
		String paramsStr = GsonUtil.gson.toJson(params);
		try {
			return new JSONArray(paramsStr);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void loadImg(String url ,ImageView img, int defaultImgResId, int errorImgResId) {
		mImgLoader.get(url, ImageLoader.getImageListener(img, defaultImgResId, errorImgResId));
	}

	public void fetchCoachList(String url, Map<String, String> params,
			Response.Listener<JSONArray> listener,
			Response.ErrorListener errorListener) {
		JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
				url, getJsonArrayParam(params), listener, errorListener);
		mQueue.add(request);
	}

	public void fetchCoachList(String url, int page, int catid, int sort,
			Response.Listener<JSONObject> listener,
			Response.ErrorListener errorListener) {
		url = url.replace("%1", String.valueOf(page));
		url = url.replace("%2", String.valueOf(catid));
		url = url.replace("%3", String.valueOf(sort));
		JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
				url, null, listener, errorListener);
		MyNetworkUtil.getInstance(mApplicationContext)
				.addToRequstQueue(request);
		System.out.println("url " + url + page);
	}

	public void fetchCoachListByParams(String url, Map<String, String> params,
			Response.Listener<JSONObject> listener,
			Response.ErrorListener errorListener) {
		if (!params.isEmpty()) {
			url += "?";
		}
		for (String key : params.keySet()) {
			String value = params.get(key);
			url = url + key + "=" + value + "&";
		}

		url = url.substring(0, url.length() - 1);
		System.out.println("map url " + url);
		JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
				url, null, listener, errorListener);
		MyNetworkUtil.getInstance(mApplicationContext)
				.addToRequstQueue(request);
	}

	public void fetchCategory(String url,
			Response.Listener<JSONArray> listenert,
			Response.ErrorListener errorListener) {
		JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
				url, null, listenert, errorListener);
		MyNetworkUtil.getInstance(mApplicationContext)
				.addToRequstQueue(request);
	}

	public void fetchCoachById(String url, int id,
			Response.Listener<JSONObject> listener,
			Response.ErrorListener errorListener) {
		JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
				url + id, null, listener, errorListener);
		MyNetworkUtil.getInstance(mApplicationContext)
				.addToRequstQueue(request);
	}

	public void fetchPhotoById(String url, int id,
			Response.Listener<JSONArray> listener,
			Response.ErrorListener errorListener) {
		JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url
				+ id, null, listener, errorListener);
		MyNetworkUtil.getInstance(mApplicationContext)
				.addToRequstQueue(request);
	}

	public void fetchLogin(String mp, String pwd, String imei,
			Listener<String> listener, ErrorListener errorListener) {
		Map<String, String> params = UrlParamGenerator.getMapParams(APIS.LOGIN,
				mp, pwd, imei);
		String url = UrlParamGenerator.getBasePath(APIS.LOGIN);
		MyRequest request = new MyRequest(Method.POST, url, params, listener,
				errorListener);
		mQueue.add(request);
	}

	public void fetchSendCode(String phone, String code,
			Listener<String> listener, ErrorListener errorListener) {
		String msg = "【大厨家到】尊敬的用户您好,本次验证码是:" + code;
		try {
			msg = URLEncoder.encode(msg, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String url = UrlParamGenerator.getPath(APIS.SEND_CODE, phone, msg);
		System.out.println(url);
		StringRequest request = new StringRequest(Request.Method.GET, url,
				listener, errorListener);
		MyNetworkUtil.getInstance(mApplicationContext)
				.addToRequstQueue(request);
	}
	
	public void fetchReg(String nickName, String mp, String pwd, String imei, String avatar, Listener<String> listener, ErrorListener errorListener) {
		Map<String, String> params = UrlParamGenerator.getMapParams(APIS.REG, nickName, mp, pwd, imei, avatar);
		String url = UrlParamGenerator.getBasePath(APIS.REG);
		MyRequest request = new MyRequest(Method.POST, url, params, listener, errorListener);
		mQueue.add(request);
	}
	
	public void fetchMyInfo(String uid, Listener<String> listener, ErrorListener errorListener) {
		String url = UrlParamGenerator.getPath(APIS.GET_MY_INFO, uid);
		MyRequest request = new MyRequest(Method.GET, url, listener, errorListener);
		mQueue.add(request);
	}
	
	public void fetchMyCourse(String uid, String status, Listener<String> listener, ErrorListener errorListener) {
		String url = UrlParamGenerator.getPath(APIS.GET_MY_COURSE, uid, status);
		MyRequest request = new MyRequest(Method.GET, url, listener, errorListener);
		mQueue.add(request);
	}
	
	public void fetchMyCoupon(String uid, Listener<String> listener, ErrorListener errorListener) {
		String url = UrlParamGenerator.getPath(APIS.GET_MY_COUPON, uid);
		MyRequest request = new MyRequest(Method.GET, url, listener, errorListener);
		mQueue.add(request);
	}
	
	public void fetchCollection(String uId, String flag,Listener<String> listener, ErrorListener errorListener){
		String url = UrlParamGenerator.getPath(APIS.GET_MY_COLLECTION, uId, flag);
		MyRequest request = new MyRequest(Method.GET, url, listener, errorListener);
		mQueue.add(request);
	}
	
	public void fetchMsgs(String uId,Listener<String> listener, ErrorListener errorListener){
		String url = UrlParamGenerator.getPath(APIS.GET_MY_MSG, uId);
		MyRequest request = new MyRequest(Method.GET, url, listener, errorListener);
		mQueue.add(request);
	}
	
	public void fetchAvailable(String uId,Listener<String> listener, ErrorListener errorListener){
		String url = UrlParamGenerator.getPath(APIS.GET_VALID_MONEY, uId);
		MyRequest request = new MyRequest(Method.GET, url, listener, errorListener);
		mQueue.add(request);
	}
	
	public void fetchWithdraw(String uId, String money, Listener<String> listener, ErrorListener errorListener){
		String url = UrlParamGenerator.getPath(APIS.WITHDRAW, uId, money);
		MyRequest request = new MyRequest(Method.GET, url, listener, errorListener);
		mQueue.add(request);
	}
	
	public void fetchWidthdrawHis(String uId, Listener<String> listener, ErrorListener errorListener) {
		String url = UrlParamGenerator.getPath(APIS.WITHDRAW_HISTORY, uId);
		MyRequest request = new MyRequest(Method.GET, url, listener, errorListener);
		mQueue.add(request);
	}
	
	public void fetchCourseDetail(String courseId, Listener<String> listener, ErrorListener errorListener) {
		String url = UrlParamGenerator.getPath(APIS.GET_COURSE_DETAIL, courseId);
		MyRequest request = new MyRequest(Method.GET, url, listener, errorListener);
		mQueue.add(request);
	}
	
}

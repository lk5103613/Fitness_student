package com.like.network;

import android.R.string;
import android.content.Context;
import android.renderscript.Sampler.Value;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class DataFetcher {

	private Context mApplicationContext;

	private static DataFetcher mFetcher;

	private DataFetcher(Context applicationContext) {
		this.mApplicationContext = applicationContext;
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

	public void fetchCoachList(String url, Map<String, String> params,
			Response.Listener<JSONArray> listener,
			Response.ErrorListener errorListener) {
		JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
				url, getJsonArrayParam(params), listener, errorListener);
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
		for (String key: params.keySet()) {
			String value = params.get(key);
			url =url +  key +"=" + value +"&";
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

	public void fetchPhotoById(String url, int id, Response.Listener<JSONArray> listener,
							   Response.ErrorListener errorListener) {
		JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + id, null,
				listener, errorListener);
		MyNetworkUtil.getInstance(mApplicationContext).addToRequstQueue(request);

	}

}

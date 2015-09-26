package com.like.fragments;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.google.gson.Gson;
import com.like.adapter.DetailListAdapter;
import com.like.entity.BaseEntity;
import com.like.entity.CoachDetailEntity;
import com.like.entity.CoachInfo;
import com.like.entity.KeChengEntity;
import com.like.entity.PingYuEntity;
import com.like.fitness.student.R;
import com.like.network.APIS;
import com.like.network.DataFetcher;
import com.like.network.GsonUtil;
import com.like.network.MyNetworkUtil;

public class CoachDetailIndexFragment extends Fragment {

    private Context mContext;
    private ListView mList;

    private List<BaseEntity> mPEntities = new ArrayList<BaseEntity>();
    private DetailListAdapter mAdapter;
    private LinearLayout mFooter;
    private DataFetcher mDataFetcher;
    private int id;
    private CoachInfo mCoachInfo;
    private CoachDetailEntity mChoachDetailEntity;

    public CoachDetailIndexFragment() {
        mDataFetcher = DataFetcher.getInstance(getActivity());
       
    }

    public void setCoachId(int coachId) {
        this.id = coachId;
        mDataFetcher.fetchCoachById(APIS.GET_COACH_DETAIL, coachId, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            	System.out.println("result " + response);
                mChoachDetailEntity = GsonUtil.gson.fromJson(response.toString(), CoachDetailEntity.class);
               
                initData(mChoachDetailEntity);
              
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_coach_detail_index, container, false);
        mContext = getActivity();

        mList = (ListView) v.findViewById(R.id.detail_list);
        mFooter = (LinearLayout) View.inflate(mContext, R.layout.coach_footer_item, null);
        mList.addFooterView(mFooter);
        ImageLoader loader = MyNetworkUtil.getInstance(mContext).getImageLoader();
        mAdapter = new DetailListAdapter(mContext, mPEntities, loader);
        mList.setAdapter(mAdapter);

        return v;
    }

    private void initData(CoachDetailEntity coachDetailEntity) {
        mPEntities.clear();
        
        System.out.println(coachDetailEntity.toString());
        CoachInfo coachInfo = GsonUtil.gson.fromJson(new Gson().toJson(coachDetailEntity), CoachInfo.class);
        List<KeChengEntity> keChengEntities = coachDetailEntity.courList;
        List<PingYuEntity> pingYuEntities = coachDetailEntity.commentList;
        mPEntities.add(coachInfo);
        
        System.out.println("coach info " + coachInfo.toString());
        mPEntities.addAll(keChengEntities);
        mPEntities.addAll(pingYuEntities);
        mAdapter.notifyDataSetChanged();
    }
    
    @Override
    public void onDestroy() {
    	System.out.println("destory ");
    	super.onDestroy();
    }

}

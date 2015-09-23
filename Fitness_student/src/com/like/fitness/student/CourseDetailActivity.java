package com.like.fitness.student;

import java.util.ArrayList;
import java.util.List;

import com.like.adapter.CoursePyListAdapter;
import com.like.utils.DisplayUtils;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

public class CourseDetailActivity extends BaseActivity {
	
	private TextView mCourseName;
	private TextView mCourseMoney;
	private TextView mCoachName;
	private TextView mTime;
	private TextView mMaxCount;
	private TextView mAddress;
	private RadioButton mTypeMore;
	
	private ListView mPingYuList;
	private CoursePyListAdapter mAdapter;
	private List<Comment> mComments = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coursedetails);
		mCourseName = (TextView) findViewById(R.id.course_name);
		mCourseName = (TextView) findViewById(R.id.course_name);
		mCourseName = (TextView) findViewById(R.id.money);
		mCoachName = (TextView) findViewById(R.id.coach_name);
		mTime = (TextView) findViewById(R.id.time);
		mMaxCount = (TextView) findViewById(R.id.max_count);
		mAddress = (TextView) findViewById(R.id.address);
		mTypeMore = (RadioButton) findViewById(R.id.type_more);
		mPingYuList = (ListView) findViewById(R.id.pingyu_listview);
		
		initData();
		mAdapter = new CoursePyListAdapter(this, mComments);
		mPingYuList.setAdapter(mAdapter);
		DisplayUtils.setListViewHeightBasedOnChildren(this,mPingYuList);
	}

	private void initData() {
		for (int i = 0; i < 3; i++) {
			Comment comment = new Comment();
			mComments.add(comment);
		}
	}

	/**
     * 分享 点击事件
     * @param view
     */
    public void share(View view){
    	
    }
    
	
}

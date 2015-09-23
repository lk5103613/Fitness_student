package com.like.fitness.student;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.like.adapter.CoursePyListAdapter;
import com.like.utils.DisplayUtil;

public class CourseDetailActivity extends BaseActivity {

	private TextView mCourseName;
	private TextView mCourseMoney;
	private TextView mCoachName;
	private TextView mTime;
	private TextView mMaxCount;
	private TextView mAddress;
	private RadioButton mTypeMore;
	private ScrollView mScroller;
	
	private ListView mPingYuList;
	private CoursePyListAdapter mAdapter;
	private List<Comment> mComments = new ArrayList<>();

	private PopupWindow mSharePop;
	private PopupWindow mBuyPop;

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
		mScroller = (ScrollView) findViewById(R.id.scrollview);

		initData();
		mAdapter = new CoursePyListAdapter(this, mComments);
		mPingYuList.setAdapter(mAdapter);
		DisplayUtil.getInstance(this).setListViewHeightBasedOnChildren(this, mPingYuList);
		mScroller.smoothScrollTo(0, 0);
	}

	private void initData() {
		for (int i = 0; i < 3; i++) {
			Comment comment = new Comment();
			mComments.add(comment);
		}
	}

	/**
	 * 分享 点击事件
	 * 
	 * @param view
	 */
	public void share(View view) {
		LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View popView = layoutInflater.inflate(R.layout.share_pop, null);

		mSharePop = new PopupWindow(popView, 240, 300, true);
		
		mSharePop.setOutsideTouchable(true);
		ColorDrawable dw = new ColorDrawable(0x00);
		mSharePop.setBackgroundDrawable(dw);
		mSharePop.showAsDropDown(view);
	}

	/**
	 * 点击购买
	 * 
	 * @param view
	 */
	public void buy(View view) {
		LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View popView = layoutInflater.inflate(R.layout.buy_popupwindow, null);

		mSharePop = new PopupWindow(popView, DisplayUtil.getInstance(this).getWidth(), DisplayUtil.getInstance(this).getHeight()/3, true);
		
		mSharePop.setOutsideTouchable(true);
		ColorDrawable dw = new ColorDrawable(0x00);
		mSharePop.setBackgroundDrawable(dw);
		mSharePop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
	}

}

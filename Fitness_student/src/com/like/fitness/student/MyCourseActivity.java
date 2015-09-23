package com.like.fitness.student;

import android.os.Bundle;

import com.like.adapter.CoursePagerAdapter;
import com.like.customview.BarViewPager;
import com.like.customview.PagerSlidingTabStrip;

public class MyCourseActivity extends BaseActivity {

	private BarViewPager mPager;
	private PagerSlidingTabStrip mTabStrip;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_course);
		initView();
		initTab();
	}

	private void initView() {
		mPager = (BarViewPager) findViewById(R.id.viewpager);
		mTabStrip = (PagerSlidingTabStrip) findViewById(R.id.order_tap);
	}

	private void initTab() {
		mPager.setAdapter(new CoursePagerAdapter(getSupportFragmentManager()));
		mTabStrip.setShouldExpand(true);
		mTabStrip.setFillViewport(true);
		mTabStrip.setIndicatorColorResource(R.color.primary_red_color);
		mTabStrip.setTextColorStateResource(R.color.tab_text_color);
		mTabStrip.setTextSize(26);
		mTabStrip.setViewPager(mPager);
		mTabStrip.setIndicatorPadding(20);
		mTabStrip.setDividerColorResource(R.color.primary_background_color);
	}

}

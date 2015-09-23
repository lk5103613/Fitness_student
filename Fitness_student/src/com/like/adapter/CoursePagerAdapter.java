package com.like.adapter;

import com.like.fragments.CourseCancelFragment;
import com.like.fragments.CourseUnConsumeFragment;
import com.like.fragments.CourseUnpayFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class CoursePagerAdapter extends FragmentStatePagerAdapter {
	
	private CourseUnpayFragment mUnpayFramgnet = new CourseUnpayFragment();
	private CourseUnConsumeFragment mUnConsumeFragment = new CourseUnConsumeFragment();
	private CourseCancelFragment mCancelFragment = new CourseCancelFragment();
	
	private Fragment[] mFragments = new Fragment[] { mUnpayFramgnet,
			mUnConsumeFragment, mCancelFragment };
	private String[] mTitles = new String[] { "未付款", "未消费", "已取消" };
	
	
	public CoursePagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
		return mFragments[index];
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		return mTitles[position];
	}

	@Override
	public int getCount() {
		return mTitles.length;
	}

}

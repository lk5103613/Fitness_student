package com.like.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.like.fragments.CoachCourseFragment;
import com.like.fragments.CoachDetailIndexFragment;
import com.like.fragments.CoachPhotoFragment;

public class CoachDetailPagerAdapter extends FragmentStatePagerAdapter{

	
	private CoachDetailIndexFragment mIndexFragment = new CoachDetailIndexFragment();
	private CoachCourseFragment mCoachCourseFragment = new CoachCourseFragment();
	private CoachPhotoFragment mCoachPhotoFragment = new CoachPhotoFragment();
	
	private Fragment[] mFragments = new Fragment[] { mIndexFragment,
			mCoachCourseFragment, mCoachPhotoFragment };
	private String[] mTitles = new String[] { "主页", "课程", "相册" };
	

	public CoachDetailPagerAdapter(FragmentManager fm, int coachId) {
		super(fm);
		mIndexFragment.setCoachId(coachId);
		mCoachPhotoFragment.setCoachId(coachId);
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
		return 3;
	}

}

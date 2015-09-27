package com.like.adapter;

import com.like.fragments.CollectHotFragment;
import com.like.fragments.CollectNearFragment;
import com.like.fragments.CollectStartingFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class CollectListPagerAdapter extends FragmentStatePagerAdapter {
	
	private CollectHotFragment mHotFragment = new CollectHotFragment();
	private CollectNearFragment mNearFragment = new CollectNearFragment();
	private CollectStartingFragment mStartingFragment = new CollectStartingFragment();
	
	
	private Fragment[] mFragments = new Fragment[] { mHotFragment,
			mNearFragment, mStartingFragment };
	private String[] mTitles = new String[] { "热门", "离我最近", "即将开始" };

	public CollectListPagerAdapter(FragmentManager fm) {
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

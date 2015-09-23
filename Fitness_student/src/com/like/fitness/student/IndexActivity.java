package com.like.fitness.student;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.like.customview.SelectLinearLayout;
import com.like.fragments.IndexFragment;
import com.like.fragments.MyInfoFragment;
import com.like.fragments.ShaiXuanFragment;

public class IndexActivity extends BaseActivity implements OnClickListener {

	private SelectLinearLayout mIndex;
	private SelectLinearLayout mShaiXuan;
	private SelectLinearLayout mMyInfo;
	
//	private BarViewPager mPagerContainer;
	private LinearLayout mPagerContainer;
	
	private Fragment[] mFragments;
	private SelectLinearLayout[] mtabs;
	
	private FragmentManager mFManager;
	private int mCurrentIndex = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		mFManager = getSupportFragmentManager();
		
		mIndex = (SelectLinearLayout) findViewById(R.id.index);
		mShaiXuan = (SelectLinearLayout) findViewById(R.id.shaixuan);
		mMyInfo = (SelectLinearLayout) findViewById(R.id.myinfo);

//		mPagerContainer = (BarViewPager) findViewById(R.id.index_pager);
		mPagerContainer = (LinearLayout) findViewById(R.id.index_pager);
		
		mFragments = new Fragment[]{new IndexFragment(), new ShaiXuanFragment(), new MyInfoFragment()};
		mtabs = new SelectLinearLayout[]{mIndex, mShaiXuan, mMyInfo};
		
		mIndex.setOnClickListener(this);
		mShaiXuan.setOnClickListener(this);
		mMyInfo.setOnClickListener(this);
		
		changeTab(0);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.index:
			changeTab(0);
			break;
		case R.id.shaixuan:
			changeTab(1);
			break;
		case R.id.myinfo:
			changeTab(2);
			break;
		default:
			break;
		}

	}
	
	private void changeTab(int to) {
		if (to != mCurrentIndex) {
			FragmentTransaction transaction = mFManager.beginTransaction();
		if (!mFragments[to].isAdded()) {
				transaction.add(R.id.index_pager, mFragments[to]);
			}
			transaction.show(mFragments[to]);
			if (mCurrentIndex != -1) {
				transaction.hide(mFragments[mCurrentIndex]);
				mtabs[mCurrentIndex].setSelected(false);
			}
			mtabs[to].setSelected(true);
			mCurrentIndex = to;
			transaction.commit();
		}
	}

}

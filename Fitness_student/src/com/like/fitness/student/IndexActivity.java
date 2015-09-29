package com.like.fitness.student;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.like.customview.SelectLinearLayout;
import com.like.fragments.BaseFragment;
import com.like.fragments.IndexFragment;
import com.like.fragments.MyInfoFragment;
import com.like.fragments.ShaiXuanFragment;
import com.like.listener.SearchListener;

public class IndexActivity extends BaseActivity implements OnClickListener, SearchListener {

	private SelectLinearLayout mIndex;
	private SelectLinearLayout mShaiXuan;
	private SelectLinearLayout mMyInfo;

	// private BarViewPager mPagerContainer;
	private LinearLayout mPagerContainer;
	
	private LinearLayout mSearchContainer;

	private BaseFragment[] mFragments;
	private SelectLinearLayout[] mtabs;

	private FragmentManager mFManager;
	private int mCurrentIndex = -1;
	private EditText mTxtSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		mFManager = getSupportFragmentManager();

		mIndex = (SelectLinearLayout) findViewById(R.id.index);
		mShaiXuan = (SelectLinearLayout) findViewById(R.id.shaixuan);
		mMyInfo = (SelectLinearLayout) findViewById(R.id.myinfo);
		mTxtSearch = (EditText) findViewById(R.id.txt_search_content);
		mSearchContainer = (LinearLayout) findViewById(R.id.search_container);

		// mPagerContainer = (BarViewPager) findViewById(R.id.index_pager);
		mPagerContainer = (LinearLayout) findViewById(R.id.index_pager);

		mFragments = new BaseFragment[] { new IndexFragment(),
				new ShaiXuanFragment(), new MyInfoFragment() };
		mtabs = new SelectLinearLayout[] { mIndex, mShaiXuan, mMyInfo };

		mIndex.setOnClickListener(this);
		mShaiXuan.setOnClickListener(this);
		mMyInfo.setOnClickListener(this);

		mTxtSearch
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						InputMethodManager imm = (InputMethodManager) mContext
								.getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
						return false;
					}
				});
		mTxtSearch.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String searchText = s.toString();
				mFragments[mCurrentIndex].updateData(searchText);
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		changeTab(0);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.index:
			changeTab(0);
			mSearchContainer.setVisibility(View.VISIBLE);
			break;
		case R.id.shaixuan:
			changeTab(1);
			mSearchContainer.setVisibility(View.VISIBLE);
			break;
		case R.id.myinfo:
			changeTab(2);
			mSearchContainer.setVisibility(View.GONE);
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
	

    public void toMap(View v) {
        Intent intent = new Intent(mContext, MapActivity.class);
        startActivity(intent);
    }

	@Override
	public String getSearchKey() {
		return mTxtSearch.getText().toString();
	}


}

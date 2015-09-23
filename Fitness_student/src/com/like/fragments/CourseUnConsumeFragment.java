package com.like.fragments;

import com.like.fitness.student.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CourseUnConsumeFragment extends BaseFragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 super.onCreateView(inflater, container, savedInstanceState);
		 View view = inflater.inflate(R.layout.fragment_unconsume, container, false);
		 return view;
	}
	
}

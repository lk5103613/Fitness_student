package com.like.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.like.fitness.student.R;

public class CoachCourseFragment extends Fragment {

    public CoachCourseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("---------------------------------- on create view");
        return inflater.inflate(R.layout.fragment_coach_course, container, false);
    }


}

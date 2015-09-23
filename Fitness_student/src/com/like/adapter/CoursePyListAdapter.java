package com.like.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;

import com.like.fitness.student.Comment;
import com.like.fitness.student.R;

public class CoursePyListAdapter extends SimpleAdapter<Comment>{

	public CoursePyListAdapter(Context context, List<Comment> datas) {
		super(context, datas);
	}

	@Override
	public int getItemResourceId() {
		return R.layout.course_pingyu_item;
	}

	@Override
	public void bindData(int position, View convertView,ViewHolder holder) {
		
	}

}

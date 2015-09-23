package com.like.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.like.entity.Course;
import com.like.fitness.student.R;
import com.like.network.APIS;
import com.like.network.MyNetworkUtil;

public class CourseListAdapter extends BaseAdapter {
	
	private List<Course> mCourses;
	private LayoutInflater mInflater;
	private ImageLoader mImgLoader;
	
	public CourseListAdapter(Context context, List<Course> courses) {
		mInflater = LayoutInflater.from(context);
		this.mCourses = courses;
		mImgLoader = MyNetworkUtil.getInstance(context).getImageLoader();
	}

	@Override
	public int getCount() {
		return mCourses.size();
	}

	@Override
	public Object getItem(int position) {
		return mCourses.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Course course = (Course) getItem(position);
		ViewHolder vh = null;
		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.item_nopay, parent, false);
			vh = new ViewHolder(convertView);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		vh.sUserName.setText(course.username);
		vh.sCourseName.setText(course.courseName);
		vh.sTime.setText(course.addtime2);
		vh.sCourseCnt.setText(course.courseDuration + "/课时");
		mImgLoader.get(APIS.BASE_URL + course.avatar, 
				ImageLoader.getImageListener(vh.sUserIcon, R.color.white, R.color.white));
		if(course.gender == Course.MALE) {
			vh.sGender.setImageResource(R.drawable.male);
		} else {
			vh.sGender.setImageResource(R.drawable.female);
		}
		return convertView;
	}
	
	public void update(List<Course> courses) {
		this.mCourses = courses;
		notifyDataSetChanged();
	}
	
	public class ViewHolder {
		public ImageView sUserIcon;
		public TextView sUserName;
		public TextView sCourseName;
		public TextView sCourseCnt;
		public TextView sTime;
		public Button sBtnPay;
		public ImageView sGender;
		
		public ViewHolder(View convertView) {
			this.sUserIcon = (ImageView) convertView.findViewById(R.id.user_icon);
			this.sUserName = (TextView) convertView.findViewById(R.id.username);
			this.sCourseName = (TextView) convertView.findViewById(R.id.course_name);
			this.sCourseCnt = (TextView) convertView.findViewById(R.id.course_cnt);
			this.sTime = (TextView) convertView.findViewById(R.id.time);
			this.sGender = (ImageView) convertView.findViewById(R.id.gender_icon);
		}
	}

}

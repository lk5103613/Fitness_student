package com.like.adapter;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.like.entity.CourseDetail.Comment;
import com.like.fitness.student.R;
import com.like.network.APIS;
import com.like.network.MyNetworkUtil;

public class CoursePyListAdapter extends BaseAdapter{
	
	private List<Comment> mComments;
	private LayoutInflater mInflater;
	private ImageLoader mImgLoader;
	
	public CoursePyListAdapter(Context context, List<Comment> comments) {
		mInflater = LayoutInflater.from(context);
		this.mComments = comments;
		mImgLoader = MyNetworkUtil.getInstance(context).getImageLoader();
	}

	@Override
	public int getCount() {
		return mComments.size();
	}

	@Override
	public Object getItem(int position) {
		return mComments.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Comment comment = (Comment) getItem(position);
		ViewHolder vh = null;
		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.course_pingyu_item, parent, false);
			vh = new ViewHolder(convertView);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		vh.sName.setText(comment.nickname);
		vh.sDes.setText(comment.content);
		mImgLoader.get(APIS.BASE_URL + comment.avatar, ImageLoader.getImageListener(vh.sIcon, R.color.white, R.color.white));
		if(TextUtils.isEmpty(comment.img0)) {
			vh.sImg1.setVisibility(View.GONE);
		} else {
			vh.sImg1.setVisibility(View.VISIBLE);
			mImgLoader.get(APIS.BASE_URL + comment.img0, ImageLoader.getImageListener(vh.sImg1, R.color.white, R.color.white));
		}
		if(TextUtils.isEmpty(comment.img1)) {
			vh.sImg2.setVisibility(View.GONE);
		} else {
			vh.sImg2.setVisibility(View.VISIBLE);
			mImgLoader.get(APIS.BASE_URL + comment.img1, ImageLoader.getImageListener(vh.sImg2, R.color.white, R.color.white));
		}
		if(TextUtils.isEmpty(comment.img2)) {
			vh.sImg3.setVisibility(View.GONE);
		} else {
			vh.sImg3.setVisibility(View.VISIBLE);
			mImgLoader.get(APIS.BASE_URL + comment.img2, ImageLoader.getImageListener(vh.sImg3, R.color.white, R.color.white));
		}
		if(TextUtils.isEmpty(comment.img3)) {
			vh.sImg4.setVisibility(View.GONE);
		} else {
			vh.sImg4.setVisibility(View.VISIBLE);
			mImgLoader.get(APIS.BASE_URL + comment.img3, ImageLoader.getImageListener(vh.sImg4, R.color.white, R.color.white));
		}
		return convertView;
	}
	
	public class ViewHolder {
		public ImageView sIcon;
		public TextView sName;
		public TextView sTime;
		public TextView sDes;
		public ImageView sImg1;
		public ImageView sImg2;
		public ImageView sImg3;
		public ImageView sImg4;
		
		public ViewHolder(View v) {
			sIcon = (ImageView) v.findViewById(R.id.avatar);
			sName = (TextView) v.findViewById(R.id.name);
			sTime = (TextView) v.findViewById(R.id.time);
			sDes = (TextView) v.findViewById(R.id.des);
			sImg1 = (ImageView) v.findViewById(R.id.img1);
			sImg2= (ImageView) v.findViewById(R.id.img2);
			sImg3 = (ImageView) v.findViewById(R.id.img3);
			sImg4 = (ImageView) v.findViewById(R.id.img4);
		}
	}

}

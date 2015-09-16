package com.like.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.like.entity.Photo;
import com.like.fitness.student.R;
import com.like.network.APIS;

public class PhotoGridAdapter extends SimpleAdapter<Photo> {
	private List<Photo> mImgList;
	private Context mContext;
	public static final int APP_PAGE_SIZE = 9;
	private ImageLoader mImageLoader;
	
	public PhotoGridAdapter(Context context, List<Photo> list, int page , ImageLoader mImageLoader) {
		super(context, list);
		mImgList = new ArrayList<Photo>();
		this.mImageLoader = mImageLoader;
		
		int i = page * APP_PAGE_SIZE;
		int iEnd = i+APP_PAGE_SIZE;
		while ((i<list.size()) && (i<iEnd)) {
			mImgList.add(list.get(i));
			i++;
		}
	}

	public int getCount() {
		return mImgList.size();
	}

	public Photo getItem(int position) {
		return mImgList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemResourceId() {
		return R.layout.photo_item;
	}
	@Override
	public void bindData(int position, View convertView,ViewHolder holder) {
		ImageView image = holder.findView(R.id.image);
		mImageLoader.get(APIS.BASE_URL +getItem(position).path, ImageLoader.getImageListener(image, R.color.white, R.color.white));
		
	}
}

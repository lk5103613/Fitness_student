package com.like.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewDebug.FlagToString;
import android.widget.TextView;

import com.like.entity.CollectItem;
import com.like.fitness.student.R;
import com.like.utils.DateUtil;

public class CollectListAdapter extends SimpleAdapter<CollectItem> {

	public CollectListAdapter(Context context, List<CollectItem> datas) {
		super(context, datas);
	}

	@Override
	public int getItemResourceId() {
		return R.layout.collect_item;
	}

	@Override
	public void bindData(int position, View convertView,ViewHolder holder) {
		CollectItem item = getItem(position);
		
		TextView name = holder.findView(R.id.name);
		TextView workYear = holder.findView(R.id.works_year);
		TextView courseName = holder.findView(R.id.course_name);
		TextView tag = holder.findView(R.id.tag);
		TextView fee = holder.findView(R.id.fee);
		TextView addTime = holder.findView(R.id.add_time);
		
		name.setText(item.truename);
		workYear.setText(item.workYears+"年教龄");
		courseName.setText(item.courseName);
		tag.setText(item.courseDescription);
		fee.setText("￥"+Float.parseFloat(item.price)/Float.parseFloat(item.cnt));
		addTime.setText(DateUtil.getDif(item.addTime)+"天前收藏");
		
	}
	
}

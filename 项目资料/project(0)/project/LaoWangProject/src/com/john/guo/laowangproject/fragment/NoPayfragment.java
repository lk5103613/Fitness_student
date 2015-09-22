package com.john.guo.laowangproject.fragment;

import java.util.ArrayList;

import com.john.guo.laowangproject.R;
import com.john.guo.laowangproject.adapter.NoPayAdapter;
import com.john.guo.laowangproject.bean.NoPayBean;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class NoPayfragment extends Fragment {
	
	private View noPayView;
	private Context context;
	private ListView listview1;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.context = activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		noPayView = View.inflate(context, R.layout.fragment_nopay, null);
		listview1 = (ListView) noPayView.findViewById(R.id.listview1);
		listview1.setAdapter(new NoPayAdapter(context, initDataSource()));
		return noPayView;
	}
	
	private ArrayList<NoPayBean> initDataSource(){
		ArrayList<NoPayBean> data = new ArrayList<NoPayBean>();
		data.add(new NoPayBean("李雪梅", "female", "2015.08.09", "拉丁舞", "5", "288/课时"));
		data.add(new NoPayBean("李雪梅", "male", "2015.09.10", "芭蕾舞", "3", "288/课时"));
		data.add(new NoPayBean("李雪梅", "female", "2015.08.09", "拉丁舞", "5", "288/课时"));
		data.add(new NoPayBean("李雪梅", "male", "2015.09.10", "芭蕾舞", "3", "288/课时"));
		data.add(new NoPayBean("李雪梅", "female", "2015.08.09", "拉丁舞", "5", "288/课时"));
		data.add(new NoPayBean("李雪梅", "male", "2015.09.10", "芭蕾舞", "3", "288/课时"));
		data.add(new NoPayBean("李雪梅", "female", "2015.08.09", "拉丁舞", "5", "288/课时"));
		data.add(new NoPayBean("李雪梅", "male", "2015.09.10", "芭蕾舞", "3", "288/课时"));
		data.add(new NoPayBean("李雪梅", "female", "2015.08.09", "拉丁舞", "5", "288/课时"));
		data.add(new NoPayBean("李雪梅", "male", "2015.09.10", "芭蕾舞", "3", "288/课时"));
		data.add(new NoPayBean("李雪梅", "female", "2015.08.09", "拉丁舞", "5", "288/课时"));
		data.add(new NoPayBean("李雪梅", "male", "2015.09.10", "芭蕾舞", "3", "288/课时"));
		data.add(new NoPayBean("李雪梅", "female", "2015.08.09", "拉丁舞", "5", "288/课时"));
		data.add(new NoPayBean("李雪梅", "male", "2015.09.10", "芭蕾舞", "3", "288/课时"));
		data.add(new NoPayBean("李雪梅", "female", "2015.08.09", "拉丁舞", "5", "288/课时"));
		data.add(new NoPayBean("李雪梅", "male", "2015.09.10", "芭蕾舞", "3", "288/课时"));
		return data;
	}
	
}

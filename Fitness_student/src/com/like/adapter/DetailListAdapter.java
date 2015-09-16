package com.like.adapter;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.like.entity.BaseEntity;
import com.like.entity.CoachInfo;
import com.like.entity.KeChengEntity;
import com.like.entity.PingYuEntity;
import com.like.fitness.student.R;
import com.like.network.APIS;

public class DetailListAdapter extends ArrayAdapter<BaseEntity> {

	private List<BaseEntity> mEntity;
	private LayoutInflater mInflater;
	private Context mContext;
	private ImageLoader mLoader;
	private int prevType = -1;

	public DetailListAdapter(Context context, List<BaseEntity> entity,
			ImageLoader loader) {
		super(context, 0);
		this.mEntity = entity;
		this.mContext = context;
		this.mLoader = loader;
		mInflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		return mEntity.size();
	}

	@Override
	public int getViewTypeCount() {
		return 6;
	}

	@Override
	public int getItemViewType(int position) {
		return mEntity.get(position).type;
	}

	@Override
	public BaseEntity getItem(int position) {
		return mEntity.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		BaseEntity baseEntity = getItem(position);

		int type = getItemViewType(position);
		CoachInfoHolder headerHolder = null;
		KeChengHolder keChengHolder = null;
		ZiDingYiHolder ziDingYiHolder = null;
		PingJiaHolder pingJiaHolder = null;
		PingYuHolder pingYuHolder = null;

		if (convertView == null) {
			switch (type) {
			case BaseEntity.TYPE_HEADER:
				convertView = mInflater.inflate(R.layout.coach_header_item,
						parent, false);
				headerHolder = new CoachInfoHolder(convertView);
				convertView.setTag(headerHolder);
				break;
			case BaseEntity.TYPE_KECHENG:

				convertView = mInflater.inflate(R.layout.coach_kecheng_item,
						parent, false);
				keChengHolder = new KeChengHolder(convertView);
				convertView.setTag(keChengHolder);
				break;
			case BaseEntity.TYPE_ZIDINGYI:
				convertView = mInflater.inflate(R.layout.coach_zidingyi_item,
						parent, false);
				ziDingYiHolder = new ZiDingYiHolder(convertView);
				convertView.setTag(ziDingYiHolder);
				break;
			case BaseEntity.TYPE_PINGJIA:
				convertView = mInflater.inflate(R.layout.coach_pingjia_item,
						parent, false);
				pingJiaHolder = new PingJiaHolder(convertView);
				convertView.setTag(pingJiaHolder);
				break;
			case BaseEntity.TYPE_PINGYU:
				convertView = mInflater.inflate(R.layout.coach_pingyu_item,
						parent, false);
				pingYuHolder = new PingYuHolder(convertView);
				convertView.setTag(pingYuHolder);
				break;

			default:
				break;
			}
		} else {
			switch (type) {
			case BaseEntity.TYPE_HEADER:
				headerHolder = (CoachInfoHolder) convertView.getTag();
				break;
			case BaseEntity.TYPE_KECHENG:
				keChengHolder = (KeChengHolder) convertView.getTag();
				break;
			case BaseEntity.TYPE_ZIDINGYI:
				ziDingYiHolder = (ZiDingYiHolder) convertView.getTag();
				break;
			case BaseEntity.TYPE_PINGJIA:
				pingJiaHolder = (PingJiaHolder) convertView.getTag();
				break;
			case BaseEntity.TYPE_PINGYU:
				pingYuHolder = (PingYuHolder) convertView.getTag();
				break;

			default:
				break;
			}
		}
		switch (type) {
		case BaseEntity.TYPE_HEADER:
			headerHolder.mTxtCoachName
					.setText(((CoachInfo) baseEntity).truename);
			System.out.println("skill " + ((CoachInfo) baseEntity).skill);
			String skill = ((CoachInfo) baseEntity).skill;
			if (!TextUtils.isEmpty(skill)) {
				String[] skils = ((CoachInfo) baseEntity).skill.split(",");

				for (int i = 0; i < headerHolder.mCoachSkill.getChildCount() - 1; i++) {
					if(i > skils.length - 1 || skils[i] == null || skils[i].trim().equals("")) {
						((TextView) headerHolder.mCoachSkill.getChildAt(i + 1))
								.setVisibility(View.GONE);
					} else {
						((TextView) headerHolder.mCoachSkill.getChildAt(i + 1))
								.setText(skils[i]);
					}
				}
			}

			break;
		case BaseEntity.TYPE_KECHENG:
			
			System.out.println("type " + prevType);
			if (prevType == ((KeChengEntity)baseEntity).type) {
				keChengHolder.topLine.setVisibility(View.GONE);
			}  else {
				keChengHolder.topLine.setVisibility(View.VISIBLE);
			}
			
			
			keChengHolder.cnt.setText(((KeChengEntity) baseEntity).cnt + "");
			keChengHolder.des
					.setText(((KeChengEntity) baseEntity).course_description);
			keChengHolder.name
					.setText(((KeChengEntity) baseEntity).course_name);
			keChengHolder.price
					.setText(((KeChengEntity) baseEntity).price + "");
			break;
		case BaseEntity.TYPE_PINGYU:
			mLoader.get(APIS.BASE_URL + ((PingYuEntity) baseEntity).avatar,
					ImageLoader.getImageListener(pingYuHolder.mHeaderImg,
							R.drawable.face_01, R.drawable.face_01));
			pingYuHolder.mNickName
					.setText(((PingYuEntity) baseEntity).nickname);
			pingYuHolder.mContent.setText(((PingYuEntity) baseEntity).content);
			for(int i=0; i<pingYuHolder.mIconContainer.getChildCount(); i++) {
				if(i == 0 && !TextUtils.isEmpty(((PingYuEntity) baseEntity).img0)){
					ImageView imageView = (ImageView) pingYuHolder.mIconContainer.getChildAt(i);
					imageView.setVisibility(View.VISIBLE);
					mLoader.get(APIS.BASE_URL + ((PingYuEntity) baseEntity).img0,
							ImageLoader.getImageListener(imageView, R.color.white, R.color.white));
				}
				if(i == 1 && !TextUtils.isEmpty(((PingYuEntity) baseEntity).img1)){
					ImageView imageView = (ImageView) pingYuHolder.mIconContainer.getChildAt(i);
					imageView.setVisibility(View.VISIBLE);
					mLoader.get(APIS.BASE_URL + ((PingYuEntity) baseEntity).img1,
							ImageLoader.getImageListener(imageView, R.color.white, R.color.white));
				}
				if(i == 2 && !TextUtils.isEmpty(((PingYuEntity) baseEntity).img2)){
					ImageView imageView = (ImageView) pingYuHolder.mIconContainer.getChildAt(i);
					imageView.setVisibility(View.VISIBLE);
					mLoader.get(APIS.BASE_URL + ((PingYuEntity) baseEntity).img2,
							ImageLoader.getImageListener(imageView, R.color.white, R.color.white));
				}
				if(i == 3 && !TextUtils.isEmpty(((PingYuEntity) baseEntity).img3)){
					ImageView imageView = (ImageView) pingYuHolder.mIconContainer.getChildAt(i);
					imageView.setVisibility(View.VISIBLE);
					mLoader.get(APIS.BASE_URL + ((PingYuEntity) baseEntity).img3,
							ImageLoader.getImageListener(imageView, R.color.white, R.color.white));
				}
				if(i == 4 && !TextUtils.isEmpty(((PingYuEntity) baseEntity).img4)){
					ImageView imageView = (ImageView) pingYuHolder.mIconContainer.getChildAt(i);
					imageView.setVisibility(View.VISIBLE);
					mLoader.get(APIS.BASE_URL + ((PingYuEntity) baseEntity).img4,
							ImageLoader.getImageListener(imageView, R.color.white, R.color.white));
				}
			}
			break;
		}
		
		prevType = baseEntity.type;
		return convertView;
	}

	class CoachInfoHolder {

		public TextView mTxtCoachName;
		public TextView mPlace;
		public LinearLayout mCoachSkill;

		public CoachInfoHolder(View convertView) {
			mTxtCoachName = (TextView) convertView
					.findViewById(R.id.coach_name);
			mPlace = (TextView) convertView.findViewById(R.id.place);
			mCoachSkill = (LinearLayout) convertView
					.findViewById(R.id.coach_skill);
		}

	}

	class KeChengHolder {
		public TextView cnt;
		public TextView des;
		public TextView name;
		public TextView price;
		public View topLine;

		public KeChengHolder(View convertView) {
			cnt = (TextView) convertView.findViewById(R.id.cnt);
			des = (TextView) convertView.findViewById(R.id.des);
			name = (TextView) convertView.findViewById(R.id.name);
			price = (TextView) convertView.findViewById(R.id.price);
			topLine = (View) convertView.findViewById(R.id.top_line);
		}

	}

	class PingJiaHolder {
		public PingJiaHolder(View convertView) {
		}
	}

	class PingYuHolder {
		public ImageView mHeaderImg;
		public TextView mNickName;
		public TextView mContent;
		public ViewGroup mIconContainer;
		public ImageView mIcon0;
		public ImageView mIcon1;
		public ImageView mIcon2;
		public ImageView mIcon3;
		public ImageView mIcon4;

		public PingYuHolder(View convertView) {
			mHeaderImg = (ImageView) convertView.findViewById(R.id.header_icon);
			mNickName = (TextView) convertView.findViewById(R.id.nick_name);
			mContent = (TextView) convertView.findViewById(R.id.content);
			mIconContainer = (ViewGroup) convertView.findViewById(R.id.icon_container);
			mIcon0 = (ImageView) convertView.findViewById(R.id.icon_0);
			mIcon1 = (ImageView) convertView.findViewById(R.id.icon_1);
			mIcon2 = (ImageView) convertView.findViewById(R.id.icon_2);
			mIcon3 = (ImageView) convertView.findViewById(R.id.icon_3);
			mIcon4 = (ImageView) convertView.findViewById(R.id.icon_4);
		}
	}

	class ZiDingYiHolder {
		public ZiDingYiHolder(View convertView) {
		}
	}

}

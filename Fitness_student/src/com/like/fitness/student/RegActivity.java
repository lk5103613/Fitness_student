package com.like.fitness.student;

import java.io.File;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response.Listener;
import com.like.customview.RoundImageView;
import com.like.network.APIS;
import com.like.utils.BitmapUtil;
import com.like.utils.DeviceUtil;
import com.like.utils.UploadUtil;
import com.like.utils.ValidateCodeUtil;
import com.like.utils.ValidateUtil;

public class RegActivity extends BaseActivity {
	private final static int REQUEST_TAKE_PHOTO = 0;
	private final static int REQUEST_FROM_FILE = 1;
	
	private final static int COUNT_DOWN_NUM = 10;
	
	private String mAvatar;
	private String mValidateCode;
	
	private EditText mTxtNickName;
	private EditText mTxtPhoneNum;
	private EditText mTxtValidateCode;
	private EditText mTxtPwd;
	private RoundImageView mHeaderIcon;
	private Button mBtnSend;
	private Dialog mDialog;
	private Handler mHandler;
	
	private int mCurrentNum = COUNT_DOWN_NUM;
	private boolean mIsSend = false;
	private String mSendPhone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		initView();
		
		mValidateCode = ValidateCodeUtil.getValidateCode();
		mHandler = new Handler(new Handler.Callback() {
			@Override
			public boolean handleMessage(Message msg) {
				mCurrentNum--;
				if(mCurrentNum == 0) {
					mBtnSend.setEnabled(true);
					mBtnSend.setText("获取验证码");
				} else if(mCurrentNum > 0) {
					mBtnSend.setText(mCurrentNum + "秒后重发");
				}
				return false;
			}
		});
	}
	
	private void initView() {
		mTxtNickName = (EditText) findViewById(R.id.txt_nick_name);
		mTxtPhoneNum = (EditText) findViewById(R.id.txt_phone);
		mTxtValidateCode = (EditText) findViewById(R.id.validate_code);
		mTxtPwd = (EditText) findViewById(R.id.txt_pwd);
		mHeaderIcon = (RoundImageView) findViewById(R.id.header_icon);
		mBtnSend = (Button) findViewById(R.id.btn_send);
		mTxtPhoneNum.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!mIsSend)
					return;
				String phone = ((EditText)v).getText().toString();
				if(!hasFocus) {
					if(!TextUtils.equals(mSendPhone, phone)) {
						mCurrentNum = -1;
						mBtnSend.setEnabled(true);
						mBtnSend.setText("获取验证码");
					}
				}
			}
		});
	}
	
	private void showDialog() {
		if(mDialog == null) {
			mDialog = new Dialog(mContext, R.style.Theme_dialog);
			View view = LayoutInflater.from(mContext).inflate(R.layout.choice_photo_dialog, null);
			Button btnCamera = (Button)view.findViewById(R.id.take_from_camert);
			btnCamera.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mDialog.dismiss();
					Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
					startActivityForResult(intent, REQUEST_TAKE_PHOTO);
				}
			});
			Button btnGalley = (Button) view.findViewById(R.id.take_from_file);
			btnGalley.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mDialog.dismiss();
					Intent intent = new Intent(Intent.ACTION_PICK);
					intent.setType("image/*");
					startActivityForResult(intent, REQUEST_FROM_FILE);
				}
			});
			mDialog.setContentView(view);
		}
		mDialog.show();
	}
	
	public void takePhoto(View v) {
		showDialog();
	}
	
	public void sendCode(View v) {
		final String phoneNum = mTxtPhoneNum.getText().toString();
		if(TextUtils.isEmpty(phoneNum)) {
			Toast.makeText(mContext, "请输入电话", Toast.LENGTH_SHORT).show();
			return;
		}
		if(!ValidateUtil.isMobileNO(phoneNum)) {
			Toast.makeText(mContext, "手机号码格式错误", Toast.LENGTH_SHORT).show();
			return;
		}
		mValidateCode = ValidateCodeUtil.getValidateCode();
		mDataFetcher.fetchSendCode(phoneNum, mValidateCode, new Listener<String>() {
			@Override
			public void onResponse(String response) {
				Toast.makeText(mContext, "发送成功", Toast.LENGTH_SHORT).show();
				mCurrentNum = COUNT_DOWN_NUM;
				mBtnSend.setEnabled(false);
				mBtnSend.setText(mCurrentNum + "秒后重发");
				mIsSend = true;
				mSendPhone = phoneNum;
				new Thread(new Runnable() {
					@Override
					public void run() {
						while (mCurrentNum >= 0) {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							mHandler.sendEmptyMessage(1);
						}
					}
				}).start();
			}
		}, mErrorListener);
	}
	
	public void reg(View v){
		String nickName = mTxtNickName.getText().toString();
		String phoneNum = mTxtPhoneNum.getText().toString();
		String validateCode = mTxtValidateCode.getText().toString();
		String pwd = mTxtPwd.getText().toString();
		if(TextUtils.isEmpty(nickName) || TextUtils.isEmpty(phoneNum) || TextUtils.isEmpty(validateCode) || TextUtils.isEmpty(pwd)) {
			Toast.makeText(mContext, "请填写完整信息", Toast.LENGTH_SHORT).show();
			return;
		}
		if(TextUtils.isEmpty(mAvatar)) {
			Toast.makeText(mContext, "请上传头像", Toast.LENGTH_SHORT).show();
			return;
		}
		if(!ValidateUtil.isMobileNO(phoneNum)) {
			Toast.makeText(mContext, "手机号码格式错误", Toast.LENGTH_SHORT).show();
			return;
		}
		if(!mValidateCode.equals(validateCode)) {
			Toast.makeText(mContext, "验证码错误", Toast.LENGTH_SHORT).show();
			return;
		}
		String imei = DeviceUtil.getIMEI(mContext);
		mDataFetcher.fetchReg(nickName, phoneNum, pwd, imei, mAvatar, new Listener<String>() {
			@Override
			public void onResponse(String response) {
			}
		}, mErrorListener);
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK) {
			Uri uri = BitmapUtil.getUriByData(data, getContentResolver());
			File file = BitmapUtil.getFileByUri(getContentResolver(), uri);
			Bitmap bmp = BitmapUtil.getResizeBitmap(file);
			mHeaderIcon.setImageBitmap(bmp);
			new AsyncTask<File, Void, String>() {
				@Override
				protected String doInBackground(File... params) {
					File uploadFile = params[0];
					final String serverImgName = UploadUtil.getImgName();
					try {
						UploadUtil.post(uploadFile, APIS.UPLOAD,
								serverImgName);
						mAvatar = "/upload/" + serverImgName + UploadUtil.getExtensionName(uploadFile.getAbsolutePath());
					} catch(Exception e) {
						e.printStackTrace();
					}
					return null;
				}
			}.execute(file);
		}
	}
	
}

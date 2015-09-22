package com.honestwalker.androidutils.views;

import java.util.HashMap;
import java.util.Map;

import android.R.integer;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.honestwalker.androidutils.R;
import com.honestwalker.androidutils.IO.LogCat;
import com.honestwalker.androidutils.equipment.DisplayUtil;
import com.honestwalker.androidutils.os.BundleObject;

/**
 * Loading窗口   ，  注意registerDialog不能在线程中直接调用，必须使用Handler<br />
 * 在Handler中调用LoadingHelper.registerDialog(...)<br />
 * @author Administrator
 *
 */
public class LoadingHelper {


	
	private static Map<String,DialogStyle> dialogStyleMap = new HashMap<String, DialogStyle>();
	private static Map<Context,Dialog> dialogMap = new HashMap<Context, Dialog>();
	
	public static DialogStyle registerDialog(String dialogName,int layoutId,int styleId,int textResourceId,int width,int height,String text) {
		LogCat.d("LoadingHelper","注册" + dialogName + "对话框");
		DialogStyle dialogStyle = new DialogStyle(dialogName, layoutId, styleId, textResourceId, width, height, text);
		dialogStyleMap.put(dialogName, dialogStyle);
		return dialogStyle;
	}
	public static DialogStyle registerDialog(String dialogName,View contentView,int width,int height,boolean cancelable) {
		LogCat.d("LoadingHelper","注册" + dialogName + "对话框");
		DialogStyle dialogStyle = new DialogStyle(dialogName, contentView, R.style.input_dialog_style, 
				width, height,cancelable);
		dialogStyleMap.put(dialogName, dialogStyle);
		return dialogStyle;
	}
	public static boolean containsDialog(String dialogName) {
		return dialogStyleMap.containsKey(dialogName);
	}
	
	public static void show(Context context,String dialogName) {
		show(context,dialogName,null);
	}
	
	public static void show(Context context,String dialogName,final Handler onBackPressHandler) {
		DialogStyle style = dialogStyleMap.get(dialogName);
		BundleObject data = new BundleObject();
		data.put("style", style);
		data.put("context", context);
		data.put("onBackPressHandler", onBackPressHandler);
		Message msg = new Message();
		msg.what=1;
		msg.obj = data;
		showDialogHandler.sendMessage(msg);
	}
	
	public static void dismiss(Context context){
		Message msg = new Message();
		msg.what = 0;
		msg.obj = context;
		showDialogHandler.sendMessage(msg);
	}
	
	private static Handler showDialogHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Dialog loadingDialog;
			if(msg.what == 1) {
				BundleObject data = (BundleObject) msg.obj;
				DialogStyle style = (DialogStyle) data.get("style");
				Context context = (Context) data.get("context");
				if(context==null || style==null){
					return;
				}
				loadingDialog = new Dialog(context,style.getStyleId());
				final Handler onBackPressHandler = (Handler) data.get("onBackPressHandler");
				if(style != null) {
					LayoutInflater factory = LayoutInflater.from(context);
					View view = null;
					if (style.getContentView() != null) {
						view = style.getContentView();
					}else {
						view = factory.inflate(style.getLayoutId(), null);
					}
					LayoutParams lp = new LayoutParams(style.getWidth(),style.getHeight());
					TextView messageTV = (TextView) view.findViewById(style.getTextResourceId());
					if(messageTV != null) {
						messageTV.setText(style.getText());
					}
					loadingDialog.setCancelable(false);
					if (style.isCancelable()) {
						loadingDialog.setCancelable(true);
					}
					loadingDialog.getWindow().setContentView(view,lp);
					loadingDialog.setOnKeyListener(new OnKeyListener() {
						private Boolean clicked = false;
						@Override
						public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
							LogCat.d("LoadingHelper", "loading key = " + keyCode);
							if(keyCode == KeyEvent.KEYCODE_BACK) {
								LogCat.d("LoadingHelper", "loading key = " + keyCode + " click =" + clicked);
								if(!clicked) {
									clicked = true;
									if(onBackPressHandler != null) {
										onBackPressHandler.sendEmptyMessage(0);
									}
								}
							}
							return false;
						}
					});
					try {
						loadingDialog.show();
						dialogMap.put(context, loadingDialog);
					} catch (Exception e) {
					}
				}
			} else if(msg.what == 0) {
				if(msg.obj != null) {
					LogCat.d("LoadingHelper",(Context)msg.obj);
					Dialog dialog = dialogMap.get((Context)msg.obj);
					if(dialog != null) {
						try {
							dialog.dismiss();
						} catch (Exception e) {
						}
					}
				}
			}
		};
	};
	
	/**
	 * Dialog风格
	 * @author Administrator
	 *
	 */
	private static class DialogStyle {
		private String dialogName;
		private int layoutId;
		private View contentView;
		private int styleId;
		private int textResourceId;
		private int width;
		private int height;
		private String text;
		private boolean cancelable;
		public DialogStyle(){}
		public DialogStyle(String dialogName,int layoutId,int styleId,int textResourceId,int width,int height,String text) {
			this.dialogName = dialogName;
			this.layoutId   = layoutId;
			this.styleId    = styleId;
			this.textResourceId	= textResourceId;
			this.width		= width;
			this.height 	= height;
			this.text 		= text;
		}
		public DialogStyle(String dialogName,View contentView,int styleId,int width,int height,boolean cancelable){
			this.dialogName = dialogName;
			this.contentView = contentView;
			this.styleId    = styleId;
			this.width		= width;
			this.height 	= height;
			this.cancelable = cancelable;
		}
		public boolean isCancelable() {
			return cancelable;
		}
		public void setCancelable(boolean cancelable) {
			this.cancelable = cancelable;
		}
		public String getDialogName() {
			return dialogName;
		}
		public void setDialogName(String dialogName) {
			this.dialogName = dialogName;
		}
		public View getContentView() {
			return contentView;
		}
		public void setContentView(View contentView) {
			this.contentView = contentView;
		}
		public int getLayoutId() {
			return layoutId;
		}
		public void setLayoutId(int layoutId) {
			this.layoutId = layoutId;
		}
		public int getStyleId() {
			return styleId;
		}
		public void setStyleId(int styleId) {
			this.styleId = styleId;
		}
		public int getTextResourceId() {
			return textResourceId;
		}
		public void setTextResourceId(int textResourceId) {
			this.textResourceId = textResourceId;
		}
		public int getWidth() {
			return width;
		}
		public void setWidth(int width) {
			this.width = width;
		}
		public int getHeight() {
			return height;
		}
		public void setHeight(int height) {
			this.height = height;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
	}
	

}

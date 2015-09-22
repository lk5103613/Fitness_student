package com.honestwalker.androidutils.views;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.honestwalker.androidutils.ImageUtil;
import com.honestwalker.androidutils.UIHandler;
import com.honestwalker.androidutils.IO.LogCat;
import com.honestwalker.androidutils.ViewUtils.ViewSizeHelper;
import com.honestwalker.androidutils.exception.ExceptionUtil;
import com.honestwalker.androidutils.pool.ThreadPool;
import com.orm.androrm.DatabaseAdapter;
import com.orm.androrm.Filter;
import com.orm.androrm.Model;

/**
 * 支持异步的ImageView 最好是用于需要异步的列表，否则static的imageCache无法及时释放 <br />
 * 初始化时设置高宽度才能时progressbar居中
 * @author honestwalker
 *
 */
public class AsyncImageView extends RelativeLayout {
	
	private String TAG = "AsyncImageView";
	
	private static ExecutorService threadPool = Executors.newFixedThreadPool(20);
	
	public static Map<String,SoftReference<Bitmap>> imageCache = new HashMap<String, SoftReference<Bitmap>>();
	
	private String imageUrl = null;
	
	private AsyncLoadListener asyncLoadListener = null;
	
	private boolean isLoaded = false;
	
	/** 是否启用 sqlite 图片缓存 */
	private boolean useDBCache = true;
	
	/** 是否根据指定的width 计算相应的inSampleSize, 开启比较耗时，但可一定程度防止OOM错误 ， 一般图片都较大时才开启 */
	private boolean caleInSampleSize = false;
	
	private Context context;
	
	private ImageView   imageView;
	private ProgressBar progressBar;
	
	private LayoutInflater inflater;
	
	/** 图片超出指定尺寸是否切割图片 */
	private boolean imageCrop = true;
	
	public AsyncImageView(Context context) {
		super(context);
		this.context = context;
//		initDB();
		createView();
	}
	
	public AsyncImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
//		initDB();
		createView();
	}
	
	public AsyncImageView(Context context, AttributeSet attrs , int defStyle) {
		super(context, attrs);
		this.context = context;
//		initDB();
		createView();
	}

	/** 初始化数据库 在application调用 */
	public static void initDB(Context context , int databaseVersion) {
	     List<Class<? extends Model>> models = new ArrayList<Class<? extends Model>>();
	     models.add(ImageCacheModel.class);         // 要建立的表

	    // 建立数据库
	     DatabaseAdapter.setDatabaseName("KANCART_DB");

	     DatabaseAdapter adapter = DatabaseAdapter.getInstance(context , databaseVersion);
	     adapter.setModels(models);   // 开始创建
	}
	
	public ProgressBar getProgressBar() {
		return progressBar;
	}
	
	/** 建立图片和loading */
	private void createView() {
		
		inflater = ((Activity)context).getLayoutInflater();
		
		progressBar = new ProgressBar(context);
		LayoutParams lp = new LayoutParams(20,20);
		lp.addRule(RelativeLayout.CENTER_IN_PARENT);
		progressBar.setLayoutParams(lp);
		this.addView(progressBar);
		
		imageView   = new ImageView(context);
		
		LayoutParams ivlp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		imageView.setLayoutParams(ivlp);
		this.addView(imageView);
		
	}
	
	public void setScaleType(ScaleType scaleType) {
		imageView.setScaleType(scaleType);
	}
	 
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
//		LogCat.d(TAG,  "(getHeight() / 2)=" + (getHeight() / 2) + "   (progressBar.getHeight() / 2)=" + (progressBar.getHeight() / 2));
//		ViewSizeHelper.getInstance(context).setWidth(progressBar, 15);
//		ViewSizeHelper.getInstance(context).setHeight(progressBar, 15);
//		ViewSizeHelper.getInstance(context).marginTop(progressBar, (getHeight() / 2) - (progressBar.getHeight() / 2));
	}
	
	/**
	 * 异步加载图片
	 * @param imageUrl
	 * @param useDBCache       是否使用数据库缓存
	 * @param useMemCache      是否使用内存缓存
	 */
	public void loadUrl(String imageUrl,final int width , boolean useDBCache , boolean useMemCache) {
		
		this.useDBCache = useDBCache;
		if(!useMemCache) {
			imageCache.remove(imageUrl);
		}
		
		loadUrl(imageUrl, width);
		
	}
	
	public void loadUrl(final String imageUrl) {
		this.imageUrl = imageUrl;
		initLoadUrl(imageUrl);
		loadImageFromUrl(imageUrl, new AsyncLoadListener() {
			@Override
			public void onComplete(AsyncImageView view , Bitmap bitmap) {
				if(unableSyncLoadBitmap(imageUrl , view)) return;
				if(bitmap != null) {
					
					
					AsyncImageView.this.imageView.setImageBitmap(bitmap);
					progressBar.setVisibility(GONE);
//					int sw = bitmap.getWidth();
//					int sh = bitmap.getHeight();
//					int height = width * sh / sw;
//					ViewSizeHelper.getInstance(getContext()).setWidth(AsyncImageView.this, (int)(DisplayUtil.getInstance(context).getWidth() * 0.4));
//					ViewSizeHelper.getInstance(getContext()).setHeight(AsyncImageView.this, (int)(DisplayUtil.getInstance(context).getWidth() * 0.4 * 0.5) );
					if(asyncLoadListener != null) {
						asyncLoadListener.onComplete(view , bitmap);
					}
					isLoaded = true;
				} else {
					if(asyncLoadListener != null) {
						asyncLoadListener.onFail(new NullPointerException());
					}
				}
			}
			@Override
			public void onFail(Exception e) {
				if(asyncLoadListener != null) {
					asyncLoadListener.onFail(e);
				}
			}
			@Override
			public void onStart() {
				if(asyncLoadListener != null) {
					asyncLoadListener.onStart();
				}
			}
		});
	
	}
	
	/**
	 * 异步加载图片
	 * @param imageUrl
	 */
	public void loadUrl(final String imageUrl,final int width) {
		this.imageUrl = imageUrl;
		
		initLoadUrl(imageUrl);
		
		loadImageFromUrl(imageUrl, width, new AsyncLoadListener() {
			@Override
			public void onComplete(AsyncImageView view , Bitmap bitmap) {
				if(unableSyncLoadBitmap(imageUrl , view)) return;
				if(bitmap != null) {
					AsyncImageView.this.imageView.setImageBitmap(bitmap);
					progressBar.setVisibility(GONE);
					int sw = bitmap.getWidth();
					int sh = bitmap.getHeight();
					int height = width * sh / sw;
					ViewSizeHelper.getInstance(getContext()).setWidth(AsyncImageView.this, width);
					ViewSizeHelper.getInstance(getContext()).setHeight(AsyncImageView.this, height);
					ViewSizeHelper.getInstance(getContext()).setWidth(imageView, width);
					ViewSizeHelper.getInstance(getContext()).setHeight(imageView, height);
					if(asyncLoadListener != null) {
						asyncLoadListener.onComplete(view , bitmap);
					}
					isLoaded = true;
				} else {
					if(asyncLoadListener != null) {
						asyncLoadListener.onFail(new NullPointerException());
					}
				}
			}
			@Override
			public void onFail(Exception e) {
				if(asyncLoadListener != null) {
					asyncLoadListener.onFail(e);
				}
			}
			@Override
			public void onStart() {
				if(asyncLoadListener != null) {
					asyncLoadListener.onStart();
				}
			}
		});
	}
	
	public void loadUrl(String imageUrl,final int width , final int height ,final int tagId) {

		this.imageUrl = imageUrl;
//		initLoadUrl(imageUrl);
		this.setTag(tagId + "");
		LogCat.d("loadUrl", "加载图片:" + imageUrl);
		setScaleType(ScaleType.FIT_XY);
		ViewSizeHelper.getInstance(getContext()).setWidth(AsyncImageView.this, width);
		ViewSizeHelper.getInstance(getContext()).setHeight(AsyncImageView.this, height);
		ViewSizeHelper.getInstance(getContext()).setWidth(imageView, width);
		ViewSizeHelper.getInstance(getContext()).setHeight(imageView, height);
		loadImageFromUrl(imageUrl, width, new AsyncLoadListener() {
			@Override
			public void onComplete(AsyncImageView view , Bitmap bitmap) {
//				if(unableSyncLoadBitmap(view)) return;
				
				if(view.getTag() != null && view.getTag().equals(tagId + "")) return;
				
				LogCat.d("loadUrl", AsyncImageView.this.imageUrl + " 加载完毕。 width=" + width + "  height=" + height);
				if(bitmap != null) {
					if(imageCrop) {
						AsyncImageView.this.imageView.setScaleType(ScaleType.CENTER_CROP);
//						AsyncImageView.this.imageView.setImageBitmap(imageCrop(bitmap, width, height));
//					} else {
					}
					AsyncImageView.this.imageView.setImageBitmap(bitmap);
					progressBar.setVisibility(GONE);
					if(asyncLoadListener != null) {
						
						asyncLoadListener.onComplete(view , bitmap);
					}
					isLoaded = true;
				} else {
					if(asyncLoadListener != null) {
						asyncLoadListener.onFail(new NullPointerException());
					}
				}
			}
			@Override
			public void onFail(Exception e) {
				if(asyncLoadListener != null) {
					asyncLoadListener.onFail(e);
				}
			}
			@Override
			public void onStart() {
				if(asyncLoadListener != null) {
					asyncLoadListener.onStart();
				}
			}
		});
	
	}
	
	/**
	 * 异步加载图片 ， 设置图片高款，不按比例
	 * @param imageUrl
	 */
	public void loadUrl(final String imageUrl,final int width , final int height) {
		this.imageUrl = imageUrl;
		initLoadUrl(imageUrl);
		LogCat.d("loadUrl", "加载图片:" + imageUrl);
		setScaleType(ScaleType.FIT_XY);
		ViewSizeHelper.getInstance(getContext()).setWidth(AsyncImageView.this, width);
		ViewSizeHelper.getInstance(getContext()).setHeight(AsyncImageView.this, height);
		ViewSizeHelper.getInstance(getContext()).setWidth(imageView, width);
		ViewSizeHelper.getInstance(getContext()).setHeight(imageView, height);
		loadImageFromUrl(imageUrl, width, new AsyncLoadListener() {
			@Override
			public void onComplete(AsyncImageView view , Bitmap bitmap) {
				if(unableSyncLoadBitmap(imageUrl , view)) return;
				LogCat.d("loadUrl", AsyncImageView.this.imageUrl + " 加载完毕。 width=" + width + "  height=" + height);
				if(bitmap != null) {
					if(imageCrop) {
						AsyncImageView.this.imageView.setScaleType(ScaleType.CENTER_CROP);
//						AsyncImageView.this.imageView.setImageBitmap(imageCrop(bitmap, width, height));
//					} else {
					}
					AsyncImageView.this.imageView.setImageBitmap(bitmap);
					progressBar.setVisibility(GONE);
					if(asyncLoadListener != null) {
						
						asyncLoadListener.onComplete(view , bitmap);
					}
					isLoaded = true;
				} else {
					if(asyncLoadListener != null) {
						asyncLoadListener.onFail(new NullPointerException());
					}
				}
			}
			@Override
			public void onFail(Exception e) {
				if(asyncLoadListener != null) {
					asyncLoadListener.onFail(e);
				}
			}
			@Override
			public void onStart() {
				if(asyncLoadListener != null) {
					asyncLoadListener.onStart();
				}
			}
		});
	}
	
	public void loadUrl(final String imageUrl,final int width, final AsyncLoadListener asyncLoadListener) {

		this.imageUrl = imageUrl;
		loadImageFromUrl(imageUrl, width, new AsyncLoadListener() {
			@Override
			public void onComplete(AsyncImageView view , Bitmap bitmap) {
				if(unableSyncLoadBitmap(imageUrl , view)) return;
				if(bitmap != null) {
					progressBar.setVisibility(GONE);
					int sw = bitmap.getWidth();
					int sh = bitmap.getHeight();
					int height = width * sh / sw;
					if(imageCrop) {
						AsyncImageView.this.imageView.setScaleType(ScaleType.CENTER_CROP);
//						AsyncImageView.this.imageView.setImageBitmap(imageCrop(bitmap, width, height));
//					} else {
					}
					AsyncImageView.this.imageView.setImageBitmap(bitmap);
					ViewSizeHelper.getInstance(getContext()).setWidth(AsyncImageView.this, width);
					ViewSizeHelper.getInstance(getContext()).setHeight(AsyncImageView.this, height);
					ViewSizeHelper.getInstance(getContext()).setWidth(imageView, width);
					ViewSizeHelper.getInstance(getContext()).setHeight(imageView, height);
					if(asyncLoadListener != null) {
						asyncLoadListener.onComplete(view , bitmap);
					}
					isLoaded = true;
				} else { 
					if(asyncLoadListener != null) {
						asyncLoadListener.onFail(new NullPointerException());
					}
				}
			}
			@Override
			public void onFail(Exception e) {
				if(asyncLoadListener != null) {
					asyncLoadListener.onFail(e);
				}
			}
			@Override
			public void onStart() {
				if(asyncLoadListener != null) {
					asyncLoadListener.onStart();
				}
			}
		});
	}
	
	public interface AsyncLoadListener {
		public void onStart();
		public void onComplete(AsyncImageView view , Bitmap bitmap);
		public void onFail(Exception e);
	}
	
	/**
	 * 异步加载图片
	 * @param url 图片url
	 * @param width 需要的图片宽度
	 * @param imageUrlLoadListener 加载监听
	 */
	private synchronized void loadImageFromUrl(final String url ,final AsyncLoadListener imageUrlLoadListener ) {
		if(imageUrlLoadListener != null) {
			imageUrlLoadListener.onStart();
		}
		if(imageCache.containsKey(url)) {
			final SoftReference<Bitmap> sbitmap = imageCache.get(url);
			if(sbitmap != null && sbitmap.get() != null) {
				if(imageUrlLoadListener != null) {
					UIHandler.post(new Runnable() {
						@Override
						public void run() {
							imageUrlLoadListener.onComplete(AsyncImageView.this, sbitmap.get());
							return;
						}
					});
				}
			} else {
				imageCache.remove(url);
				loadImageFromUrl(url, imageUrlLoadListener);
			}
		} else if(useDBCache) {
	        UIHandler.post(new Runnable() {
				@Override
				public void run() {
					Filter filter = new Filter();
					filter.is("url", url);   // 查找条件， 查找指定uname的用户
					List<ImageCacheModel> icList = null;
					try {
						icList = ImageCacheModel.objects(context, ImageCacheModel.class).filter(filter).all().toList();
					} catch (Exception e) {}
					if(icList != null && icList.size() > 0) {
						ImageCacheModel ic = icList.get(0);
						final byte[] data = ic.getImage();
						ThreadPool.threadPool(new Runnable() {
							@Override
							public void run() {
								if(data != null) {
									final Bitmap bmp = BitmapFactory.decodeByteArray(data,0,data.length);
									if(bmp != null) {
										UIHandler.post(new Runnable() {
											@Override
											public void run() {
												imageUrlLoadListener.onComplete(AsyncImageView.this , bmp);
												imageCache.put(url, new SoftReference<Bitmap>(bmp));
											}
										});
									} else {
										loadFromNet(url, imageUrlLoadListener);
									}
								} else {
									loadFromNet(url, imageUrlLoadListener);
								}
							}
						});
					} else {
						loadFromNet(url, imageUrlLoadListener);
					}
				}
			});
		} else {
			loadFromNet(url , imageUrlLoadListener);
		}
		
	}
	
	/**
	 * 异步加载图片
	 * @param url 图片url
	 * @param width 需要的图片宽度
	 * @param imageUrlLoadListener 加载监听
	 */
	private synchronized void loadImageFromUrl(final String url , final int width ,final AsyncLoadListener imageUrlLoadListener ) {
		if(imageUrlLoadListener != null) {
			imageUrlLoadListener.onStart();
		}
		
		if(imageCache.containsKey(url)) {
			LogCat.d("loadUrl" , "内存加载 " + url);
			final SoftReference<Bitmap> sbitmap = imageCache.get(url);
			if(sbitmap != null && sbitmap.get() != null) {
				if(imageUrlLoadListener != null) {
					UIHandler.post(new Runnable() {
						@Override
						public void run() {
							imageUrlLoadListener.onComplete(AsyncImageView.this , sbitmap.get());
							return;
						}
					});
				}
			} else {
				imageCache.remove(url);
				loadImageFromUrl(url, width, imageUrlLoadListener);
			}
			
		} else if(useDBCache) {
			
	        UIHandler.post(new Runnable() {
				@Override
				public void run() {
					Filter filter = new Filter();
					filter.is("url", url);   // 查找条件， 查找指定uname的用户
					List<ImageCacheModel> icList = null;
					try {
						icList = ImageCacheModel.objects(context, ImageCacheModel.class).filter(filter).all().toList();
					} catch (Exception e) {}
					if(icList != null && icList.size() > 0) {
						ImageCacheModel ic = icList.get(0);
						final byte[] data = ic.getImage();
						ThreadPool.threadPool(new Runnable() {
							@Override
							public void run() {
								if(data != null) {
									final Bitmap bmp = BitmapFactory.decodeByteArray(data,0,data.length);
									
									if(bmp != null) {
										UIHandler.post(new Runnable() {
											@Override
											public void run() {
												LogCat.d("loadUrl" , "数据库加载 " + url);
												imageUrlLoadListener.onComplete(AsyncImageView.this , bmp);
												imageCache.put(url, new SoftReference<Bitmap>(bmp));
											}
										});
									} else {
										loadFromNet(url, width, imageUrlLoadListener);
									}
								} else {
									loadFromNet(url, width, imageUrlLoadListener);
								}
							}
						});
					} else {
						loadFromNet(url, width, imageUrlLoadListener);
					}
					
				}
			});
		} else {
			
			loadFromNet(url, width, imageUrlLoadListener);
		}
		
	}
	
	private void loadFromNet(final String url ,final AsyncLoadListener imageUrlLoadListener ) {
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				try {
//					String fixUrl = url;
//					if(fixUrl.indexOf("屏幕快照 2013-10-28 下午3_43_44") > 0) {
//						fixUrl = fixUrl.replace("屏幕快照 2013-10-28 下午3_43_44", java.net.URLEncoder.encode("屏幕快照 2013-10-28 下午3_43_44"));
//					
//					}
//					org.apache.commons.httpclient.URI uri = new org.apache.commons.httpclient.URI(fixUrl, false	, "UTF-8");
//					fixUrl = uri.toString();  
					
					org.apache.commons.httpclient.URI uri = new org.apache.commons.httpclient.URI(url, false, "UTF-8");
					
					URL aURL = new URL( uri.toString() );
					URLConnection con = aURL.openConnection();
					con.connect();
					InputStream is = con.getInputStream();
					
					BitmapFactory.Options opts = new BitmapFactory.Options();
					
					int inSampleSize = 1;
					
					// 重新取得连接取得bitmap
					con = aURL.openConnection();
					con.connect();
					is = con.getInputStream();
					
					BufferedInputStream bis = new BufferedInputStream(is);
					
					opts.inSampleSize = inSampleSize;
					final Bitmap bm = BitmapFactory.decodeStream(bis,null,opts);
					bis.close();
					is.close();
					
					if(imageUrlLoadListener != null) {
						if(bm == null) {
							imageUrlLoadListener.onFail(new Exception("读取不到图片."));
						} else {
							imageCache.put(url, new SoftReference<Bitmap>(bm));
							ImageCacheModel imageCache = new ImageCacheModel();
							imageCache.setCteateTime(System.currentTimeMillis());
							
							ByteArrayOutputStream os = new ByteArrayOutputStream();
							if(url.toLowerCase().endsWith(".png")) {
								bm.compress(Bitmap.CompressFormat.PNG, 100, os);  // 100是清晰度 100最清晰
							} else {
								bm.compress(Bitmap.CompressFormat.JPEG, 100, os);
							}
							imageCache.setImage(os.toByteArray());
							os.close();
							
							imageCache.setUrl(url);
							
							try {
								imageCache.save(context);
							} catch (Exception e) {
							}
							
							UIHandler.post(new Runnable() {
								@Override
								public void run() {
									imageUrlLoadListener.onComplete(AsyncImageView.this , bm);
								}
							});
						}
					}
					
				} catch (final Exception e) {
					ExceptionUtil.showException("ImageCache",e);
					if(imageUrlLoadListener != null) {
						UIHandler.post(new Runnable() {
							@Override
							public void run() {
								imageUrlLoadListener.onFail(e);
							}
						});
					}
				}
			}
		});
	}
	
	private void loadFromNet(final String url , final int width ,final AsyncLoadListener imageUrlLoadListener ) {
		LogCat.d("loadUrl" , "网络加载 " + url);
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				try {
//					String fixUrl = url;
//					if(fixUrl.indexOf("屏幕快照 2013-10-28 下午3_43_44") > 0) {
//						fixUrl = fixUrl.replace("屏幕快照 2013-10-28 下午3_43_44", java.net.URLEncoder.encode("屏幕快照 2013-10-28 下午3_43_44"));
//					
//					}
//					org.apache.commons.httpclient.URI uri = new org.apache.commons.httpclient.URI(fixUrl, false	, "UTF-8");
//					fixUrl = uri.toString();  
					
					org.apache.commons.httpclient.URI uri = new org.apache.commons.httpclient.URI(url, false, "UTF-8");
					
					URL aURL = new URL( uri.toString() );
					URLConnection con = aURL.openConnection();
					con.setConnectTimeout(60000);
					con.connect();
					InputStream is = con.getInputStream();
					
					//根据要设置的图片宽度设置缩放比例
					int inSampleSize = getSampleSizeAccorindToWidth(is, width);
					
					// 重新取得连接取得bitmap
					con = aURL.openConnection();
					con.connect();
					is = con.getInputStream();
					BufferedInputStream bis = new BufferedInputStream(is);
					
					BitmapFactory.Options newOpts = new BitmapFactory.Options();
					newOpts.inJustDecodeBounds = false;
					newOpts.inSampleSize = inSampleSize;//设置采样率  
			        newOpts.inPurgeable = true;// 同时设置才会有效  
			        newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收  
			          
			        final Bitmap bm = BitmapFactory.decodeStream(is, null, newOpts);  
					
					Log.d("test", "width="+bm.getWidth()+",height="+bm.getHeight());
					
					bis.close();
					is.close();
					
					if(imageUrlLoadListener != null) {
						if(bm == null) {
							imageUrlLoadListener.onFail(new Exception("读取不到图片."));
						} else {
							imageCache.put(url, new SoftReference<Bitmap>(bm));
							ImageCacheModel imageCache = new ImageCacheModel();
							imageCache.setCteateTime(System.currentTimeMillis());
							
							ByteArrayOutputStream os = new ByteArrayOutputStream();
							if(url.toLowerCase().endsWith(".png")) {
								bm.compress(Bitmap.CompressFormat.PNG, 100, os);  // 100是清晰度 100最清晰
							} else {
								bm.compress(Bitmap.CompressFormat.JPEG, 100, os);
							}
							imageCache.setImage(os.toByteArray());
							os.close();
							
							imageCache.setUrl(url);
							
							try {
								imageCache.save(context);
							} catch (Exception e) {
							}
							
							UIHandler.post(new Runnable() {
								@Override
								public void run() {
									imageUrlLoadListener.onComplete(AsyncImageView.this , bm);
								}
							});
						}
					}
					
				} catch (final Exception e) {
					ExceptionUtil.showException("ImageCache",e);
					if(imageUrlLoadListener != null) {
						UIHandler.post(new Runnable() {
							@Override
							public void run() {
								imageUrlLoadListener.onFail(e);
							}
						});
					}
				}
			}
		});
	}
	
	/**
	 * 获取bitmap的大小，不会内存溢出，只取得大小,
	 * @param context
	 * @param is imageUrl建立连接后的is
	 * @return
	 */
	private int getOptionalInSampleSize(InputStream is,int width){
		long startTime = System.currentTimeMillis();
		BitmapFactory.Options opt=new BitmapFactory.Options(); 
		opt.inPreferredConfig=Bitmap.Config.RGB_565; 
		opt.inPurgeable=true; 
		opt.inInputShareable=true; 
		Bitmap bitmap = BitmapFactory.decodeStream(is,null,opt);
		try {
			LogCat.d(TAG, "bitmap size =" + bitmap.getWidth() + "   " + bitmap.getHeight());
		} catch (Exception e) {
		}
		if(bitmap.getWidth() <= width) {
			LogCat.d(TAG, "getOptimalInSampleSize耗时：" + (System.currentTimeMillis() - startTime));
			return 1;
		} else {
			for(int i=2;i<=50;i++) {
				if(bitmap.getWidth() / i < width) {
					LogCat.d(TAG, "getOptimalInSampleSize耗时：" + (System.currentTimeMillis() - startTime) + " inSampleSize=" + i);
					return i;
				}
			}
		}
		return 1;
	}
	
	private int getSampleSizeAccorindToWidth(InputStream is,int width) {  
        BitmapFactory.Options newOpts = new BitmapFactory.Options();  
        newOpts.inJustDecodeBounds = true;//只读边,不读内容  
        BitmapFactory.decodeStream(is, null, newOpts);  
  
        int w = newOpts.outWidth;  
        int be = 1;  
        if(w <= width || Math.abs(getResources().getDisplayMetrics().widthPixels - w) < 300) {
			be = 1;
		} else {
			while (w / be++ / width > 1);
		}
        Log.d("test", "outWidth="+w+",width="+width+"be="+be);
        
        return be;
    }  
	
	/**
	 * 回收，同时会清掉设置imageview无图片
	 */
	public void recyle() {
		if(imageUrl != null && imageCache.get(imageUrl) != null) {
			Bitmap bitmap = imageCache.get(imageUrl).get();
			if(bitmap != null) {
				AsyncImageView.this.imageView.setImageDrawable(null);
				bitmap.recycle();
				imageCache.remove(imageUrl);
			}
		}
	}

	public AsyncLoadListener getAsyncLoadListener() {
		return asyncLoadListener;
	}

	public void setAsyncLoadListener(AsyncLoadListener asyncLoadListener) {
		this.asyncLoadListener = asyncLoadListener;
	}
	
	public boolean isLoaded() {
		return isLoaded;
	}
	
	/**
	 * 手动重新设置图片
	 */
	public void setImageResource(int resId) {
		AsyncImageView.this.imageView.setImageResource(resId);
	}
	public void setImageResource(int resId , int width , int height) {
		AsyncImageView.this.imageView.setImageResource(resId);
		ViewSizeHelper.getInstance(getContext()).setWidth(AsyncImageView.this, width);
		ViewSizeHelper.getInstance(getContext()).setHeight(AsyncImageView.this, height);
		ViewSizeHelper.getInstance(getContext()).setWidth(imageView, width);
		ViewSizeHelper.getInstance(getContext()).setHeight(imageView, height);
	}
	public void setImageBitmap (Bitmap bm) {
		if(bm == null) {
			AsyncImageView.this.imageView.setImageBitmap(null);
			return;
		}
		if(imageCrop) {
			AsyncImageView.this.imageView.setScaleType(ScaleType.CENTER_CROP);
//			AsyncImageView.this.imageView.setImageBitmap(imageCrop(bm, bm.getWidth(), bm.getHeight()));
//		} else {
		}
		AsyncImageView.this.imageView.setImageBitmap(bm);
		progressBar.setVisibility(GONE);
		if(bm == null) return; 
		ViewSizeHelper.getInstance(getContext()).setWidth(AsyncImageView.this, bm.getWidth());
		ViewSizeHelper.getInstance(getContext()).setHeight(AsyncImageView.this, bm.getHeight());
		ViewSizeHelper.getInstance(getContext()).setWidth(imageView, bm.getWidth());
		ViewSizeHelper.getInstance(getContext()).setHeight(imageView, bm.getHeight());
	}
	public void setImageDrawable (Drawable drawable)  {
		AsyncImageView.this.imageView.setImageDrawable(drawable);
		progressBar.setVisibility(GONE);
	}
	
	/**
	 * 根据tag中当前最新的url，判定是否允许对当前imageview setImageBitmap
	 * @param view
	 * @return
	 */
	private boolean unableSyncLoadBitmap(String imageUrl , AsyncImageView view) {
		String urlTag = view.getTag() == null ? null : view.getTag().toString();
		LogCat.d("loadUrl" , "urlTag=" + urlTag + "   imageUrl=" + imageUrl);
		if(urlTag != null && imageUrl != null && !imageUrl.equals(urlTag)) {
			return true; 
		} else {
			return false;
		}
	}
	
	/**
	 * loadUrl前初始化
	 * @param imageUrl
	 */
	private void initLoadUrl(String imageUrl) {
		this.setTag(imageUrl);
		setImageBitmap(null);
		progressBar.setVisibility(View.VISIBLE);
	}
	
	private Object tag;
	public void setTag(Object tag) {
		this.tag = tag;
	}
	
	public Object getTag() {
		return tag;
	}
	
	public ImageView getImageView() {
		return imageView;
	}
	
	/** 图片超出指定尺寸是否切割图片 */
	public void setImageCrop(boolean imageCrop) {
		this.imageCrop = imageCrop;
	}
	
	/**
	 * 按正方形裁切图片
	 */
	public Bitmap imageCrop(Bitmap bitmap , int width , int height)
	{
		int w = bitmap.getWidth(); // 得到图片的宽，高
		int h = bitmap.getHeight();
		LogCat.d("TEST", "w=" + w + "  h=" + h);
//		int wh = w > h ? h : w;// 裁切后所取的正方形区域边长
//		int retX = w > h ? (w - h) / 2 : 0;// 基于原图，取正方形左上角x坐标
//		int retY = w > h ? 0 : (h - w) / 2;

//		LogCat.d("TEST", "0width=" + width + "  height=" + height + " w=" + w + " h=" + h);
//		if(w >= h) {
//			int scalWidth = (width * h / height);
//			if(height > h) height = h;
//			if(width > w) width = w;
//			LogCat.d("TEST", "1width=" + width + "  height=" + height);
//		} else {
//			int scalHeight = (height * w / width);
//			if(width > w) width = w;
//			if(height > h) height = scalHeight > h ? h : scalHeight;
//			LogCat.d("TEST", "2width=" + width + "  height=" + height);
//		}
		if(width > w) width = w;
		if(height > h) height = h;
		
		return Bitmap.createBitmap(bitmap, 0, 0, width, height);
//		return Bitmap.createBitmap(bitmap, retX, retY, width, height, null, false);

	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
}

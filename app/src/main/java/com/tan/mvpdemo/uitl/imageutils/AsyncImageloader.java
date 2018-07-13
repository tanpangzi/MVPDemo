package com.tan.mvpdemo.uitl.imageutils;//package com.hxyd.dyt.utils.imageutils;
//
//import java.io.File;
//import java.io.IOException;
//import java.lang.ref.SoftReference;
//import java.net.MalformedURLException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Handler;
//import android.os.Message;
//import android.widget.ImageView;
//import android.widget.ImageView.ScaleType;
//
//import com.hxyd.dyt.BaseApplication;
//import com.hxyd.dyt.utils.HttpUtil;
//import com.hxyd.dyt.utils.StringUtil;
//
///**
// * 异步加载图片Imageloader
// *
// * <br> Author: 叶青
// * <br> Version: 1.0.0
// * <br> Date: 2016年12月11日
// * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
// */
//public class AsyncImageloader {
//
//	// *****************************本地消息状态码 ******************************//
//	/** 异步下载图片失败 */
//	public static final int WHAT_ASYN_LOADIMAGE_ERROR = 1;
//	/** 异步下载图片成功 */
//	public static final int WHAT_ASYN_LOADIMAGE_SUCCESS = 2;
//
//	// *********************线程池管理*********************//
//	/** 锁对象 */
//	private Object lock = new Object();
//	/** 是否允许加载 */
//	private boolean mAllowLoad = true;
//	/** 是否第一次加载 */
//	private boolean firstLoad = true;
//	/** 下载开始范围 */
//	private int mStartLoadLimit = 0;
//	/** 下载结束范围 */
//	private int mStopLoadLimit = 0;
//
//	/** 设置默认图片，加载图片失败的时候返回默认图片 */
//	public Bitmap defaultBitmap = null;
//
//	/** 使用线程池 */
//	private ExecutorService executorService;
//	/** 最大允许线程数 */
//	private int maxThread = 1;
//
//	/** 引入内存缓存机制 ,将加载过的图片放入缓存 */
//	private Map<String, SoftReference<Bitmap>> imageCache = new HashMap<String, SoftReference<Bitmap>>();
//
//	/**
//	 * 构造函数
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016-11-17,下午6:37:42
//	 * <br> UpdateTime: 2016-11-17,下午6:37:42
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
//	 *
//	 * @param maxThread
//	 *            最大允许线程数
//	 */
//	public AsyncImageloader(int maxThread) {
//		this.maxThread = maxThread;
//		executorService = Executors.newFixedThreadPool(this.maxThread);
//	}
//
//	/**
//	 * 描述：设置默认的图片
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016-4-9 下午2:36:35
//	 * <br> CreateAuthor: 叶青
//	 *
//	 * <br> UpdateTime: 2016-4-9 下午2:36:35
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
//	 *
//	 * @param image
//	 *            图片显示控件
//	 */
//	public void setDefaul(ImageView image) {
//		if (image != null) {
//			image.setScaleType(ScaleType.FIT_CENTER);
//			image.setImageBitmap(defaultBitmap);
//		}
//	}
//
//	/**
//	 * 下载图片
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016-12-27,下午9:48:03
//	 * <br> UpdateTime: 2016-12-27,下午9:48:03
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
//	 *
//	 * @param image
//	 *            设置图片的控件
//	 * @param position
//	 *            位置
//	 * @param imagePath
//	 *            图片路径
//	 * @param callback
//	 *            图片下载完成后回调
//	 * @param isPortrait
//	 *            是否是头像，true是，false不是
//	 * @param callback
//	 *            图片下载完成后回调
//	 * @return 返回软引用图片
//	 * @param localPath
//	 *            文件保存路径
//	 * @param resId
//	 *            默认显示图
//	 */
//	public Bitmap loadImageBitmap(final ImageView image, final int position, final String imagePath, final ImageCallback callback, int resId) {
//		final Context context = BaseApplication.getInstance().getApplicationContext();
//		defaultBitmap = BitmapFactory.decodeResource(context.getResources(), resId);
//		// 如果缓存过就从缓存中取出数据
//		final Handler handler = new Handler() {
//			@Override
//			public void handleMessage(Message msg) {
//				switch (msg.what) {
//				case WHAT_ASYN_LOADIMAGE_ERROR:
//					if (image == null) {
//						callback.imageLoaded((Bitmap) msg.obj, imagePath);
//					} else {
//						setDefaul(image);
//					}
//					break;
//				case WHAT_ASYN_LOADIMAGE_SUCCESS:
//					callback.imageLoaded((Bitmap) msg.obj, imagePath);
//					break;
//				}
//			}
//		};
//
//		// 缓存中是否有该图片，有则直接从缓存中取出图片
//		if (imageCache.containsKey(imagePath)) {
//			SoftReference<Bitmap> softReference = imageCache.get(imagePath);
//			if (softReference.get() != null) {
//				// return softReference.get();
//				Bitmap bitmap = softReference.get();
//				if (bitmap != null) {
//					if (image != null) {
//						callback.imageLoaded(bitmap, imagePath);
//					}
//					return bitmap;
//				}
//			}
//		}
//
//		if (image != null) {
//			setDefaul(image);
//		}
//
//		// 线程池提交线程
//		executorService.login(new Runnable() {
//			@Override
//			public void run() {
//				if (!mAllowLoad) {
//					synchronized (lock) {
//						try {
//							lock.wait();
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					}
//				}
//				if (mAllowLoad && firstLoad) {
//					cacheLoadBitmap(context, imagePath, handler);
//				}
//				if (mAllowLoad && position <= mStopLoadLimit && position >= mStartLoadLimit) {
//					cacheLoadBitmap(context, imagePath, handler);
//				}
//			}
//		});
//		return defaultBitmap;
//	}
//
//	/**
//	 * 缓存图片
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016年4月5日,下午3:10:26
//	 * <br> UpdateTime: 2016年4月5日,下午3:10:26
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
//	 *
//	 * @param context
//	 *            上下文
//	 * @param imagePath
//	 *            图片路径
//	 * @param handler
//	 *            异步通知
//	 */
//	private void cacheLoadBitmap(Context context, String imagePath, Handler handler) {
//		Bitmap bitmap = null;
//		bitmap = loadImage(imagePath);
//		if (bitmap == null) {
//			bitmap = defaultBitmap;
//			handler.sendMessage(handler.obtainMessage(WHAT_ASYN_LOADIMAGE_ERROR, bitmap));
//		} else {
//			imageCache.put(imagePath, new SoftReference<Bitmap>(bitmap));
//			handler.sendMessage(handler.obtainMessage(WHAT_ASYN_LOADIMAGE_SUCCESS, bitmap));
//		}
//	}
//
//	/**
//	 * 根据路径加载图片并处理
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016年4月5日,下午3:13:47
//	 * <br> UpdateTime: 2016年4月5日,下午3:13:47
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
//	 *
//	 * @param imagePath
//	 *            图片路径
//	 * @return Bitmap
//	 */
//	private Bitmap loadImage(String imagePath) {
//
//		Bitmap bitmap = null;
//		String loaclPath = imagePath;
//		if (!new File(loaclPath).exists()) {
//			loaclPath = StringUtil.getLocalImagePath(imagePath);
//		}
//
//		// 普通图片
//		if (new File(loaclPath).exists()) {
//			bitmap = BitmapFactory.decodeFile(loaclPath);
//			// 如果bitmap为空，文件已经损毁删除文件
//			if (bitmap == null) {
//				new File(loaclPath).delete();
//			}
//		} else {
//			try {
//				new HttpUtil().downloadFile(imagePath, loaclPath);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			if (new File(loaclPath).exists()) {
//				// String pathName = new ImageUtil().compressImage(loaclPath,
//				// false, 100, 0f);
//				String pathName = loaclPath;
//				bitmap = BitmapFactory.decodeFile(pathName);
//			}
//		}
//
//		return bitmap;
//	}
//
//	/**
//	 * 设置图片加载开始位置
//	 *
//	 * <br> Author: 叶青
//	 * <br> Version: 1.0.0
//	 * <br> Date: 2016年12月11日
//	 *
//	 * @param startLoadLimit
//	 *            开始加载图片范围
//	 * @param stopLoadLimit
//	 *            结束图片加载范围
//	 */
//	public void setLoadLimit(int startLoadLimit, int stopLoadLimit) {
//		if (startLoadLimit > stopLoadLimit) {
//			return;
//		}
//		mStartLoadLimit = startLoadLimit;
//		mStopLoadLimit = stopLoadLimit;
//	}
//
//	public void restore() {
//		mAllowLoad = true;
//		firstLoad = true;
//	}
//
//	/**
//	 * 锁定下载图片线程
//	 *
//	 * <br> Author: 叶青
//	 * <br> Version: 1.0.0
//	 * <br> Date: 2016年12月11日
//	 */
//	public void lock() {
//		mAllowLoad = false;
//		firstLoad = false;
//	}
//
//	/**
//	 * 解锁下载图片的线程
//	 *
//	 * <br> Author: 叶青
//	 * <br> Version: 1.0.0
//	 * <br> Date: 2016年12月11日
//	 */
//	public void unlock() {
//		mAllowLoad = true;
//		synchronized (lock) {
//			lock.notifyAll();
//		}
//	}
//
//	/**
//	 * 加载图片回调接口
//	 *
//	 * <br> Author: 叶青
//	 * <br> Version: 1.0.0
//	 * <br> Date: 2016年12月11日
//	 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
//	 */
//	public interface ImageCallback {
//
//		/**
//		 * 图片加载完成之后回调该接口
//		 *
//		 * <br> Version: 1.0.0
//		 * <br> CreateTime: 2016-10-24,下午2:02:10
//		 * <br> UpdateTime: 2016-10-24,下午2:02:10
//		 * <br> CreateAuthor: 叶青
//		 * <br> UpdateAuthor: 叶青
//		 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
//		 *
//		 * @param bitmap
//		 *            图片位图对象 ，null加载失败，否则返回得到的位图对象
//		 * @param imagePath
//		 *            图片路径
//		 */
//		public void imageLoaded(Bitmap bitmap, String imagePath);
//
//	}
//
//}
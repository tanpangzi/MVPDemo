package com.tan.mvpdemo.uitl.imageutils;//package com.hxyd.dyt.utils.imageutils;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.media.ThumbnailUtils;
//import android.provider.MediaStore;
//import android.provider.MediaStore.Video.Thumbnails;
//import android.text.TextUtils;
//import android.widget.ImageView;
//import android.widget.ImageView.ScaleType;
//
//import com.hxyd.dyt.R;
//import com.hxyd.dyt.BaseApplication;
//import com.hxyd.dyt.bean.ResponseBean;
//import com.hxyd.dyt.configs.Constant;
//import com.hxyd.dyt.executor.BaseTask;
//import com.hxyd.dyt.executor.RequestExecutor;
//import com.hxyd.dyt.utils.HttpUtil;
//import com.hxyd.dyt.utils.StringUtil;
//import com.hxyd.dyt.utils.imageutils.AsyncImageloader.ImageCallback;
//
//import java.io.File;
//
///**
// * 异步加载图片
// *
// * <br> Author: 叶青
// * <br> Version: 1.0.0
// * <br> Date: 2016年12月11日
// * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
// */
//public class AsyncImageSetter {
//
//	/** 异步加载图片对象 常驻内存的方法 */
//	private static AsyncImageloader asyncLoadImage = new AsyncImageloader(1);
//
//	/**
//	 * 设置视频封面图
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016-12-27,下午8:52:49
//	 * <br> UpdateTime: 2016-12-27,下午8:52:49
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
//	 *
//	 * @param img
//	 *            图片显示控件
//	 * @param localPath
//	 *            视频本地路径
//	 * @param resId
//	 *            默认图资源id ，resId <= 0時 默认显示图为ic_launcher
//	 */
//	public static void setVideoImage(ImageView img, String localPath, int resId) {
//		if (resId <= 0) {
//			resId = R.drawable.img_default;
//		}
//
//		if (img != null) {
//			setDefaul(img, resId);
//		} else {
//			return;
//		}
//
//		if (TextUtils.isEmpty(localPath)) {
//			return;
//		}
//
//		// img.setTag(localPath);
//		Bitmap bitmap = null;
//		if (new File(localPath).exists()) {
//			bitmap = ThumbnailUtils.createVideoThumbnail(localPath, Thumbnails.MINI_KIND);
//
//			if (bitmap != null) {
//				bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() * 300 / bitmap.getHeight(), 300, true);
//			}
//		}
//
//		// Object tag = img.getTag();
//		// if (tag != null && tag.equals(localPath)) {
//		if (bitmap != null) {
//			img.setScaleType(ScaleType.CENTER_CROP);
//			img.setImageBitmap(bitmap);
//		}
//		// }
//	}
//
//	/**
//	 * 设置 从媒体库获取的图片<br>
//	 * 4.4以上版本，媒体库选择图片后没有路径,只有图片编号。
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016-12-27,下午8:52:49
//	 * <br> UpdateTime: 2016-12-27,下午8:52:49
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
//	 *
//	 * @param img
//	 *            图片显示控件
//	 * @param photoId
//	 *            图片在媒体库里边的id
//	 * @param resId
//	 *            默认图资源id ，resId <= 0時 默认显示图为ic_launcher
//	 */
//	public static void setMediaPhoto(ImageView img, int photoId, int resId) {
//		Context context = BaseApplication.getInstance().getApplicationContext();
//		if (resId <= 0) {
//			resId = R.drawable.img_default;
//		}
//
//		if (img != null) {
//			setDefaul(img, resId);
//		}
//
//		// img.setTag(photoId);
//
//		try {
//			// Object tag = img.getTag();
//			// if (tag != null && tag.equals(photoId)) {
//			Bitmap bitmap = MediaStore.Images.Thumbnails.getThumbnail(context.getContentResolver(), photoId, Thumbnails.MICRO_KIND, null);
//			if (bitmap != null) {
//
//				img.setScaleType(ScaleType.CENTER_CROP);
//				img.setImageBitmap(bitmap);
//			}
//			// }
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 设置本地图片
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016年4月11日,下午12:47:35
//	 * <br> UpdateTime: 2016年4月11日,下午12:47:35
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
//	 *
//	 * @param img
//	 *            图片控件
//	 * @param position
//	 *            图片位置--设置tag
//	 * @param netPath
//	 *            图片全路径
//	 * @param radius
//	 *            圆角大小 小于等于0 代表不需要圆角
//	 * @param resId
//	 *            默认图资源id ，resId <= 0時 默认显示图为ic_launcher
//	 * @param isblur
//	 *            是否需要模糊
//	 * @param scale
//	 *            图片尺寸压缩宽高比例：(height：width) 小于等于0 代表不裁剪
//	 * @param isGray
//	 *            true黑白处理false则不处理
//	 */
//	public static void setLocalImage(ImageView img, String localPath, int resId, int radius, boolean isblur, float scale, boolean isGray) {
//		// 设置默认图资源
//		if (resId <= 0) {
//			resId = R.drawable.img_default;
//		}
//
//		// 设置默认的图片
//		if (img != null) {
//			setDefaul(img, resId);
//		} else {
//			return;
//		}
//
//		// 图片网络路径为空 直接返回
//		if (TextUtils.isEmpty(localPath)) {
//			return;
//		}
//
//		// 设置tag，防止错乱
//		img.setTag(localPath);
//
//		setBitmap(img, localPath, radius, isblur, scale, isGray);
//	}
//
//	/**
//	 * 描述：异步加载图片
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016-12-27,下午9:55:45
//	 * <br> UpdateTime: 2016-12-27,下午9:55:45
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
//	 *
//	 * @param img
//	 *            图片控件
//	 * @param position
//	 *            图片位置--设置tag
//	 * @param netPath
//	 *            图片全路径
//	 * @param radius
//	 *            圆角大小 小于等于0 代表不需要圆角
//	 * @param resId
//	 *            默认图资源id ，resId <= 0時 默认显示图为ic_launcher
//	 * @param isblur
//	 *            是否需要模糊
//	 * @param scale
//	 *            图片尺寸压缩宽高比例：(height：width) 0 代表不裁剪
//	 * @param isGray
//	 *            true黑白处理false则不处理
//	 */
//	public static void setImage(final ImageView img, final String netPath, int resId, final int radius, final boolean isblur, final float scale, final boolean isGray) {
//
//		// 设置默认图资源
//		if (resId <= 0) {
//			resId = R.drawable.img_default;
//		}
//
//		// 设置默认的图片
//		if (img != null) {
//			setDefaul(img, resId);
//		} else {
//			return;
//		}
//
//		// 图片网络路径为空 直接返回
//		if (TextUtils.isEmpty(netPath)) {
//			return;
//		}
//
//		// 设置tag，防止错乱
//		img.setTag(netPath);
//
//		// 图片本地路径
//		String loaclPath = StringUtil.getLocalImagePath(netPath);
//
//		// 图片本地裁剪过后路径
//		String loaclPathNew;
//
//		// 如果需要黑白
//		if (isGray) {
//			loaclPathNew = StringUtil.replaceLast(netPath, ".", Constant.IMAGE_LOGO_COMPRESS_GRAY);
//		} else {
//			loaclPathNew = StringUtil.replaceLast(netPath, ".", Constant.IMAGE_LOGO_COMPRESS);
//		}
//
//		String tempPath = loaclPath.replace(loaclPath.substring(loaclPath.lastIndexOf("."), loaclPath.length()), ".temp");
//		if (new File(tempPath).exists()) {
//		} else {
//
//			// 如果普通图片或者裁剪后的图片存在，则直接显示
//			if (new File(loaclPath).exists() || new File(loaclPathNew).exists()) {
//
//				setBitmap(img, netPath, radius, isblur, scale, isGray);
//			}
//			// 否则，异步网络加载
//			else {
//				asyncLoadImage.loadImageBitmap(img, 0, netPath, new ImageCallback() {
//					@Override
//					public void imageLoaded(Bitmap bitmap, String imagePath) {
//						if (!isblur && radius <= 0 && scale < 0 && !isGray) {
//							if (bitmap != null) {
//								Object tag = img.getTag();
//								if (tag == null || tag.equals(netPath)) {
//									img.setScaleType(ScaleType.CENTER_CROP);
//									img.setImageBitmap(bitmap);
//								}
//							}
//						} else {
//							bitmapNew = bitmap;
//							setBitmap(img, netPath, radius, isblur, scale, isGray);
//						}
//					}
//				}, resId);
//			}
//		}
//
//	}
//
//	private static Bitmap bitmapNew = null;
//
//	/**
//	 * 图片处理，展示
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016-12-28,下午1:15:34
//	 * <br> UpdateTime: 2016-12-28,下午1:15:34
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
//	 *
//	 * @param img
//	 *            图片控件
//	 * @param netPath
//	 *            图片全路径
//	 * @param radius
//	 *            圆角大小 小于等于0 代表不需要圆角
//	 * @param isblur
//	 *            是否需要模糊
//	 * @param scale
//	 *            图片尺寸压缩宽高比例：(height：width) 小于等于0 代表不裁剪
//	 * @param isGray
//	 *            true黑白处理false则不处理
//	 */
//	private static void setBitmap(final ImageView img, final String netPath, final int radius, final boolean isblur, final float scale, final boolean isGray) {
//
//		RequestExecutor.addTask(new BaseTask() {
//
//			@Override
//			public ResponseBean sendRequest() {
//                Context context = BaseApplication.getInstance().getApplicationContext();
//				String loaclPath;
//				if (HttpUtil.checkURL(netPath)) {
//					loaclPath = StringUtil.getLocalImagePath(netPath);
//				} else {
//					loaclPath = netPath;
//				}
//
//				// 裁剪压缩
//				if (scale >= 0 || isGray) {
//
//					String loaclPathNew;
//					// 如果需要黑白
//					if (isGray) {
//						loaclPathNew = StringUtil.replaceLast(netPath, ".", Constant.IMAGE_LOGO_COMPRESS_GRAY);
//					} else {
//						loaclPathNew = StringUtil.replaceLast(netPath, ".", Constant.IMAGE_LOGO_COMPRESS);
//					}
//
//					// 如果裁剪后的图片存在
//					if (new File(loaclPathNew).exists()) {
//						bitmapNew = BitmapFactory.decodeFile(loaclPathNew);
//						// 如果bitmap为空，文件已经损毁删除文件
//						if (bitmapNew == null) {
//							new File(loaclPathNew).delete();
//						}
//					} else {
//						// 普通图片
//						if (new File(loaclPath).exists()) {
//							String pathName = ImageCompressUtil.compressImg(loaclPath);
////							String pathName = new ImageUtil().compressImage(loaclPath, isGray, 100, scale);
//							bitmapNew = BitmapFactory.decodeFile(pathName);
//							// 如果bitmap为空，文件已经损毁删除文件
//							if (bitmapNew == null) {
//								new File(loaclPath).delete();
//							}
//						}
//					}
//				} else {
//					// 普通图片
//					if (new File(loaclPath).exists()) {
//						bitmapNew = BitmapFactory.decodeFile(loaclPath);
//						// 如果bitmap为空，文件已经损毁删除文件
//						if (bitmapNew == null) {
//							new File(loaclPath).delete();
//						}
//					}
//				}
//
//				// 模糊处理
//				if (isblur) {
//					Drawable drawable = ImageOpera.BlurImages(bitmapNew, context);
//					BitmapDrawable bd = (BitmapDrawable) drawable;
//					bitmapNew = bd.getBitmap();
//				}
//
//				// 圆角处理
//				if (radius > 0) {
//					bitmapNew = ImageOpera.cutRound(bitmapNew, radius);
//				}
//
//				return new ResponseBean("10000", "", "");
//			}
//
//			@Override
//			public void onSuccess(ResponseBean result) {
//				Object tag = img.getTag();
//				if (tag == null || tag.equals(netPath)) {
//					if (bitmapNew != null) {
//						img.setScaleType(ScaleType.CENTER_CROP);
//						img.setImageBitmap(bitmapNew);
//					}
//				}
//			}
//
//			@Override
//			public void onFail(ResponseBean result) {
//				Object tag = img.getTag();
//				if (tag == null || tag.equals(netPath)) {
//					if (bitmapNew != null) {
//						img.setScaleType(ScaleType.CENTER_CROP);
//						img.setImageBitmap(bitmapNew);
//					}
//				}
//			}
//		});
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
//	 *            ImageView
//	 * @param resId
//	 *            默认图资源id ，resId <= 0時 默认显示图为ic_launcher
//	 */
//	public static void setDefaul(ImageView image, int resId) {
//		if (image != null) {
//			image.setScaleType(ScaleType.CENTER_CROP);
//			image.setImageResource(resId);
//		}
//	}
//}
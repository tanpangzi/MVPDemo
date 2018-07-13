package com.tan.mvpdemo.uitl.imageutils;//package com.chnyoufu.ufield.utils.imageutils;
//
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.ColorMatrix;
//import android.graphics.ColorMatrixColorFilter;
//import android.graphics.Matrix;
//import android.graphics.Paint;
//import android.graphics.PaintFlagsDrawFilter;
//import android.graphics.Rect;
//import android.media.ExifInterface;
//import android.text.TextUtils;
//
//import com.chnyoufu.ufield.configs.ConfigFile;
//import com.chnyoufu.ufield.configs.ScanCodeKey;
//
///**
// * 图片操作工具类
// *
// * <br> Author: 叶青
// * <br> Version: 1.0.0
// * <br> Date: 2016年12月11日
// * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
// */
//public class ImageUtil {
//
//	/** 图片的最大尺寸，任意边长大于720，都将进行压缩 */
//	private static int maxSize = 720;
//	/** 图片质量压缩比例,这里100表示不压缩 */
//	private int quality = 100;
//
//	/**
//	 * 压缩图片并保存到相应的文件夹中
//	 *
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016-11-5,下午3:22:38
//	 * <br> UpdateTime: 2016-11-5,下午3:22:38
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
//	 *
//	 * @param imageLocalPath
//	 *            源图绝对路径
//	 * @param isGray
//	 *            true黑白处理false则不处理
//	 * @param quality
//	 *            图片质量压缩比例
//	 * @param scale
//	 *            图片尺寸压缩宽高比例：(height：width=scale==0时，按照图片原始比例压缩)
//	 */
//	public String compressImage(String imageLocalPath, boolean isGray, int quality, float scale) {
//		this.quality = quality;
//		String compress_images_size = "";
//		if (isGray) {
//			compress_images_size = ScanCodeKey.IMAGE_LOGO_COMPRESS_GRAY;
//		} else {
//			compress_images_size = ScanCodeKey.IMAGE_LOGO_COMPRESS;
//		}
//
//		Bitmap bitmap = getBitmap(imageLocalPath);
//
//		// 如果图片加载失败，则返回空
//		if (bitmap == null) {
//			return "";
//		}
//
//		// 获取原图的显示方向
//		int digree = getImageDigree(imageLocalPath);
//
//		// 旋转图片的显示方向
//		Matrix matrix = new Matrix();
//		matrix.postRotate(digree);
//
//		// 原始位图的宽高参数
//		int srcWidth = bitmap.getWidth();
//		int srcHeight = bitmap.getHeight();
//
//		if (scale <= 0) {
//			scale = (float) srcHeight / srcWidth;
//		}
//
//		// 压缩图片的尺寸属性对象
//		Size newSize = getNewSize(srcWidth, srcHeight, scale);
//
//		// 压缩图的画布区域
//		Rect newDst = new Rect(0, 0, newSize.width, newSize.height);
//		// 原图的裁剪区域
//		Rect src;
//
//		if (srcHeight / srcWidth >= scale) {
//			src = new Rect(0//
//					, (int) ((srcHeight - srcWidth * (scale)) / 2)//
//					, srcWidth//
//					, (int) (((srcHeight - srcWidth * (scale)) / 2) + srcWidth * (scale))//
//			);
//		} else {
//			src = new Rect((int) ((srcWidth - srcHeight * (1 / scale)) / 2)//
//					, 0//
//					, (int) (((srcWidth - srcHeight * (1 / scale)) / 2) + srcHeight * (1 / scale))//
//					, srcHeight//
//			);
//		}
//
//		// *****************压缩图对象*******************//
//		Bitmap newBitmap;
//		if (digree == 90 || digree == 270) {// 垂直显示
//			newBitmap = Bitmap.createBitmap(newSize.height, newSize.width, Bitmap.Config.RGB_565);
//		} else {// 横向显示
//			newBitmap = Bitmap.createBitmap(newSize.width, newSize.height, Bitmap.Config.RGB_565);
//		}
//		// 绘制压缩图
//		Canvas canvas = new Canvas(newBitmap);
//		canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
//		canvas.save();
//		// 旋转画布
//		rotate(canvas, digree, newSize);
//
//		if (isGray) {
//			ColorMatrix cm = new ColorMatrix();
//			cm.setSaturation(0);
//			Paint paint = new Paint();
//			ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
//			paint.setColorFilter(f);
//			canvas.drawBitmap(bitmap, src, newDst, paint);
//		} else {
//			canvas.drawBitmap(bitmap, src, newDst, null);
//		}
//
//		String fileName = imageLocalPath.substring(imageLocalPath.lastIndexOf("/") + 1);
//		canvas.restore();
//		fileName = fileName.replace(".", compress_images_size);
//		// 保存生成的位图
//		saveBitmapToFile(ConfigFile.PATH_IMAGES, fileName, newBitmap);
//		// 回收位图空间，释放内存
//		newBitmap.recycle();
//		bitmap.recycle();
//		System.gc();
//
//		return ConfigFile.PATH_IMAGES + fileName;
//
//	}
//
//	/**
//	 * 获取位图
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016-11-6,下午9:01:29
//	 * <br> UpdateTime: 2016-11-6,下午9:01:29
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
//	 *
//	 * @param imageLocalPath
//	 *            图片路径
//	 * @return
//	 */
//	private Bitmap getBitmap(String imageLocalPath) {
//
//		if (TextUtils.isEmpty(imageLocalPath)) {// 文件路径为空
//			return null;
//		}
//
//		return loadBitmap(imageLocalPath);
//
//	}
//
//	/**
//	 * 根据路径获得图片并压缩，返回bitmap用于显示
//	 *
//	 * @param imageLocalPath
//	 *            图片路径
//	 * @return
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016年1月17日, 下午7:50:21
//	 * <br> UpdateTime: 2016年1月17日, 下午7:50:21
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
//	 */
//	public static Bitmap loadBitmap(String imageLocalPath) {
//		final BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inJustDecodeBounds = true;
//		BitmapFactory.decodeFile(imageLocalPath, options);
//		options.inSampleSize = calculateInSampleSize(options, maxSize, maxSize);
//		options.inJustDecodeBounds = false;
//		return BitmapFactory.decodeFile(imageLocalPath, options);
//	}
//
//	/**
//	 * 计算图片的缩放值
//	 *
//	 * @return
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016年1月17日, 下午7:49:52
//	 * <br> UpdateTime: 2016年1月17日, 下午7:49:52
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
//	 *
//	 * @param options
//	 * @param reqWidth
//	 *            参考宽度
//	 * @param reqHeight
//	 *            参考高度
//	 */
//	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
//		final int height = options.outHeight;
//		final int width = options.outWidth;
//		int inSampleSize = 1;
//
//		if (height > reqHeight || width > reqWidth) {
//			final int heightRatio = Math.round((float) height / (float) reqHeight);
//			final int widthRatio = Math.round((float) width / (float) reqWidth);
//			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
//		}
//		return inSampleSize;
//	}
//
//	/**
//	 * 获取压缩图片的尺寸大小属性
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016-11-5,下午3:05:20
//	 * <br> UpdateTime: 2016-11-5,下午3:05:20
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
//	 *
//	 * @param srcWidth
//	 *            原始图片的宽度
//	 * @param srcHeight
//	 *            原始图片的高度
//	 * @param scale
//	 *            图片尺寸压缩宽高比例：(height：width)
//	 * @return
//	 */
//	private Size getNewSize(int srcWidth, int srcHeight, float scale) {
//		Size newSize = new Size();
//		// 判断图片形状
//		newSize.width = maxSize;
//		newSize.height = (int) (maxSize * scale);
//		return newSize;
//	}
//
//	/**
//	 * 旋转画布
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016-12-9,下午3:56:05
//	 * <br> UpdateTime: 2016-12-9,下午3:56:05
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
//	 *
//	 * @param canvas
//	 *            画布
//	 * @param digree
//	 *            旋转角度
//	 * @param size
//	 *            画布尺寸
//	 * @Ps: 画布旋转之后，整个坐标系也会跟随旋转，所以旋转之后的平移要考虑坐标系的变动，不然平移必然是错位的
//	 */
//	private void rotate(Canvas canvas, int digree, Size size) {
//
//		switch (digree) {
//		case 0:// 正横拍
//			break;
//		case 90:// 正竖拍
//			canvas.rotate(90, canvas.getWidth() / 2, canvas.getHeight() / 2);
//			canvas.translate(-Math.abs(size.height - size.width) / 2, Math.abs(size.height - size.width) / 2);
//			break;
//		case 180:// 反横拍
//			canvas.rotate(180, canvas.getWidth() / 2, canvas.getHeight() / 2);
//			break;
//		case 270:// 反竖拍
//			canvas.rotate(-90, canvas.getWidth() / 2, canvas.getHeight() / 2);
//			canvas.translate(-Math.abs(size.height - size.width) / 2, Math.abs(size.height - size.width) / 2);
//			break;
//
//		default:
//			break;
//		}
//
//	}
//
//	/**
//	 * 保存位图到本地文件
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016-11-5,下午2:33:35
//	 * <br> UpdateTime: 2016-11-5,下午2:33:35
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
//	 *
//	 * @param saveParentPath
//	 *            // 文件保存目录
//	 * @param fileName
//	 *            文件名称
//	 * @param bitmap
//	 *            位图对象
//	 */
//	public void saveBitmapToFile(String saveParentPath, String fileName, Bitmap bitmap) {
//
//		try {
//			File saveimg = new File(saveParentPath + fileName);
//			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(saveimg));
//			bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos);
//			bos.flush();
//			bos.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 获取图片的显示方向
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016-11-6,下午9:23:10
//	 * <br> UpdateTime: 2016-11-6,下午9:23:10
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
//	 *
//	 * @param imageLocalPath
//	 *            图片路径
//	 * @return
//	 */
//	private int getImageDigree(String imageLocalPath) {
//		int digree = 0;
//		ExifInterface exif = null;
//		try {
//			exif = new ExifInterface(imageLocalPath);
//		} catch (IOException e) {
//			e.printStackTrace();
//			exif = null;
//		}
//		if (exif != null) {
//			// 读取图片中相机方向信息
//			int ori = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
//			// 计算旋转角度
//			switch (ori) {
//			case ExifInterface.ORIENTATION_ROTATE_90:
//				digree = 90;
//				break;
//			case ExifInterface.ORIENTATION_ROTATE_180:
//				digree = 180;
//				break;
//			case ExifInterface.ORIENTATION_ROTATE_270:
//				digree = 270;
//				break;
//			default:
//				digree = 0;
//				break;
//			}
//		}
//
//		return digree;
//
//	}
//
//	/**
//	 * 处理多张图片
//	 *
//	 *
//	 *  <br> Version: 1.0.0
//	 * <br> CreateTime:: 2016年1月17日,下午7:54:16
//	  * <br> UpdateTime: 2016年1月17日,下午7:54:16
//	  * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: : (此处输入修改内容,若无修改可不写.)
//	 *
//	 * @param srcPaths
//	 * @return
//	 */
//	public String compressImages(String srcPaths) {
//		try {
//			if (TextUtils.isEmpty(srcPaths)) {
//				return "";
//			}
//
//			String images[] = srcPaths.split(",");
//			String compressImges = "";
//
//			for (int i = 0; i < images.length; i++) {
//				if (i == 0) {
//					compressImges += compressImage(images[i], false, quality, 0);
//				} else {
//					compressImges += "," + compressImage(images[i], false, quality, 0);
//				}
//			}
//
//			return compressImges;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "";
//		}
//	}
//
//	/**
//	 * 尺寸类
//	 *
//	 * <br> Author: 叶青
//	 * <br> Version: 1.0.0
//	 * <br> Date: 2016年12月11日
//	 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
//	 */
//	private class Size {
//		/** 宽度 */
//		private int width;
//		/** 高度 */
//		private int height;
//	}
//}
package com.tan.mvpdemo.uitl.imageutils;//package com.hxyd.dyt.utils.imageutils;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.Bitmap.Config;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.PorterDuff.Mode;
//import android.graphics.PorterDuffXfermode;
//import android.graphics.Rect;
//import android.graphics.RectF;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//
///**
// * 图片操作类
// *
// * <br> Author: 叶青
// * <br> Version: 1.0.0
// * <br> Date: 2016年12月11日
// * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
// */
//public class ImageOpera {
//
//	/**
//	 * 处理图片圆角,将图片的四角处理成圆角.
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016-12-28,下午1:51:41
//	 * <br> UpdateTime: 2016-12-28,下午1:51:41
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
//	 *
//	 *
//	 * @param bitmap
//	 *            处理图片.
//	 * @param pixels
//	 *            处理角度.
//	 * @return 处理完成的图片.
//	 */
//	public static Bitmap cutRound(Bitmap bitmap, int pixels) {
//		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
//		Canvas canvas = new Canvas(output);
//		final int color = 0xff424242;
//		final Paint paint = new Paint();
//		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
//		final RectF rectF = new RectF(rect);
//		final float roundPx = pixels;
//		paint.setAntiAlias(true);
//		canvas.drawARGB(0, 0, 0, 0);
//		paint.setColor(color);
//		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
//		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
//		canvas.drawBitmap(bitmap, rect, rect, paint);
//		canvas.save();
//		bitmap.recycle();
//		return output;
//	}
//
//	/**
//	 * 转换图片成圆形
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016-12-28,下午1:51:52
//	 * <br> UpdateTime: 2016-12-28,下午1:51:52
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
//	 *
//	 *
//	 * @param bitmap
//	 *            传入Bitmap对象
//	 * @return
//	 */
//	public static Bitmap toRoundBitmap(Bitmap bitmap) {
//		int width = bitmap.getWidth();
//		int height = bitmap.getHeight();
//		float roundPx;
//		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
//		if (width <= height) {
//			roundPx = width / 2;
//			top = 0;
//			bottom = width;
//			left = 0;
//			right = width;
//			height = width;
//			dst_left = 0;
//			dst_top = 0;
//			dst_right = width;
//			dst_bottom = width;
//		} else {
//			roundPx = height / 2;
//			float clip = (width - height) / 2;
//			left = clip;
//			right = width - clip;
//			top = 0;
//			bottom = height;
//			width = height;
//			dst_left = 0;
//			dst_top = 0;
//			dst_right = height;
//			dst_bottom = height;
//		}
//
//		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
//		Canvas canvas = new Canvas(output);
//
//		final int color = 0xff424242;
//		final Paint paint = new Paint();
//		final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
//		final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
//		final RectF rectF = new RectF(dst);
//
//		paint.setAntiAlias(true);
//
//		canvas.drawARGB(0, 0, 0, 0);
//		paint.setColor(color);
//		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
//
//		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
//		canvas.drawBitmap(bitmap, src, dst, paint);
//		return output;
//	}
//
//	/**
//	 * 降低图片质量压缩图片.
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016-12-28,下午1:52:04
//	 * <br> UpdateTime: 2016-12-28,下午1:52:04
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
//	 *
//	 * @param bitmap
//	 *            压缩图片.
//	 * @param size
//	 *            压缩质量,单位kb.
//	 * @return 压缩完成的图片.
//	 */
//	public static Bitmap compressImage(Bitmap bitmap, int size) {
//		if (bitmap == null) {
//			return null;
//		}
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
//		int options = 100;
//		int flag = baos.toByteArray().length;
//		if (flag / 1024 > size) { // 判断如果压缩后图片是否大于size,大于继续压缩
//			options = (size * 1024 * 100) / flag;
//			baos.reset();// 重置baos即清空baos
//			bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
//		}
//		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
//		Bitmap bm = BitmapFactory.decodeStream(bais, null, null);// 把ByteArrayInputStream数据生成图片
//		bitmap.recycle();
//		return bm;
//	}
//
//	/**
//	 * 按指定尺寸缩放图片.-----尺寸缩放
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016-11-24,下午6:02:35
//	 * <br> UpdateTime: 2016-11-24,下午6:02:35
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
//	 *
//	 * @param path
//	 *            图片路径.
//	 * @param width
//	 *            指定长度.
//	 * @param height
//	 *            指定高度.
//	 * @return 处理完成的图片.
//	 * @return
//	 */
//	public static Bitmap scaleImage(String path, int width, int height) {
//		BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inJustDecodeBounds = true;
//		Bitmap bitmap = BitmapFactory.decodeFile(path, options); // 此时返回bitmap为空
//		options.inJustDecodeBounds = false;
//		int w = options.outWidth;
//		int h = options.outHeight;
//		int wScale = 1; // 长度缩放比例
//		int hScale = 1; // 高度缩放比例
//		if (w > width) // 若宽度大于指定宽度,则计算出宽度缩放比例.
//			wScale = w / width;
//		if (h > height) // 若高度大于指定高度,则计算出高度缩放比例.
//			hScale = h / height;
//		int scale = 1;
//		if (wScale <= hScale)
//			scale = hScale;
//		else
//			scale = wScale;
//		options.inSampleSize = scale; // 设置缩放比例
//		bitmap = BitmapFactory.decodeFile(path, options);
//		return bitmap;
//	}
//
//	/**
//	 * 按指定尺寸缩放图片.----压缩质量
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016-11-24,下午6:02:26
//	 * <br> UpdateTime: 2016-11-24,下午6:02:26
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
//	 *
//	 * @param path
//	 *            图片路径.
//	 * @param size
//	 *            压缩质量,单位kb.
//	 * @param width
//	 *            指定长度.
//	 * @param height
//	 *            指定高度.
//	 * @return 处理完成的图片.
//	 * @return
//	 */
//	public static Bitmap scaleImage(Bitmap bitmap, int size, int width, int height) {
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//		int flag = baos.toByteArray().length;
//		if (flag / 1024 > size) {// 判断如果图片大于size,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
//			baos.reset();// 重置baos即清空baos
//			bitmap.compress(Bitmap.CompressFormat.JPEG, (size * 1024 * 100) / flag, baos);// 这里压缩50%，把压缩后的数据存放到baos中
//		}
//		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
//		BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inJustDecodeBounds = true;
//		Bitmap bm = BitmapFactory.decodeStream(bais, null, options);
//		options.inJustDecodeBounds = false;
//		int w = options.outWidth;
//		int h = options.outHeight;
//		int wScale = 1; // 长度缩放比例
//		int hScale = 1; // 高度缩放比例
//		if (w > width) // 若宽度大于指定宽度,则计算出宽度缩放比例.
//			wScale = w / width;
//		if (h > height) // 若高度大于指定高度,则计算出高度缩放比例.
//			hScale = h / height;
//		int scale = 1;
//		if (wScale <= hScale)
//			scale = hScale;
//		else
//			scale = wScale;
//		options.inSampleSize = scale;// 设置缩放比例
//		bais = new ByteArrayInputStream(baos.toByteArray());
//		bm = BitmapFactory.decodeStream(bais, null, options);
//		bitmap.recycle();
//		return bm;// 压缩好比例大小后再进行质量压缩
//	}
//
//	// / ************************ 图片高斯模糊算法**************************** */
//	/** 水平方向模糊度 */
//	private static float hRadius = 10;
//	/** 竖直方向模糊度 */
//	private static float vRadius = 10;
//	/** 模糊迭代度 */
//	private static int iterations = 6;
//
//	/**
//	 * 图片高斯模糊处理
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016年8月16日,上午9:34:49
//	 * <br> UpdateTime: 2016年8月16日,上午9:34:49
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo:
//	 * @param bmp
//	 *            位图对象
//	 * @param context
//	 *            上下文对象
//	 * @return
//	 */
//	public static Drawable BlurImages(Bitmap bmp, Context context) {
//
//		int width = bmp.getWidth();
//		int height = bmp.getHeight();
//		int[] inPixels = new int[width * height];
//		int[] outPixels = new int[width * height];
//		Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//		bmp.getPixels(inPixels, 0, width, 0, 0, width, height);
//		for (int i = 0; i < iterations; i++) {
//			blur(inPixels, outPixels, width, height, hRadius);
//			blur(outPixels, inPixels, height, width, vRadius);
//		}
//		blurFractional(inPixels, outPixels, width, height, hRadius);
//		blurFractional(outPixels, inPixels, height, width, vRadius);
//		bitmap.setPixels(inPixels, 0, width, 0, 0, width, height);
//		Drawable drawable = new BitmapDrawable(context.getResources(), bitmap);
//		return drawable;
//	}
//
//	/**
//	 * 图片高斯模糊算法
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016年8月16日,上午9:27:47
//	 * <br> UpdateTime: 2016年8月16日,上午9:27:47
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo:
//	 * @param in
//	 * @param out
//	 * @param width
//	 * @param height
//	 * @param radius
//	 */
//	public static void blur(int[] in, int[] out, int width, int height, float radius) {
//		int widthMinus1 = width - 1;
//		int r = (int) radius;
//		int tableSize = 2 * r + 1;
//		int divide[] = new int[256 * tableSize];
//
//		for (int i = 0; i < 256 * tableSize; i++)
//			divide[i] = i / tableSize;
//
//		int inIndex = 0;
//
//		for (int y = 0; y < height; y++) {
//			int outIndex = y;
//			int ta = 0, tr = 0, tg = 0, tb = 0;
//
//			for (int i = -r; i <= r; i++) {
//				int rgb = in[inIndex + clamp(i, 0, width - 1)];
//				ta += (rgb >> 24) & 0xff;
//				tr += (rgb >> 16) & 0xff;
//				tg += (rgb >> 8) & 0xff;
//				tb += rgb & 0xff;
//			}
//
//			for (int x = 0; x < width; x++) {
//				out[outIndex] = (divide[ta] << 24) | (divide[tr] << 16) | (divide[tg] << 8) | divide[tb];
//
//				int i1 = x + r + 1;
//				if (i1 > widthMinus1)
//					i1 = widthMinus1;
//				int i2 = x - r;
//				if (i2 < 0)
//					i2 = 0;
//				int rgb1 = in[inIndex + i1];
//				int rgb2 = in[inIndex + i2];
//
//				ta += ((rgb1 >> 24) & 0xff) - ((rgb2 >> 24) & 0xff);
//				tr += ((rgb1 & 0xff0000) - (rgb2 & 0xff0000)) >> 16;
//				tg += ((rgb1 & 0xff00) - (rgb2 & 0xff00)) >> 8;
//				tb += (rgb1 & 0xff) - (rgb2 & 0xff);
//				outIndex += height;
//			}
//			inIndex += width;
//		}
//	}
//
//	/**
//	 * 图片高斯模糊算法
//	 *
//	 * <br> Version: 1.0.0
//	 * <br> CreateTime: 2016年8月16日,上午9:37:35
//	 * <br> UpdateTime: 2016年8月16日,上午9:37:35
//	 * <br> CreateAuthor: 叶青
//	 * <br> UpdateAuthor: 叶青
//	 * <br> UpdateInfo:
//	 * @param in
//	 * @param out
//	 * @param width
//	 * @param height
//	 * @param radius
//	 */
//	public static void blurFractional(int[] in, int[] out, int width, int height, float radius) {
//		radius -= (int) radius;
//		float f = 1.0f / (1 + 2 * radius);
//		int inIndex = 0;
//
//		for (int y = 0; y < height; y++) {
//			int outIndex = y;
//
//			out[outIndex] = in[0];
//			outIndex += height;
//			for (int x = 1; x < width - 1; x++) {
//				int i = inIndex + x;
//				int rgb1 = in[i - 1];
//				int rgb2 = in[i];
//				int rgb3 = in[i + 1];
//
//				int a1 = (rgb1 >> 24) & 0xff;
//				int r1 = (rgb1 >> 16) & 0xff;
//				int g1 = (rgb1 >> 8) & 0xff;
//				int b1 = rgb1 & 0xff;
//				int a2 = (rgb2 >> 24) & 0xff;
//				int r2 = (rgb2 >> 16) & 0xff;
//				int g2 = (rgb2 >> 8) & 0xff;
//				int b2 = rgb2 & 0xff;
//				int a3 = (rgb3 >> 24) & 0xff;
//				int r3 = (rgb3 >> 16) & 0xff;
//				int g3 = (rgb3 >> 8) & 0xff;
//				int b3 = rgb3 & 0xff;
//				a1 = a2 + (int) ((a1 + a3) * radius);
//				r1 = r2 + (int) ((r1 + r3) * radius);
//				g1 = g2 + (int) ((g1 + g3) * radius);
//				b1 = b2 + (int) ((b1 + b3) * radius);
//				a1 *= f;
//				r1 *= f;
//				g1 *= f;
//				b1 *= f;
//				out[outIndex] = (a1 << 24) | (r1 << 16) | (g1 << 8) | b1;
//				outIndex += height;
//			}
//			out[outIndex] = in[width - 1];
//			inIndex += width;
//		}
//	}
//
//	public static int clamp(int x, int a, int b) {
//		return (x < a) ? a : (x > b) ? b : x;
//	}
//}
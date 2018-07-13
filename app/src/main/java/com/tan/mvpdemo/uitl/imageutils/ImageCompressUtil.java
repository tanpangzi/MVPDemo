package com.tan.mvpdemo.uitl.imageutils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.TextUtils;

import com.tan.mvpdemo.config.ConfigFile;
import com.tan.mvpdemo.uitl.Constant;
import com.tan.mvpdemo.uitl.FileUtil;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片裁剪压缩类
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class ImageCompressUtil {

    /**
     * 图片的最大尺寸，宽度大于 720，都将进行压缩
     */
    private static final int MAX_WIDTH = 720;
    /**
     * 图片的最大尺寸，高度大于 1280，都将进行压缩
     */
    private static final int MAX_HEIGHT = 1280;
    /**
     * 图片的大于200KB才压缩
     */
    private static final int MAX_FILE_SIZE = 200;
    /**
     * 图片质量压缩比例,这里100表示不压缩
     */
    private static final int QUALITY = 90;

    /**
     * 设置bitmap options属性，降低图片的质量，像素不会减少
     * 第一个参数为需要压缩的bitmap图片对象，第二个参数为压缩后图片保存的位置
     * 设置options 属性0-100，来实现压缩
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016/5/30 20:36
     * <br> UpdateTime: 2016/5/30 20:36
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param filePath
     *         图片全路径
     */
    public static String compressImg(String filePath) {

        if (!TextUtils.isEmpty(filePath)) {
            //       如果图片大小小于200kb
            if (FileUtil.getFileSize(filePath) / 1024 < MAX_FILE_SIZE) {
                return filePath;
            }

            try {
                Bitmap bmp = getBitmap(filePath);
                return saveBitmapToFile(filePath, bmp, QUALITY);
            } catch (Exception e) {
                e.printStackTrace();
                return filePath;
            }
        }
        return filePath;
    }

    /**
     * 处理多张图片
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime:: 2016年1月17日, 下午7:54:16
     * <br> UpdateTime: 2016年1月17日, 下午7:54:16
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: : (此处输入修改内容, 若无修改可不写.)
     *
     * @param srcPaths
     *         多张图片全路径逗号风分隔
     */
    public String compressImgs(String srcPaths) {
        try {
            if (TextUtils.isEmpty(srcPaths)) {
                return "";
            }

            String images[] = srcPaths.split(",");
            String compressImges = "";

            for (int i = 0; i < images.length; i++) {
                if (i == 0) {
                    compressImges += compressImg(images[i]);
                } else {
                    compressImges += "," + compressImg(images[i]);
                }
            }

            return compressImges;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 通过缩放图片像素 来减少图片占用内存大小================Canvas
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016/5/30 20:36
     * <br> UpdateTime: 2016/5/30 20:36
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param filePath
     *         图片文件全路径
     */
    public static String compressImg4Canvas(String filePath) {

        if (!TextUtils.isEmpty(filePath)) {
            //       如果图片大小小于200kb
            if (FileUtil.getFileSize(filePath) / 1024 < MAX_FILE_SIZE) {
                return filePath;
            }

            try {
                Bitmap bmp = getBitmap(filePath);

                // 尺寸压缩倍数,值越大，图片尺寸越小
                float ratio = getRatioSize(bmp.getWidth(), bmp.getHeight());
                // 压缩Bitmap到对应尺寸
                Bitmap result = Bitmap.createBitmap((int) (bmp.getWidth() / ratio), (int) (bmp.getHeight() / ratio), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(result);
                Rect rect = new Rect(0, 0, (int) (bmp.getWidth() / ratio), (int) (bmp.getHeight() / ratio));
                canvas.drawBitmap(bmp, null, rect, null);

                return saveBitmapToFile(filePath, result, QUALITY);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        return "";
    }


    /**
     * 降低图片质量压缩图片.
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-28,下午1:52:04
     * <br> UpdateTime: 2016-12-28,下午1:52:04
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param bitmap
     *         压缩图片.
     * @param size
     *         压缩质量,单位kb.
     *
     * @return 压缩完成的图片.
     */
    public static Bitmap compressImage(Bitmap bitmap, int size) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        int flag = baos.toByteArray().length;

        // 循环判断如果压缩后图片是否大于size kb,大于继续压缩
        if (flag / 1024 > size) {
            options = (size * 1024 * options) / flag;
            // 重置baos即清空baos
            baos.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bm = BitmapFactory.decodeStream(bais, null, null);// 把ByteArrayInputStream数据生成图片
        bitmap.recycle();
        return bm;
    }

    /**
     * 根据宽高 获取缩放比例
     *
     * @param bitWidth
     *         原始宽度
     * @param bitHeight
     *         原始高度
     *
     * @return 缩放比例
     */
    public static float getRatioSize(int bitWidth, int bitHeight) {
        // 图片最大分辨率
        int imageHeight = MAX_HEIGHT;
        int imageWidth = MAX_WIDTH;
        // 缩放比
        float ratio = 1;
        // 缩放比,由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        if (bitWidth > bitHeight && bitWidth > imageWidth) {
            // 如果图片宽度比高度大,以宽度为基准
            ratio = (float) bitWidth / imageWidth;
        } else if (bitWidth < bitHeight && bitHeight > imageHeight) {
            // 如果图片高度比宽度大，以高度为基准
            ratio = (float) bitHeight / imageHeight;
        }
        return ratio;
    }

    /**
     * 保存位图到本地文件
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-5,下午2:33:35
     * <br> UpdateTime: 2016-11-5,下午2:33:35
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param filePath
     *         文件全路径
     * @param bitmap
     *         位图对象
     * @param quality
     *         图片质量压缩比例,0-100 100为不压缩
     */
    public static String saveBitmapToFile(String filePath, Bitmap bitmap, int quality) {

        try {
            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            fileName = fileName.replace(".", Constant.IMAGE_LOGO_COMPRESS);
            File saveimg = new File(ConfigFile.PATH_IMAGES + fileName);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(saveimg));
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos);
            bos.flush();
            bos.close();
            bitmap.recycle();
            return ConfigFile.PATH_IMAGES + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    // ==========================================获取位图Bitmap

    /**
     * 获取位图
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-6,下午9:01:29
     * <br> UpdateTime: 2016-11-6,下午9:01:29
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param imageLocalPath
     *         图片路径
     */
    public static Bitmap getBitmap(String imageLocalPath) {

        if (TextUtils.isEmpty(imageLocalPath)) {// 文件路径为空
            return null;
        }

        return loadBitmap(imageLocalPath);
    }

    /**
     * 根据路径获得图片并压缩，返回bitmap用于显示
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年1月17日, 下午7:50:21
     * <br> UpdateTime: 2016年1月17日, 下午7:50:21
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param imageLocalPath
     *         图片路径
     */
    public static Bitmap loadBitmap(String imageLocalPath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        // 只是返回的是图片的宽和高，并不是返回一个Bitmap对象
        options.inJustDecodeBounds = true;
        // 信息没有保存在bitmap里面，而是保存在options里面
        BitmapFactory.decodeFile(imageLocalPath, options);
        // 缩略图大小为原始图片大小的几分之一。
        options.inSampleSize = calculateInSampleSize(options, MAX_WIDTH, MAX_HEIGHT);
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds设回false
        options.inJustDecodeBounds = false;
        // 设置是否深拷贝，与inPurgeable结合使用
        options.inInputShareable = true;
        // 设置为True时，表示系统内存不足时可以被回 收，设置为False时，表示不能被回收。
        options.inPurgeable = true;
        // 避免出现内存溢出的情况，进行相应的属性设置。
        // ALPHA_8 代表8位Alpha位图 ARGB_4444 代表16位ARGB位图 ARGB_8888 代表32位ARGB位图 RGB_565 代表16位RGB位图
        // 位图位数越高代表其可以存储的颜色信息越多，当然图像也越、占内存
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        return BitmapFactory.decodeFile(imageLocalPath, options);
    }

    /**
     * 计算图片的缩放值
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年1月17日, 下午7:49:52
     * <br> UpdateTime: 2016年1月17日, 下午7:49:52
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param options
     *         缩放值
     * @param reqWidth
     *         参考宽度
     * @param reqHeight
     *         参考高度
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
}
package com.tan.mvpdemo.uitl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;


import com.tan.mvpdemo.config.ConfigFile;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * bitmap工具类：用于Bitmap, byte array, Drawable之间进行转换以及图片缩放
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.  :
 */
public class BitmapUtil {

    /**
     * 将Bitmap转换成字节数组
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午6:44:28
     * <br> UpdateTime: 2016-1-23,下午6:44:28
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param b
     *         Bitmap
     */
    public static byte[] bitmapToByte(Bitmap b) {
        if (b == null) {
            return null;
        }

        ByteArrayOutputStream o = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, o);
        return o.toByteArray();
    }

    /**
     * 字节数组转换成Bitmap
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午6:44:28
     * <br> UpdateTime: 2016-1-23,下午6:44:28
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param b
     *         字节数组
     */
    public static Bitmap byteToBitmap(byte[] b) {
        return (b == null || b.length == 0) ? null : BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    /**
     * Drawable转换成Bitmap
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午6:44:28
     * <br> UpdateTime: 2016-1-23,下午6:44:28
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param d
     *         Drawable
     */
    public static Bitmap drawableToBitmap(Drawable d) {
        return d == null ? null : ((BitmapDrawable) d).getBitmap();
    }

    /**
     * 将Bitmap转换成Drawable
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午6:44:28
     * <br> UpdateTime: 2016-1-23,下午6:44:28
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param b
     *         Bitmap
     */
    @SuppressWarnings("deprecation")
    public static Drawable bitmapToDrawable(Bitmap b) {
        return b == null ? null : new BitmapDrawable(b);
    }

    /**
     * 将Drawable转换成字节数组
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午6:44:28
     * <br> UpdateTime: 2016-1-23,下午6:44:28
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param d
     *         Drawable
     */
    public static byte[] drawableToByte(Drawable d) {
        return bitmapToByte(drawableToBitmap(d));
    }

    /**
     * 将字节数组转换成Drawable
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午6:44:28
     * <br> UpdateTime: 2016-1-23,下午6:44:28
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param b
     *         字节数组
     */
    public static Drawable byteToDrawable(byte[] b) {
        return bitmapToDrawable(byteToBitmap(b));
    }

    /**
     * 从网络获取输入流
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午6:44:28
     * <br> UpdateTime: 2016-1-23,下午6:44:28
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param imageUrl
     *         图片路径
     * @param readTimeOutMillis
     *         超时时长
     */
    public static InputStream getInputStreamFromUrl(String imageUrl, int readTimeOutMillis) {
        return getInputStreamFromUrl(imageUrl, readTimeOutMillis, null);
    }

    /**
     * 从网络获取输入流
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午6:44:28
     * <br> UpdateTime: 2016-1-23,下午6:44:28
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param imageUrl
     *         图片路径
     * @param requestProperties
     *         http请求参数
     * @param readTimeOutMillis
     *         超时时长
     */
    public static InputStream getInputStreamFromUrl(String imageUrl, int readTimeOutMillis, Map<String, String> requestProperties) {
        InputStream stream = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            if (requestProperties == null || requestProperties.size() == 0 || con == null) {
                return null;
            }

            for (Map.Entry<String, String> entry : requestProperties.entrySet()) {
                if (!TextUtils.isEmpty(entry.getKey())) {
                    con.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            if (readTimeOutMillis > 0) {
                con.setReadTimeout(readTimeOutMillis);
            }
            stream = con.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stream;
    }

    /**
     * 通过网络路径imageUrl获取drawable
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午6:44:28
     * <br> UpdateTime: 2016-1-23,下午6:44:28
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param imageUrl
     *         图片路径
     * @param readTimeOutMillis
     *         超时时长
     */
    public static Drawable getDrawableFromUrl(String imageUrl, int readTimeOutMillis) {
        return getDrawableFromUrl(imageUrl, readTimeOutMillis, null);
    }

    /**
     * 通过网络路径imageUrl获取drawable
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午6:44:28
     * <br> UpdateTime: 2016-1-23,下午6:44:28
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param imageUrl
     *         图片路径
     * @param requestProperties
     *         http请求参数
     * @param readTimeOutMillis
     *         超时时长
     */
    public static Drawable getDrawableFromUrl(String imageUrl, int readTimeOutMillis, Map<String, String> requestProperties) {
        InputStream stream = getInputStreamFromUrl(imageUrl, readTimeOutMillis, requestProperties);
        Drawable d = Drawable.createFromStream(stream, "src");
        if (stream != null) {
            try {
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return d;
    }

    /**
     * 通过网络路径imageUrl获取Bitmap
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午6:44:28
     * <br> UpdateTime: 2016-1-23,下午6:44:28
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param imageUrl
     *         图片路径
     * @param readTimeOut
     *         超时时长
     */
    public static Bitmap getBitmapFromUrl(String imageUrl, int readTimeOut) {
        return getBitmapFromUrl(imageUrl, readTimeOut, null);
    }

    /**
     * 通过网络路径imageUrl获取Bitmap
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午6:44:28
     * <br> UpdateTime: 2016-1-23,下午6:44:28
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param imageUrl
     *         图片路径
     * @param requestProperties
     *         http请求参数
     */
    public static Bitmap getBitmapFromUrl(String imageUrl, int readTimeOut, Map<String, String> requestProperties) {
        InputStream stream = getInputStreamFromUrl(imageUrl, readTimeOut, requestProperties);
        Bitmap b = BitmapFactory.decodeStream(stream);
        if (stream != null) {
            try {
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return b;
    }

    /**
     * scale image
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午6:44:28
     * <br> UpdateTime: 2016-1-23,下午6:44:28
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param org
     *         原始bitmap
     * @param newWidth
     * @param newHeight
     */
    public static Bitmap scaleImageTo(Bitmap org, int newWidth, int newHeight) {
        return scaleImage(org, (float) newWidth / org.getWidth(), (float) newHeight / org.getHeight());
    }

    /**
     * scale image
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午6:44:28
     * <br> UpdateTime: 2016-1-23,下午6:44:28
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param org
     *         原始bitmap
     * @param scaleWidth
     *         sacle of width
     * @param scaleHeight
     *         scale of height
     */
    public static Bitmap scaleImage(Bitmap org, float scaleWidth, float scaleHeight) {
        if (org == null) {
            return null;
        }

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(org, 0, 0, org.getWidth(), org.getHeight(), matrix, true);
    }

    /**
     * 获取本地图片的宽高比
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-28,下午2:12:06
     * <br> UpdateTime: 2016-12-28,下午2:12:06
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param localImagePath
     *         文件路径
     */
    public static float height2Width(String localImagePath) {
        // 解析本地图片，获得图片尺寸
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 不生成Bitmap 只是获取bitmap的宽高，相对省内存
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(localImagePath, options);
        int height = options.outHeight;
        int width = options.outWidth;
        if (width != 0) {
            return (float) height / width;
        } else {
            return 0;
        }
    }

    // ************************ 截取当前屏幕****************************

    /**
     * 截取当前屏幕
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午4:39:25
     * <br> UpdateTime: 2016-11-24,下午4:39:25
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         上下文
     *
     * @return Bitmap
     */
    public static Bitmap takeScreenShot(Context context) {

        Bitmap returnBitmap = null;
        try {
            // View是你需要截图的View(整个屏幕)
            View view = ((Activity) context).getWindow().getDecorView();

            Bitmap bitmap = Bitmap.createBitmap(ScreenUtil.getScreenWidthPx(), ScreenUtil.getScreenHeightPx(), Bitmap.Config.RGB_565);
            view.draw(new Canvas(bitmap));

            // 压缩图的画布区域
            Rect newDst = new Rect(0, 0, bitmap.getWidth() * 3 / 5, bitmap.getHeight() * 3 / 5);
            // 原图的裁剪区域
            Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

            // *****************压缩图对象*******************//
            Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth() * 3 / 5, bitmap.getHeight() * 3 / 5, Bitmap.Config.RGB_565);

            // 绘制压缩图
            Canvas canvas = new Canvas(newBitmap);
            canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
            canvas.save();

            canvas.drawBitmap(bitmap, src, newDst, null);
            canvas.restore();
            // 回收bitmap
            bitmap.recycle();

            // 获取状态栏高度
            Rect frame = new Rect();
            ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
            int titleBarHeight = frame.top;

            // 去除状态栏
            if (bitmap != null) {
                returnBitmap = Bitmap.createBitmap(newBitmap, 0, titleBarHeight * 3 / 5, newBitmap.getWidth(), newBitmap.getHeight() - titleBarHeight);
                // 回收returnBitmap
                newBitmap.recycle();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnBitmap;
    }

    /**
     * 生成view对应的截图
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-30,上午10:20:18
     * <br> UpdateTime: 2016-12-30,上午10:20:18
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     * @param view
     */
    public static String takeScreenShot4View(Context context, View view) {

        Bitmap bitmap = null;
        String fileName = System.currentTimeMillis() + ".jpg";

        if (view == null) {
            bitmap = takeScreenShot(context);
        } else {
            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache();
            bitmap = view.getDrawingCache();
        }

        // 保存生成的位图
        saveBitmapToFile(ConfigFile.PATH_IMAGES, fileName, bitmap);

        return ConfigFile.PATH_IMAGES + fileName;
    }

    /**
     * 保存bitmap到文件
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-30,上午10:20:18
     * <br> UpdateTime: 2016-12-30,上午10:20:18
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param saveParentPath
     *         文件保存目录
     * @param fileName
     *         文件名称
     * @param bitmap
     *         位图对象
     */
    public static void saveBitmapToFile(String saveParentPath, String fileName, Bitmap bitmap) {

        BufferedOutputStream bos = null;
        try {
            File saveimg = new File(saveParentPath + fileName);
            bos = new BufferedOutputStream(new FileOutputStream(saveimg));
            if (bitmap != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
                // bitmap.recycle();// 保存图片不需要回收bitmap
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
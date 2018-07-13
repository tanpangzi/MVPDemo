package com.tan.mvpdemo.uitl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.tan.mvpdemo.BaseApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tanjun on 2017/5/31.
 * 手机本地地址管理
 */

public class UriTools {
    public static final int TYPE_FILE_IMAGE = 1;
    public static final int TYPE_FILE_VEDIO = 2;

    //得到输出文件的URI
    public static Uri getOutFileUri(int fileType) {
        File file = getOutFile(fileType);
        if (null != file) {
            return Uri.fromFile(file);
        } else {
            return null;
        }

    }

    private static String getPhoneRootPath() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            return BaseApplication.getInstance().getExternalCacheDir().getPath() + "/dyt/";
        } else {
            return "";
        }
    }

    //生成输出文件
    private static File getOutFile(int fileType) {
        String path = getPhoneRootPath();
        if (!TextUtils.isEmpty(path)) {
            File mediaStorageDir = new File(path);
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Logger.i("创建图片存储路径目录失败");
                    Logger.i("mediaStorageDir : " + mediaStorageDir.getAbsolutePath());
                    return null;
                }
            }
            File file = new File(getFilePath(mediaStorageDir, fileType));
            return file;
        } else {
            return null;
        }
    }

    //生成输出文件路径
    private static String getFilePath(File mediaStorageDir, int fileType) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());
        String filePath = mediaStorageDir.getPath() + File.separator;
        if (fileType == TYPE_FILE_IMAGE) {
            filePath += ("IMG_" + timeStamp + ".jpg");
        } else if (fileType == TYPE_FILE_VEDIO) {
            filePath += ("VIDEO_" + timeStamp + ".mp4");
        } else {
            return null;
        }
        return filePath;
    }

    /**
     * 保存Bitmap图片在SD卡中
     * 如果没有SD卡则存在手机中
     *
     * @param mbitmap 需要保存的Bitmap图片
     * @return 保存成功时返回图片的路径，失败时返回null
     */
    private static String savePhotoToSD(Bitmap mbitmap) {
        FileOutputStream outStream = null;
        String fileName = getOutFile(TYPE_FILE_IMAGE).getAbsolutePath();
        try {
            outStream = new FileOutputStream(fileName, false);
            // 把数据写入文件，100表示不压缩
            mbitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (outStream != null) {
                    // 记得要关闭流！
                    outStream.close();
                }
                if (mbitmap != null) {
                    mbitmap.recycle();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static Bitmap getFile(String path) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;//只读边,不读内容
        Bitmap bitmap = BitmapFactory.decodeFile(path, newOpts);
        newOpts.inJustDecodeBounds = false;
        Logger.e("options getFile w= " + newOpts.outWidth + " h = " + newOpts.outHeight);
        int size = 2;
        newOpts.inSampleSize = size;
        newOpts.inPreferredConfig = Bitmap.Config.ARGB_8888;//该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收
        bitmap = BitmapFactory.decodeFile(path, newOpts);
        return bitmap;
    }

    /**
     * 处理旋转后的图片
     *
     * @param originpath 原图路径
     * @return 返回修复完毕后的图片路径
     */
    public static String amendRotatePhoto(String originpath) {

        // 取得图片旋转角度
        int angle = readPictureDegree(originpath);

        // 把原图压缩后得到Bitmap对象
        Bitmap bmp = getFile(originpath);
        if (bmp == null) {
            return "";
        }
        // 修复图片被旋转的角度
        Bitmap bitmap = rotaingImageView(angle, bmp);
        if (bitmap == null) {
            return "";
        }
        // 保存修复后的图片并返回保存后的图片路径
        return savePhotoToSD(bitmap);
    }

    /**
     * 读取照片旋转角度
     *
     * @param path 照片路径
     * @return 角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转图片
     *
     * @param angle  被旋转角度
     * @param bitmap 图片对象
     * @return 旋转后的图片
     */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        Bitmap returnBm = null;
        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bitmap;
        }
        if (bitmap != returnBm) {
            bitmap.recycle();
        }
        return returnBm;
    }
}

package com.tan.mvpdemo.uitl;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by tanjun on 2017/5/31.
 * 图片处理类
 */

public class Bitmaps {

    private static int IMAGE_UP_SIZE = 1024;
    //    private static int IMAGE_UP_SIZE_MAX = 204;
//    private static int IMAGE_UP_SIZE_MIN = 124;
    private static int MAXNUMOFPIXELS = 12000000;

    public static String compressImage(String compressPath, String imgPath) {
        if (!TextUtils.isEmpty(imgPath) && !TextUtils.isEmpty(compressPath)) {
            double size = Files.getAutoFileOrFilesSize(compressPath);
            if ((size * 1024) > IMAGE_UP_SIZE) {
                return compressImageByPixel(compressPath, imgPath);
            }
        }
        return compressPath;
    }

    public static String compressImageByPixel(String compressPath, String imgPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;//只读边,不读内容
        Bitmap bitmap = BitmapFactory.decodeFile(compressPath, newOpts);
        newOpts.inJustDecodeBounds = false;
        newOpts.inSampleSize = Bitmaps.calculateInSampleSize(newOpts, 1280, 960);//设置采样率
//        newOpts.inSampleSize = computeSampleSize(newOpts, -1, MAXNUMOFPIXELS);
        //图像文件大小计算：文件的字节数=图像分辨率*图像量化位数/8
//        Logger.e("options w =  " + newOpts.outWidth + " h = " + newOpts.outHeight);
//        Logger.e("options inSampleSize =  " + Bitmaps.calculateInSampleSize(newOpts, 1280, 960));
        newOpts.inPreferredConfig = Bitmap.Config.ARGB_8888;//该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收
        bitmap = BitmapFactory.decodeFile(compressPath, newOpts);
        return compressImage(bitmap, imgPath, compressPath);//压缩好比例大小后再进行质量压缩
    }


    private static String compressImage(Bitmap beforBitmap, String imgPath, String compressPath) {


        int angle = UriTools.readPictureDegree(compressPath);
        beforBitmap = UriTools.rotaingImageView(angle, beforBitmap);

        // 可以捕获内存缓冲区的数据，转换成字节数组。
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        if (beforBitmap != null) {
            // 第一个参数：图片压缩的格式；第二个参数：压缩的比率；第三个参数：压缩的数据存放到bos中
            beforBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            int options = 100;
            // 循环判断压缩后的图片是否是大于100kb,如果大于，就继续压缩，否则就不压缩
            Logger.e("options bos >= " + (bos.toByteArray().length / 1024));
            while (bos.toByteArray().length / 1024 > IMAGE_UP_SIZE && options > 10) {
                bos.reset();// 置为空
                // 压缩options%
                beforBitmap.compress(Bitmap.CompressFormat.JPEG, options, bos);
                // 每次都减少10
                options -= 10;
            }
//            Logger.e("options = " + options);
//            Logger.e("options bos = " + (bos.toByteArray().length / 1024));
            try {
                FileOutputStream fos = new FileOutputStream(createFileWithDir(imgPath));//将压缩后的图片保存的本地上指定路径中
                fos.write(bos.toByteArray());
                fos.flush();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
                return compressPath;
            }
        }
        return imgPath;
    }

    /**
     * 根据路径创建一个文件。
     *
     * @param filePath
     * @return File
     */
    private static File createFileWithDir(String filePath) {
        if (isEmpty(filePath)) {
            return null;
        }
        int index = filePath.lastIndexOf(File.separator);
        String dirPath;  // 文件的目录（除去文件名）
        if (index == -1) {
            return null;
        } else {
            dirPath = filePath.substring(0, index);
            File file = new File(dirPath);
            isDirExists(file);  // 如果目录不存在，或者不是目录，首先创建目录
        }
        return new File(filePath);
    }

    /**
     * 判断字符串是否为空。
     *
     * @param string
     * @return boolean
     */
    public static boolean isEmpty(String string) {
        return (TextUtils.isEmpty(string)) ? true : false;
    }

    /**
     * 判断目录文件是否存在，如果不存在则创建。
     *
     * @param file
     */
    public static void isDirExists(File file) {
        if (!file.exists() || !file.isDirectory()) {
            boolean flag = file.mkdirs();
        }
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;

//            //使用需要的宽高的最大值来计算比率
//            final int suitedValue = reqHeight > reqWidth ? reqHeight : reqWidth;
//            final int heightRatio = Math.round((float) height / (float) suitedValue);
//            final int widthRatio = Math.round((float) width / (float) suitedValue);
//            inSampleSize = heightRatio > widthRatio ? heightRatio : widthRatio;//用最大

        }
        return inSampleSize;
    }

    /**
     * 图片的缩放方法
     *
     * @param orgBitmap ：源图片资源
     * @param newWidth  ：缩放后宽度
     * @param newHeight ：缩放后高度
     * @return
     */
    public static Bitmap getZoomImage(Bitmap orgBitmap, double newWidth, double newHeight) {
        if (null == orgBitmap) {
            return null;
        }
        if (orgBitmap.isRecycled()) {
            return null;
        }
        if (newWidth <= 0 || newHeight <= 0) {
            return null;
        }

        // 获取图片的宽和高
        float width = orgBitmap.getWidth();
        float height = orgBitmap.getHeight();
        // 创建操作图片的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(orgBitmap, 0, 0, (int) width, (int) height, matrix, true);
        return bitmap;
    }

    /**
     * 保存方法
     */
    public static void saveBitmap(String path, Bitmap bm) {

        File f = new File(path);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static int computeSampleSize(BitmapFactory.Options options,
                                        int minSideLength, int maxNumOfPixels) {

        int initialSize = computeInitialSampleSize(options, minSideLength,
                maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }


        return roundedSize;

    }

    private static int computeInitialSampleSize(BitmapFactory.Options options,
                                                int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;
        int lowerBound = (maxNumOfPixels == -1) ? 1 :
                (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 :
                (int) Math.min(Math.floor(w / minSideLength),
                        Math.floor(h / minSideLength));
        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) &&
                (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    /**
     * 图片上画文字
     */
    public static Bitmap drawTextAtBitmap(Bitmap bitmap, String name, float nameX, float nameY,
                                          String phone, float phoneX, float phoneY, float textSize) {
        int x = bitmap.getWidth();
        int y = bitmap.getHeight();
        // 创建一个和原图同样大小的位图
        Bitmap newbit = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newbit);
        Paint paint = new Paint();
        // 在原始位置0，0插入原图
        canvas.drawBitmap(bitmap, 0, 0, paint);
        //Color.parseColor(“#f34649”)
        paint.setColor(Color.rgb(0, 0, 0));
        paint.setTextSize(textSize);
        // 在原图指定位置写上字
        canvas.drawText(name, nameX, nameY, paint);
        canvas.drawText(phone, phoneX, phoneY, paint);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        // 存储
        canvas.restore();
        return newbit;
    }

    public static Bitmap getBitmap(String compressPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;//只读边,不读内容
        Bitmap bitmap = BitmapFactory.decodeFile(compressPath, newOpts);
        newOpts.inJustDecodeBounds = false;
        newOpts.inSampleSize = calculateInSampleSize(newOpts, 1280, 960);//设置采样率
//        newOpts.inSampleSize = computeSampleSize(newOpts, -1, MAXNUMOFPIXELS);
        //图像文件大小计算：文件的字节数=图像分辨率*图像量化位数/8
        Logger.e("options inSampleSize =  " + Bitmaps.calculateInSampleSize(newOpts, 1280, 960));
        newOpts.inPreferredConfig = Bitmap.Config.ARGB_8888;//该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收
        return BitmapFactory.decodeFile(compressPath, newOpts);
    }


}

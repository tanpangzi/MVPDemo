package com.tan.mvpdemo.uitl;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.storage.StorageManager;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.tan.mvpdemo.BaseApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by tanjun on 2018/5/2.
 */

public class Files {

    private static final int SIZETYPE_B = 1;//获取文件大小单位为B的double值

    private static final int SIZETYPE_KB = 2;//获取文件大小单位为KB的double值

    private static final int SIZETYPE_MB = 3;//获取文件大小单位为MB的double值

    private static final int SIZETYPE_GB = 4;//获取文件大小单位为GB的double值

    private static final int COMPANY_B = 1024;

    private static final int COMPANY_KB = (int) Math.pow(COMPANY_B, 2);

    private static final int COMPANY_MB = (int) Math.pow(COMPANY_B, 3);

    private static final int COMPANY_GB = (int) Math.pow(COMPANY_B, 4);

    public static String getStorageDirectory() {

        String path = getStoragePath(BaseApplication.getInstance(), false);
        if (!TextUtils.isEmpty(path)) {
            return path + File.separator + Constant.CACHE_CATALOG_NAME + File.separator;
        } else {
            return BaseApplication.getInstance().getApplicationContext().getCacheDir().getPath() + File.separator + Constant.CACHE_CATALOG_NAME + File.separator;
        }
    }

    public static String getDeleteDirectory() {

        String path = getStoragePath(BaseApplication.getInstance(), false);
        if (!TextUtils.isEmpty(path)) {
            return path + File.separator + "download" + File.separator;
        } else {
            return BaseApplication.getInstance().getCacheDir().getPath() + File.separator + "download" + File.separator;
        }
    }


    /**
     * @param mContext
     * @param is_removale 为false时得到的是内置sd卡路径，为true则为外置sd卡路径。
     * @return
     */
    private static String getStoragePath(Context mContext, boolean is_removale) {

        StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Object result = getVolumeList.invoke(mStorageManager);
            final int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                if (is_removale == removable) {
                    return path;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


//    public static String HOME_PATH = MyAppliaction.getContext().getFilesDir().getPath();

    public static void saveMyBitmap(Bitmap mBitmap, String path) {
        File f = new File(path);
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized InputStream readFileIn(String path) {
        InputStream inputStream = null;
        try {

            File file = new File(path);
            inputStream = new FileInputStream(file);

        } catch (Exception e) {

        }
        return inputStream;
    }

    /**
     * 调用此方法自动计算指定文件或指定文件夹的大小
     *
     * @param filePath 文件路径
     * @return 计算好的带B、KB、MB、GB的字符串
     */
    public static double getAutoFileOrFilesSize(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            long blockSize = 0;
            try {
                if (file.isDirectory()) {
                    blockSize = getFileSizes(file);
                } else {
                    blockSize = getFileSize(file);
                }
            } catch (Exception e) {
                e.printStackTrace();
//            UOLog.e(TAG,"获取失败!");
            }
            return FormetFileSize(blockSize, SIZETYPE_MB);
        }
        return 0;
    }

    /**
     * 获取指定文件夹
     *
     * @param f
     * @return
     * @throws Exception
     */
    private static long getFileSizes(File f) throws Exception {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSizes(flist[i]);
            } else {
                size = size + getFileSize(flist[i]);
            }
        }
        return size;
    }

    /**
     * 获取指定文件大小
     *
     * @param
     * @return
     * @throws Exception
     */
    private static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
        }
        return size;
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    private static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < COMPANY_B) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < (COMPANY_KB)) {
            fileSizeString = df.format((double) fileS / COMPANY_B) + "KB";
        } else if (fileS < COMPANY_MB) {
            fileSizeString = df.format((double) fileS / COMPANY_KB) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / COMPANY_MB) + "GB";
        }

        return fileSizeString;
    }

    /**
     * 转换文件大小,指定转换的类型
     *
     * @param fileS
     * @param sizeType
     * @return
     */
    private static double FormetFileSize(long fileS, int sizeType) {
        DecimalFormat df = new DecimalFormat("#.00");
        double fileSizeLong = 0;
        switch (sizeType) {
            case SIZETYPE_B:
                fileSizeLong = Double.valueOf(df.format((double) fileS));
                break;
            case SIZETYPE_KB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / COMPANY_B));
                break;
            case SIZETYPE_MB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / COMPANY_KB));
                break;
            case SIZETYPE_GB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / COMPANY_MB));
                break;
            default:
                break;
        }
        return fileSizeLong;
    }

    /**
     * 若缓存目录已满，删除时间最早的前 50% 文件。
     *
     * @param dirPath 要删除的目录（缓存目录）的路径
     * @return 是否删除成功
     * @author Joker
     */
    public static boolean deleteExtraFiles(String dirPath) {
        File dirFile = new File(dirPath);
        if (dirFile.exists()) {
            long startTime = System.currentTimeMillis();
            File[] files = dirFile.listFiles();
            List<FileInfo> fileList = new ArrayList<>();

            // 遍历所有文件，存入 fileList
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                FileInfo fileInfo = new FileInfo();
                fileInfo.setFileName(file.getName());
                fileInfo.setFilePath(file.getPath());
                fileInfo.setLastModified(file.lastModified());
                fileList.add(fileInfo);
            }
            long endTime = System.currentTimeMillis();
            Collections.sort(fileList);  // 将文件按时间排序

            // 删除前 50% 的文件
            int n = files.length;
            int i = 0;
            for (; i < n; i++) {
                String deletePath = fileList.get(i).getFilePath();
                deleteFile(deletePath);

            }
            if (i + 1 >= n) {

                return true;
            } else {

                return false;
            }
        }
        return false;
    }

    /**
     * 根据路径查找文件是否存在
     *
     * @param path
     * @return
     */
    public static boolean isExistFile(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * 根据路径删除文件
     *
     * @param path
     * @return
     */
    public static boolean deleteFile(String path) {
        boolean falg = false;
        try {
            if (isExistFile(path)) {
                File f = new File(path);
                f.delete();
                falg = true;
            }
        } catch (Exception e) {

        }

        return falg;
    }

    /**
     * 根据路径删除目录及其子目录
     *
     * @param dirPath 要删除的目录的路径
     */
    public static void deleteAllFiles(String dirPath) {
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
        } else {
            deleteFiles(dirFile);
        }
    }

    /**
     * 递归删除目录及其子目录的具体过程
     *
     * @param dirFile 要删除的目录对象
     */
    public static void deleteFiles(File dirFile) {
        if (dirFile.isFile()) {
            dirFile.delete();
            return;
        } else if (dirFile.isDirectory()) {
            File[] childFile = dirFile.listFiles();
            if (childFile == null || childFile.length == 0) {
                dirFile.delete();
                return;
            }
            for (File file : childFile) {
                deleteFiles(file);
            }
            dirFile.delete();

        }
    }

    /**
     * 存储文件名、文件路径及最后修改时间的封装类
     */
    static class FileInfo implements Comparable<FileInfo> {
        String fileName;

        String filePath;

        long lastModified;

        private String getFilePath() {
            return filePath;
        }

        private void setFileName(String fileName) {
            this.fileName = fileName;
        }

        private void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        private void setLastModified(long lastModified) {
            this.lastModified = lastModified;
        }

        @Override
        public int compareTo(FileInfo another) {
            return (lastModified > another.lastModified) ? 1 : -1;
        }
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
            Logger.d("FilePath not correct!");
            return null;
        } else {
            dirPath = filePath.substring(0, index);
            File file = new File(dirPath);

            isDirExists(file);  // 如果目录不存在，或者不是目录，首先创建目录
            File dir = new File(filePath);
            if (!dir.exists()) {
                try {
                    //在指定的文件夹中创建文件
                    dir.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
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

    public static boolean writeResponseBodyToDisk(ResponseBody body, String path) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = Files.createFileWithDir(path);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Logger.d("file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
}

package com.tan.mvpdemo.uitl;

import android.os.Environment;
import android.text.TextUtils;


import com.tan.mvpdemo.config.ConfigFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 文件操作工具类
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class FileUtil {

    /**
     * 构建本地文件目录
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-17,下午3:50:55
     * <br> UpdateTime: 2016-12-17,下午3:50:55
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    public static void createAllFile() {

        mkdirs(ConfigFile.PATH_BASE);
        mkdirs(ConfigFile.PATH_CAMERA);
        mkdirs(ConfigFile.PATH_DOWNLOAD);
        mkdirs(ConfigFile.PATH_IMAGES);
        mkdirs(ConfigFile.PATH_IMAGE_EDITTEMP);
        mkdirs(ConfigFile.PATH_LOG);

        // 当用户账户不为空的时候，构建用户私有文件夹
        // if (BaseApplication.getUserInfoBean() != null &&
        // !TextUtils.isEmpty(BaseApplication.getUserInfoBean().getUserNo())) {
        // // 以登录用户的帐号生成Md5串作为用户私有文件夹
        // FileConfig.PATH_USER_FILE = FileConfig.PATH_BASE +
        // (BaseApplication.getUserInfoBean().getUserNo()) + "/";
        // FileConfig.PATH_USER_IMAGE = FileConfig.PATH_USER_FILE + "image/";
        // FileConfig.PATH_USER_THUMBNAIL = FileConfig.PATH_USER_FILE +
        // "thumbnail/";
        //
        // mkdirs(FileConfig.PATH_USER_FILE);
        // mkdirs(FileConfig.PATH_USER_IMAGE);
        // mkdirs(FileConfig.PATH_USER_THUMBNAIL);
        // }
    }

    /**
     * 构建文件夹路径
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-17,下午3:51:24
     * <br> UpdateTime: 2016-12-17,下午3:51:24
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param path 文件夹路径
     */
    public static void mkdirs(String path) {
        new File(path).mkdirs();
        try {
            new File(path + "/.nomedia").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 计算文件夹大小（非递归遍历文件夹）
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-17,下午3:51:34
     * <br> UpdateTime: 2016-12-17,下午3:51:34
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param path 文件夹路径
     * @return 文件夹大小
     */
    public static float getDirectorySize(String path) {
        LinkedList<File> list = new LinkedList<>(); // 保存待遍历文件夹的列表
        File file = new File(path);
        float size = 0;

        size += getDirRootSize(file, list); // 调用遍历文件夹根目录文件的方法
        while (!list.isEmpty()) {
            File tmp = list.removeFirst();
            if (tmp.isDirectory()) {
                size += getDirRootSize(tmp, list);
            } else {
                size += tmp.length();
            }
        }

        return size;
    }

    /**
     * 遍历指定文件夹根目录下的文件
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-17,下午3:51:43
     * <br> UpdateTime: 2016-12-17,下午3:51:43
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param file 需要遍历的文件夹
     * @param list 存放文件链接的列表
     */
    public static float getDirRootSize(File file, LinkedList<File> list) {
        // 每个文件夹遍历都会调用该方法
        float size = 0;
        File[] files = file.listFiles();
        if (files == null || files.length == 0) {
            return size;
        }

        for (File f : files) {
            if (f.isDirectory()) {
                list.add(f);
            } else {
                // 这里列出当前文件夹根目录下的所有文件
                size += f.length();
            }
        }
        return size;
    }

    /**
     * 清理文件夹(递归清理)
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-17,下午3:51:51
     * <br> UpdateTime: 2016-12-17,下午3:51:51
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param file
     */
    public static void clearDirectory(File file) {
        File[] flist = file.listFiles();
        if (flist == null || flist.length == 0) {
            file.delete();
        } else {
            for (File f : flist) {
                if (f.isDirectory()) {
                    clearDirectory(f);
                } else {
                    f.delete();
                }
            }
            file.delete();
        }
    }

    /**
     * 判断文件是否存在
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-25,下午1:36:38
     * <br> UpdateTime: 2016-11-25,下午1:36:38
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param filePath
     */
    public static boolean fileIsExists(String filePath) {
        try {
            File f = new File(filePath);
            return f.exists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 普通权限复制文件
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-17,下午3:51:59
     * <br> UpdateTime: 2016-12-17,下午3:51:59
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param fromfile 源文件
     * @param tofile   目标文件
     */
    public static boolean copyFile(String fromfile, String tofile) {
        if (TextUtils.isEmpty(fromfile) || TextUtils.isEmpty(tofile)) {
            return false;
        } else {
            return copyFile(new File(fromfile), new File(tofile));
        }
    }

    /**
     * 普通权限复制文件
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-17,下午3:51:59
     * <br> UpdateTime: 2016-12-17,下午3:51:59
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param fromfile 源文件
     * @param tofile   目标文件
     */
    public static boolean copyFile(File fromfile, File tofile) {

        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        byte[] buffer = new byte[4 * 1024];
        int temp;
        try {
            tofile.createNewFile();
            inputStream = new FileInputStream(fromfile);
            outputStream = new FileOutputStream(tofile);
            while ((temp = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            tofile.delete();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            tofile.delete();
            return false;
        } finally {
            try {
                buffer.clone();
                outputStream.close();
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return true;
    }

    /**
     * 移动文件
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-22,下午2:38:23
     * <br> UpdateTime: 2016-1-22,下午2:38:23
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param sourceFilePath 源文件名
     * @param destFilePath   目标文件名
     */
    public static void moveFile(String sourceFilePath, String destFilePath) {
        if (!TextUtils.isEmpty(sourceFilePath) && !TextUtils.isEmpty(destFilePath)) {
            moveFile(new File(sourceFilePath), new File(destFilePath));
        } else {
            throw new RuntimeException("Both sourceFilePath and destFilePath cannot be null.");
        }
    }

    /**
     * 移动文件
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-22,下午2:38:23
     * <br> UpdateTime: 2016-1-22,下午2:38:23
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param srcFile  源文件
     * @param destFile 目标文件
     */
    public static void moveFile(File srcFile, File destFile) {
        boolean rename = srcFile.renameTo(destFile);
        if (!rename) {
            copyFile(srcFile, destFile);
            deleteFile(srcFile);
        }
    }

    /**
     * 删除文件
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-22,下午2:47:13
     * <br> UpdateTime: 2016-1-22,下午2:47:13
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param path 目标文件名字
     * @return true删除成功
     */
    public static boolean deleteFile(String path) {
        return path != null && deleteFile(new File(path));
    }

    /**
     * 删除文件
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-22,下午2:47:13
     * <br> UpdateTime: 2016-1-22,下午2:47:13
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param file 目标文件
     * @return true删除成功
     */
    public static boolean deleteFile(File file) {
        if (!file.exists()) {
            return true;
        } else if (file.isFile()) {
            return file.delete();
        } else if (!file.isDirectory()) {
            return false;
        } else {
            File[] arrs = file.listFiles();
            int lens = arrs.length;

            for (int i = 0; i < lens; ++i) {
                File f = arrs[i];
                if (f.isFile()) {
                    f.delete();
                } else if (f.isDirectory()) {
                    deleteFile(f.getAbsolutePath());
                }
            }

            return file.delete();
        }
    }

    /**
     * 获取文件大小
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-22,下午2:50:14
     * <br> UpdateTime: 2016-1-22,下午2:50:14
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param path 文件路径
     * @return -1 代表文件不存在
     */
    public static long getFileSize(String path) {
        if (TextUtils.isEmpty(path)) {
            return -1L;
        } else {
            File file = new File(path);
            return file.exists() && file.isFile() ? file.length() : -1L;
        }
    }

    /**
     * 文件大小转 B、MB、GB
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-22,下午2:50:59
     * <br> UpdateTime: 2016-1-22,下午2:50:59
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param size 1024
     * @return 1B
     */
    public static String showFileSize(long size) {
        String fileSize;
        if ((double) size < 1024.0D) {
            fileSize = size + "B";
        } else if ((double) size < 1048576.0D) {
            fileSize = String.format("%.1f", new Object[]{Double.valueOf((double) size / 1024.0D)}) + "KB";
        } else if ((double) size < 1.073741824E9D) {
            fileSize = String.format("%.1f", new Object[]{Double.valueOf((double) size / 1048576.0D)}) + "MB";
        } else {
            fileSize = String.format("%.1f", new Object[]{Double.valueOf((double) size / 1.073741824E9D)}) + "GB";
        }

        return fileSize;
    }

    /**
     * 遍历文件夹 得到文件列表 (过滤隐藏文件) 注意的是并不是所有的文件夹都可以进行读取的，权限问题
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-3-31,下午10:37:02
     * <br> UpdateTime: 2016-3-31,下午10:37:02
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param path
     * @param fileList
     */
    public static void getFileList(File path, HashMap<String, String> fileList) {
        // 如果是文件夹的话
        if (path.isDirectory()) {
            // 返回文件夹中有的数据
            File[] files = path.listFiles();
            // 先判断下有没有权限，如果没有权限的话，就不执行了
            if (null == files)
                return;

            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                // 过滤隐藏文件
                if (!file.isHidden()) {
                    getFileList(file, fileList);
                }
            }
        }
        // 如果是文件的话直接加入
        else {
            // 进行文件的处理
            String filePath = path.getAbsolutePath();
            // 文件名
            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            // 添加
            fileList.put(fileName, filePath);
        }
    }


    /**
     * 获取系统存储路径 data/data/
     * <p>
     * <br> Version: 3.0.0
     * <br> CreateAuthor: 叶青
     * <br> CreateTime: 2017/3/7 15:17
     * <br> UpdateAuthor: 叶青
     * <br> UpdateTime: 2017/3/7 15:17
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

    /**
     * 判断SDCard是否可用
     * <p>
     * <br> Version: 3.0.0
     * <br> CreateAuthor: 叶青
     * <br> CreateTime: 2017/3/7 15:19
     * <br> UpdateAuthor: 叶青
     * <br> UpdateTime: 2017/3/7 15:19
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡路径 /storage/emulated/0
     * <p>
     * <br> Version: 3.0.0
     * <br> CreateAuthor: 叶青
     * <br> CreateTime: 2017/3/7 15:19
     * <br> UpdateAuthor: 叶青
     * <br> UpdateTime: 2017/3/7 15:19
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    /**
     * 获取文件夹大小
     *
     * @param file File实例
     * @return long
     */
    public static long getFolderSize(File file) {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);

                } else {
                    size = size + fileList[i].length();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 删除指定目录下文件及目录
     *
     * @param deleteThisPath
     * @param filePath
     * @return
     */
    public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {// 处理目录
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    } else {// 目录
                        if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 格式化单位
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size/1024;
        if(kiloByte < 1) {
            return size + "KB";
        }

        double megaByte = kiloByte/1024;
        if(megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte/1024;
        if(gigaByte < 1) {
            BigDecimal result2  = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte/1024;
        if(teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }
}
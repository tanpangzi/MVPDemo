package com.tan.mvpdemo.config;

import android.os.Environment;

/**
 * 文件属性类
 * <p>
 * 设置文件目录
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年4月4日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class ConfigFile {

    // *************************** 应用使用的文件路径 *****************************//
    // 公用文件路径
    /** 应用文件夹名字 */
    private static final String PATH_BASE_NAME = "dyt";
    /** 应用根目录 */
    public static final String PATH_BASE = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + PATH_BASE_NAME + "/";
    /** 应用Log日志 */
    public static final String PATH_LOG = PATH_BASE + "Log/";
    /** 下载文件文件夹 */
    public static final String PATH_DOWNLOAD = PATH_BASE + "Download/";
    /** 拍照文件夹 */
    public static final String PATH_CAMERA = PATH_BASE + "Camera/";
    /** 应用基本缓存文件图片路径 */
    public static final String PATH_IMAGES = PATH_BASE + "Image/";
    /** galleryfinal编辑图片后保存的文件夹 */
    public static final String PATH_IMAGE_EDITTEMP = PATH_BASE + "EditTemp/";
    //	/** 拍照缓存文件 */
    //	public static final String PATH_IMAGE_TEMP = PATH_CAMERA + "temp.jpg";

    // // 用户私有文件路径
    // /** 用户私有缓存文件夹 */
    // public static String PATH_USER_FILE = "";
    // /** 用户私有图片缓存文件夹 */
    // public static String PATH_USER_IMAGE = "";
    // /** 用户私有图片缩略图 缓存文件夹 */
    // public static String PATH_USER_THUMBNAIL = "";

}
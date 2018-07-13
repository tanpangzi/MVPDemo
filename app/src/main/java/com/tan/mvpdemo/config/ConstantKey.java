package com.tan.mvpdemo.config;


import com.tan.mvpdemo.BaseApplication;

/**
 * SP文件的Key、value:*****:Intent数据传递的Key
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class ConstantKey {

    private static final String CONSTANT_KEY_BASE = BaseApplication.getInstance().getApplicationContext().getPackageName() + ".";

    /* *******************权限************************/
    /** gps监控 */
    public static final String PERMISSION_GPS_MONITOR = "zmxAPI:MONITORS";//GPS监控

    /** gps超级管理员 */
    public static final String PERMISSION_GPS_WARDEN = "zmxAPI:WARDEN";//GPS超级管理员

    /** gps安装 */
    public static final String PERMISSION_GPS_INSTALL = "zmxAPI:INSTALL";//GPS安装

    /** 请求接口TOKEN失效状态码 */
    public static final int RESPONSE_STATUS_TOKEN_ERROR = 101;

    /** token */
    public static final String TOKEN_KEY = "token";

    /** 版本更新 */
    public static final String VERCODE_KEY = "verCode";

    public static final String TYPE_KEY = "type";

    public static final String APP_TYPE = "1";

    /** 强制更新的code */
    public static final String FORCE_CODE = "1";

    /* ***********************SP数据模块*********************** */
    /** sp保存在data/data目录下的文件名 */
    public static final String SP_FILE_NAME = CONSTANT_KEY_BASE + ".spData";

    /** sp文件名---用户 */
    public static final String SP_KEY_FILE_USER_INFO = "sp_key_file_user_info";
    /** sp文件名---语言 */
    public static final String SP_KEY_FILE_LANGUAGE = "sp_key_file_language";
    /** sp文件名---版本号 */
    public static final String SP_KEY_FILE_VERSION = "sp_key_file_version";
    //    /** sp文件名---网络请求缓存 */
    //    public static final String SP_KEY_FILE_REQUEST = CONSTANT_KEY_BASE + "sp_key_file_request";
    /** sp文件名---移动网络下载 */
    public static final String SP_KEY_FILE_DOWN_4G = "sp_key_file_down_4g";

	/* *********************Intent数据模块********************* */
    /** 版本更新……apk下载路径 */
    public static final String INTENT_KEY_APK_PATH = CONSTANT_KEY_BASE + "apk.path";
    /** 版本更新……apk3000b */
    public static final String INTENT_KEY_APK_SIZE = CONSTANT_KEY_BASE + "apk.size";
    /** 位置 下标 */
    public static final String INTENT_KEY_POSITION = CONSTANT_KEY_BASE + "position";
    /** 数组b */
    public static final String INTENT_KEY_DATAS = CONSTANT_KEY_BASE + "dataList";
    /** 账号 */
    public static final String INTENT_KEY_ACCOUNT = CONSTANT_KEY_BASE + "account";
    /** 验证码 */
    public static final String INTENT_KEY_CODE = CONSTANT_KEY_BASE + "code";
    /** 类别 */
    public static final String INTENT_KEY_TYPE = CONSTANT_KEY_BASE + "type";
    /** 标题 */
    public static final String INTENT_KEY_TITLE = CONSTANT_KEY_BASE + "title";
    /** 字符串 */
    public static final String INTENT_KEY_STRING = CONSTANT_KEY_BASE + "string";
    public static final String INTENT_KEY_STRING1 = CONSTANT_KEY_BASE + "string1";
    /** 布尔值 */
    public static final String INTENT_KEY_BOOLEAN = CONSTANT_KEY_BASE + "boolean";
    public static final String INTENT_KEY_BOOLEAN1 = CONSTANT_KEY_BASE + "boolean1";
    /** id */
    public static final String INTENT_KEY_ID = CONSTANT_KEY_BASE + "id";
    public static final String INTENT_KEY_BUSINESS_ID = CONSTANT_KEY_BASE + "BUSINESSid";
    /** 数据 */
    public static final String INTENT_KEY_DATA = CONSTANT_KEY_BASE + "data";

}
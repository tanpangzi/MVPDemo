package com.tan.mvpdemo.uitl;

import android.os.Environment;
import android.text.TextUtils;
import android.widget.EditText;

import com.tan.mvpdemo.config.ConfigFile;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 字符串操作工具类:用于常见字符串操作
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class StringUtil {

    /**
     * 转换成千进制.
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年1月20日, 下午5:44:05
     * <br> UpdateTime: 2016年1月20日, 下午5:44:05
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param number
     *         原数.
     */
    public static String getThousandSystem(String number) {
        DecimalFormat df = new DecimalFormat("###,###,###,###");
        return df.format(Integer.valueOf(number));
    }

    /**
     * 转换成千进制.
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年1月20日, 下午5:44:05
     * <br> UpdateTime: 2016年1月20日, 下午5:44:05
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param number
     *         原数.
     */
    public static String getThousandSystem(int number) {
        DecimalFormat df = new DecimalFormat("###,###,###,###");
        return df.format(number);
    }

    /**
     * 生成带颜色字体
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-8-1,下午5:45:05
     * <br> UpdateTime: 2016-8-1,下午5:45:05
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param text
     *         需要处理的文本
     * @param color
     *         文本颜色 rgb #ffffff
     *
     * @return 处理后的html格式文本
     */
    public static String makeColorText(String text, String color) {

        return "<font color=" + color + ">" + text + "</font>";
    }

    /**
     * 生成大号字体
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-8-1,下午5:45:05
     * <br> UpdateTime: 2016-8-1,下午5:45:05
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param text
     *         需要处理的文本
     *
     * @return 处理后的html格式文本
     */
    public static String makeBigText(String text, int size) {
        String htmlStart = "<font >";
        String htmlEnd = "</font >";
        for (int i = 0; i < size; i++) {
            htmlStart += "<big>";
            htmlEnd = "</big>" + htmlEnd;
        }
        return htmlStart + text + htmlEnd;
    }

    /**
     * 生成大号带颜色字体
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-8-1,下午5:45:05
     * <br> UpdateTime: 2016-8-1,下午5:45:05
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param text
     *         需要处理的文本
     * @param color
     *         文本颜色 rgb #ffffff
     *
     * @return 处理后的html格式文本
     */
    public static String makeBigColorText(String text, String color) {

        return "<font color=" + color + "><big>" + text + "</big></font>";
    }

    // *************************************文件路径

    public static String getFileName(String path) {
        int separatorIndex = path.lastIndexOf("/");
        return (separatorIndex < 0) ? "" : path.substring(separatorIndex + 1);
    }

    /**
     * 根据网络图片路径,获取本地图片路径.
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-16,下午9:50:49
     * <br> UpdateTime: 2016-11-16,下午9:50:49
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param netPath
     *         网络图片路径
     */
    public static String getLocalImagePath(String netPath) {
        if (TextUtils.isEmpty(netPath)) {
            return "";
        }
        return ConfigFile.PATH_IMAGES + getFileName(netPath);
    }

    /**
     * 获取本地私有文件路径
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-16,下午9:50:49
     * <br> UpdateTime: 2016-11-16,下午9:50:49
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param parent
     *         父文件夹
     * @param netPath
     *         图片网络路径
     *
     * @return 本地文件存储绝对路径
     */
    public static String getLocalImagePath(String parent, String netPath) {
        if (TextUtils.isEmpty(netPath)) {
            return "";
        }
        return parent + getFileName(netPath);
    }

    /**
     * 获取用户缩略图
     *
     * @param netPath
     *         <br> Version: 1.0.0
     *         <br> CreateTime: 2016-11-16,下午9:51:48
     *         <br> UpdateTime: 2016-11-16,下午9:51:48
     *         <br> CreateAuthor: 叶青
     *         <br> UpdateAuthor: 叶青
     *         <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     */
    public static String getUserLocalThumPath(String netPath) {
        if (TextUtils.isEmpty(netPath)) {
            return "";
        }
        return Environment.getExternalStorageDirectory() + "/UField/EditTemp/" + getFileName(netPath);
    }

    /**
     * 获取网络文件在本地缓存的路径
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-3,下午1:57:32
     * <br> UpdateTime: 2016-11-3,下午1:57:32
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param netPath
     *         网络文件路径
     * @param localParentPath
     *         本地文件缓存目录
     *
     * @return 本地缓存文件的绝对路径
     */
    public static String getLocalCachePath(String netPath, String localParentPath) {
        return localParentPath + getFileName(netPath);
    }

    /**
     * 格式化文件大小
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-7-27,下午4:06:54
     * <br> UpdateTime: 2016-7-27,下午4:06:54
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param size
     */
    public static String formatFileSize(float size) {
        DecimalFormat format = new DecimalFormat("###,###,##0.00");
        if (size < 1024) {
            format.applyPattern("###,###,##0.00B");
        } else if (size >= 1024 && size < 1024 * 1024) {
            size /= 1024;
            format.applyPattern("###,###,##0.00KB");
        } else if (size >= 1024 * 1024 && size < 1024 * 1024 * 1024) {
            size /= (1024 * 1024);
            format.applyPattern("###,###,##0.00MB");
        } else if (size >= 1024 * 1024 * 1024 && size < 1024 * 1024 * 1024 * 1024) {
            size /= (1024 * 1024 * 1024);
            format.applyPattern("###,###,##0.00GB");
        } else if (size >= 1024 * 1024 * 1024 * 1024 && size < 1024 * 1024 * 1024 * 1024 * 1024) {
            size /= (1024 * 1024 * 1024 * 1024);
            format.applyPattern("###,###,##0.00GB");
        }
        return format.format(size);
    }

    /**
     * 描述：获取EditText控件所输入的文字 并去除空字符
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-4-21 上午10:50:04
     * <br> UpdateTime: 2016-4-21 上午10:50:04
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor:
     * <br> UpdateInfo: (修改内容描述)
     *
     * @param editTextInput
     *         要获取文本内容的edittext控件
     */
    public static String trimEditTextInput(EditText editTextInput) {
        return editTextInput.getText().toString().trim();
    }

    /**
     * 替换最后一个“随意字符”
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-28,下午1:23:43
     * <br> UpdateTime: 2016-12-28,下午1:23:43
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param str
     *         原字符串
     * @param strWaitReplace
     *         等待替换的字符串
     * @param strReplace
     *         替换后的字符串
     */
    public static String replaceLast(String str, String strWaitReplace, String strReplace) {
        try {
            StringBuffer buffer = new StringBuffer(str);
            String newStr = (buffer.replace(str.lastIndexOf(strWaitReplace), str.lastIndexOf(strWaitReplace) + 1, strReplace)).toString();
            return newStr;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 数组转arraylist
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016/3/24 9:44
     * <br> UpdateTime: 2016/3/24 9:44
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param obj
     *         Object[]
     */
    public static ArrayList getList(Object[] obj) {
        ArrayList list = new ArrayList();
        Collections.addAll(list, obj);
        return list;
    }

    /**
     * 是否为零字符串或空字符串
     *
     * @param str
     * @return
     */
    public static boolean isZeroNull(String str) {
        if (str == null)
            return true;

        if (str.length() == 0)
            return true;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '0') {
                return false;
            }
        }

        return true;
    }

    /**
     * 是否为空字符串
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }


}
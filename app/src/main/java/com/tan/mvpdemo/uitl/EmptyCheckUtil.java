package com.tan.mvpdemo.uitl;

import java.util.ArrayList;

/**
 * 常用空校验工具
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class EmptyCheckUtil {


    /**
     * 非空判断
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-22,下午12:55:25
     * <br> UpdateTime: 2016-1-22,下午12:55:25
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param str
     *         字符串
     *
     * @return true==空
     */
    public static boolean isEmpty(String str) {
        return isEmpty(str, false);
    }

    /**
     * 非空判断
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-22,下午12:55:25
     * <br> UpdateTime: 2016-1-22,下午12:55:25
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param str
     *         字符串
     * @param isReplace
     *         是否去除空格
     *
     * @return true==空
     */
    public static boolean isEmpty(String str, boolean isReplace) {
        if (str == null || str.length() == 0 || str.toLowerCase().equals("null")) {
            return true;
        } else {
            str = str.replaceAll(" ", "");
            return isReplace && str.length() == 0;
        }
    }

    /**
     * 非空判断
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-22,下午12:55:25
     * <br> UpdateTime: 2016-1-22,下午12:55:25
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param list
     *         ArrayList
     *
     * @return true==空
     */
    public static boolean isEmpty(ArrayList<Object> list) {
        if (list == null || list.size() <= 0) {
            return true;
        } else {
            return false;
        }
    }
}
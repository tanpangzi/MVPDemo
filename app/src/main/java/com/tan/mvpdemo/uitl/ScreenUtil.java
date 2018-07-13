package com.tan.mvpdemo.uitl;

import android.content.Context;


import com.tan.mvpdemo.BaseApplication;

import java.lang.reflect.Field;

/**
 * 屏幕显示工具类：用于获取通知栏高度;屏幕宽度、高度;dp、px、sp之間的转换
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class ScreenUtil {

    /**
     * 获取通知栏高度.
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午4:38:41
     * <br> UpdateTime: 2016-11-24,下午4:38:41
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @return 通知栏高度.
     */
    public static int getStatusBarHeight() {
        Context context = BaseApplication.getInstance().getApplicationContext();
        int statusBarHeight = 0;
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 描述：获取屏幕宽度
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午4:38:53
     * <br> UpdateTime: 2016-11-24,下午4:38:53
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    public static int getScreenWidthPx() {
        Context context = BaseApplication.getInstance().getApplicationContext();
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 描述：获取屏幕高度
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午4:38:59
     * <br> UpdateTime: 2016-11-24,下午4:38:59
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    public static int getScreenHeightPx() {
        Context context = BaseApplication.getInstance().getApplicationContext();
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午4:39:07
     * <br> UpdateTime: 2016-11-24,下午4:39:07
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param dpValue
     *         dp值
     */
    public static int dip2px(float dpValue) {
        Context context = BaseApplication.getInstance().getApplicationContext();
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午4:39:13
     * <br> UpdateTime: 2016-11-24,下午4:39:13
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param spValue
     *         sp值
     */
    public static int sp2px(float spValue) {
        Context context = BaseApplication.getInstance().getApplicationContext();
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午4:39:17
     * <br> UpdateTime: 2016-11-24,下午4:39:17
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param pxValue
     *         像素
     */
    public static int px2dip(float pxValue) {
        Context context = BaseApplication.getInstance().getApplicationContext();
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午4:39:25
     * <br> UpdateTime: 2016-11-24,下午4:39:25
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param pxValue
     *         像素
     */
    public static int px2sp(float pxValue) {
        Context context = BaseApplication.getInstance().getApplicationContext();
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
package com.tan.mvpdemo.uitl;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;


/**
 * Toast操作工具类
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class ToastUtil {

    /** LENGTH_LONG 时长 */
    private static final int LONG_DELAY = 3500; // 3.5 seconds
    /** LENGTH_SHORT 时长 */
    private static final int SHORT_DELAY = 2000; // 2 seconds
    /** Toast */
    private static Toast mToast;
    /** Toast内容 */
    private static String text = "";
    /** Toast更新内容的时间搓 */
    private static long currentTimeMillis = 0;
    /** 是否为LENGTH_SHORT */
    private static boolean isShort = true;
    /** Toast内容 */
    private static String className = "";

    /**
     * 显示提示信息
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午3:44:34
     * <br> UpdateTime: 2016-11-24,下午3:44:34
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param text
     *         提示内容
     */
    public static void showToast(Context context, String text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    /**
     * 显示提示信息(时间较长)
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午3:44:34
     * <br> UpdateTime: 2016-11-24,下午3:44:34
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param text
     *         提示内容
     */
    public static void showLongToast(Context context, String text) {
        show(context, text, Toast.LENGTH_LONG);
    }

    /**
     * 显示提示信息(时间较长)
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午3:44:34
     * <br> UpdateTime: 2016-11-24,下午3:44:34
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         上下文
     * @param text
     *         提示内容
     * @param duration
     *         LENGTH_SHORT  LENGTH_LONG
     */
    private static void show(Context context, String text, int duration) {
        if (null == context || TextUtils.isEmpty(text)) {
            return;
        }
        ToastUtil.className = context.getClass().getName();
        ToastUtil.isShort = duration == Toast.LENGTH_SHORT;

        if (mToast != null) {
            mToast.setText(text);
            if (!ToastUtil.text.equals(text)) {
                mToast.setDuration(duration);
                mToast.show();
            } else {
                if (!isShowing()) {
                    mToast.show();
                }
            }
        } else {
            mToast = Toast.makeText(context, text, duration);
            mToast.show();
        }
        ToastUtil.text = text;
        ToastUtil.currentTimeMillis = System.currentTimeMillis();
    }

    /**
     * Toast是否在显示中
     * <p>
     * <br> Version: 3.0.0
     * <br> CreateAuthor: 叶青
     * <br> CreateTime: 2017/3/3 11:53
     * <br> UpdateAuthor: 叶青
     * <br> UpdateTime: 2017/3/3 11:53
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @return true Toast显示中
     */
    private static boolean isShowing() {
        int delay = LONG_DELAY;
        if (isShort) {
            delay = SHORT_DELAY;
        }
        return System.currentTimeMillis() - ToastUtil.currentTimeMillis < delay;
    }


    /**
     * 隐藏toast
     * <p>
     * <br> Version: 3.0.0
     * <br> CreateAuthor: 叶青
     * <br> CreateTime: 2017/3/3 11:53
     * <br> UpdateAuthor: 叶青
     * <br> UpdateTime: 2017/3/3 11:53
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    public static void cancelToast(Context context) {
        //        LogUtil.i(ToastUtil.className + "....." + (context.getClass().getName()));
        try {
            if (ToastUtil.className.equals(context.getClass().getName())) {// 目的為了防止页面切换的时候 在上一个页面的onDestroy方法 关闭了当前页面的toast
                if (mToast != null) {
                    mToast.cancel();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (mToast != null) {
                mToast.cancel();
            }
        }
    }

}
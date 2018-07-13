package com.tan.mvpdemo.uitl;

import android.view.View;

/**
 * 其他工具类
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class OtherUtils {

    /** 单例 */
    private static OtherUtils instance;
    private long lastClickTime;
    private int viewId;

    public static OtherUtils getInstance() {
        // if(instance == null){
        synchronized (OtherUtils.class) {
            if (instance == null) {
                instance = new OtherUtils();
            }
        }
        // }
        return instance;
    }

    /**
     * 是否为快速点击
     *
     * @return true 是快速点击
     */
    public boolean isFastClick(View view) {
        long time = System.currentTimeMillis();
        if (viewId == view.getId()) {
            long timeDifference = time - lastClickTime;
            // LogUtil.i(timeDifference + ".....");
            lastClickTime = time;
            return timeDifference < 500;
        } else {
            viewId = view.getId();
            lastClickTime = time;
            return false;
        }
    }
}
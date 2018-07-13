package com.tan.mvpdemo.activityMvp;

/**
 * <br> Description 公共的view层
 * 一些公共的view方法放在这里
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp
 * <br> Date: 2018/6/7
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public interface BaseView {
    /** 消息提示 */
    void showToast(String msg);
    /** 成功 */
    void onSuccess();
}

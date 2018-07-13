package com.tan.mvpdemo.activityMvp;

/**
 * <br> Description Presenter的基类
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp
 * <br> Date: 2018/6/28
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public interface BasePresenter {
    //默认初始化
    void start();

    //Activity关闭把view对象置为空
    void detach();
}

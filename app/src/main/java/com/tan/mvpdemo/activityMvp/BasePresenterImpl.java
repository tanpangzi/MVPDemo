package com.tan.mvpdemo.activityMvp;

/**
 * <br> Description 继承BasePresenter的抽象类 P层
 * 在这里实例化了view层
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo
 * <br> Date: 2018/6/28
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter {

    protected V view;

    public BasePresenterImpl(V view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void detach() {

    }
}

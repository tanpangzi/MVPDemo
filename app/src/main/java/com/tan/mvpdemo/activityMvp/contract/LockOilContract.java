package com.tan.mvpdemo.activityMvp.contract;

import com.tan.mvpdemo.activityMvp.BasePresenter;
import com.tan.mvpdemo.activityMvp.BaseView;

import java.util.Map;

import rx.Observable;

/**
 * <br> Description
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp.contract
 * <br> Date: 2018/6/14
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public interface LockOilContract {
    interface LockOilModel {
        Observable<Object> lockInstrution(Map<String, String> map);
    }

    interface LockOilView extends BaseView{
        /** 获取锁状态 */
        String getLockStatus();
    }

    interface LockOilPresenter extends BasePresenter{
        void lockInstruction(String imeiId);
    }
}

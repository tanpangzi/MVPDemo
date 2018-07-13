package com.tan.mvpdemo.activityMvp.model;

import com.tan.mvpdemo.activityMvp.contract.MainContract;
import com.tan.mvpdemo.bean.UpdateInfoBean;
import com.tan.mvpdemo.common.http.RequestServer;
import com.tan.mvpdemo.common.http.ResponseFunc;

import java.util.HashMap;

import rx.Observable;

/**
 * <br> Description mainactivity M层
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp.model
 * <br> Date: 2018/6/4
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class MainModel implements MainContract.MainModel {

    /** 检查更新 */
    @Override
    public Observable<UpdateInfoBean> checkVersion(HashMap<String, String> map) {
        return RequestServer
                .createRetrofit()
                .checkVersion(map)
                .map(new ResponseFunc<UpdateInfoBean>());
    }

}

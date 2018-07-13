package com.tan.mvpdemo.activityMvp.model;

import com.tan.mvpdemo.activityMvp.contract.GpsInstallContract;
import com.tan.mvpdemo.bean.gpsInstall.GPSBean;
import com.tan.mvpdemo.common.http.RequestServer;
import com.tan.mvpdemo.common.http.ResponseFunc;

import java.util.Map;

import rx.Observable;

/**
 * <br> Description gps安装 信号检测 m层
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp.model
 * <br> Date: 2018/6/14
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class GpsInstallModel implements GpsInstallContract. GpsInstallModel {

    @Override
    public Observable<GPSBean> getLoctionInfo(Map<String, String> map) {
        return RequestServer.createRetrofit().getGpsLocationBean(map).map(new ResponseFunc<GPSBean>());
    }

    @Override
    public Observable<Object> tearDown(Map<String, String> map) {
        return RequestServer.createRetrofit().tearDown(map).map(new ResponseFunc<Object>());
    }

    @Override
    public Observable<Object> installComplete(Map<String, String> map) {
        return RequestServer.createRetrofit().installComplete(map).map(new ResponseFunc<Object>());
    }

}

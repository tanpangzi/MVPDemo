package com.tan.mvpdemo.activityMvp.model;

import com.tan.mvpdemo.activityMvp.contract.ZMXDeviceInfoContract;
import com.tan.mvpdemo.bean.gpsInstall.ZMXGPSDeviceInfoBean;
import com.tan.mvpdemo.common.http.RequestServer;
import com.tan.mvpdemo.common.http.ResponseFunc;

import java.util.Map;

import rx.Observable;

/**
 * <br> Description
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp.model
 * <br> Date: 2018/6/6
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class ZMXDeviceInfoModel implements ZMXDeviceInfoContract.ZMXDeviceInfoModel {

    /** 根据imei获取设备信息 */
    @Override
    public Observable<ZMXGPSDeviceInfoBean> getDeviceInfo(Map<String, String> map) {
        return RequestServer.createRetrofit().getDeviceInfo(map).map(new ResponseFunc<ZMXGPSDeviceInfoBean>());
    }

    /** 传入sim imei 下一步*/
    @Override
    public Observable<Object> detect(Map<String, String> map) {
        return RequestServer.createRetrofit().detect(map).map(new ResponseFunc<Object>());
    }

}

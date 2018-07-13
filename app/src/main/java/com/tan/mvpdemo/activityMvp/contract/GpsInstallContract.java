package com.tan.mvpdemo.activityMvp.contract;

import com.tan.mvpdemo.activityMvp.BasePresenter;
import com.tan.mvpdemo.activityMvp.BaseView;
import com.tan.mvpdemo.bean.gpsInstall.GPSBean;

import java.util.Map;

import rx.Observable;


/**
 * <br> Description gps安装 信号检测
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp.contract
 * <br> Date: 2018/6/14
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public interface GpsInstallContract{
    interface GpsInstallModel {
        Observable<GPSBean> getLoctionInfo(Map<String, String> map);
        Observable<Object> tearDown(Map<String, String> map);
        Observable<Object> installComplete(Map<String, String> map);

    }

    interface GpsInstallView extends BaseView{
        /** 获取gps信息 */
        void getGpsLocationInfo(String subTitle , boolean isComplete, GPSBean bean);
        /** 拆除 安装完成共用方法*/
        void commonFun(String tip, int type);
        /** 安装完成 */
        void installComplete();
    }

    interface GpsInstallPresenter{
        /** 拆除 */
        void tearDown(String imeiId);

        /** 安装完成 */
        void installComplete(String custId);

        /** 获取gps信息 */
        void getLocationInfo(String subTitle ,String custId, boolean isComplete);
    }

}

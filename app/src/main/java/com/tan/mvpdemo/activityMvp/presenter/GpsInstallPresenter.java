package com.tan.mvpdemo.activityMvp.presenter;

import com.tan.mvpdemo.BaseApplication;
import com.tan.mvpdemo.activityMvp.contract.GpsInstallContract;
import com.tan.mvpdemo.activityMvp.model.GpsInstallModel;
import com.tan.mvpdemo.bean.gpsInstall.GPSBean;
import com.tan.mvpdemo.common.http.FilterSubscriber;
import com.tan.mvpdemo.uitl.ThreeDES;

import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <br> Description gps安装 信号检测P层
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp.presenter
 * <br> Date: 2018/6/14
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class GpsInstallPresenter implements GpsInstallContract.GpsInstallPresenter {

    GpsInstallContract.GpsInstallView view;
    GpsInstallContract.GpsInstallModel model;

    public GpsInstallPresenter(GpsInstallContract.GpsInstallView view) {
        this.view = view;
        model = new GpsInstallModel();
    }

    /** 拆除 */
    @Override
    public void tearDown(String imeiId) {
        Map<String, String> map = ThreeDES.getPostHeadMap();
        map.put("token", BaseApplication.getInstance().getToken());
        map.put("imeiId", imeiId);

        model.tearDown(map)
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<Object>() {
                    @Override
                    public void onNext(Object data) {

                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(String str) {
                        view.showToast(str);
                    }
                });

    }

    /** 安装完成 */
    @Override
    public void installComplete(String custId) {
        Map<String, String> map = ThreeDES.getPostHeadMap();
        map.put("token", BaseApplication.getInstance().getToken());
        map.put("userId", BaseApplication.getInstance().getUserInfoBean().getUserId());
        map.put("custId", custId);

        model.installComplete(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<Object>() {
                    @Override
                    public void onNext(Object data) {
                        view.showToast("安装完成");
                        view.installComplete();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(String str) {
                        view.showToast(str);
                    }
                });
    }

     /** 获取定位信息 */
    @Override
    public void getLocationInfo(final String subTitle , final String custId, final boolean isComplete) {
        Map<String, String> map = ThreeDES.getPostHeadMap();
        map.put("token", BaseApplication.getInstance().getToken());
        map.put("custId", custId);

        model.getLoctionInfo(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<GPSBean>() {
                    @Override
                    public void onNext(GPSBean data) {
                        view.getGpsLocationInfo(subTitle, isComplete, data);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(String str) {
                        view.showToast(str);
                    }
                });
    }

}

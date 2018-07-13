package com.tan.mvpdemo.activityMvp.presenter;

import android.text.TextUtils;

import com.tan.mvpdemo.BaseApplication;
import com.tan.mvpdemo.activityMvp.BasePresenterImpl;
import com.tan.mvpdemo.activityMvp.contract.ZMXDeviceInfoContract;
import com.tan.mvpdemo.activityMvp.model.ZMXDeviceInfoModel;
import com.tan.mvpdemo.bean.gpsInstall.ZMXGPSDeviceInfoBean;
import com.tan.mvpdemo.common.http.FilterSubscriber;
import com.tan.mvpdemo.uitl.ThreeDES;

import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <br> Description
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp.presenter
 * <br> Date: 2018/6/6
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class ZMXDeviceInfoPresenter extends BasePresenterImpl<ZMXDeviceInfoContract.ZMXDeviceInfoView> implements ZMXDeviceInfoContract.ZMXDeviceInfoPresenter {

    ZMXDeviceInfoContract.ZMXDeviceInfoModel model;

    public ZMXDeviceInfoPresenter(ZMXDeviceInfoContract.ZMXDeviceInfoView view) {
        super(view);
    }

    /** 根据Imei获取设备信息 */
    @Override
    public void getInfoByImei(String custId) {
        String imeiId = view.getImei();
        if (TextUtils.isEmpty(imeiId)){
            view.showToast("请扫描或输入Imei号");
            return;
        }

        Map<String, String> map = ThreeDES.getPostHeadMap();
        map.put("token", BaseApplication.getInstance().getToken());
        map.put("custId", custId);
        map.put("imeiId", imeiId);

        model.getDeviceInfo(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<ZMXGPSDeviceInfoBean>() {
                    @Override
                    public void onNext(ZMXGPSDeviceInfoBean data) {
                        view.getDeviceInfoByImei(data);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(String str) {
                        /** 显示错误信息 */
                        view.showToast(str);
                    }
                });


    }

    /** GPS安装 下一步 */
    @Override
    public void detect(String custId) {
        String imei = view.getImei(); //imei号
        String deviceType = view.getDeviceType(); //设备类型
        String deviceName = view.getDeviceName(); //设备名称
        String sim = view.getSimId(); //sim卡

        if (TextUtils.isEmpty(imei)){
            view.showToast("imei号不能为空");
            return;
        }

        if (TextUtils.isEmpty(deviceType)){
            view.showToast("设备类型不能为空");
            return;
        }

        if (TextUtils.isEmpty(deviceName)){
            view.showToast("设备名称不能为空");
            return;
        }

        if (TextUtils.isEmpty(sim)){
            view.showToast("sim卡不能为空");
            return;
        }

        Map<String, String> map = ThreeDES.getPostHeadMap();
        map.put("token", BaseApplication.getInstance().getToken());
        map.put("custId", custId);
        map.put("imeiId", imei);
        map.put("simId", sim);
        model.detect(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<Object>() {
                    @Override
                    public void onNext(Object data) {
                         /** 成功 */
                        view.onSuccess();
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

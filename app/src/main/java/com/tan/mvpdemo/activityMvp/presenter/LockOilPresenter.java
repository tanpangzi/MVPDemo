package com.tan.mvpdemo.activityMvp.presenter;

import com.tan.mvpdemo.BaseApplication;
import com.tan.mvpdemo.activityMvp.BasePresenterImpl;
import com.tan.mvpdemo.activityMvp.contract.LockOilContract;
import com.tan.mvpdemo.activityMvp.model.LockOilModel;
import com.tan.mvpdemo.common.http.FilterSubscriber;
import com.tan.mvpdemo.uitl.ThreeDES;

import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <br> Description
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp.presenter
 * <br> Date: 2018/6/14
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class LockOilPresenter extends BasePresenterImpl<LockOilContract.LockOilView> implements LockOilContract.LockOilPresenter {

    LockOilContract.LockOilModel model;

    public LockOilPresenter(LockOilContract.LockOilView view) {
        super(view);
    }

    /** 锁油断电 */
    @Override
    public void lockInstruction(String imeiId) {
        Map<String, String> map = ThreeDES.getPostHeadMap();
        String lockStatus = view.getLockStatus(); //选择操作指令
        String uId = BaseApplication.getInstance().getUserInfoBean().getUserId();

        map.put("token", BaseApplication.getInstance().getToken());
        map.put("imeiId", imeiId);
        map.put("lock", lockStatus);
        map.put("uId", uId);
        map.put("tenantId", "0000100001");

        model.lockInstrution(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<Object>() {
                    @Override
                    public void onNext(Object data) {
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

package com.tan.mvpdemo.activityMvp.presenter;

import android.text.TextUtils;

import com.tan.mvpdemo.BaseApplication;
import com.tan.mvpdemo.activityMvp.BasePresenterImpl;
import com.tan.mvpdemo.activityMvp.contract.ZMXCarContract;
import com.tan.mvpdemo.activityMvp.model.ZMXCarModel;
import com.tan.mvpdemo.bean.gpsInstall.CustIdBean;
import com.tan.mvpdemo.bean.PickerCardBean;
import com.tan.mvpdemo.common.http.FilterSubscriber;
import com.tan.mvpdemo.uitl.ThreeDES;

import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <br> Description
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp.presenter
 * <br> Date: 2018/6/5
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class ZMXCarPresenter extends BasePresenterImpl<ZMXCarContract.ZMXCarView> implements ZMXCarContract.ZMXCarPresenter {

    ZMXCarContract.ZMXCarModel model;

    public ZMXCarPresenter(ZMXCarContract.ZMXCarView view) {
        super(view);
    }

    /** 获取组织信息 */
    @Override
    public void getOrgInfo() {
        String token = BaseApplication.getInstance().getToken();
        Map<String, String> map = ThreeDES.getPostHeadMap();
        map.put("token", token);

        model.getOrgInfo(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<PickerCardBean>() {
                    @Override
                    public void onNext(PickerCardBean data) {
                        view.getOrgList(data);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(String str) {

                    }
                });
    }

    /**
     * 提交生成信息
     */
    @Override
    public void createCustomerInfo() {
        String name = view.getCustomerName(); //用户名
        String carNum = view.getCarNum(); //车牌号
        String orgId = view.getOrg(); //组织id
        String token = BaseApplication.getInstance().getToken(); //token

        if (TextUtils.isEmpty(name)){
            view.showToast("请输入用户姓名");
            return;
        }

        if (TextUtils.isEmpty(carNum)){
            view.showToast("请输入车牌号");
            return;
        }

        if (TextUtils.isEmpty(orgId)){
            view.showToast("请选择所属组织");
            return;
        }

        Map<String, String> map = ThreeDES.getPostHeadMap();
        map.put("custName", name);
        map.put("plateNo", carNum);
        map.put("orgId", orgId);
        map.put("token", token);

        model.createCustomerInfo(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<CustIdBean>() {
                    @Override
                    public void onNext(CustIdBean bean) {
                        view.getCustId(bean.getCustId());
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

    @Override
    public void start() {

    }

    @Override
    public void detach() {

    }
}

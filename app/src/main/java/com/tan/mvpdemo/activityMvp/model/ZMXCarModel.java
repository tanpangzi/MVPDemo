package com.tan.mvpdemo.activityMvp.model;


import com.tan.mvpdemo.activityMvp.contract.ZMXCarContract;
import com.tan.mvpdemo.bean.gpsInstall.CustIdBean;
import com.tan.mvpdemo.bean.PickerCardBean;
import com.tan.mvpdemo.common.http.RequestServer;
import com.tan.mvpdemo.common.http.ResponseFunc;

import java.util.Map;

import rx.Observable;

/**
 * <br> Description 提交客户信息
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp.model
 * <br> Date: 2018/6/5
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class ZMXCarModel implements ZMXCarContract.ZMXCarModel {

    /** 获取组织信息 */
    @Override
    public Observable<PickerCardBean> getOrgInfo(Map<String, String> map) {
        return RequestServer.createRetrofit().getOrgInfo(map).map(new ResponseFunc<PickerCardBean>());
    }

    /** 提交客户信息 */
    @Override
    public Observable<CustIdBean> createCustomerInfo(Map<String, String> map) {
        return RequestServer.createRetrofit()
                .createCustomerInfo(map)
                .map(new ResponseFunc<CustIdBean>());
    }
}

package com.tan.mvpdemo.activityMvp.model;

import com.tan.mvpdemo.activityMvp.contract.LockOilContract;
import com.tan.mvpdemo.common.http.RequestServer;
import com.tan.mvpdemo.common.http.ResponseFunc;

import java.util.Map;

import rx.Observable;

/**
 * <br> Description
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp.model
 * <br> Date: 2018/6/14
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class LockOilModel implements LockOilContract.LockOilModel {
    @Override
    public Observable<Object> lockInstrution(Map<String, String> map) {
        return RequestServer.createRetrofit().lockInstructiom(map).map(new ResponseFunc<Object>());
    }
}

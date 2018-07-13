package com.tan.mvpdemo.activityMvp.model;


import com.tan.mvpdemo.bean.UserInfoLoginBean;
import com.tan.mvpdemo.common.http.RequestServer;
import com.tan.mvpdemo.common.http.ResponseFunc;
import com.tan.mvpdemo.activityMvp.contract.LoginContract;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by tanjun on 2018/5/31.
 * 登陆网络请求m层
 */

public class LoginModel implements LoginContract.LoginModel {

    @Override
    public Observable<UserInfoLoginBean> login(HashMap<String, String> map) {
        return RequestServer
                .createRetrofit()
                .login(map)
                .map(new ResponseFunc<UserInfoLoginBean>());
    }

}

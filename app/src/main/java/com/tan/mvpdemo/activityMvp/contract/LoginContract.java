package com.tan.mvpdemo.activityMvp.contract;

import com.tan.mvpdemo.activityMvp.BasePresenter;
import com.tan.mvpdemo.activityMvp.BaseView;
import com.tan.mvpdemo.bean.UserInfoLoginBean;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by tanjun on 2018/3/15.
 * 登录contract
 */

public interface LoginContract {

    interface LoginModel {
        Observable<UserInfoLoginBean> login(HashMap<String,String> map);
    }

    interface LoginView extends BaseView{
        String getName();
        String getPassword();
    }

    interface LoginPresenter extends BasePresenter{
        void login();
    }
}

package com.tan.mvpdemo.activityMvp.presenter;

import android.text.TextUtils;

import com.tan.mvpdemo.BaseApplication;
import com.tan.mvpdemo.activityMvp.BasePresenterImpl;
import com.tan.mvpdemo.bean.UserInfoLoginBean;
import com.tan.mvpdemo.common.http.FilterSubscriber;
import com.tan.mvpdemo.activityMvp.contract.LoginContract;
import com.tan.mvpdemo.activityMvp.model.LoginModel;
import com.tan.mvpdemo.uitl.Constant;
import com.tan.mvpdemo.uitl.ThreeDES;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tanjun on 2018/5/31.
 * 登录Presnter
 */
public class LoginPresenter extends BasePresenterImpl<LoginContract.LoginView> implements LoginContract.LoginPresenter {

    LoginContract.LoginModel model;

    public LoginPresenter(LoginContract.LoginView view) {
        super(view);
    }

    @Override
    public void login() {
        String name = view.getName();
        String password = view.getPassword();

        if (TextUtils.isEmpty(name)){
            view.showToast("请输入用户名！");
            return;
        }

        if (TextUtils.isEmpty(password)){
            view.showToast("请输入密码！");
            return;
        }

        HashMap<String, String> map = ThreeDES.getPostHeadMap();
        map.put(Constant.USERNAME_KEY, name);
        map.put(Constant.PASSWORD_KEY, password);
        map = ThreeDES.paramsEncrypt(map);

        model.login(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<UserInfoLoginBean>() {
                    @Override
                    public void onNext(UserInfoLoginBean data) {
                        String name = data.getUserName();
                        String token = data.getToken();
                        UserInfoLoginBean bean = BaseApplication.getInstance().getUserInfoBean();

                        BaseApplication.getInstance().setUserInfoBean(data);

                        if (!TextUtils.isEmpty(token)){//登录成功
                            view.onSuccess();
                        }else {
                            view.showToast("登陆失败");
                        }
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

package com.tan.mvpdemo.activityMvp.contract;

import com.tan.mvpdemo.activityMvp.BasePresenter;
import com.tan.mvpdemo.activityMvp.BaseView;
import com.tan.mvpdemo.bean.UpdateInfoBean;

import java.util.HashMap;

import rx.Observable;

/**
 * <br> Description
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp.contract
 * <br> Date: 2018/6/4
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public interface MainContract {

    interface MainModel {
        Observable<UpdateInfoBean> checkVersion(HashMap<String, String> map);
    }

    interface MainView extends BaseView{
        void onShowDialog(String title, String message);

        void onCloseDialog();

        void onShowProgress(String title, String message);

        void onSetProgress(int max, int Progress);

        void onCloseProgress();

        void onPrompt(int type, String message);

        void onSetCountTotal(String countTotal);

        void installApk(String path);

        void onShowAlertDialog(String title, String message,boolean isForce);

    }

    interface MainPresenter extends BasePresenter{
         /** 版本更新 */
        void checkVersion();
    }
}

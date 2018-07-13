package com.tan.mvpdemo.activityMvp.presenter;

import android.text.TextUtils;

import com.tan.mvpdemo.activityMvp.BasePresenterImpl;
import com.tan.mvpdemo.activityMvp.contract.MainContract;
import com.tan.mvpdemo.bean.UpdateInfoBean;
import com.tan.mvpdemo.common.http.FilterSubscriber;
import com.tan.mvpdemo.config.ConstantKey;
import com.tan.mvpdemo.uitl.ThreeDES;
import com.tan.mvpdemo.uitl.Tools;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <br> Description
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp.presenter
 * <br> Date: 2018/6/4
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class MainPresenter extends BasePresenterImpl<MainContract.MainView> implements MainContract.MainPresenter {

    MainContract.MainModel mainModel;

    /**
     * 当前app的版本号
     */
    int localVerCode;

    /**
     * app下载地址
     */
    String downUrl;

    public MainPresenter(MainContract.MainView view) {
        super(view);
    }


    @Override
    public void checkVersion() {
        if (mainModel != null && view != null){
            view.onShowDialog("提示", "正本检查更新...");

            /** 当前app版本号 */
            String verName = Tools.getVersionName();
            String type = ConstantKey.APP_TYPE;
            HashMap<String, String> map = ThreeDES.getPostHeadMap();
            map.put(ConstantKey.VERCODE_KEY, verName);
            map.put(ConstantKey.TYPE_KEY, type);

            mainModel.checkVersion(map)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new FilterSubscriber<UpdateInfoBean>() {
                        @Override
                        public void onNext(UpdateInfoBean data) {
                            view.onCloseDialog();
                            compareVerCode(data);
                        }

                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(String str) {

                        }
                    });

        }
    }

    /**
     * 比较版本code
     * @param bean
     */
    private void compareVerCode(UpdateInfoBean bean){
        if (bean != null){
            /** 数据不为空且 当前的 */
            if (!TextUtils.isEmpty(bean.getVerCode()) && localVerCode < Integer.parseInt(bean.getVerCode())){
                boolean isForce = false; //是否强制更新
                String content = "";//提示语
                /** 是否强制更新 1是true 0是false */
                if (ConstantKey.FORCE_CODE.equals(bean.getForce())){
                    isForce = true;
                    content = "请下载最新的版本，否则会导致无法正常使用贷业通！";
                }else {
                    content = "请下载最新的版本";
                }
                downUrl = bean.getUrl();
                view.onShowAlertDialog("贷业通版本更新", content, isForce);

            }
        }
    }

}

package com.tan.mvpdemo.activityMvp.presenter

import com.tan.mvpdemo.BaseApplication
import com.tan.mvpdemo.activityMvp.BasePresenterImpl
import com.tan.mvpdemo.activityMvp.contract.SettingContract
import com.tan.mvpdemo.activityMvp.model.SettingModel
import com.tan.mvpdemo.common.http.FilterSubscriber
import com.tan.mvpdemo.uitl.ThreeDES
import rx.android.schedulers.AndroidSchedulers
import rx.internal.subscriptions.Unsubscribed
import rx.schedulers.Schedulers

/**
 * <br></br> Description
 * <br></br> Author: 谭俊
 * <br></br> PackageName com.tan.mvpdemo.activityMvp.presenter
 * <br></br> Date: 2018/6/25
 * <br></br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
class SettingPresenter(view: SettingContract.SettingView) : BasePresenterImpl<SettingContract.SettingView>(view),SettingContract.SettingPresenter {

    var settingModel : SettingContract.SettingModel ?= null

    override fun logout() {
        var map = ThreeDES.getPostHeadMap();
        map["token"] = BaseApplication.getInstance().token

        settingModel!!.logout(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : FilterSubscriber<Any>(){

                    override fun onNext(data: Any?) {
                        view.onSuccess()
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(str: String?) {
                        view.showToast(str)
                    }

                })
    }
}

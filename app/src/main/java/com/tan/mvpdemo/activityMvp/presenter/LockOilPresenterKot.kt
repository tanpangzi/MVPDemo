package com.tan.mvpdemo.activityMvp.presenter

import com.tan.mvpdemo.BaseApplication
import com.tan.mvpdemo.activityMvp.BasePresenterImpl
import com.tan.mvpdemo.activityMvp.contract.LockOilContract
import com.tan.mvpdemo.common.http.FilterSubscriber
import com.tan.mvpdemo.uitl.ThreeDES

import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * <br></br> Description
 * <br></br> Author: 谭俊
 * <br></br> PackageName com.tan.mvpdemo.activityMvp.presenter
 * <br></br> Date: 2018/6/14
 * <br></br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
class LockOilPresenterKot(view: LockOilContract.LockOilView) : BasePresenterImpl<LockOilContract.LockOilView>(view), LockOilContract.LockOilPresenter {

    internal var model: LockOilContract.LockOilModel? = null

    /** 锁油断电  */
    override fun lockInstruction(imeiId: String) {
        val map = ThreeDES.getPostHeadMap()
        val lockStatus = view.lockStatus //选择操作指令
        val uId = BaseApplication.getInstance().userInfoBean.userId

        map["token"] = BaseApplication.getInstance().token
        map["imeiId"] = imeiId
        map["lock"] = lockStatus
        map["uId"] = uId
        map["tenantId"] = "0000100001"

        model!!.lockInstrution(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : FilterSubscriber<Any>() {
                    override fun onNext(data: Any) {
                        view.onSuccess()
                    }

                    override fun onCompleted() {

                    }

                    override fun onError(str: String) {
                        view.showToast(str)
                    }
                })
    }
}

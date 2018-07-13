package com.tan.mvpdemo.activityMvp.presenter

import android.text.TextUtils
import com.tan.mvpdemo.BaseApplication
import com.tan.mvpdemo.activityMvp.BasePresenterImpl
import com.tan.mvpdemo.activityMvp.contract.GPSMonitorStoryContract
import com.tan.mvpdemo.bean.gpsMonitor.GpsMonitor
import com.tan.mvpdemo.common.http.FilterSubscriber
import com.tan.mvpdemo.uitl.ThreeDES
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * <br></br> Description
 * <br></br> Author: 谭俊
 * <br></br> PackageName com.tan.mvpdemo.activityMvp.presenter
 * <br></br> Date: 2018/7/10
 * <br></br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
class GPSMonitorStoryPresenter(view : GPSMonitorStoryContract.GPSMonitorStoryView)
    : BasePresenterImpl<GPSMonitorStoryContract.GPSMonitorStoryView>(view)
        ,GPSMonitorStoryContract.GPSMonitorStoryPresenter {

    private var model : GPSMonitorStoryContract.GPSMonitorStoryModel ?= null

    override fun getEquipmentList(pageIndex: String, strSearch: String) {
        var map = ThreeDES.getPostHeadMap()
        map["token"] = BaseApplication.getInstance().token
        map["username"] = BaseApplication.getInstance().userInfoBean.userName
        map["pageIndex"] = pageIndex
        if (!TextUtils.isEmpty(strSearch)){
            map["conditions"] = strSearch
        }
        model!!.getEquipmentList(map).
                subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : FilterSubscriber<GpsMonitor.StoryList>(){
                    override fun onNext(data: GpsMonitor.StoryList?) {
                        view.getStoryList(data!!)
                    }

                    override fun onCompleted() {

                    }

                    override fun onError(str: String?) {
                        view.showToast(str)
                    }

                })


    }

    override fun getEquipmentListForSuperManager(pageIndex: String, strSearch: String, storyId: String) {

    }


}

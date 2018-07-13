package com.tan.mvpdemo.activityMvp.presenter

import com.tan.mvpdemo.BaseApplication
import com.tan.mvpdemo.activityMvp.BasePresenterImpl
import com.tan.mvpdemo.activityMvp.contract.GpsMonitorHomeContractKot
import com.tan.mvpdemo.activityMvp.model.GpsMonitorHomeModelKot
import com.tan.mvpdemo.bean.gpsMonitor.GpsMonitor
import com.tan.mvpdemo.common.http.FilterSubscriber
import com.tan.mvpdemo.uitl.ThreeDES
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * <br></br> Description
 * <br></br> Author: 谭俊
 * <br></br> PackageName com.tan.mvpdemo.activityMvp.presenter
 * <br></br> Date: 2018/6/22
 * <br></br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
class GpsMonitorHomePresenter2(view: GpsMonitorHomeContractKot.HomeView) : BasePresenterImpl<GpsMonitorHomeContractKot.HomeView>(view), GpsMonitorHomeContractKot.HomePresenter {

    //var gpsViewKot : GpsMonitorHomeContractKot.HomeView ?= null
    var gpsModelKot : GpsMonitorHomeContractKot.HomeModel ?= null

    /** 构造方法 */
    init {
        gpsModelKot = GpsMonitorHomeModelKot()
    }
    /** 被上面的方法替代 */
    /*constructor(gpsViewKot: GpsMonitorHomeContractKot.HomeView?) {
        this.gpsViewKot = gpsViewKot
        this.gpsModelKot = GpsMonitorHomeModelKot()
    }*/

     /** 请求方法 */
    override fun getStoreList() {
         var map = ThreeDES.getPostHeadMap()

         /** 这相当于map.put */
         map["token"] = BaseApplication.getInstance().token
         map["name"] = BaseApplication.getInstance().userInfoBean.userName

         /*map.put("token", token)
         map.put("name", name)*/

         gpsModelKot!!.getStoreList(map)
                 .subscribeOn(Schedulers.io())
                 .unsubscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 /** 注意这里有一个object */
                 .subscribe(object : FilterSubscriber<GpsMonitor.HomeBean>(){
                     override fun onNext(data: GpsMonitor.HomeBean?) {
                        view!!.getStoreList(data!!)
                     }

                     override fun onCompleted() {

                     }

                     override fun onError(str: String?) {
                         view!!.showToast(str)
                     }

                 })
    }
}

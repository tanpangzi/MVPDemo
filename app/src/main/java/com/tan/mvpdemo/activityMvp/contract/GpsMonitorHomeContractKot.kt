package com.tan.mvpdemo.activityMvp.contract

import com.tan.mvpdemo.activityMvp.BasePresenter
import com.tan.mvpdemo.activityMvp.BaseView
import com.tan.mvpdemo.bean.gpsMonitor.GpsMonitor
import rx.Observable

/**
 * <br></br> Description
 * <br></br> Author: 谭俊
 * <br></br> PackageName com.tan.mvpdemo.activityMvp.contract
 * <br></br> Date: 2018/6/22
 * <br></br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */

interface GpsMonitorHomeContractKot {
    interface HomeModel{
        fun getStoreList(map : Map<String, String>) :Observable<GpsMonitor.HomeBean>
    }

    interface HomeView : BaseView{
        fun getStoreList(stores : GpsMonitor.HomeBean)
    }

    interface HomePresenter : BasePresenter{
        fun getStoreList()
    }
}

package com.tan.mvpdemo.activityMvp.model

import com.tan.mvpdemo.activityMvp.contract.GpsMonitorHomeContractKot
import com.tan.mvpdemo.bean.gpsMonitor.GpsMonitor
import com.tan.mvpdemo.common.http.RequestServer
import com.tan.mvpdemo.common.http.ResponseFunc
import rx.Observable

/**
 * <br></br> Description
 * <br></br> Author: 谭俊
 * <br></br> PackageName com.tan.mvpdemo.activityMvp.model
 * <br></br> Date: 2018/6/22
 * <br></br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
class GpsMonitorHomeModelKot : GpsMonitorHomeContractKot.HomeModel {
    override fun getStoreList(map: Map<String, String>): Observable<GpsMonitor.HomeBean> {
        return RequestServer.createRetrofit().getStoreList(map)
                .map(ResponseFunc<GpsMonitor.HomeBean>())
    }
}

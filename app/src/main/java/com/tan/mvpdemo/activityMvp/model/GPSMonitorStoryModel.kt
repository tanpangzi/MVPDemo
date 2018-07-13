package com.tan.mvpdemo.activityMvp.model

import com.tan.mvpdemo.activityMvp.contract.GPSMonitorStoryContract
import com.tan.mvpdemo.bean.gpsMonitor.GpsMonitor
import com.tan.mvpdemo.common.http.RequestServer
import com.tan.mvpdemo.common.http.ResponseFunc

import rx.Observable

/**
 * <br></br> Description gps监控第二层列表
 * <br></br> Author: 谭俊
 * <br></br> PackageName com.tan.mvpdemo.activityMvp.model
 * <br></br> Date: 2018/7/10
 * <br></br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
class GPSMonitorStoryModel : GPSMonitorStoryContract.GPSMonitorStoryModel {

    /** GPS监控获取门店下设备列表*/
    override fun getEquipmentList(map: Map<String, String>): Observable<GpsMonitor.StoryList> {
        return RequestServer.createRetrofit().getEquipmentList(map)
                .map(ResponseFunc<GpsMonitor.StoryList>())
    }

    /** 超级管管理员GPS监控获取门店下设备列表 */
    override fun getEquipmentListForSuperManager(map: Map<String, String>): Observable<GpsMonitor.StoryList> {
        return RequestServer.createRetrofit().getEquipmentListForSuperManager(map)
                .map(ResponseFunc<GpsMonitor.StoryList>())
    }
}

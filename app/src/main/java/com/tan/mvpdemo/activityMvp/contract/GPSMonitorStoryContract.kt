package com.tan.mvpdemo.activityMvp.contract

import com.tan.mvpdemo.activityMvp.BasePresenter
import com.tan.mvpdemo.activityMvp.BaseView
import com.tan.mvpdemo.bean.gpsMonitor.GpsMonitor
import rx.Observable

/**
 * <br></br> Description
 * <br></br> Author: 谭俊
 * <br></br> PackageName com.tan.mvpdemo.activityMvp.contract
 * <br></br> Date: 2018/7/10
 * <br></br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
interface GPSMonitorStoryContract {
    interface GPSMonitorStoryModel {
        /** GPS监控获取门店下设备列表*/
        fun getEquipmentList(map: Map<String, String>): Observable<GpsMonitor.StoryList>
        /** 超级管管理员GPS监控获取门店下设备列表 */
        fun getEquipmentListForSuperManager(map: Map<String, String>) : Observable<GpsMonitor.StoryList>
    }

    interface GPSMonitorStoryView : BaseView {
        fun getStoryList(data : GpsMonitor.StoryList)
    }

    interface GPSMonitorStoryPresenter : BasePresenter {
        /** GPS监控获取门店下设备列表*/
        fun getEquipmentList(pageIndex : String, strSearch : String)
        /** 超级管管理员GPS监控获取门店下设备列表 */
        fun getEquipmentListForSuperManager(pageIndex: String, strSearch: String, storyId: String)
    }
}

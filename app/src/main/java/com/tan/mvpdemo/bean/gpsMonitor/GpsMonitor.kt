package com.tan.mvpdemo.bean.gpsMonitor

/**
 * <br> Description
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.bean.gpsMonitor
 * <br> Date: 2018/6/22
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
class GpsMonitor {
    /** gps 监控第一层*/
    /*****************************************/
    data class HomeBean(val invalid :String,
                        val sum : String,
                        val offline: String,
                        val online : String,
                        val returnList: ArrayList<GpsMonitorHomeListBean>)

    /** gps监控第一层 */
    data class GpsMonitorHomeListBean(val orgId : String,//机构id
                                      val invalid : String, //失效个数
                                      val sum : String,//当前门店总数
                                      val orgName : String,//机构名称
                                      val offline : String, //当前门店下线
                                      val online : String) //当前门店上线


    /**********************************/
    /** gps监控第二层 */

    data class StoryList(val returnList : ArrayList<GpsMonitorStoryBean>)

    data class GpsMonitorStoryBean(val status : String, //设备报警
                                   val speed : String, //速度
                                   val accState : String, //0关 1开
                                   val orgName : String, //组织名称 广东
                                   val type : String,//设备类型 0有线 1无线 2ODB
                                   val warning_name : String, //越界报警
                                   val location_time : String, //定位时间
                                   val driveStatus : String, //行驶状态 静止
                                   val name : String, // name : 测试14530108763
                                   val carNumber : String, //车牌号
                                   val imeiId : String, //imeId
                                   val warning_type : String, // warning_type : 5
                                   val beOnline : String, //是否在线
                                   val online_status :String,
                                   val color : String)


}
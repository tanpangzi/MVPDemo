package com.tan.mvpdemo.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.tan.mvpdemo.R
import com.tan.mvpdemo.bean.gpsMonitor.GpsMonitor
import org.jetbrains.anko.find
import org.jetbrains.anko.textColor

/**
 * <br> Description
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.adapter
 * <br> Date: 2018/6/26
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
class GpsMonitorStoryAdapter(context : Context, resId: Int, data : ArrayList<GpsMonitor.GpsMonitorStoryBean>)
    : ArrayAdapter<GpsMonitor.GpsMonitorStoryBean>(context, resId, data) {
    /** xml布局  ID */
    val resId = resId

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        /** 数据 */
        val item = getItem(position)
        /** 布局view */
        val view = LayoutInflater.from(context).inflate(resId, null)
        /** 用户名 */
        val container_name = view.find<TextView>(R.id.container_plate)
        /** 车牌号 */
        val container_plate = view.find<TextView>(R.id.container_plate)
        /** 图标状态 有线 无线 odb*/
        val containe_image = view.find<ImageView>(R.id.containe_image)
        /** 车辆状态 行驶 停止 */
        val container_type = view.find<TextView>(R.id.container_type)
        /** 车辆数据 石家庄 | ACC 开 33KM/H*/
        val container_parameter = view.find<TextView>(R.id.container_parameter)
        /** 日期 */
        val container_date = view.find<TextView>(R.id.container_date)
        /** 状态 断电报警*/
        val container_state = view.find<TextView>(R.id.container_state)

        container_name.text = item.name
        container_plate.text = item.carNumber

        /** 图标 */
        if ("0" == item.type){
            containe_image.setImageResource(R.mipmap.alarm_icon_wired)
        }else if ("1" == item.type){
            containe_image.setImageResource(R.mipmap.alarm_info_icon_wireless)
        }else if ("2" == item.type){
            containe_image.setImageResource(R.mipmap.odb_icon)
        }

        container_type.text = item.driveStatus

        val orgName = item.orgName
        var acc = "ACC"
        val accState = item.accState
        if ("0" == accState){
            acc += "关"
        }else if ("1".equals(accState)){
            acc += "开"
        }

        /** 速度 */
        val speed = item.speed
        val unitSpeed = speed + "KM/H"
        if (unitSpeed.toInt() > 0){
            container_type.textColor = Color.GREEN
        }else{
            container_type.textColor = Color.BLUE
        }

        //container_parameter.text = orgName + "|" + acc + " " + unitSpeed
        container_parameter.text = "$orgName|$acc $unitSpeed"

        val time = item.location_time
        val status = item.status
        container_date.text = "$time $status"

        container_state.text = item.warning_name
        container_state.textColor = getTextColor(item.color)

        /** item点击 */
        convertView!!.setOnClickListener {  }

        return view

    }

    private fun getTextColor(color : String) : Int{
        var c = -1
        when(color){
            "0" -> c = Color.GRAY
            "1" -> c = Color.RED
            "2" -> c = Color.GREEN
            "3" -> c = Color.YELLOW
            "4" -> c = Color.BLUE
        }
        return c
    }


}
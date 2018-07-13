package com.tan.mvpdemo.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.tan.mvpdemo.R
import com.tan.mvpdemo.activity.gpsMonitor.GpsMonitorStoryActivity
import com.tan.mvpdemo.bean.gpsMonitor.GpsMonitor
import com.tan.mvpdemo.config.ConstantKey
import com.tan.mvpdemo.uitl.IntentUtil
import org.jetbrains.anko.find

/**
 * <br> Description
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.adapter
 * <br> Date: 2018/6/22
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
class  GpsMonitorHomeAdapter(context: Context, resId: Int, datas : ArrayList<GpsMonitor.GpsMonitorHomeListBean>)
    : ArrayAdapter<GpsMonitor.GpsMonitorHomeListBean>(context, resId, datas){
    val resId = resId

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        /** 获取当前的位置 */
        val item = getItem(position)
        val view = LayoutInflater.from(context).inflate(resId, null)
        /** 地址 */
        val txt_store_name = view.find<TextView>(R.id.txt_store_name)
        val txt_total = view.find<TextView>(R.id.txt_total)
        val txt_on_line = view.find<TextView>(R.id.txt_on_line)
        val txt_off_line = view.find<TextView>(R.id.txt_off_line)
        val txt_invalid = view.find<TextView>(R.id.txt_invalid)

        txt_store_name.text = item.orgName
        txt_total.text = "总数" + "(" + item.sum + ")"
        txt_on_line.text = "在线" + " | " + item.online
        txt_off_line.text = "离线" + " | " + item.offline
        txt_invalid.text = "失效" + " | " + item.invalid

        /** item点击事件 */
        convertView!!.setOnClickListener(View.OnClickListener {
            var bundle = Bundle()
            bundle.putString(ConstantKey.INTENT_KEY_STRING, item.orgName)
            bundle.putString(ConstantKey.INTENT_KEY_ID, item.orgId)
            IntentUtil.gotoActivity(context, GpsMonitorStoryActivity::class.java, bundle)
        })

        return view
    }
}
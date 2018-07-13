package com.tan.mvpdemo.activity.gpsMonitor

import android.text.TextUtils
import android.view.View
import com.tan.mvpdemo.R
import com.tan.mvpdemo.activity.BaseActivity
import com.tan.mvpdemo.activityMvp.contract.GPSMonitorStoryContract
import com.tan.mvpdemo.activityMvp.presenter.GPSMonitorStoryPresenter
import com.tan.mvpdemo.adapter.GpsMonitorStoryAdapter
import com.tan.mvpdemo.bean.gpsMonitor.GpsMonitor
import com.tan.mvpdemo.config.ConstantKey
import com.tan.mvpdemo.uitl.ToastUtil

import org.jetbrains.anko.find
import kotlinx.android.synthetic.main.activity_gps_monitor_story.*

/**
 * <br> Description GPS监控 门店设备列表数据
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activity.gpsMonitor
 * <br> Date: 2018/6/26
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
class GpsMonitorStoryActivity : BaseActivity<GPSMonitorStoryContract.GPSMonitorStoryPresenter>()
        , GPSMonitorStoryContract.GPSMonitorStoryView{

    override fun initPresenter(): GPSMonitorStoryContract.GPSMonitorStoryPresenter {
        return GPSMonitorStoryPresenter(this)
    }

//    /** 标题 */
//    private var title_view : TitleView ?= null
//    /** 搜索框 */
//    private var et_search : DYTEditText?= null
//    /** 搜索按钮 */
//    private var btn_search : AutoBgButton ?= null
//    /** 列表 */
//    private var lv_base : PullToRefreshListView?= null
//    /** 报警信息查看按钮 */
//    private var btn_see : AutoBgButton ?= null

    /** 当前加载页码  */
    private var pageNum = 1
    private var storyId = ""
    private var storyName = ""

    /** true 超级管理员  */
    private var isAdmin = true
    /** true 一进页面就加载数据  */
    private var isShowData = true
    /** 搜索的内容  */
    private var searchStr = ""

    private var mAdapter : GpsMonitorStoryAdapter?= null

    private var listData : ArrayList<GpsMonitor.GpsMonitorStoryBean> ?= null

    override fun getContentViewId(): Int {
        return R.layout.activity_gps_monitor_story
    }

    override fun findViews() {
        /*title_view = find(R.id.title_view)
        et_search = find(R.id.et_search)
        btn_search = find(R.id.btn_search)
        lv_base = find(R.id.lv_base)
        btn_see = find(R.id.btn_see)*/
    }

    override fun initGetData() {
        var bundle = intent.extras
        if (bundle != null){
            storyId = bundle.getString(ConstantKey.INTENT_KEY_ID ,"")
            storyName = bundle.getString(ConstantKey.INTENT_KEY_STRING, "")
            isAdmin = bundle.getBoolean(ConstantKey.INTENT_KEY_BOOLEAN, true)
            isShowData = bundle.getBoolean(ConstantKey.INTENT_KEY_BOOLEAN1, true)
        }
    }

    override fun init() {
        title_view!!.setLeftBtnImg()
        if (TextUtils.isEmpty(storyName)){
            title_view.setTitle("车辆列表")
        }else{
            title_view.setTitle(storyName + "风控平台")
        }

        mAdapter = GpsMonitorStoryAdapter(this, R.layout.item_gps_monitor_story ,listData!!)
        lv_base.refreshableView.adapter = mAdapter

        if (isShowData){
            btn_see.visibility = View.VISIBLE
        }else{

        }
    }

    override fun widgetListener() {

    }

    /** 获取 */
    override fun getStoryList(data: GpsMonitor.StoryList) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** 消息提示 */
    override fun showToast(msg: String?) {
        ToastUtil.showToast(this, msg!!)
    }

    /** 成功 */
    override fun onSuccess() {
    }

}
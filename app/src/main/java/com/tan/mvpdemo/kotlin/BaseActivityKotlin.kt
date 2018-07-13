package com.tan.mvpdemo.kotlin

import android.app.Dialog
import android.app.ProgressDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.readystatesoftware.systembartint.SystemBarTintManager
import com.tan.mvpdemo.BaseApplication
import com.tan.mvpdemo.R
import com.tan.mvpdemo.activity.LoginActivity
import com.tan.mvpdemo.broadcast.BroadCastFilter
import com.tan.mvpdemo.uitl.*

/**
 * <br> Description 用kotlin写的BaseActivity
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.kotlin
 * <br> Date: 2018/6/22
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
abstract class BaseActivityKotlin :AppCompatActivity(){
    /** 父视图 */
    protected var viewParent : View ?=null
    /** 广播接收器 */
    protected var receiver : BroadcastReceiver ?= null
    /** 广播过滤器 */
    protected var filter : IntentFilter ?= null
    /** SystemBarTintManager 用于修改状态栏的颜色 */
    protected var tintManager: SystemBarTintManager ?= null

    /** 获取别的界面传来的Bundle */
    protected var getBundle : Bundle ?= null
    /** 要传到别的界面的Bundle */
    protected var postBundle : Bundle ?= null

    private var mProgressDialog : ProgressDialog ? =null

    private var mDialog : Dialog ?= null
    /** dialog提示 */
    private val mDialogTitle = "提示"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManagerUtil.getAppManager().addActivity(this)
        /** 系统版本大于4.4.4 */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            setTranslucentStatus(true)
        }
        viewParent = View.inflate(this, getContentViewId(), null)
        setContentView(viewParent)
        window.setBackgroundDrawable(null)
        /** 取值用的Bundle */
        getBundle = intent.extras
        /** 传值用的Bundle */
        postBundle = intent.extras

        findViews()
        initGetData()
        widgetListener()
        init()

        registerReceiver()
        SystemUtil.getChannelName()

    }

    private fun setTranslucentStatus(on: Boolean){
        val win : Window = window // window就是java里的getWindow
        val winParams :WindowManager.LayoutParams = win.attributes
        val bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        /** java里的| kotlin为or ,& 是and , ~i是nv*/
        if (on){
            winParams.flags = winParams.flags or bits
        } else{
            winParams.flags = winParams.flags and (bits.inv())
        }
        win.attributes = winParams
        /** 沉浸模式 会导致输入法与edittext不能顶起 */
        tintManager = SystemBarTintManager(this)
        tintManager!!.setNavigationBarTintEnabled(true)
        tintManager!!.isStatusBarTintEnabled
        /** 设置状态栏背景 */
        setTintResource(R.color.transparent)
    }

    /** 设置状态栏的背景颜色 */
    fun setTintResource(resId:Int){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            if (null != tintManager){
                tintManager!!.setTintResource(resId)
            }
        }
    }

    /** 获取xml文件ID */
    protected abstract fun getContentViewId() : Int

    /** 控件查找 */
    protected abstract fun findViews()

    /** 获取上一个界面传过来的值 */
    protected abstract fun initGetData()

    /** 初始化程序 设置一些初始化数据都获取等扣件 */
    protected abstract fun init()

    /** 组件监听模块 */
    protected abstract fun widgetListener()

    /*********************控件操作*************************/

    /** 控件查找 */
    protected fun <T : View> findViewByIds(id: Int): T {
        if (viewParent == null) {
            viewParent = View.inflate(this, getContentViewId(), null)
        }
        return viewParent!!.findViewById<View>(id) as T
    }

    /**
     * 注册广播
     */
    protected fun registerReceiver(){
        setFilterActions()
        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                this@BaseActivityKotlin.onReceive(intent)

            }
        }
        registerReceiver(receiver, filter)
    }

    /**
     * 广播过虑器
     */
    protected fun setFilterActions(){
        filter = IntentFilter()
        filter!!.addAction(BroadCastFilter.ACTION_TONKEN_EXPIRED)
    }

    /**
     * 广播监听回调
     */
    protected fun onReceive(intent: Intent){
        if (intent.action.equals(BroadCastFilter.ACTION_TONKEN_EXPIRED)){
            /** 不是登录界面 */
            if (!this.javaClass.name.equals("LoginActivity")){
                tokenInvalid()
            }
            LogUtil.e("退出登录" + Constant.LOG_TAG + this.javaClass.name)
        }
    }

    /** token失效 退出登录 */
    fun tokenInvalid(){
        val token = BaseApplication.getInstance().token
        if (TextUtils.isEmpty(token)){
            ToastUtil.showToast(this, "登录过期，请重新登录")
            val userInfoBean = BaseApplication.getInstance().userInfoBean
            userInfoBean.token = ""
            BaseApplication.getInstance().userInfoBean = userInfoBean
            IntentUtil.gotoActivityToTopAndFinish(this, LoginActivity::class.java)
        }else{
            IntentUtil.gotoActivityToTopAndFinish(this, LoginActivity::class.java)
        }
    }

}
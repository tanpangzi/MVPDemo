package com.tan.mvpdemo.activity

import com.tan.mvpdemo.BaseApplication
import com.tan.mvpdemo.R
import com.tan.mvpdemo.activityMvp.contract.SettingContract
import com.tan.mvpdemo.activityMvp.presenter.SettingPresenter
import com.tan.mvpdemo.uitl.AppManagerUtil
import com.tan.mvpdemo.uitl.IntentUtil
import com.tan.mvpdemo.uitl.TitleView
import com.tan.mvpdemo.uitl.ToastUtil
import com.tan.mvpdemo.widget.AutoBgButton
import com.tan.mvpdemo.widget.AutoBgTextView
import org.jetbrains.anko.find

/**
 * <br> Description 设置界面
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activity
 * <br> Date: 2018/6/25
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
class SettingActivity : BaseActivity<SettingContract.SettingPresenter>() ,SettingContract.SettingView{

    override fun initPresenter(): SettingContract.SettingPresenter {
        return SettingPresenter(this)
    }

    /** 标题 */
    var title_view : TitleView ?= null
    /** 修改密码 */
    var txt_edit_password : AutoBgTextView ?= null
    /** 检查更新 */
    var txt_check_version : AutoBgTextView ?= null
    /** 退出登录 */
    var btn_login_out : AutoBgButton ?= null


    override fun getContentViewId(): Int {
        return R.layout.activity_setting
    }

    override fun findViews() {
        title_view = find(R.id.title_view)
        txt_edit_password = find(R.id.txt_edit_password)
        txt_check_version = find(R.id.txt_check_version)
        btn_login_out = find(R.id.btn_login_out)
    }

    override fun initGetData() {
    }

    override fun init() {
        title_view!!.setLeftBtnImg()
        title_view!!.setTitle("设置")
    }

    override fun widgetListener() {
        btn_login_out!!.setOnClickListener {
            mPresenter!!.logout()
        }
    }

    /****************/
    /** 操作成功 */
    override fun onSuccess() {
        var bean = BaseApplication.getInstance().userInfoBean
        bean.token = ""
        BaseApplication.getInstance().userInfoBean = bean
        AppManagerUtil.getAppManager().finishAllActivity()
        IntentUtil.gotoActivityToTopAndFinish(this, LoginActivity::class.java)
    }

    override fun showToast(msg: String?) {
        ToastUtil.showToast(this@SettingActivity, msg)
    }
}
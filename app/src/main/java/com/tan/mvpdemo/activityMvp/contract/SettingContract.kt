package com.tan.mvpdemo.activityMvp.contract


import com.tan.mvpdemo.activityMvp.BasePresenter
import com.tan.mvpdemo.activityMvp.BaseView
import rx.Observable

/**
 * <br></br> Description 设置的contract
 * <br></br> Author: 谭俊
 * <br></br> PackageName com.tan.mvpdemo.activityMvp.contract
 * <br></br> Date: 2018/6/25
 * <br></br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
interface SettingContract {
    interface SettingModel {
        fun logout(map : Map<String, String>) : Observable<Any>
    }

    interface SettingView : BaseView

    interface SettingPresenter : BasePresenter{
        fun logout()
    }

}

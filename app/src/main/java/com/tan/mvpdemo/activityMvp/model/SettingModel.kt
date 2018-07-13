package com.tan.mvpdemo.activityMvp.model

import com.tan.mvpdemo.activityMvp.contract.SettingContract
import com.tan.mvpdemo.common.http.RequestServer
import com.tan.mvpdemo.common.http.ResponseFunc
import rx.Observable

/**
 * <br></br> Description
 * <br></br> Author: 谭俊
 * <br></br> PackageName com.tan.mvpdemo.activityMvp.model
 * <br></br> Date: 2018/6/25
 * <br></br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
class SettingModel : SettingContract.SettingModel {
    override fun logout(map: Map<String, String>): Observable<Any> {
        return RequestServer.createRetrofit().logOut(map)
                .map(ResponseFunc<Any>())
    }

}

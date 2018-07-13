package com.tan.mvpdemo.broadcast;

import com.tan.mvpdemo.BaseApplication;

/**
 * <br> Description 广播过虑
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.broadcast
 * <br> Date: 2018/6/4
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class BroadCastFilter {
    private static final String BASE_ACTION = BaseApplication.getInstance().getApplicationContext().getPackageName();

    /** 登陆过期 */
    public static String ACTION_TONKEN_EXPIRED = BASE_ACTION +  "tonken.expired";

}

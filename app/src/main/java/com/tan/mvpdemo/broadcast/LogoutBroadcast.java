package com.tan.mvpdemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tan.mvpdemo.activity.LoginActivity;

/**
 * <br> Description 广播接收 用于退出app
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.broadcast
 * <br> Date: 2018/6/4
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class LogoutBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == BroadCastFilter.ACTION_TONKEN_EXPIRED){
            Intent logoutIntent = new Intent(context, LoginActivity.class);
            logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }


    }

}

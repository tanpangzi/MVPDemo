package com.tan.mvpdemo.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;

import com.tan.mvpdemo.uitl.LogUtil;

import java.io.FileDescriptor;
import java.io.PrintWriter;

/**
 * 高德地图定位服务
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class KillNotificationsService extends Service {

    @Override
    public void onCreate() {
        LogUtil.w("服务：onCreate");
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.w("服务：onStartCommand..." + intent);
        // START_STICKY: 和原来的onStart类似，在被系统kill掉后会restart，但是不同的是onStartCommand会被调用并传入一个null值作为intent，这个时候service就可以对这种case做出处理。
        // START_NOT_STICKY： 被kill掉后就不会自动restart了。
        // START_REDELIVER_INTENT： 如果service被kill掉了，系统会把之前的intent再次发送给service，直到service处里完成。
        return Service.START_STICKY;
        // return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        LogUtil.w("服务：onStart");
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        LogUtil.w("服务：onDestroy");
        LogUtil.i("onDestroy...");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.w("服务：onBind");
        // return mBinder;
        return null;
    }

    @Override
    public void onRebind(Intent it) {
        LogUtil.w("服务：onRebind");
        super.onRebind(it);
    }

    @Override
    public boolean onUnbind(Intent it) {
        LogUtil.w("服务：onUnbind");
        return super.onUnbind(it);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        LogUtil.w("服务：onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        LogUtil.w("服务：onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        LogUtil.w("服务：onTrimMemory " + level);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        // NotificationUtil.cancelNotification();
        LogUtil.w("服务：onTaskRemoved");
        super.onTaskRemoved(rootIntent);
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        LogUtil.w("服务：dump");
        super.dump(fd, writer, args);
    }
}
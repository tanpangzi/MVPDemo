package com.tan.mvpdemo.uitl;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;


import com.tan.mvpdemo.BaseApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Activity管理类：用于管理Activity和退出程序
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class AppManagerUtil {

    /** 堆栈 */
    private Stack<Activity> activityStack;
    /** Activity管理类对象 */
    private static AppManagerUtil instance;

    private AppManagerUtil() {
    }

    /**
     * 单一实例：获取Activity管理类：用于管理Activity和退出程序
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-25,上午11:38:26
     * <br> UpdateTime: 2016-12-25,上午11:38:26
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @return Activity管理类对象
     */
    public static AppManagerUtil getAppManager() {
        // if (instance == null) {
        synchronized (AppManagerUtil.class) {
            if (instance == null) {
                instance = new AppManagerUtil();
            }
        }
        // }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-25,上午11:39:13
     * <br> UpdateTime: 2016-12-25,上午11:39:13
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param activity
     *         Activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 移除堆栈Activity
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-25,上午11:39:38
     * <br> UpdateTime: 2016-12-25,上午11:39:38
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    public void removeActivity(Activity activityRemove) {
        if (null != activityStack && activityStack.size() > 0) {
            if (activityStack.contains(activityRemove)) {
                // LogUtil.i("contains.........");
                activityStack.remove(activityRemove);
                // activity.finish(); 该方法在onDestroy内调用 无需再调finish方法
            }
        }
    }

    /**
     * 结束指定类名的Activity
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-25,上午11:40:11
     * <br> UpdateTime: 2016-12-25,上午11:40:11
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param cls
     *         类名
     */
    public void finishActivity(Class<?> cls) {
        if (null != activityStack && activityStack.size() >= 1) {
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    activityStack.remove(activity);
                    activity.finish();
                    break;
                }
            }
        }
    }

    /**
     * 结束 除指定类名外的 所有Activity
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-25,上午11:40:11
     * <br> UpdateTime: 2016-12-25,上午11:40:11
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    public void finishOtherActivity() {
        Activity currentActivity = activityStack.lastElement();
        finishOtherActivity(currentActivity);
    }

    /**
     * 结束 除指定类名外的 所有Activity
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-25,上午11:40:11
     * <br> UpdateTime: 2016-12-25,上午11:40:11
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param activity
     *         类名
     */
    public void finishOtherActivity(Activity activity) {
        if (null != activityStack && activityStack.size() >= 1) {
            Stack<Activity> activityStackClone = (Stack<Activity>) activityStack.clone();
            if (activityStackClone.contains(activity)) {
                activityStackClone.remove(activity);
            }

            for (Activity otherActivity : activityStackClone) {
                activityStack.remove(otherActivity);
                otherActivity.finish();
            }
        }
    }

    /**
     * 结束 除指定类名外的 所有Activity
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-25,上午11:40:11
     * <br> UpdateTime: 2016-12-25,上午11:40:11
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param cls
     *         类名
     */
    public void finishOtherActivity(Class<?> cls) {
        if (null != activityStack && activityStack.size() >= 1) {
            Stack<Activity> activityStackClone = (Stack<Activity>) activityStack.clone();
            for (Activity activity : activityStackClone) {
                if (!activity.getClass().equals(cls)) {
                    activityStack.remove(activity);
                    activity.finish();
                }
            }
        }
    }

    /**
     * 结束所有Activity
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-25,上午11:40:28
     * <br> UpdateTime: 2016-12-25,上午11:40:28
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    public void finishAllActivity() {
        if (null != activityStack && activityStack.size() >= 1) {
            Stack<Activity> activityStackClone = (Stack<Activity>) activityStack.clone();
            for (Activity activity : activityStackClone) {
                activity.finish();
            }
            activityStack.clear();
        }
    }


    /**
     * 退出应用程序
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-25,上午11:42:58
     * <br> UpdateTime: 2016-12-25,上午11:42:58
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    public void exitApp() {
        Context context = BaseApplication.getInstance().getApplicationContext();
        //            // 发送定位成功的广播
        //            Intent intent = new Intent();
        //            intent.setAction(BroadcastFilters.ACTION_APP_EXIT);
        //            context.sendBroadcast(intent);
        // 获取Activity管理器
        ActivityManager activityManger = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        // 从窗口管理器中获取正在运行的Service
        List<ActivityManager.RunningServiceInfo> serviceList = activityManger.getRunningServices(100);
        // 当前app所有服务的类名
        ArrayList<String> service = getServicesName(serviceList);
        for (String serviceName : service) {
            LogUtil.i(serviceName);
            try {
                context.stopService(new Intent(context, Class.forName(serviceName)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        finishAllActivity();
        // 退出应用程序
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /**
     * 获取当前app所有服务的名称
     *
     * @param list
     *         List<ActivityManager.RunningServiceInfo>
     *
     * @return 当前app所有服务的名称
     */
    private ArrayList<String> getServicesName(List<ActivityManager.RunningServiceInfo> list) {//
        ArrayList<String> service = new ArrayList<>();
        for (ActivityManager.RunningServiceInfo runServiceInfo : list) {
            // 获得Service所在的进程的信息
            // service所在的进程ID号
            int pid = runServiceInfo.pid;
            if (pid == android.os.Process.myPid()) {
                service.add(runServiceInfo.service.getClassName());
                //                int uid = runServiceInfo.uid; // 用户ID 类似于Linux的权限不同，ID也就不同 比如 root等
                //                // 进程名，默认是包名或者由属性android：process指定
                //                String processName = runServiceInfo.process;
                //                // 该Service启动时的时间值
                //                long activeSince = runServiceInfo.activeSince;
                //                // 如果该Service是通过Bind方法方式连接，则clientCount代表了service连接客户端的数目
                //                int clientCount = runServiceInfo.clientCount;
                //                // 获得该Service的组件信息 可能是pkgname/servicename
                //                ComponentName serviceCMP = runServiceInfo.service;
                //                // service 的类名
                //                String serviceName = serviceCMP.getShortClassName();
                //                // 包名
                //                String pkgName = serviceCMP.getPackageName();
                //if (pkgName.equals(BaseApplication.getInstance().getPackageName())) {
                //service.add(runServiceInfo.service.getClassName());
                //LogUtil.i(runServiceInfo.service.getClassName() + "...." + pkgName + "..." + BaseApplication.getInstance().getPackageName());
                //}
            }
        }
        return service;
    }
    //    /**
    //     * 结束指定的Activity
    //     * <p>
    //     * <br> Version: 1.0.0
    //     * <br> CreateTime: 2016-12-25,上午11:39:44
    //     * <br> UpdateTime: 2016-12-25,上午11:39:44
    //     * <br> CreateAuthor: 叶青
    //     * <br> UpdateAuthor: 叶青
    //     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
    //     *
    //     * @param activity
    //     *         指定的Activity
    //     */
    //    private void finishActivity(Activity activity) {
    //        if (activity != null) {
    //            activityStack.remove(activity);
    //            // IntentUtil.finish(activity);
    //        }
    //    }
    //    /**
    //     * 指定类名 Activity 是否存在棧内
    //     * <p>
    //     * <br> Version: 1.0.0
    //     * <br> CreateTime: 2016-12-25,上午11:40:11
    //     * <br> UpdateTime: 2016-12-25,上午11:40:11
    //     * <br> CreateAuthor: 叶青
    //     * <br> UpdateAuthor: 叶青
    //     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
    //     *
    //     * @param cls
    //     *         类名
    //     */
    //    public boolean isExist(Class<?> cls) {
    //        Stack<Activity> activityStack1 = (Stack<Activity>) activityStack.clone();
    //        for (Activity activity : activityStack1) {
    //            if (activity.getClass().equals(cls)) {
    //                return true;
    //            }
    //        }
    //
    //        return false;
    //    }
    //    /**
    //     * 结束当前Activity（堆栈中最后一个压入的）
    //     * <p>
    //     * <br> Version: 1.0.0
    //     * <br> CreateTime: 2016-12-25,上午11:39:38
    //     * <br> UpdateTime: 2016-12-25,上午11:39:38
    //     * <br> CreateAuthor: 叶青
    //     * <br> UpdateAuthor: 叶青
    //     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
    //     */
    //    public void finishActivity() {
    //        finishActivity(currentActivity());
    //    }

    /**
     * 指定类名 Activity 是否存在棧内
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-25,上午11:40:11
     * <br> UpdateTime: 2016-12-25,上午11:40:11
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param cls
     *         类名
     */
    public boolean isExist(Class<?> cls) {
        Stack<Activity> activityStack1 = (Stack<Activity>) activityStack.clone();
        for (Activity activity : activityStack1) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }

        return false;
    }
}
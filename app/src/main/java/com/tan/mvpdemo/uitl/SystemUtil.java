package com.tan.mvpdemo.uitl;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.tan.mvpdemo.BaseApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;


/**
 * 系统信息工具类：用户获取当前app版本号、版本名称；手机Mac地址、IMEI码等
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class SystemUtil {

    /**
     * 获取当前系统版本号
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-10-21,下午3:45:06
     * <br> UpdateTime: 2016-10-21,下午3:45:06
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @return 当前系统版本号
     */
    public static int getCurrentVersionCode() {
        Context context = BaseApplication.getInstance().getApplicationContext();
        int versionCode = 1;
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packInfo.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            versionCode = 1;
        }
        return versionCode;
    }

    /**
     * 获取当前系统版本名称
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-10-21,下午3:45:17
     * <br> UpdateTime: 2016-10-21,下午3:45:17
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @return 当前系统版本名称
     */
    public static String getCurrentVersionName() {
        Context context = BaseApplication.getInstance().getApplicationContext();
        String versionName = "";
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            versionName = "1.0.0";
        }
        return versionName;
    }

    /**
     * 获取本机的Mac地址
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-13,上午11:58:43
     * <br> UpdateTime: 2016-11-13,上午11:58:43
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @return 本机mac 00:00:00:00:00:00
     */
    public static String getPhoneMac() {
        Context context = BaseApplication.getInstance().getApplicationContext();
        String mac;
        try {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            mac = info.getMacAddress();
        } catch (Exception e) {
            e.printStackTrace();
            mac = "00:00:00:00:00:00";
        }
        return mac;
    }

    /**
     * 获取当前手机IMEI号
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-5,上午9:53:25
     * <br> UpdateTime: 2016-1-5,上午9:53:25
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @return 返回手机IMEI号
     */
    public static String getIMEI() {
        Context context = BaseApplication.getInstance().getApplicationContext();
        return ((TelephonyManager) context.getSystemService(Activity.TELEPHONY_SERVICE)).getDeviceId();
    }

    /**
     * 获取当前系统可用运行内存
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午4:08:08
     * <br> UpdateTime: 2016-11-24,下午4:08:08
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @return 系统总的运行内存 xxxMB
     */
    public static long getAvailMemory() {
        Context context = BaseApplication.getInstance().getApplicationContext();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        // 当前系统的可用内存
        // mi.availMem;
        // 将获取的内存大小规格化
        // return Formatter.formatFileSize(context, mi.availMem);
        // 将获取的内存大小规格化Mb
        return mi.availMem / (1024 * 1024);
    }

    /**
     * 获取当前系统总的运行内存
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午4:07:57
     * <br> UpdateTime: 2016-11-24,下午4:07:57
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @return 系统总的运行内存 xxxMB
     */
    public static long getTotalMemory() {
        // 系统内存信息文件
        String filePath = "/proc/meminfo";
        String strFileContent;
        String[] arrayOfString;

        long memory = 0;
        try {
            FileReader localFileReader = new FileReader(filePath);
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            // 读取meminfo第一行，系统总内存大小
            strFileContent = localBufferedReader.readLine();

            arrayOfString = strFileContent.split("\\s+");

            // 获得系统总内存，单位是KB，除以1024转换为Mb
            memory = Integer.valueOf(arrayOfString[1]).intValue() / 1024;
            localBufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        // return Formatter.formatFileSize(context, memory);//
        // Byte转换为KB或者MB，内存大小规格化
        // MB，内存大小规格化
        return memory;
    }

    /**
     * 是否更新
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-18,上午10:24:35
     * <br> UpdateTime: 2016-12-18,上午10:24:35
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param net
     *         官网版本 2.0.5
     * @param local
     *         当前版本2.0.6
     *
     * @return true==更新
     */
    public static boolean compareVersion(String net, String local) {

        if (TextUtils.isEmpty(net) || TextUtils.isEmpty(local)) {
            return false;
        }

        boolean isUpdate = false;
        try {
            String[] strings1 = net.split("\\.");
            String[] strings2 = local.split("\\.");
            int[] integers1 = new int[strings1.length];
            int[] integers2 = new int[strings2.length];
            for (int i = 0; i < strings1.length; i++) {
                integers1[i] = Integer.parseInt(strings1[i]);
            }

            for (int i = 0; i < strings2.length; i++) {
                integers2[i] = Integer.parseInt(strings2[i]);
            }

            // 官网第一位 >当前版本第一位
            if (integers1[0] > integers2[0]) {
                isUpdate = true;
            }
            // 官网第一位 ==当前版本第一位
            else if (integers1[0] == integers2[0]) {

                // 官网第2位 >当前版本第2位
                if (integers1[1] > integers2[1]) {
                    isUpdate = true;
                }
                // 官网第2位 ==当前版本第2位
                else if (integers1[1] == integers2[1]) {
                    // 官网第3位 >当前版本第3位
                    if (integers1[2] > integers2[2]) {
                        isUpdate = true;
                    }
                    // 官网第3位 ==当前版本第3位
                    else if (integers1[2] == integers2[2]) {
                        isUpdate = false;
                    }
                    // 官网第3位 <当前版本第3位
                    else {
                        isUpdate = false;
                    }
                }
                // 官网第2位 <当前版本第2位
                else {
                    isUpdate = false;
                }
            }
            // 官网第一位 <当前版本第一位
            else {
                isUpdate = false;
            }

            // if (isUpdate) {
            // Toast.makeText(this, net + "大于" + local,
            // Toast.LENGTH_SHORT).show();
            // } else {
            // Toast.makeText(this, net + "小于" + local,
            // Toast.LENGTH_SHORT).show();
            // }
        } catch (Exception e) {
            isUpdate = net.compareTo(local) > 0;
            e.printStackTrace();
        }

        return isUpdate;
    }

    /**
     * 获取手机系统语言
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-26,下午5:33:18
     * <br> UpdateTime: 2016-12-26,下午5:33:18
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    public static String getSystemLanguage() {
        Context context = BaseApplication.getInstance().getApplicationContext();
        String language = context.getResources().getConfiguration().locale.getLanguage();

        if (language.startsWith("zh")) {
            language = "zh";
        } else if (language.startsWith("en")) {
            language = "en";
        }

        return language;
    }

    /**
     * 获取当前设备UUID唯一识别码
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-22,下午12:44:32
     * <br> UpdateTime: 2016-1-22,下午12:44:32
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    public static String getUUID() {
        Context context = BaseApplication.getInstance().getApplicationContext();
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String tmSerial = tm.getSimSerialNumber();
        String androidId = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) getIMEI().hashCode() << 32) | tmSerial.hashCode());
        // 获取可变UUID
        // UUID uuid = UUID.randomUUID();
        return deviceUuid.toString();
    }

    /** ApplicationMetaData_key值 */
    private static final String APPLICATION_METADATA = "UMENG_CHANNEL";

    /**
     * appliction MetaData读取
     * <p>
     * <br/> Version: 1.0
     * <br/> CreateTime:  2015-5-6,上午11:27:45
     * <br/> UpdateTime:  2015-5-6,上午11:27:45
     * <br/> CreateAuthor:  yeqing
     * <br/> UpdateAuthor:  yeqing
     * <br/> UpdateInfo:  (此处输入修改内容,若无修改可不写.)
     */
    public static String getChannelName() {
        Context context = BaseApplication.getInstance().getApplicationContext();
        String channelName = "";
        try {
            ApplicationInfo info = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);

            channelName = info.metaData.getString(APPLICATION_METADATA);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //LogUtil.i(channelName);

        return channelName;
    }

    /**
     * 获取栈顶app的包名
     * 5.0版本之后google废弃了getRunningTasks（）方法，意味着我们在5.0以后不能通过该方法获取正在运行的应用程序
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateAuthor: 叶青
     * <br> CreateTime: 2017/3/4 22:39
     * <br> UpdateAuthor: 叶青
     * <br> UpdateTime: 2017/3/4 22:39
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @return 栈顶app的包名
     */
    public static String getTopAppPackageName() {
        Context context = BaseApplication.getInstance().getApplicationContext();
        ActivityManager.RunningAppProcessInfo currentInfo = null;
        Field field = null;
        int START_TASK_TO_FRONT = 2;
        String currentApp = "CurrentNULL";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            try {
                field = ActivityManager.RunningAppProcessInfo.class.getDeclaredField("processState");
            } catch (Exception e) {
                e.printStackTrace();
            }
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> appList = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo app : appList) {
                if (app.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    Integer state = null;
                    try {
                        state = field.getInt(app);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (state != null && state == START_TASK_TO_FRONT) {
                        currentInfo = app;
                        break;
                    }
                }
            }
            if (currentInfo != null) {
                currentApp = currentInfo.processName;
            }
        } else {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> tasks = am.getRunningAppProcesses();
            currentApp = tasks.get(0).processName;
        }
        Log.e("TAG", "Current App in foreground is: " + currentApp);
        return currentApp;
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机号
     */
    public static String getPhoneNum() {
        Context context = BaseApplication.getInstance().getApplicationContext();
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm == null) {
            return null;
        }else{
            return tm.getLine1Number();
        }
    }
}

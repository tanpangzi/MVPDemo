package com.tan.mvpdemo.uitl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.content.FileProvider;


import com.tan.mvpdemo.BuildConfig;
import com.tan.mvpdemo.R;
import com.tan.mvpdemo.config.RequestCode;

import java.io.File;


/**
 * Intent辅助类.
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class IntentUtil {

    /**
     * 跳转至指定activity.
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午3:48:25
     * <br> UpdateTime: 2016-11-24,下午3:48:25
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         上下文环境.
     * @param gotoClass
     *         待启动的界面.
     */
    public static void gotoActivity(Context context, Class<?> gotoClass) {
        gotoActivity(context, gotoClass, null);
    }

    /**
     * 携带传递数据跳转至指定activity.
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午3:47:51
     * <br> UpdateTime: 2016-11-24,下午3:47:51
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         上下文环境.
     * @param gotoClass
     *         待启动的界面.
     * @param bundle
     *         携带数据.
     */
    public static void gotoActivity(Context context, Class<?> gotoClass, Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setClass(context, gotoClass);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
    }

    /**
     * 跳转至指定activity.
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午3:47:41
     * <br> UpdateTime: 2016-11-24,下午3:47:41
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         上下文.
     * @param gotoClass
     *         待启动的界面.
     * @param requestCode
     *         请求码.
     */
    public static void gotoActivityForResult(Context context, Class<?> gotoClass, int requestCode) {
        gotoActivityForResult(context, gotoClass, null, requestCode);
    }

    /**
     * 携带传递数据跳转至指定activity.
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午3:47:30
     * <br> UpdateTime: 2016-11-24,下午3:47:30
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         上下文.
     * @param gotoClass
     *         待启动的界面.
     * @param bundle
     *         携带数据.
     * @param requestCode
     *         请求码.
     */
    public static void gotoActivityForResult(Context context, Class<?> gotoClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setClass(context, gotoClass);
        ((Activity) context).startActivityForResult(intent, requestCode);
        ((Activity) context).overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
    }


    /**
     * 跳转至指定activity,并关闭当前activity.
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午3:47:21
     * <br> UpdateTime: 2016-11-24,下午3:47:21
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         上下文.
     * @param gotoClass
     *         待启动的界面.
     */
    public static void gotoActivityAndFinish(Context context, Class<?> gotoClass) {
        gotoActivityAndFinish(context, gotoClass, null);
    }

    /**
     * 携带传递数据跳转至指定activity,并关闭当前activity.
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午3:47:12
     * <br> UpdateTime: 2016-11-24,下午3:47:12
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         上下文.
     * @param gotoClass
     *         待启动的界面.
     * @param bundle
     *         携带数据.
     */
    public static void gotoActivityAndFinish(Context context, Class<?> gotoClass, Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setClass(context, gotoClass);
        context.startActivity(intent);
        ((Activity) context).finish();
        ((Activity) context).overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
    }

    /**
     * 单例模式跳转至指定activity
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午3:48:18
     * <br> UpdateTime: 2016-11-24,下午3:48:18
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         上下文.
     * @param gotoClass
     *         待启动的界面.
     */
    public static void gotoActivityToTopAndFinish(Context context, Class<?> gotoClass) {
        gotoActivityToTopAndFinish(context, gotoClass, null);
    }

    /**
     * 单例模式跳转并携带数据
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午3:48:00
     * <br> UpdateTime: 2016-11-24,下午3:48:00
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         上下文
     * @param gotoClass
     *         待启动的界面.
     * @param bundle
     *         Bundle
     */
    public static void gotoActivityToTopAndFinish(Context context, Class<?> gotoClass, Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setClass(context, gotoClass);
        //        standard-默认模式
        //        singleTop-栈顶复用模式
        //        singleTask-栈内复用模式
        //        singleInstance-全局唯一模式
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
        ((Activity) context).finish();
        ((Activity) context).overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
    }

    /**
     * 携带传递数据跳转至指定activity,并关闭当前activity.
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午3:47:01
     * <br> UpdateTime: 2016-11-24,下午3:47:01
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         上下文环境.
     * @param gotoClass
     *         待启动的界面.
     */
    public static void gotoActivityToTop(Context context, Class<?> gotoClass) {
        gotoActivityToTop(context, gotoClass, null);
    }

    /**
     * 携带传递数据跳转至指定activity,并关闭当前activity.
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午3:46:29
     * <br> UpdateTime: 2016-11-24,下午3:46:29
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         上下文环境.
     * @param gotoClass
     *         待启动的界面.
     * @param bundle
     *         携带数据.
     */
    public static void gotoActivityToTop(Context context, Class<?> gotoClass, Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setClass(context, gotoClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
    }

    /**
     * 携带传递数据跳转至指定activity,
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午3:46:51
     * <br> UpdateTime: 2016-11-24,下午3:46:51
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         上下文环境.
     * @param gotoClass
     *         待启动的界面.
     * @param requestCode
     *         下一个页面关闭时的返回码
     */
    public static void gotoActivityToTopForResult(Context context, Class<?> gotoClass, int requestCode) {
        gotoActivityToTopForResult(context, gotoClass, null, requestCode);
    }

    /**
     * 携带传递数据跳转至指定activity,
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午3:46:38
     * <br> UpdateTime: 2016-11-24,下午3:46:38
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         上下文环境.
     * @param gotoClass
     *         待启动的界面.
     * @param bundle
     *         Bundle
     * @param requestCode
     *         下一个页面关闭时的返回码
     */
    public static void gotoActivityToTopForResult(Context context, Class<?> gotoClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setClass(context, gotoClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
        ((Activity) context).startActivityForResult(intent, requestCode);
        ((Activity) context).overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
    }

    /**
     * 描述：关闭某个activity
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午3:46:07
     * <br> UpdateTime: 2016-11-24,下午3:46:07
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         需要关闭的界面
     */
    public static void finish(Context context) {
        ((Activity) context).finish();
        ((Activity) context).overridePendingTransition(R.anim.exit_enter, R.anim.exit_exit);
    }

    /* **************************************系统相关跳转******************************************/

    /**
     * 跳转到发送短信界面
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午3:46:18
     * <br> UpdateTime: 2016-11-24,下午3:46:18
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         上下文
     * @param phoneNum
     *         手机号码
     * @param content
     *         短信内容
     */
    public static void gotoSendMessageActivity(Context context, String phoneNum, String content) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNum));
        intent.putExtra("sms_body", content);
        context.startActivity(intent);
    }

    /**
     * 跳转拨号界面
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午3:46:18
     * <br> UpdateTime: 2016-11-24,下午3:46:18
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         上下文
     * @param phoneNum
     *         手机号码
     */
    public static void gotoInputPhoneNumActivity(Context context, String phoneNum) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNum));
        context.startActivity(intent);
        //        ((Activity) context).overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
    }

    /**
     * 直接拨打电话
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午3:46:18
     * <br> UpdateTime: 2016-11-24,下午3:46:18
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         上下文
     * @param phoneNum
     *         手机号码
     */
    public static void gotoCallPhoneActivity(Context context, String phoneNum) {
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNum));
            context.startActivity(intent);
            //            ((Activity) context).overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
        } catch (Exception e) {
            gotoInputPhoneNumActivity(context, phoneNum);
        }
    }

    /**
     * 打开系统设置界面
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-5,下午3:53:58
     * <br> UpdateTime: 2016-1-5,下午3:53:58
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         Context
     */
    public static void gotoSystemSettingActivity(Context context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_SETTINGS);
        if (!(context instanceof Activity)) {
            // 从非页面启动的，需要添加FLAG_ACTIVITY_NEW_TASK
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
        //        ((Activity) context).overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
    }

    /**
     * 打开网络设置界面
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-5,下午3:53:58
     * <br> UpdateTime: 2016-1-5,下午3:53:58
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         Context
     */
    public static void gotoWifiActivity(Context context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_WIFI_SETTINGS);
        if (!(context instanceof Activity)) {
            // 从非页面启动的，需要添加FLAG_ACTIVITY_NEW_TASK
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
        //        ((Activity) context).overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
    }

    /***
     * 调用系统相机拍照
     *
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateAuthor: 叶青
     * <br> CreateTime: 2017/11/27 20:56
     * <br> UpdateAuthor: 叶青
     * <br> UpdateTime: 2017/11/27 20:56
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context    Context
     */
    public static void takePhoto(Context context, String path) {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory(Intent.CATEGORY_DEFAULT); // 根据文件地址创建文件
        // String path = ConfigFile.PATH_IMAGES + "/fzd_" + System.currentTimeMillis() + ".jpg";
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", new File(path));
        } else {
            uri = Uri.fromFile(new File(path));
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        ((Activity) context).startActivityForResult(intent, RequestCode.REQUEST_CODE_TAKE_PHOTO);
    }

    /***
     * 系统相册选取图片
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateAuthor: 叶青
     * <br> CreateTime: 2017/11/27 20:56
     * <br> UpdateAuthor: 叶青
     * <br> UpdateTime: 2017/11/27 20:56
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     * @param context    Context
     */
    public static void chosePhoto(Context context) {
        // 使用该方式选择图 当前activity 不能设置 singleTop、singleTask、singleInstance
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);//  Pick an item fromthe data
        intent.setType("image/*");
        ((Activity) context).startActivityForResult(intent, RequestCode.REQUEST_CODE_CHOSE_PHOTO);
    }
}
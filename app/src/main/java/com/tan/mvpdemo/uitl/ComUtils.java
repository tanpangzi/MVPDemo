package com.tan.mvpdemo.uitl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.tan.mvpdemo.BaseApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tanjun on 2017/10/26.
 * 一些不好归类的方法放于此
 */

public class ComUtils {
    /**
     * 检测GPS是否打开
     *
     * @return
     */
    public static boolean checkGPSIsOpen(Context context) {
        boolean isOpen;
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        isOpen = locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
        return isOpen;
    }

    /*public static void openGpsSetting(final Context context){
        //没有打开则弹出对话框
        new AlertDialog.Builder(context)
                .setTitle(R.string.notifyTitle)
                .setMessage(R.string.gpsNotifyMsg)
                // 拒绝, 退出应用
                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //finish();
                                Toast.makeText(context.getApplicationContext(), "请未开启GPS功能", Toast.LENGTH_SHORT).show();
                            }
                        })

                .setPositiveButton(R.string.setting,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //跳转GPS设置界面
                                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                context.startActivity(intent);
                            }
                        })
                .setCancelable(false)
                .show();
    }*/


    //String转毫秒
    public static long dateToMs(String strDate){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    //String转Date
    public static Date strToDate(String strDate){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 点击空白隐藏键盘
     * @param view
     */
    public static void closeKeyBorad(Context mContext, View view){
        InputMethodManager imm = (InputMethodManager)
                mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static Bitmap convertDrawable2BitmapByCanvas(Drawable drawable) {
        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static void startActivity(Context context, Class clazz) {
        startActivity(context, clazz, null);
    }

    public static void startActivity(Context context, Class clazz, Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * 获取斜线后面的文件名
     * @param path
     */
    public static String getFileName(String path){
        int index = path.lastIndexOf("/");
        return path.substring(index+1);
    }

    /**
     * 权限判断
     * @param key
     * @return
     */
    public static boolean getPermissionKey(String key){
        boolean isPermission = false;
        ArrayList<String> permissions = BaseApplication.getInstance().getUserInfoBean().getPermissionKey();

        if (permissions.size() >0 ){
            for (int i = 0; i < permissions.size(); i++) {
                if (key.equals(permissions.get(i))){
                    isPermission = true;
                    break;
                }
            }
        }
        return isPermission;
    }


}

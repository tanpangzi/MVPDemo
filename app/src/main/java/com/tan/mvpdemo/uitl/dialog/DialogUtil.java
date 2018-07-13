package com.tan.mvpdemo.uitl.dialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.tan.mvpdemo.uitl.DateUtil;
import com.tan.mvpdemo.uitl.StringUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * 对话框封装工具类
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class DialogUtil {

    /**
     * 显示日期选择对话框
     */
    public static void showDatePickDialog(Context context, final View v) {
        DatePickerDialog.OnDateSetListener odsl = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar c = Calendar.getInstance();
                c.set(year, monthOfYear, dayOfMonth);
                String dateString = DateUtil.formatTime(c.getTimeInMillis(), DateUtil.DATE_FORMAT_YMD);
                ((TextView) v).setText(dateString);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(context, odsl, year, month, day);
        dpd.show();
    }

    /**
     * 显示选择时间控件
     */
    public static void showTimePickDialog(final Context context, String time, OnTimeSetListener callBack) {
        Calendar cal = Calendar.getInstance();
        int hourOfDay = cal.get(Calendar.HOUR_OF_DAY); // 系统时间
        int minute = cal.get(Calendar.MINUTE);// 系统分钟

        if (!TextUtils.isEmpty(time)) {
            String[] times = time.split(":");
            if (times.length != 2) { // 如果传入时间格式不合法，将默认显示当前日期

                hourOfDay = Integer.parseInt(times[0]);
                minute = Integer.parseInt(times[1]);
            }
        }

        TimePickerDialog tpd = new TimePickerDialog(context, callBack, hourOfDay, minute, true);
        tpd.show();
    }

    /**
     * 显示日期对话框
     */
    public static void showTimePickDialog(Context context, OnTimeSetListener otsl) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(context, otsl, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true); // 是否为二十四制
        dialog.show();
    }

    //    public static void showMessageDg(Context context, String title, String content, String txtLeft, String txtRight, CustomDialog.OnDialogClickListener listener) {
    //        showMessageDg(context, title, content, txtLeft, txtRight, null, listener, Gravity.LEFT);
    //    }
    //
    //    public static void showMessageDg(Context context, String title, String content, String txtLeft, String txtRight, CustomDialog.OnDialogClickListener listener, int gravity) {
    //        showMessageDg(context, title, content, txtLeft, txtRight, null, listener, gravity);
    //    }

    public static void showMessageDg(Context context, String title, String content, String txtLeft, String txtRight, CustomDialog.OnDialogClickListener leftClick, CustomDialog.OnDialogClickListener rightClick) {
        showMessageDg(context, title, content, txtLeft, txtRight, leftClick, rightClick, Gravity.LEFT);
    }

    /**
     * 显示信息选择对话框 自定义左右按钮点击事件
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年5月27日, 下午5:59:20
     * <br> UpdateTime: 2016年5月27日, 下午5:59:20
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param context
     *         上下文对象
     * @param title
     *         标题栏
     * @param content
     *         信息
     * @param txtLeft
     *         左侧按钮文字
     * @param txtRight
     *         右侧按钮文字
     * @param rightClick
     *         按钮点击监听对象
     * @param leftClick
     *         按钮点击监听对象
     * @param gravity
     *         content显示位置
     */
    public static void showMessageDg(Context context, String title, String content, String txtLeft, String txtRight, CustomDialog.OnDialogClickListener leftClick, CustomDialog.OnDialogClickListener rightClick, int gravity) {
        CustomDialog dialog = new CustomDialog(context);
        dialog.showMessageDialog(title, content, txtLeft, txtRight, leftClick, rightClick, gravity);
        dialog.show();
    }

    //
    //    public static void showKnowDialog(Context context, String title, String content, CustomDialog.OnDialogClickListener listener) {
    //        showKnowDialog(context, title, content, listener, Gravity.CENTER, null);
    //    }
    //
    //    public static void showKnowDialog(Context context, String title, String content, CustomDialog.OnDialogClickListener listener, String btnStr) {
    //        showKnowDialog(context, title, content, listener, Gravity.CENTER, btnStr);
    //    }
    //
    //    /**
    //     * 显示文本提示对话框---知道了
    //     * <p>
    //     * <br> Version: 1.0.0
    //     * <br> CreateTime: 2016年7月29日, 下午4:58:28
    //     * <br> UpdateTime: 2016年7月29日, 下午4:58:28
    //     * <br> CreateAuthor: 叶青
    //     * <br> UpdateAuthor: 叶青
    //     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
    //     *
    //     * @param context
    //     *         上下文对象
    //     * @param title
    //     *         提示文本标题
    //     * @param content
    //     *         提示信息
    //     * @param listener
    //     *         点击监听
    //     * @param gravity
    //     *         content显示位置
    //     * @param btnStr
    //     *         按钮文字
    //     */
    //    public static void showKnowDialog(Context context, String title, String content, CustomDialog.OnDialogClickListener listener, int gravity, String btnStr) {
    //        CustomDialog dialog = new CustomDialog(context);
    //        dialog.showKnowDialog(title, content, listener, gravity, btnStr);
    //        dialog.show();
    //    }

    // ******************************************** 单选列表对话框 **************************************************
    public static void showSingleSelectDialog(Context context, String title, String check, final String[] items, CustomDialog.OnDialogClickListener listener) {
        showSingleSelectDialog(context, title, -1, check, StringUtil.getList(items), listener);
    }

    public static void showSingleSelectDialog(Context context, String title, String check, final ArrayList<String> items, CustomDialog.OnDialogClickListener listener) {
        showSingleSelectDialog(context, title, -1, check, items, listener);
    }

    /**
     * 显示单选对话框
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-10-12,上午11:11:36
     * <br> UpdateTime: 2016-10-12,上午11:11:36
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param context
     *         上下文
     * @param title
     *         提示文本标题
     * @param check
     *         选中的内容
     * @param items
     *         选项组合
     * @param itemColor
     *         选项文本颜色
     * @param listener
     *         单击监听
     */
    public static void showSingleSelectDialog(Context context, String title, int itemColor, String check, final ArrayList items, CustomDialog.OnDialogClickListener listener) {
        CustomDialog dialog = new CustomDialog(context);
        int checkedItem = -1;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).equals(check)) {
                checkedItem = i;
                break;
            }
        }
        dialog.setSingleSelectDialog(items, title, itemColor, checkedItem, listener);
        dialog.show();
    }

    // ******************************************** 指定位置显示单选列表对话框 **************************************************

    public static void showSingleSelectDialog4Gravity(Context context, String title, final String[] items, int gravity, CustomDialog.OnDialogClickListener listener) {
        showSingleSelectDialog4Gravity(context, title, StringUtil.getList(items), gravity, listener);
    }

    /**
     * 在指定位置显示单选列表对话框
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年5月28日, 下午3:15:28
     * <br> UpdateTime: 2016年5月28日, 下午3:15:28
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param items
     *         列表item
     * @param gravity
     *         Gravity
     * @param title
     *         标题
     * @param listener
     *         item点击事件
     */
    public static void showSingleSelectDialog4Gravity(Context context, String title, final ArrayList items, int gravity, CustomDialog.OnDialogClickListener listener) {
        CustomDialog dialog = new CustomDialog(context);
        dialog.showSingleSelectDialog4Gravity(items, title, gravity, listener);
        dialog.show();
    }

    //***************************************** iOS底部彈出框 ***********************************************
    public static void showIosDialog(Context context, String title, final String[] items, int gravity, int[] viewMargin, CustomDialog.OnDialogClickListener listener) {
        showIosDialog(context, title, StringUtil.getList(items), gravity, viewMargin, null, listener);
    }

    public static void showIosDialogPurple(Context context, String title, final String[] items, int gravity, int[] viewMargin, CustomDialog.OnDialogClickListener listener) {
        showIosDialogPurple(context, title, StringUtil.getList(items), gravity, viewMargin, null, listener);
    }

    public static void showIosDialog(Context context, String title, final ArrayList<String> items, int gravity, int[] viewMargin, CustomDialog.OnDialogClickListener listener) {
        showIosDialog(context, title, items, gravity, viewMargin, null, listener);
    }

    /**
     * 设置单选列表对话框（取消灰色字体）+ 选项颜色修改===样式：iOS底部彈出框
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年7月2日, 下午3:13:08
     * <br> UpdateTime: 2016年7月2日, 下午3:13:08
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param viewMargin
     *         取消按钮margin
     * @param context
     *         上下文
     * @param title
     *         提示文本标题
     * @param gravity
     *         内容位置
     * @param items
     *         选项组合
     * @param listener
     *         单击监听
     */
    public static void showIosDialog(Context context, String title, ArrayList items, int gravity, int[] viewMargin, HashMap<Integer, Integer> positionColor, CustomDialog.OnDialogClickListener listener) {
        CustomDialog dialog = new CustomDialog(context);
        dialog.showIosDialog(title, items, gravity, viewMargin, positionColor, listener);
        dialog.show();
    }

    public static void showIosDialogPurple(Context context, String title, ArrayList items, int gravity, int[] viewMargin, HashMap<Integer, Integer> positionColor, CustomDialog.OnDialogClickListener listener) {
        CustomDialog dialog = new CustomDialog(context);
        dialog.showIosDialogPurple(title, items, gravity, viewMargin, positionColor, listener);
        dialog.show();
    }

    // ******************************************** 自定义View对话框 **************************************************
    public static void showDialog4View(Context context, View view) {
        CustomDialog dialog = new CustomDialog(context);
        dialog.createDialog(view, false);
        dialog.show();
    }

}
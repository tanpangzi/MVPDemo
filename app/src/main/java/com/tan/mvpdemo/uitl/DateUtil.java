package com.tan.mvpdemo.uitl;

import android.content.Context;
import android.text.TextUtils;


import com.tan.mvpdemo.BaseApplication;
import com.tan.mvpdemo.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


/**
 * 时间处理工具：用于时间相关操作
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class DateUtil {

    /**
     * 时间日期格式化到年月日时分秒. HH：24小时制(0-23)  hh：1~12小时制(1-12)
     */
    public static final String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间日期格式化到年月日.
     */
    public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_YMD_NEW = "yyyy-mm-dd";
    /**
     * 时间日期格式化到年月.
     */
    public static final String DATE_FORMAT_YM = "yyyy-MM";
    /**
     * 时间日期格式化到年月日时分.
     */
    public static final String DATE_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_YMDHMS_NEW = "yyyyMMddHHmmss";
    /**
     * 时间日期格式化到月日.
     */
    public static final String DATE_FORMAT_MD = "MM/dd";
    /**
     * 时分秒.
     */
    public static final String DATE_FORMAT_HMS = "HH:mm:ss";
    /**
     * 时分.
     */
    public static final String DATE_FORMAT_HM = "HH:mm";
    /**
     * 上午.
     */
    public static final String DATE_FORMAT_AM = "AM";
    /**
     * 下午.
     */
    public static final String DATE_FORMAT_PM = "PM";

    // ************************************* 获取当前时间

    /**
     * 获取系统当前时间-yyyy-MM-dd HH:mm:ss
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午2:52:36
     * <br> UpdateTime: 2016-11-24,下午2:52:36
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @return 时间字符串
     */
    public static String getDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_YMDHMS, Locale.getDefault());
        return formatter.format(currentTime);
    }

    /**
     * 获取系统当前时间
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午2:52:28
     * <br> UpdateTime: 2016-11-24,下午2:52:28
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param str
     *         yyyy-MM-dd HH:mm:ss
     *
     * @return 时间字符串
     */
    public static String getDate(String str) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(str, Locale.getDefault());
        return formatter.format(currentTime);
    }

    /**
     * 获取当前时间戳
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午2:52:06
     * <br> UpdateTime: 2016-11-24,下午2:52:06
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     */
    public static long getDateMillis() {
        return System.currentTimeMillis();
    }

    // ************************************* 2016年1月5日 10:44:55 时间格式化

    /**
     * 格式化时间( yyyy-MM-dd hh:mm:ss)
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-18,下午9:06:13
     * <br> UpdateTime: 2016-11-18,下午9:06:13
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param time
     *         原始时间啊， 格式 yyyy-MM-dd hh:mm:ss
     * @param pattern
     *         新时间格式
     *
     * @return 返回格式后的时间
     */
    public static String formatTime(String time, String pattern) {

        if (TextUtils.isEmpty(time)) {
            return "1970-01-01";
        }
        String newTime = "";
        try {
            Date date = new SimpleDateFormat(pattern, Locale.getDefault()).parse(time);
            long timeMillis = date.getTime();
            newTime = new SimpleDateFormat(pattern, Locale.getDefault()).format(timeMillis);

        } catch (Exception e) {
            newTime = time;
            e.printStackTrace();
        }

        return newTime;
    }

    /**
     * 格式化时间(时间戳)
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-15,下午11:23:18
     * <br> UpdateTime: 2016-11-15,下午11:23:18
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param timeMillis
     *         时间戳
     * @param pattern
     *         时间正则
     *
     * @return 返回格式后的时间
     */
    public static String formatTime(long timeMillis, String pattern) {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        // 1451382700000/1000
        if (String.valueOf(timeMillis).length() > 10) {
            return sdf.format(new Date(timeMillis));
        } else {
            return sdf.format(new Date(timeMillis * 1000L));
        }
    }

    /**
     * 获取指定时间格式的时间戳
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年1月3日, 下午1:55:49
     * <br> UpdateTime: 2016年1月3日, 下午1:55:49
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param time
     *         时间
     * @param pattern
     *         格式
     *
     * @return 指定时间格式的时间戳
     */
    public static long formatTime2Millis(String time, String pattern) {
        long timeMillis = 0;
        try {
            Date date = new SimpleDateFormat(pattern, Locale.getDefault()).parse(time);
            // 1451382700000
            timeMillis = date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return timeMillis;
    }

    /**
     * 更改时间格式
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年1月3日, 下午2:08:33
     * <br> UpdateTime: 2016年1月3日, 下午2:08:33
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param time
     *         原时间
     * @param fromPattern
     *         原始格式 yyyy-MM-dd hh:mm:ss
     * @param toPattern
     *         目标格式 yyyy-MM-dd hh:mm:ss
     *
     * @return 格式化之后的时间
     */
    public static String formatTime(String time, String fromPattern, String toPattern) {

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(toPattern, Locale.getDefault());
        try {
            date = new SimpleDateFormat(fromPattern, Locale.getDefault()).parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sdf.format(date);
    }

    // ************************************* 2016年1月5日 10:44:55 时间比较

    /**
     * 日期是否大于今天.
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午2:53:26
     * <br> UpdateTime: 2016-11-24,下午2:53:26
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param date
     *         时间. yyyy-MM-dd
     *
     * @return 大于返回true, 否则返回false.
     */
    public static boolean compareDate4Today(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YMD, Locale.getDefault());
        Date d;
        try {
            d = sdf.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return System.currentTimeMillis() <= d.getTime();
    }

    /**
     * 比较日期
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午2:49:56
     * <br> UpdateTime: 2016-11-24,下午2:49:56
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param s1
     *         yyyy-MM-dd
     * @param s2
     *         yyyy-MM-dd
     *
     * @return 0=(c1相等c2) <0=(c1小于c2) >0=(c1大于c2)
     */
    public static int compareDate(String s1, String s2) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_YMD, Locale.getDefault());
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(s1));
            c2.setTime(df.parse(s2));
        } catch (Exception e) {
            return 0;
        }

        return c1.compareTo(c2);
    }

    /**
     * 比较时间
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-22,下午5:23:41
     * <br> UpdateTime: 2016-11-22,下午5:23:41
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param time1
     *         时间1（格式 yyyy-MM-dd HH:mm:ss）
     * @param time2
     *         时间2（格式 yyyy-MM-dd HH:mm:ss）
     *
     * @return 0=(time1相等time2) <0=(time1小于time2) >0=(time1大于time2)
     */
    public static float compareTime(String time1, String time2) {
        float status = -1;
        try {
            Date date1 = new SimpleDateFormat(DATE_FORMAT_YMDHMS, Locale.getDefault()).parse(time1);
            Date date2 = new SimpleDateFormat(DATE_FORMAT_YMDHMS, Locale.getDefault()).parse(time2);
            long timeMillis1 = date1.getTime();
            long timeMillis2 = date2.getTime();

            status = (float) (timeMillis2 - timeMillis1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // ************************************* 时间工具

    /**
     * 判断当前日期是星期几
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午2:51:45
     * <br> UpdateTime: 2016-11-24,下午2:51:45
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param time
     *         原始时间啊， 格式 yyyy-MM-dd hh:mm:ss
     */
    public static int day4Week(String time) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YMD, Locale.getDefault());
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(time));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        int dayForWeek;
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 判断字符串是否是标准时间格式
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-3-25,下午7:46:25
     * <br> UpdateTime: 2016-3-25,下午7:46:25
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param time
     *         判断的字符串
     * @param fromPattern
     *         时间格式
     *
     * @return 是返回true，不是返回false
     */
    public static boolean checkTime(String time, String fromPattern) {

        try {
            new SimpleDateFormat(fromPattern, Locale.getDefault()).parse(time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 计算时间差：获取两个日期之间的间隔天数
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-22,下午5:48:18
     * <br> UpdateTime: 2016-12-22,下午5:48:18
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param startDate
     *         开始时间
     * @param endDate
     *         结束时间
     *
     * @return 是返回true，不是返回false
     */
    public static float getCalculateDate(String startDate, String endDate) {

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YMD, Locale.getDefault());
        Date date_start = null;
        Date date_end = null;
        try {
            date_start = sdf.parse(startDate);
            date_end = sdf.parse(endDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(date_start);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(date_end);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24f));
    }

    /**
     * 计算时间差：获取两个日期之间的间隔天数
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-22,下午5:23:41
     * <br> UpdateTime: 2016-11-22,下午5:23:41
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param time1
     *         时间1（格式 yyyy-MM-dd HH:mm:ss）
     * @param time2
     *         时间2（格式 yyyy-MM-dd HH:mm:ss）
     *
     * @return * @return 间隔天数
     */
    public static float getCalculateTime(String time1, String time2) {
        float status = 0;
        Date date1;
        try {
            date1 = new SimpleDateFormat(DATE_FORMAT_YMDHMS, Locale.getDefault()).parse(time1);
            Date date2 = new SimpleDateFormat(DATE_FORMAT_YMDHMS, Locale.getDefault()).parse(time2);
            long timeMillis1 = date1.getTime();
            long timeMillis2 = date2.getTime();

            status = (timeMillis1 - timeMillis2) / (1000 * 60 * 60 * 24f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    /**
     * 获取某个月份的天数
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-10-31,下午2:48:20
     * <br> UpdateTime: 2016-10-31,下午2:48:20
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param strDate
     *         yyyy-MM
     */
    public static int getMonth4Days(String strDate) {

        int days = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat();
            Calendar calendar = new GregorianCalendar();
            Date date1 = sdf.parse(strDate);
            calendar.setTime(date1); // 放入你的日期
            days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            // System.out.println("天数为=" + days);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 方法2
        // System.out.println("天数为=" + new Date(2007,02,0).getDate());

        return days;
    }

    /**
     * 计算消息发送时间
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-6,下午8:41:04
     * <br> UpdateTime: 2016-12-6,下午8:41:04
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param date
     *         目标时间
     */
    @SuppressWarnings("deprecation")
    public static String caculateMessageTime(String date) {
        Context context = BaseApplication.getInstance().getApplicationContext();
        String newTime = "";

        // 当前日期
        Date currentDate = new Date(System.currentTimeMillis());
        // 源日期
        Date sourceDate;

        Calendar calendar = Calendar.getInstance();// 获取Calendar实例

        // 当前星期
        int weekPosition = 0;
        // 星期
        String[] weeks = context.getResources().getStringArray(R.array.weeks);

        try {
            sourceDate = new SimpleDateFormat(DATE_FORMAT_YMDHMS, Locale.getDefault()).parse(date);

            // 当前周在当年的第几周
            calendar.setTime(currentDate);
            int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);

            // 源日期在当年的第几周
            calendar.setTime(sourceDate);
            int sourceWeek = calendar.get(Calendar.WEEK_OF_YEAR);

            // 星期下标
            weekPosition = sourceDate.getDay();

            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YMD, Locale.getDefault());
            if (sdf.format(currentDate).equals(sdf.format(sourceDate))) {// 当天，显示小时
                sdf.applyPattern(DATE_FORMAT_HM);
                newTime = sdf.format(sourceDate);
            } else if (currentWeek == sourceWeek) {// 本周之内
                newTime = weeks[weekPosition];
            } else {// 显示日期
                sdf.applyPattern("yy-MM-dd");
                newTime = sdf.format(sourceDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newTime;
    }

    /**
     * 计算多久以前
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午5:14:39
     * <br> UpdateTime: 2016-11-24,下午5:14:39
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param timeStr
     *         yyyy-MM-dd HH:mm:ss
     *
     * @return xxxx前
     */
    public static String timeAgo(String timeStr) {
        Date date = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YMDHMS, Locale.getDefault());
            date = format.parse(timeStr);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        long timeStamp = date.getTime();

        Date currentTime = new Date();
        long currentTimeStamp = currentTime.getTime();
        long seconds = (currentTimeStamp - timeStamp) / 1000;

        long minutes = Math.abs(seconds / 60);
        long hours = Math.abs(minutes / 60);
        long days = Math.abs(hours / 24);

        if (seconds <= 15) {
            return "刚刚";
        } else if (seconds < 60) {
            return seconds + "秒前";
        } else if (seconds < 120) {
            return "1分钟前";
        } else if (minutes < 60) {
            return minutes + "分钟前";
        } else if (minutes < 120) {
            return "1小时前";
        } else if (hours < 24) {
            return hours + "小时前";
        } else if (hours < 24 * 2) {
            return "1天前";
        } else if (days < 30) {
            return days + "天前";
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_YMD, Locale.getDefault());
            return formatter.format(date);
        }
    }

    // ************************************* 判断是否闰年

    /**
     * 获取一年的天数，用集合来装
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午2:53:55
     * <br> UpdateTime: 2016-11-24,下午2:53:55
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @return 天数集合
     */
    @SuppressWarnings("deprecation")
    public static ArrayList<String> getdateDay() {
        ArrayList<String> arrayLists = new ArrayList<>();
        Date date = new Date();
        for (int j = 1; j <= 31; j++) {
            arrayLists.add("1-" + j);
        }
        if (isLeapYear((date.getYear() + 1900))) {// 判断是否闰年
            for (int j = 1; j <= 29; j++) {
                arrayLists.add("2-" + j);
            }
        } else {
            for (int j = 1; j <= 28; j++) {
                arrayLists.add("2-" + j);
            }
        }
        for (int j = 1; j <= 31; j++) {
            arrayLists.add("3-" + j);
        }
        for (int j = 1; j <= 30; j++) {
            arrayLists.add("4-" + j);
        }
        for (int j = 1; j <= 31; j++) {
            arrayLists.add("5-" + j);
        }
        for (int j = 1; j <= 30; j++) {
            arrayLists.add("6-" + j);
        }
        for (int j = 1; j <= 31; j++) {
            arrayLists.add("7-" + j);
        }
        for (int j = 1; j <= 31; j++) {
            arrayLists.add("8-" + j);
        }
        for (int j = 1; j <= 30; j++) {
            arrayLists.add("9-" + j);
        }
        for (int j = 1; j <= 31; j++) {
            arrayLists.add("10-" + j);
        }
        for (int j = 1; j <= 30; j++) {
            arrayLists.add("11-" + j);
        }
        for (int j = 1; j <= 31; j++) {
            arrayLists.add("12-" + j);
        }
        return arrayLists;
    }

    /**
     * 判断是否闰年
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-24,下午2:51:07
     * <br> UpdateTime: 2016-11-24,下午2:51:07
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param year
     *         2015
     */
    public static boolean isLeapYear(int year) {
        return (year % 400) == 0 || (year % 4) == 0 && (year % 100) != 0;
    }


    /**
     * yyyy-MM-dd 周几 HH:mm
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016/3/28 18:54
     * <br> UpdateTime: 2016/3/28 18:54
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param date
     *         yyyy-MM-dd HH:mm:ss
     */
    public static String dayForWeek(String date) {
        Context context = BaseApplication.getInstance().getApplicationContext();
        String newTime;
        try {
            Date sourceDate = new SimpleDateFormat(DATE_FORMAT_YMDHMS, Locale.getDefault()).parse(date);
            // 星期
            String[] weeks = context.getResources().getStringArray(R.array.weeks);
            int weekPosition = sourceDate.getDay();
            newTime = weeks[weekPosition];

            newTime = formatTime(date, DATE_FORMAT_YMD) + " " + newTime + " " + formatTime(date, DATE_FORMAT_HM);
        } catch (Exception e) {
            e.printStackTrace();
            newTime = date;
        }

        return newTime;
    }
}
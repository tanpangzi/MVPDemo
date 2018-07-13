package com.tan.mvpdemo.uitl;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.tan.mvpdemo.BaseApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;

/**
 * sp数据操作工具类：用于方便的向SharedPreferences中读取和写入相关类型数据
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class PreferencesUtil {

    //
    //	/** sp数据操作工具类 */
    //	private static PreferencesUtil spUtil;
    //
    //	/**
    //	 * 获取一个操作sp数据的实例
    //	 * <p>
    //	 * <br> Version: 1.0.0
    //	 * <br> CreateTime: 2016-11-24,下午4:22:17
    //	 * <br> UpdateTime: 2016-11-24,下午4:22:17
    //	 * <br> CreateAuthor: 叶青
    //	 * <br> UpdateAuthor: 叶青
    //	 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
    //	 * <p>
    //	 * SharedPreferences的四种操作模式:
    //	 * <p>
    //	 * Context.MODE_PRIVATE：为默认操作模式,代表该文件是私有数据,只能被应用本身访问,在该模式下, 写入的内容会覆盖原文件的内容
    //	 * <p>
    //	 * Context.MODE_APPEND：模式会检查文件是否存在,存在就往文件追加内容,否则就创建新文件. 用来控制其他应用是否有权限读写该文件.
    //	 * <p>
    //	 * MODE_WORLD_READABLE：表示当前文件可以被其他应用读取.
    //	 * MODE_WORLD_WRITEABLE：表示当前文件可以被其他应用写入.
    //	 *
    //	 * @return sp数据操作工具类
    //	 */

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-30,下午9:12:50
     * <br> UpdateTime: 2016-12-30,下午9:12:50
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param key
     *         SP文件内容Key
     * @param object
     *         数据
     */
    public static void put(String key, Object object) {
        if (key.contains("/")) {
            key = key.replaceAll("/", "_");
        }
        Context context = BaseApplication.getInstance().getApplicationContext();
        SharedPreferences sp = context.getSharedPreferences(Constant.SP_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            putObject(key, object);
        }

        if (!editor.commit()) {
            editor.apply();
        }
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-30,下午9:12:50
     * <br> UpdateTime: 2016-12-30,下午9:12:50
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param key
     *         SP文件内容Key
     * @param defaultObject
     *         默认值
     */
    public static Object get(String key, Object defaultObject) {
        if (key.contains("/")) {
            key = key.replaceAll("/", "_");
        }
        Context context = BaseApplication.getInstance().getApplicationContext();
        SharedPreferences sp = context.getSharedPreferences(Constant.SP_FILE_NAME, Context.MODE_PRIVATE);

        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        } else {
            return getObject(key);
        }
    }

    /**
     * sp保存对象
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年12月3日,下午6:07:50
     * <br> UpdateTime: 2016年12月3日,下午6:07:50
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param fileKey
     *         SP文件内容Key
     * @param obj
     *         要保存的对象，只能保存实现了serializable的对象
     */
    public static void putObject(String fileKey, Object obj) {
        Context context = BaseApplication.getInstance().getApplicationContext();
        if (fileKey.contains("/")) {
            fileKey = fileKey.replaceAll("/", "_");
        }
        SharedPreferences sp = context.getSharedPreferences(Constant.SP_FILE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        try {
            // 先将序列化结果写到byte缓存中，其实就分配一个内存空间
            if (obj != null) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream os = new ObjectOutputStream(bos);
                // 将对象序列化写入byte缓存
                os.writeObject(obj);
                // 将序列化的数据转为16进制保存
                String bytesToHexString = HexBytesUtils.bytes2HexString(bos.toByteArray());
                // 保存该16进制数组
                editor.putString(fileKey, bytesToHexString);
            } else {
                editor.putString(fileKey, "");
            }

            if (!editor.commit()) {
                editor.apply();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取sp里面的obj
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年12月3日,下午6:11:18
     * <br> UpdateTime: 2016年12月3日,下午6:11:18
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param fileKey
     *         SP文件内容Key
     */
    private static Object getObject(String fileKey) {
        Context context = BaseApplication.getInstance().getApplicationContext();
        if (fileKey.contains("/")) {
            fileKey = fileKey.replaceAll("/", "_");
        }
        SharedPreferences sp = context.getSharedPreferences(Constant.SP_FILE_NAME, Activity.MODE_PRIVATE);
        try {
            if (sp.contains(fileKey)) {
                String string = sp.getString(fileKey, "");
                if (TextUtils.isEmpty(string)) {
                    return null;
                } else {
                    // 将16进制的数据转为数组，准备反序列化
                    byte[] stringToBytes = HexBytesUtils.hexString2Bytes(string);
                    ByteArrayInputStream bis = new ByteArrayInputStream(stringToBytes);
                    ObjectInputStream is = new ObjectInputStream(bis);
                    // 返回反序列化得到的对象
                    return is.readObject();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 所有异常返回null
        return null;

    }

    /**
     * 移除某个key值已经对应的值
     */
    public static void remove(String key) {
        Context context = BaseApplication.getInstance().getApplicationContext();
        SharedPreferences sp = context.getSharedPreferences(Constant.SP_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);

        if (!editor.commit()) {
            editor.apply();
        }
    }

    /**
     * 清除所有数据
     */
    public static void clear() {
        Context context = BaseApplication.getInstance().getApplicationContext();
        SharedPreferences sp = context.getSharedPreferences(Constant.SP_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();

        if (!editor.commit()) {
            editor.apply();
        }
    }

    /**
     * 查询某个key是否已经存在
     */
    public static boolean contains(String key) {
        Context context = BaseApplication.getInstance().getApplicationContext();
        SharedPreferences sp = context.getSharedPreferences(Constant.SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     */
    public static Map<String, ?> getAll() {
        Context context = BaseApplication.getInstance().getApplicationContext();
        SharedPreferences sp = context.getSharedPreferences(Constant.SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * sp保存ArrayList<Object>
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime:  2016-4-2,下午1:17:00
     * <br> UpdateTime:  2016-4-2,下午1:17:00
     * <br> CreateAuthor:  叶青
     * <br> UpdateAuthor:  叶青
     * <br> UpdateInfo:  (此处输入修改内容,若无修改可不写.)
     *
     * @param fileKey
     *         SP文件名
     * @param array
     *         ArrayList
     */
    public static void putArrayObject(String fileKey, ArrayList<Object> array) {
        for (int i = 0; i < array.size(); i++) {
            putObject(fileKey + i, array.get(i));
        }
    }

    /**
     * 获取sp里面的ArrayList<Object>
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime:  2016-4-2,下午1:17:00
     * <br> UpdateTime:  2016-4-2,下午1:17:00
     * <br> CreateAuthor:  叶青
     * <br> UpdateAuthor:  叶青
     * <br> UpdateInfo:  (此处输入修改内容,若无修改可不写.)
     *
     * @param fileKey
     *         SP文件名
     */
    public static ArrayList<Object> getArrayObject(String fileKey) {
        ArrayList<Object> array = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (getObject(fileKey + i) != null) {
                array.add(getObject(fileKey + i));
            }
        }
        return array;
    }
}
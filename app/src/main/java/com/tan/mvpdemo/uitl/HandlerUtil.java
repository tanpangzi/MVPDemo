package com.tan.mvpdemo.uitl;

import android.os.Handler;
import android.os.Message;

import com.tan.mvpdemo.executor.Cancel;


/**
 * handler封装工具类
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月30日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class HandlerUtil {
    /**
     * 发送消息.
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-30,下午9:01:57
     * <br> UpdateTime: 2016-12-30,下午9:01:57
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param handler
     *         异步处理对象.
     * @param what
     *         消息.
     */
    public static void sendMessage(Handler handler, int what) {
        if (Cancel.checkCancel()) {
            return;
        }
        Message message = handler.obtainMessage();
        message.what = what;
        handler.sendMessage(message);
    }

    /**
     * 发送消息.
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-30,下午9:01:57
     * <br> UpdateTime: 2016-12-30,下午9:01:57
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param handler
     *         异步处理对象.
     * @param what
     *         消息.
     * @param object
     *         传递对象.
     */
    public static void sendMessage(Handler handler, int what, Object object) {
        if (Cancel.checkCancel(object)) {
            return;
        }
        Message message = handler.obtainMessage();
        message.what = what;
        message.obj = object;
        handler.sendMessage(message);
    }

    //    /**
    //     * 发送消息
    //     * <p>
    //     * <br> Version: 1.0.0
    //     * <br> CreateTime:: 2016年2月21日 下午3:27:38
    //     * <br> UpdateTime: 2016年2月21日 下午3:27:38
    //     * <br> CreateAuthor: 叶青
    //     * <br> UpdateAuthor: 叶青
    //     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
    //     *
    //     * @param handler
    //     *         异步处理对象.
    //     * @param what
    //     *         消息.
    //     * @param object
    //     *         传递对象.
    //     * @param arg1
    //     *         消息.
    //     */
    //    public static void sendMessage(Handler handler, int what, int arg1, Object object) {
    //        if (Cancel.checkCancel(object)) {
    //            return;
    //        }
    //        Message message = handler.obtainMessage();
    //        message.what = what;
    //        message.arg1 = arg1;
    //        message.obj = object;
    //        handler.sendMessage(message);
    //    }
    //
    //    /**
    //     * 发送消息.
    //     * <p>
    //     * <br> Version: 1.0.0
    //     * <br> CreateTime: 2016-12-30,下午9:01:57
    //     * <br> UpdateTime: 2016-12-30,下午9:01:57
    //     * <br> CreateAuthor: 叶青
    //     * <br> UpdateAuthor: 叶青
    //     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
    //     *
    //     * @param handler
    //     *         异步处理对象.
    //     * @param what
    //     *         消息.
    //     * @param arg1
    //     *         消息.
    //     * @param arg2
    //     *         消息.
    //     */
    //    public static void sendMessage(Handler handler, int what, int arg1, int arg2) {
    //        if (Cancel.checkCancel()) {
    //            return;
    //        }
    //        Message message = handler.obtainMessage();
    //        message.what = what;
    //        message.arg1 = arg1;
    //        message.arg2 = arg2;
    //        handler.sendMessage(message);
    //    }
    //
    //    /**
    //     * 发送消息.
    //     * <p>
    //     * <br> Version: 1.0.0
    //     * <br> CreateTime: 2016-12-30,下午9:01:57
    //     * <br> UpdateTime: 2016-12-30,下午9:01:57
    //     * <br> CreateAuthor: 叶青
    //     * <br> UpdateAuthor: 叶青
    //     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
    //     *
    //     * @param handler
    //     *         异步处理对象.
    //     * @param what
    //     *         消息.
    //     * @param arg1
    //     *         消息.
    //     * @param arg2
    //     *         消息.
    //     * @param object
    //     *         传递对象.
    //     */
    //    public static void sendMessage(Handler handler, int what, int arg1, int arg2, Object object) {
    //        if (Cancel.checkCancel(object)) {
    //            return;
    //        }
    //        Message message = handler.obtainMessage();
    //        message.what = what;
    //        message.arg1 = arg1;
    //        message.arg2 = arg2;
    //        message.obj = object;
    //        handler.sendMessage(message);
    //    }

}
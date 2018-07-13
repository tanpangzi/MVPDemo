package com.tan.mvpdemo.executor;

import android.content.Context;

import com.tan.mvpdemo.BaseApplication;
import com.tan.mvpdemo.R;
import com.tan.mvpdemo.bean.ResponseBean;
import com.tan.mvpdemo.uitl.LogUtil;


/**
 * 描述：用于取消用户操作的工具类
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class Cancel {

    /**
     * 描述：检测是否被操作取消
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-30,下午8:40:45
     * <br> UpdateTime: 2016-12-30,下午8:40:45
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param object
     *         ResponseBean
     */
    public static boolean checkCancel(Object object) {
        if (object instanceof ResponseBean) {
            ResponseBean bean = (ResponseBean) object;
            if (BaseApplication.getInstance().getString(R.string.exception_cancel_code).equals(bean.getStatus())) {
                return true;
            }
        }
        try {
            checkInterrupted();
        } catch (CancelException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    /**
     * 描述：检测是否被操作取消
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-30,下午8:41:09
     * <br> UpdateTime: 2016-12-30,下午8:41:09
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @return true操作取消
     */
    public static boolean checkCancel() {
        try {
            checkInterrupted();
        } catch (CancelException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    /**
     * 描述：检测当前线程是否被取消
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-30,下午8:41:29
     * <br> UpdateTime: 2016-12-30,下午8:41:29
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @throws CancelException
     *         抛出取消的异常
     */
    public static void checkInterrupted() throws CancelException {
        checkInterrupted(Thread.currentThread());
    }

    /**
     * 描述：检测当前线程是否被取消
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-30,下午8:44:47
     * <br> UpdateTime: 2016-12-30,下午8:44:47
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param currentThread
     *         当前线程
     *
     * @throws CancelException
     *         抛出取消的异常
     */
    public static void checkInterrupted(Thread currentThread) throws CancelException {

        // currentThread.i.isInterrupted()测试是否当前线程已被中断 中断返回true，否则返回false
        if (currentThread != null && currentThread.isInterrupted()) {
            Context context = BaseApplication.getInstance().getApplicationContext();
            CancelException exception = new CancelException(context.getString(R.string.exception_cancel_code), context.getString(R.string.exception_cancel_message));
            LogUtil.i("checkInterrupted");
            throw exception;
        }
    }

    /**
     * 描述：用户取消操作之后，抛出的异常
     * <p>
     * <br> Author: 叶青
     * <br> Version: 1.0.0
     * <br> Date: 2016年12月11日
     * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
     */
    public static class CancelException extends Exception {
        private static final long serialVersionUID = 1L;

        /** 状态码 */
        public String mStatus;
        /** 取消的信息 */
        public String mInfo;

        CancelException(String status, String info) {
            mStatus = status;
            mInfo = info;
        }
    }

}
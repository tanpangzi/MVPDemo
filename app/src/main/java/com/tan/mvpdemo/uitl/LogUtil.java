package com.tan.mvpdemo.uitl;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * 日志工具类
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class LogUtil {

    /** 是否显示日志 */
    private static final boolean IS_SHOW_LOG = true;
    public static final String TAG = "123456";
    private static final int MAX_LENGTH = 90;
    private static final String LINE_STRING = "-";

    /**
     * 日志输出-->Log.v
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-28,下午5:17:25
     * <br> UpdateTime: 2016-11-28,下午5:17:25
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param message
     *         需要输出的日志信息
     */
    public static void v(String message) {
        if (IS_SHOW_LOG) {
            Log.v(TAG, formatStr("Log.v", message));
        }
    }

    /**
     * 日志输出-->Log.d
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-28,下午5:17:25
     * <br> UpdateTime: 2016-11-28,下午5:17:25
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param message
     *         需要输出的日志信息
     */
    public static void d(String message) {
        if (IS_SHOW_LOG) {
            Log.d(TAG, formatStr("Log.d", message));
        }
    }

    /**
     * 日志输出-->Log.i
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-28,下午5:17:25
     * <br> UpdateTime: 2016-11-28,下午5:17:25
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param message
     *         需要输出的日志信息
     */
    public static void i(String message) {
        if (IS_SHOW_LOG) {
            Log.i(TAG, formatStr("Log.i", message));
        }
    }

    /**
     * 日志输出-->Log.w
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-28,下午5:17:25
     * <br> UpdateTime: 2016-11-28,下午5:17:25
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param message
     *         需要输出的日志信息
     */
    public static void w(String message) {
        if (IS_SHOW_LOG) {
            Log.w(TAG, formatStr("Log.w", message));
        }
    }

    /**
     * 日志输出-->Log.e
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-28,下午5:17:25
     * <br> UpdateTime: 2016-11-28,下午5:17:25
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param message
     *         需要输出的日志信息
     */
    public static void e(String message) {
        if (IS_SHOW_LOG) {
            Log.e(TAG, formatStr("Log.e", message));
        }
    }

    public static void e(Throwable t) {
        String info = "";
        ByteArrayOutputStream baos = null;
        PrintStream printStream = null;
        try {
            baos = new ByteArrayOutputStream();
            printStream = new PrintStream(baos);
            t.printStackTrace(printStream);
            byte[] data = baos.toByteArray();
            info = new String(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (printStream != null) {
                    printStream.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (IS_SHOW_LOG) {
            Log.e(TAG, formatStr("Log.e", info));
        }
    }

    /**
     * 日志输出-->System.out
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-28,下午5:17:25
     * <br> UpdateTime: 2016-11-28,下午5:17:25
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param message
     *         需要输出的日志信息
     */
    public static void out(String message) {
        if (IS_SHOW_LOG) {
            System.out.println(formatStr("System.out", message));
        }
    }

    /**
     * 日志输出-->json格式字符串
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-28,下午5:17:25
     * <br> UpdateTime: 2016-11-28,下午5:17:25
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param json
     *         json格式字符串
     */
    public static void json( String json) {
        if (IS_SHOW_LOG) {
            if (TextUtils.isEmpty(json)) {
                json = "Empty or Null json content";
            }

            Object o = null;
            try {
                o = new JSONObject(json);
            } catch (JSONException ex) {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        o = new JSONArray(json);
                    }
                } catch (JSONException ex1) {
                    Log.e(Constant.LOG_TAG, formatOutPut(json));
                    return;
                }
            }

            if (o != null) {
                try {
                    if (o instanceof JSONObject) {
                        json = (((JSONObject) o).toString(2));
                    } else {
                        json = (((JSONArray) o).toString(2));
                    }
                } catch (Exception e) {
                    Log.e(Constant.LOG_TAG, formatOutPut(json));
                    e.printStackTrace();
                }
            }

            Log.e(Constant.LOG_TAG, formatOutPut(json));
        }
    }

    /**
     * 日志输出-->xml格式字符串
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-28,下午5:17:25
     * <br> UpdateTime: 2016-11-28,下午5:17:25
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param xml
     *         xml格式字符串
     */
    public static void xml(String xml) {
        if (IS_SHOW_LOG) {
            if (TextUtils.isEmpty(xml)) {
                xml = "Empty or Null xml content";
            }
            try {
                Source xmlInput = new StreamSource(new StringReader(xml));
                StreamResult xmlOutput = new StreamResult(new StringWriter());
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                transformer.transform(xmlInput, xmlOutput);
                xml = xmlOutput.getWriter().toString().replaceFirst(">", ">\n");
            } catch (TransformerException e) {
                xml = e.getCause().getMessage() + "\n" + xml;
            }

            Log.i(TAG, formatStr("XML message", xml));
        }
    }

    /**
     * 输出日志相关信息拼接 at
     * com.hxyd.dyt.activity.MainActivity.init(MainActivity.java:58)
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-22,下午5:31:06
     * <br> UpdateTime: 2016-1-22,下午5:31:06
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @return at
     * com.hxyd.dyt.activity.MainActivity.init(MainActivity.java:58)
     */
    private static String getLogInfo() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[5];
        // // 获取线程名
        // String threadName = Thread.currentThread().getName();
        // // 获取线程ID
        // long threadID = Thread.currentThread().getId();
        // 获取文件名.即xxx.java
        String fileName = stackTraceElement.getFileName();
        // 获取类名.即包名+类名
        String className = stackTraceElement.getClassName();
        // 获取方法名称
        String methodName = stackTraceElement.getMethodName();
        // 获取生日输出行数
        int lineNumber = stackTraceElement.getLineNumber();

        // + ("ThreadName: " + threadName + " ==== ThreadID: " + threadID +
        // "\n");
        // logInfoStr = logInfoStr + ("\n \n \n" + "at " + className + "." +
        // methodName + "(" + fileName + ":" + lineNumber + ")" + LINE_STRING +
        // "\n")
        // + ("ThreadName: " + threadName + " ==== ThreadID: " + threadID +
        // "\n");

        return "at " + className + "." + methodName + "(" + fileName + ":" + lineNumber + ")" + "\n";
    }

    /**
     * 格式化输出日志格式
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-5-5,下午6:24:47
     * <br> UpdateTime: 2016-5-5,下午6:24:47
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param method
     *         操作模块
     * @param str
     *         日志内容
     */
    private static String formatStr(String method, String str) {
        if (TextUtils.isEmpty(method)) {
            method = "";
        }

        // 结束横线
        String endLine = "\n";
        for (int i = 0; i < MAX_LENGTH; i++) {
            endLine = endLine + " ";
        }

        int startLine = MAX_LENGTH - method.length();
        for (int i = 0; i < startLine; i++) {
            if (i < startLine / 2) {
                method = LINE_STRING + method;
            } else {
                method = method + LINE_STRING;
            }
        }

        return method + "\n" + getLogInfo() + str + endLine;
    }

    private static String formatOutPut(String json){
        // 结束横线
        String endLine = "\n";
        for (int i = 0; i < MAX_LENGTH; i++) {
            endLine = endLine + " ";
        }

        return getLogInfo() + json + endLine;
    }


}
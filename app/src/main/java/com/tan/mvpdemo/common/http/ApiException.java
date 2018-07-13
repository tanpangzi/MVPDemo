package com.tan.mvpdemo.common.http;

import com.tan.mvpdemo.uitl.Constant;


/**
 * Created by tanjun on 2018/5/31.
 * 错误返回信息
 */

public class ApiException extends RuntimeException {
    public ApiException(int status) {
        super(getErrMessage(status));
    }

    public ApiException(String str) {
        super(str);
    }

    private static String getErrMessage(int status) {
        String message = "";
        switch (status) {
            case 120:
                message = "用户名或密码错误，请重新登录！";
                break;
            case 101:
                Constant.setToken(null);
                Constant.setAlias(null);
                message = "登录已过期，请重新登录！";
                break;
            case 102:
                message = "签名无效！";
                break;
            case 112:
                message = "参数不能为空！";
                break;
            case 1:
                message = "操作失败！";
                break;
            case 100:
                message = "Token过期!";
                break;
            case 111:
                message = "参数长度非法";
                break;
            case 110:
                message = "参数格式非法";
                break;
            case 404:
                message = "服务器连接失败";
                break;
            case 500:
                message = "服务器内部错误";
                break;
            case 502:
                message = "当前网络不可用，请重新检查网络设置";
                break;
            case 400:
                message = "请求错误";
                break;
            case 135:
                message = "原始密码错误";
                break;
            case 410:
                message = "找不到对应的数据！";
                break;
            default:
                message = "未知错误";
                break;
        }
        return message;
    }

}


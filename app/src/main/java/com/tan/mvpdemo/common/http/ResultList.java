package com.tan.mvpdemo.common.http;

import java.util.ArrayList;
import java.util.List;


/**
 * created by tanjun
 * 请求网络list数据
 * @param <T>
 */
public class ResultList<T> {
    private int code;
    private String msg;
    private List<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public ResultList(int code, String msg, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}

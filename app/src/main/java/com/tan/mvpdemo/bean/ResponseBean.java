package com.tan.mvpdemo.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 网络请求返回状态属性
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月30日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class ResponseBean extends BaseBean {

    /** 返回状态码 */
    private String status = "";
    /** 返回消息 */
    private String info = "";
    /** 返回数据 */
    private Object object;

    public ResponseBean() {
    }

    public ResponseBean(String msgStr) {
        super(msgStr);
    }

    /**
     * 构造函数
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年4月19日,下午4:21:43
     * <br> UpdateTime: 2016年4月19日,下午4:21:43
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param status
     *         状态码
     * @param info
     *         状态描述符
     * @param object
     *         附带信息
     */
    public ResponseBean(String status, String info, Object object) {
        this.status = status;
        this.info = info;
        this.object = object;
    }

    @Override
    protected void init(JSONObject jSon) throws JSONException {
        status = jSon.optString("status", "");
        info = jSon.optString("info", "");
        object = jSon.optString("object");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "ResponseBean [status=" + status + ", info=" + info + ", object=" + object + "]";
    }
}
package com.tan.mvpdemo.bean;

/**
 * <br> Description 版本更新
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.bean
 * <br> Date: 2018/6/4
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class UpdateInfoBean{
    /** 版本名称 */
    private String verName;
    /** 版本号 */
    private String verCode;
    /** 是否强制更新 */
    private String force;
    /** 下载地址 */
    private String url;
    /** 描述 */
    private String info;

    public String getVerName() {
        return verName;
    }

    public void setVerName(String verName) {
        this.verName = verName;
    }

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }

    public String getForce() {
        return force;
    }

    public void setForce(String force) {
        this.force = force;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

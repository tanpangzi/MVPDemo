package com.tan.mvpdemo.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by tanjun on 2018/5/31.
 * 登陆返回的用户信息
 */

public class UserInfoLoginBean implements Serializable {

    /**
     * permissionKey : ["zmxAPI:INSTALL","zmxAPI:MONITORS","zmxAPI:WARDEN"]
     * alias : bddedb41c5d8af7816c6bee0d1193388
     * userName : hq0001
     * userId : 14
     * token : afd71767e4fd78110c46ad0cba10e6cf
     */

    /** 极光alias */
    private String alias;
    /** 用户名 */
    private String userName;
    /** 用户id */
    private String userId;
    /** token */
    private String token;
    /** 权限列表 */
    private ArrayList<String> permissionKey;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ArrayList<String> getPermissionKey() {
        return permissionKey;
    }

    public void setPermissionKey(ArrayList<String> permissionKey) {
        this.permissionKey = permissionKey;
    }

}

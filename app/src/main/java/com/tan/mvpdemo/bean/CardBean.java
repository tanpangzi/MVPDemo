package com.tan.mvpdemo.bean;

/**
 * <br> Description
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.bean
 * <br> Date: 2018/6/6
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class CardBean {
    private String id;
    private String name;

    public CardBean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

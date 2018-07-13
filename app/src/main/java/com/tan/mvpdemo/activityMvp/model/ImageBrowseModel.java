package com.tan.mvpdemo.activityMvp.model;

import com.tan.mvpdemo.activityMvp.contract.ImageBrowseContract;
import com.tan.mvpdemo.common.http.RequestServer;
import com.tan.mvpdemo.common.http.ResponseFunc;

import java.util.Map;

import rx.Observable;

/**
 * <br> Description 图片浏览m层
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp.model
 * <br> Date: 2018/6/11
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class ImageBrowseModel implements ImageBrowseContract.ImageBrowseModel {
    @Override
    public Observable<Object> deleteImg(Map<String, String> map) {
        return RequestServer.createRetrofit().deleteImg(map).map(new ResponseFunc<Object>());
    }
}

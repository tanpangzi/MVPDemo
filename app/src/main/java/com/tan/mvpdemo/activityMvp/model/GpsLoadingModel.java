package com.tan.mvpdemo.activityMvp.model;


import com.tan.mvpdemo.activityMvp.contract.GpsLoadingContract;
import com.tan.mvpdemo.bean.ImgBean;
import com.tan.mvpdemo.bean.gpsInstall.FiledBean;
import com.tan.mvpdemo.bean.gpsInstall.GPSBean;
import com.tan.mvpdemo.common.http.RequestServer;
import com.tan.mvpdemo.common.http.ResponseFunc;

import java.util.Map;

import okhttp3.RequestBody;
import rx.Observable;


/**
 * <br> Description
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp.model
 * <br> Date: 2018/6/7
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class GpsLoadingModel implements GpsLoadingContract.GpsLoadingModel {

    /** 绑定设备 */
    @Override
    public Observable<Object> newBinding(Map<String, String> map) {
        return RequestServer.createRetrofit().newBinding(map).map(new ResponseFunc<Object>());
    }

    /** 查询图片 */
    @Override
    public Observable<ImgBean> queryAllImg(Map<String, String> map) {
        return RequestServer.createRetrofit().queryAllImg(map).map(new ResponseFunc<ImgBean>());
    }

    /** 上传图片 */
    @Override
    public Observable<FiledBean> uploadImg(String url, Map<String, String> map, Map<String, RequestBody> fileMap) {
        return RequestServer.createRetrofit().upLoadImg(url, map, fileMap).map(new ResponseFunc<FiledBean>());
    }

    @Override
    public Observable<GPSBean> getGpsLocationInfo(Map<String, String> map) {
        return RequestServer.createRetrofit().getGpsLocationBean(map).map(new ResponseFunc<GPSBean>());
    }

}

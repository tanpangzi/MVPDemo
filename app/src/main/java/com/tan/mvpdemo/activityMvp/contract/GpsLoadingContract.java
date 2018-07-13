package com.tan.mvpdemo.activityMvp.contract;

import com.tan.mvpdemo.activityMvp.BasePresenter;
import com.tan.mvpdemo.activityMvp.BaseView;
import com.tan.mvpdemo.adapter.PhotoGridAdapter;
import com.tan.mvpdemo.bean.ImgBean;
import com.tan.mvpdemo.bean.gpsInstall.FiledBean;
import com.tan.mvpdemo.bean.gpsInstall.GPSBean;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * <br> Description
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp.contract
 * <br> Date: 2018/6/7
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public interface GpsLoadingContract {
    interface GpsLoadingModel {
        /**
         * 绑定
         */
        Observable<Object> newBinding(Map<String, String> map);

        /**
         * 查询图片
         */
        Observable<ImgBean> queryAllImg(Map<String, String> map);

        /**
         * 上传图片
         */
        Observable<FiledBean> uploadImg(String url, Map<String, String> map, Map<String, RequestBody> fileMap);

        /** 获取gps定位信息 */
        Observable<GPSBean> getGpsLocationInfo(Map<String, String> map);
    }

    interface GpsLoadingView extends BaseView {
        /**
         * 文字描述
         */
        String getDescrption();

        /**
         * 查询图片
         */
        void queryImgInfo(ImgBean bean);

        /**
         * 获取返回的filedId
         */
        void getFiledId(FiledBean filedBean);

         /** 绑定成功后跳转 */
        void onBindSuccess(int bindCode);

        /** 获取GPS定位信息 */
        void getGpsLocationInfo();

        void getGpsReturnInfo(GPSBean bean);
    }

    interface GpsLoadingPresenter extends BasePresenter{
        void newBinding(PhotoGridAdapter mAdapter, String userName, String custId, String simId, String imeiId,String isGetLocation);

        /**
         * 查询图片
         */
        void queryAllImg(String imeiId);

        /**
         * 上传图片
         */
        void upLoadImg(PhotoGridAdapter mAdapter, String imgType, List<ImgBean.ImgListBean> datas, String pressPath, String imeiId);

        /** 获取gps定位信息 */
        void getGpsLocationInfo(String custId);
    }

}

package com.tan.mvpdemo.activityMvp.contract;


import com.tan.mvpdemo.activityMvp.BasePresenter;
import com.tan.mvpdemo.activityMvp.BaseView;

import java.util.Map;

import rx.Observable;

/**
 * <br> Description
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp.contract
 * <br> Date: 2018/6/11
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public interface ImageBrowseContract {

    interface ImageBrowseModel {
        /** 图片删除 */
        Observable<Object> deleteImg(Map<String, String> map);
    }

    interface ImageBrowseView extends BaseView{

    }

    interface ImageBrowsePresenter extends BasePresenter{
        void deleteImg(String imageType, String fileName, String imeiId);
    }
}

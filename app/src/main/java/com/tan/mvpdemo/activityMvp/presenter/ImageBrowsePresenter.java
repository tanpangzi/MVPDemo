package com.tan.mvpdemo.activityMvp.presenter;

import com.tan.mvpdemo.BaseApplication;
import com.tan.mvpdemo.activityMvp.BasePresenterImpl;
import com.tan.mvpdemo.activityMvp.contract.ImageBrowseContract;
import com.tan.mvpdemo.activityMvp.model.ImageBrowseModel;
import com.tan.mvpdemo.common.http.FilterSubscriber;
import com.tan.mvpdemo.uitl.ThreeDES;

import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <br> Description 图片查看P层
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp.presenter
 * <br> Date: 2018/6/11
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class ImageBrowsePresenter extends BasePresenterImpl< ImageBrowseContract.ImageBrowseView> implements ImageBrowseContract.ImageBrowsePresenter {

    ImageBrowseContract.ImageBrowseModel model;

    public ImageBrowsePresenter(ImageBrowseContract.ImageBrowseView view) {
        super(view);
    }

    /** 图片删除 */
    @Override
    public void deleteImg(String imageType, String fileName, String imeiId) {
        Map<String, String> map = ThreeDES.getPostHeadMap();
        map.put("token", BaseApplication.getInstance().getToken());
        map.put("groupId", "positionImages");
        map.put("imeiId", imeiId);
        map.put("imageName", fileName);
        model.deleteImg(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<Object>() {
                    @Override
                    public void onNext(Object data) {
                        /** 删除成功 */
                        view.onSuccess();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(String str) {
                        /** 失败报错 */
                        view.showToast(str);
                    }
                });
    }

}

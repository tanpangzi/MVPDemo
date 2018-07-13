package com.tan.mvpdemo.activityMvp.presenter;

import android.text.TextUtils;

import com.tan.mvpdemo.BaseApplication;
import com.tan.mvpdemo.activityMvp.BasePresenterImpl;
import com.tan.mvpdemo.activityMvp.contract.GpsLoadingContract;
import com.tan.mvpdemo.activityMvp.model.GpsLoadingModel;
import com.tan.mvpdemo.adapter.PhotoGridAdapter;
import com.tan.mvpdemo.bean.ImgBean;
import com.tan.mvpdemo.bean.gpsInstall.FiledBean;
import com.tan.mvpdemo.bean.gpsInstall.GPSBean;
import com.tan.mvpdemo.common.http.FilterSubscriber;
import com.tan.mvpdemo.config.ConfigFile;
import com.tan.mvpdemo.uitl.Constant;
import com.tan.mvpdemo.uitl.FileUtil;
import com.tan.mvpdemo.uitl.ThreeDES;
import com.tan.mvpdemo.uitl.imageutils.ImageCompressUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.http.POST;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <br> Description 装车位置 P层
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp.presenter
 * <br> Date: 2018/6/7
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class GpsLoadingPresenter extends BasePresenterImpl<GpsLoadingContract.GpsLoadingView> implements GpsLoadingContract.GpsLoadingPresenter {

    GpsLoadingContract.GpsLoadingModel model;

    public GpsLoadingPresenter(GpsLoadingContract.GpsLoadingView view) {
        super(view);
    }

    /**
     * 绑定
     */
    //继续安装传0
    //信号检测传1
    @Override
    public void newBinding(PhotoGridAdapter mAdapter, String userName, String custId, String simId, String imeiId, final String isGetLocation) {
        /** 安装位置描述 */
        String posDescrip = view.getDescrption();
        /** 图片张数 */
        int imgCount = mAdapter.getCount();

        if (TextUtils.isEmpty(posDescrip)) {
            view.showToast("安装位置描述不能为空");
            return;
        }

        if (imgCount == 0) {
            view.showToast("上传图片不能为空");
            return;
        }

        Map<String, String> map = ThreeDES.getPostHeadMap();
        String token = BaseApplication.getInstance().getToken();
        map.put("token", token);
        map.put("name", userName);
        map.put("custId", custId);
        map.put("simId", simId);
        map.put("imeiId", imeiId);
        map.put("position", posDescrip);
        map.put("isGetLocation", isGetLocation);

        model.newBinding(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<Object>() {
                    @Override
                    public void onNext(Object data) {
                        view.onBindSuccess(Integer.parseInt(isGetLocation));
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(String str) {
                        view.showToast(str);
                    }
                });
    }

    /**
     * 查询图片
     */
    @Override
    public void queryAllImg(String imeiId) {
        Map<String, String> map = ThreeDES.getPostHeadMap();
        String token = BaseApplication.getInstance().getToken();
        map.put("imeiId", imeiId);
        map.put("token", token);
        map.put("groupId", "positionImages");
        model.queryAllImg(map)
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<ImgBean>() {
                    @Override
                    public void onNext(ImgBean data) {
                        data.getImgList();
                        view.queryImgInfo(data);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(String str) {
                        view.showToast(str);
                    }
                });

    }

    /**
     * 上传图片
     */
    @Override
    public void upLoadImg(PhotoGridAdapter mAdapter, String imgType, List<ImgBean.ImgListBean> datas, String pressPath, String imeiId) {
        List<ImgBean.ImgListBean> imgs = datas;
        String fileName = "";
        int itemPosition;
        if (imgs != null) {
            /** 图片位置 */
            itemPosition = mAdapter.getPositionItem();
            if (itemPosition < 9) {
                fileName = Constant.GROUPID_POSITIONIMAGES + "_0" + (itemPosition + 1); //文件名
            } else {
                fileName = Constant.GROUPID_POSITIONIMAGES + "_" + (itemPosition + 1); //文件名
            }
        }

        if (TextUtils.isEmpty(fileName)) {
            return;
        }

        Map<String, String> map = ThreeDES.getPostHeadMap();
        String token = BaseApplication.getInstance().getToken();
        map.put("token", token);
        map.put("imeiId", imeiId);
        map.put("groupId", imgType);

        Map<String, RequestBody> fileMap = new HashMap<>();
        final String vPath = ImageCompressUtil.compressImg(pressPath);
        String jpg = vPath.substring(vPath.lastIndexOf("."));
        fileName = fileName + jpg;
        File file = new File(ConfigFile.PATH_IMAGES + fileName); //文件

        try {
            if (file.exists()) {
                if (file.delete()) {
                    if (file.createNewFile()) {
                        FileUtil.copyFile(new File(vPath), file);
                        /** filename必须加 这是Retrofit的规定  */
                        fileMap.put("imageFiles\";filename=\"" + fileName, getRequestBody(file));
                    }
                }
            } else {
                if (file.createNewFile()) {
                    FileUtil.copyFile(new File(vPath), file);
                    /** filename必须加 这是Retrofit的规定  */
                    fileMap.put("imageFiles\";filename=\"" + fileName, getRequestBody(file));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.uploadImg(Constant.BASE_URL + Constant.METHOD_UPLOADIMG, map, fileMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<FiledBean>() {
                    @Override
                    public void onNext(FiledBean data) {
                        view.getFiledId(data);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(String str) {
                        view.showToast(str);
                    }
                });

    }

    private RequestBody getRequestBody(File file) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), file);
    }

    /**
     * 获取gps定位信息
     */
    @Override
    public void getGpsLocationInfo(String custId) {
        Map<String, String> map = ThreeDES.getPostHeadMap();
        String token = BaseApplication.getInstance().getToken();
        map.put("custId", custId);
        map.put("token", token);

        model.getGpsLocationInfo(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<GPSBean>() {
                    @Override
                    public void onNext(GPSBean data) {
                        view.getGpsReturnInfo(data);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(String str) {
                        view.showToast(str);
                    }
                });

    }

}

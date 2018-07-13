package com.tan.mvpdemo.activityMvp.contract;

import com.tan.mvpdemo.activityMvp.BasePresenter;
import com.tan.mvpdemo.activityMvp.BaseView;
import com.tan.mvpdemo.bean.gpsInstall.ZMXGPSDeviceInfoBean;

import java.util.Map;

import rx.Observable;

/**
 * <br> Description GPS安装设备信息
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp.contract
 * <br> Date: 2018/6/6
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public interface ZMXDeviceInfoContract {
    interface ZMXDeviceInfoModel {
        /** 根据imei获取设备信息 */
        Observable<ZMXGPSDeviceInfoBean> getDeviceInfo(Map<String, String> map);
        /** gps安装设备信息 下一步 */
        Observable<Object> detect(Map<String, String> map);
    }

     /** 继承公共baseView */
    interface ZMXDeviceInfoView extends BaseView {
        /** 获取ImeiId */
        String getImei();
        /** 获取 sim*/
        String getSimId();
        /** 设备类型 */
        String getDeviceType();
        /** 设备名称 */
        String getDeviceName();
        /** 查询后获取返回的数据 */
        void getDeviceInfoByImei(ZMXGPSDeviceInfoBean infoBean);
    }

    interface ZMXDeviceInfoPresenter extends BasePresenter{
        /** 获取设备信息 */
        void getInfoByImei(String custId);
        /** 绑定设备 */
        void detect(String custId);
    }
}

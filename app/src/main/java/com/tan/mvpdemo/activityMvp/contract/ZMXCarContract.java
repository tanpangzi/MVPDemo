package com.tan.mvpdemo.activityMvp.contract;

import com.tan.mvpdemo.activityMvp.BasePresenter;
import com.tan.mvpdemo.activityMvp.BaseView;
import com.tan.mvpdemo.bean.gpsInstall.CustIdBean;
import com.tan.mvpdemo.bean.PickerCardBean;

import java.util.Map;

import rx.Observable;

/**
 * <br> Description 紫米星车辆信息Contract
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activityMvp.contract
 * <br> Date: 2018/6/5
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public interface ZMXCarContract {

    interface ZMXCarModel {
        /** 获取组织信息 */
        Observable<PickerCardBean> getOrgInfo(Map<String, String> map);
        /** 提交客户信息 */
        Observable<CustIdBean> createCustomerInfo(Map<String, String> map);
    }

    interface ZMXCarView extends BaseView{
        void showToast(String msg);
        /** 客户姓名 */
        String getCustomerName();
        /** 车牌号 */
        String getCarNum();
        /** 所属组织 */
        String getOrg();
        /** 获取返回的组织列表 */
        void getOrgList(PickerCardBean datas);
        /** 获取返回的custId */
        void getCustId(String custId);
        /** 成功 */
        void onSuccess();
    }

    interface ZMXCarPresenter extends BasePresenter {
        void getOrgInfo();
        void createCustomerInfo();
    }

}

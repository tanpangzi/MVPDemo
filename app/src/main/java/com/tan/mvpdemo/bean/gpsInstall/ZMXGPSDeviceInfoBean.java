package com.tan.mvpdemo.bean.gpsInstall;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanjun on 2018/1/18.
 * gps安装 输入imei sim卡号
 */

public class ZMXGPSDeviceInfoBean{
    private String custId;
    private String simId;
    private String isInput;
    private List<ReturnListBean> returnList = new ArrayList<>();

    public String getSimId() {
        return simId;
    }

    public void setSimId(String simId) {
        this.simId = simId;
    }

    public String getIsInput() {
        return isInput;
    }

    public void setIsInput(String isInput) {
        this.isInput = isInput;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public List<ReturnListBean> getReturnList() {
        return returnList;
    }

    public void setReturnList(List<ReturnListBean> returnList) {
        this.returnList = returnList;
    }

    public static class ReturnListBean{
        /**
         * versionType : HX-110
         * type : 1
         * belongsId : 16
         */

        private String versionType;
        private String type;
        private String belongsId;
        private String supplierId;
        private String serviceDate;

        public String getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(String supplierId) {
            this.supplierId = supplierId;
        }

        public String getServiceDate() {
            return serviceDate;
        }

        public void setServiceDate(String serviceDate) {
            this.serviceDate = serviceDate;
        }

        public String getVersionType() {
            return versionType;
        }

        public void setVersionType(String versionType) {
            this.versionType = versionType;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBelongsId() {
            return belongsId;
        }

        public void setBelongsId(String belongsId) {
            this.belongsId = belongsId;
        }

    }

}

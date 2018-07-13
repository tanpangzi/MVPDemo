package com.tan.mvpdemo.bean.gpsInstall;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/13.
 */

public class GPSBean implements Serializable {
    /**
     * isCounterProduct : 1
     * returnList : [{"accState":"0","serviceDate":"2019-12-28 21:19:01","color":"4","latitude":"38.25732104024117","onlineStatus":0,"phoneNum":"13666666666","type":0,"speed":0,"stateLength":"0秒","installDate":"2017-10-09 15:19:00","simId":"1064861055802","batteryCapacity":0,"locationState":0,"longitude":"109.77166273691928","imeiId":"14530708109","versionType":"XT200C","orgName":"榆林店","receiveDate":"2017-11-29 00:05:09","gpsSignalIntensity":0,"carNumber":"粤B00021","gsmSignalIntensity":0,"name":"测试柜面","locationDate":"2017-11-29 00:05:07","gpsOrGms":"0","status":"停止"}]
     */

    private String isCounterProduct;
    private ArrayList<ReturnListBean> returnList = new ArrayList<>();

    public String getIsCounterProduct() {
        return isCounterProduct;
    }

    public void setIsCounterProduct(String isCounterProduct) {
        this.isCounterProduct = isCounterProduct;
    }

    public ArrayList<ReturnListBean> getReturnList() {
        return returnList;
    }

    public void setReturnList(ArrayList<ReturnListBean> returnList) {
        this.returnList = returnList;
    }

    public static class ReturnListBean implements Serializable {
        /**
         * accState : 0
         * serviceDate : 2020-01-25 16:40:45
         * color : 0
         * latitude : 35.593785292227864
         * onlineStatus : 1
         * phoneNum : 13312312312
         * type : 0
         * speed : 6
         * stateLength : 0秒
         * installDate : 2017-03-22 12:00:00
         * simId : 1064861055780
         * batteryCapacity : 0
         * locationState : 0
         * longitude : 104.61641354682766
         * imeiId : 14530164913
         * versionType : XT200C
         * orgName : 兰州店
         * receiveDate : 2018-01-11 21:52:04
         * gpsSignalIntensity : 0
         * carNumber : 粤A88888
         * gsmSignalIntensity : 0
         * name : 测试别动1
         * locationDate : 2018-01-11 21:52:04
         * gpsOrGms : GPS
         * status : 离线
         */
        private int onlineStatus;
        private int type;
        private int speed;
        private int batteryCapacity;
        private int locationState;
        private int gpsSignalIntensity;
        private int gsmSignalIntensity;
        private String accState;
        private String serviceDate;
        private String color;
        private String latitude;
        private String phoneNum;
        private String stateLength;
        private String installDate;
        private String simId;
        private String longitude;
        private String imeiId;
        private String versionType;
        private String orgName;
        private String receiveDate;
        private String carNumber;
        private String name;
        private String locationDate;
        private String gpsOrGms;
        private String status;

        public String getAccState() {
            return accState;
        }

        public void setAccState(String accState) {
            this.accState = accState;
        }

        public String getServiceDate() {
            return serviceDate;
        }

        public void setServiceDate(String serviceDate) {
            this.serviceDate = serviceDate;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public int getOnlineStatus() {
            return onlineStatus;
        }

        public void setOnlineStatus(int onlineStatus) {
            this.onlineStatus = onlineStatus;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public String getStateLength() {
            return stateLength;
        }

        public void setStateLength(String stateLength) {
            this.stateLength = stateLength;
        }

        public String getInstallDate() {
            return installDate;
        }

        public void setInstallDate(String installDate) {
            this.installDate = installDate;
        }

        public String getSimId() {
            return simId;
        }

        public void setSimId(String simId) {
            this.simId = simId;
        }

        public int getBatteryCapacity() {
            return batteryCapacity;
        }

        public void setBatteryCapacity(int batteryCapacity) {
            this.batteryCapacity = batteryCapacity;
        }

        public int getLocationState() {
            return locationState;
        }

        public void setLocationState(int locationState) {
            this.locationState = locationState;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getImeiId() {
            return imeiId;
        }

        public void setImeiId(String imeiId) {
            this.imeiId = imeiId;
        }

        public String getVersionType() {
            return versionType;
        }

        public void setVersionType(String versionType) {
            this.versionType = versionType;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getReceiveDate() {
            return receiveDate;
        }

        public void setReceiveDate(String receiveDate) {
            this.receiveDate = receiveDate;
        }

        public int getGpsSignalIntensity() {
            return gpsSignalIntensity;
        }

        public void setGpsSignalIntensity(int gpsSignalIntensity) {
            this.gpsSignalIntensity = gpsSignalIntensity;
        }

        public String getCarNumber() {
            return carNumber;
        }

        public void setCarNumber(String carNumber) {
            this.carNumber = carNumber;
        }

        public int getGsmSignalIntensity() {
            return gsmSignalIntensity;
        }

        public void setGsmSignalIntensity(int gsmSignalIntensity) {
            this.gsmSignalIntensity = gsmSignalIntensity;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocationDate() {
            return locationDate;
        }

        public void setLocationDate(String locationDate) {
            this.locationDate = locationDate;
        }

        public String getGpsOrGms() {
            return gpsOrGms;
        }

        public void setGpsOrGms(String gpsOrGms) {
            this.gpsOrGms = gpsOrGms;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }

}

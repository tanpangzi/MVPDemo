package com.tan.mvpdemo.bean.gpsInstall;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanjun on 2018/1/19.
 * 装车位置 绑定 GPS信息
 */

public class NewBindingBean{

    private List<ReturnListBean> returnList = new ArrayList<>();

    public List<ReturnListBean> getReturnList() {
        return returnList;
    }

    public void setReturnList(List<ReturnListBean> returnList) {
        this.returnList = returnList;
    }

    public static class ReturnListBean {
        /**
         * receiveDate :
         * serviceDate : 2019-09-07 23:59:00
         * accState : 0
         * type : 0
         * onlineStatus : 1
         * phoneNum :
         * installDate : 2017-09-07 16:23:00
         * name : 祖尔
         * longitude : 0
         * batteryCapacity : 0
         * gpsSignalIntensity : 0
         * gpsOrGms : 0
         * status : 离线
         * speed : 0
         * simId :
         * orgName :
         * gsmSignalIntensity : 0
         * color : 0
         * carNumber : 粤C12345
         * locationDate :
         * imeiId : 201709060009
         * stateLength : 0秒
         * latitude : 0
         * locationState : 0
         * versionType : XT800
         */

        private String receiveDate;
        private String serviceDate;
        private String accState;
        private String type;
        private String onlineStatus;
        private String phoneNum;
        private String installDate;
        private String name;
        private double longitude;
        private int batteryCapacity;
        private int gpsSignalIntensity;
        private String gpsOrGms;
        private String status;
        private int speed;
        private String simId;
        private String orgName;
        private int gsmSignalIntensity;
        private String color;
        private String carNumber;
        private String locationDate;
        private String imeiId;
        private String stateLength;
        private double latitude;
        private int locationState;
        private String versionType;

        public String getReceiveDate() {
            return receiveDate;
        }

        public void setReceiveDate(String receiveDate) {
            this.receiveDate = receiveDate;
        }

        public String getServiceDate() {
            return serviceDate;
        }

        public void setServiceDate(String serviceDate) {
            this.serviceDate = serviceDate;
        }

        public String getAccState() {
            return accState;
        }

        public void setAccState(String accState) {
            this.accState = accState;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOnlineStatus() {
            return onlineStatus;
        }

        public void setOnlineStatus(String onlineStatus) {
            this.onlineStatus = onlineStatus;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public String getInstallDate() {
            return installDate;
        }

        public void setInstallDate(String installDate) {
            this.installDate = installDate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(int longitude) {
            this.longitude = longitude;
        }

        public int getBatteryCapacity() {
            return batteryCapacity;
        }

        public void setBatteryCapacity(int batteryCapacity) {
            this.batteryCapacity = batteryCapacity;
        }

        public int getGpsSignalIntensity() {
            return gpsSignalIntensity;
        }

        public void setGpsSignalIntensity(int gpsSignalIntensity) {
            this.gpsSignalIntensity = gpsSignalIntensity;
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

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public String getSimId() {
            return simId;
        }

        public void setSimId(String simId) {
            this.simId = simId;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public int getGsmSignalIntensity() {
            return gsmSignalIntensity;
        }

        public void setGsmSignalIntensity(int gsmSignalIntensity) {
            this.gsmSignalIntensity = gsmSignalIntensity;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getCarNumber() {
            return carNumber;
        }

        public void setCarNumber(String carNumber) {
            this.carNumber = carNumber;
        }

        public String getLocationDate() {
            return locationDate;
        }

        public void setLocationDate(String locationDate) {
            this.locationDate = locationDate;
        }

        public String getImeiId() {
            return imeiId;
        }

        public void setImeiId(String imeiId) {
            this.imeiId = imeiId;
        }

        public String getStateLength() {
            return stateLength;
        }

        public void setStateLength(String stateLength) {
            this.stateLength = stateLength;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(int latitude) {
            this.latitude = latitude;
        }

        public int getLocationState() {
            return locationState;
        }

        public void setLocationState(int locationState) {
            this.locationState = locationState;
        }

        public String getVersionType() {
            return versionType;
        }

        public void setVersionType(String versionType) {
            this.versionType = versionType;
        }


    }

}

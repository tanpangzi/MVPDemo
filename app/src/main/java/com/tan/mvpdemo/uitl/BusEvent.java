package com.tan.mvpdemo.uitl;

import android.support.v4.app.Fragment;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by tanjun on 2017/5/31.
 * eventBus消息处理
 */

public class BusEvent {


    public String businessId;
    public String custId;

    public static final int SHOW_USERINFO_VIEW = 1;

    public static final int SHOW_LOADNINFO_VIEW = 2;

    public static final int SHOW_VEHICLEINFO_VIEW = 3;

    public static final int SHOW_IMAGEINFO_VIEW = 4;

    public static final int SINGLE_SCCESS = 5;

    public static final int STARTCAMERA = 6;

    public static final int STARTIMAGESELECTOR = 7;

    public static final int IMAGEINFO_SHOWCAMERA = 8;

    public static final int IMAGEINFO_SHOWIMAGESELECTOR = 9;

    public static final int IMAGEINFO_SHOW_UP_DAILOG = 10;

    public static final int IMAGEINFO_DIMS_UP_DAILOG = 11;

    public static final int SHOW_SPACE_IMAGE_DETAIL_ACTIVITY = 12;

    public static final int SHOW_SPACE_IMAGE_DETAIL_ACTIVITY_SINGLE = 14;

    public static final int UP_COUNT_TOTAL = 13;

    public static final int POST_DEALER_BUY_PRICE = 15;

    public static final int SHOW_DEALER_BUY_PRICE = 16;

    public static final int IAMGE_ITEM_RESH_STATE = 17;

    public static final int IAMGE_SYNCAPPDATA = 18;

    public static final int IMAGE_PREVIEW = 19;

    public static final int IMAGE_PREVIEW_DELETE = 20;

    public static final int IMAGE_PREVIEW_DELETE_SHOW = 21;

//    public static final int SHOW_LOOK_AL = 22;
//
//    public static final int GONE_LOOK_AL = 23;

    public static final int LOGIN_OUT = 24;

    public static final int UPDATA_LOGIN_SUCCESS = 25;

    public static final int ACTIVITY_OUT = 26;

    public static final int REFRESH_COUNTTOTAL = 27;

    public static final int FRAGMENT_SUPPLEMENTARY = 28;

    public static final int FRAGMENT_ASSESSMENT_RESULT = 29;

    public static final int FRAGMENT_CUSTOMERINFO = 30;

//    public static final int FRAGMENT_SINGLE_HOME = 31;

    public static final int FRAGMENT_IMAGEINFO_NEW = 32;

    public static final int FRAGMENT_USERINFO_NEW = 33;

    public static final int SHOW_OCR_ID = 34;

    public static final int SHOW_OCR_XD = 35;

    public static final int LOAD_VIN = 36;

//    public static final int SHOW_ADD_CONTACT = 37;

    public static final int SHOW_ASSESSMENT_IMAGESELECTOR = 38;

    public static final int SHOW_ASSESSMENT_CAMERA = 39;

    public static final int DELETE_ASSESSMENT_IMAGE = 40;

    public static final int SEE_CAR_DATA_IMAGE = 41;

    public static final int SEE_CAR_DATA_IMAGE_ERR = 42;

    public static final int ORDER_DEFAULT_FINSH = 43;

//    public static final int COLSE_DIALOG_ZD = 44;

    public static final int SINGLE_BACK = 45;

    public static final int ASSESSMENTRESULT_GETORDERDEFAULT = 46;

    public static final int ORDERDEFAULT_LOANDETAIL = 47;

    public static final int USERINFONEWFRAGMENT_SAVE = 48;

    public static final int SUPPLEMENTARYFRAGMENT_SAVE = 49;

    public static final int SHOW_OCR_XD_VALUE = 50;

    public int event;

    public int paraInt;

    public String para;

    public Object object;

    public View view;


    public ArrayList<String> list;

    public Fragment f;

    public BusEvent(int e) {
        this.event = e;
    }

    public BusEvent(int e, String p) {
        this.event = e;
        this.para = p;
    }

    public BusEvent(int e, ArrayList<String> list, int paraInt) {
        this.event = e;
        this.list = list;
        this.paraInt = paraInt;
    }

    public BusEvent(int e, int p) {
        this.event = e;
        this.paraInt = p;
    }

    public BusEvent(int e, int p, Object o) {
        this.event = e;
        this.paraInt = p;
        this.object = o;
    }

    public BusEvent(int e, String p, Object o) {
        this.event = e;
        this.para = p;
        this.object = o;
    }

    public BusEvent(int e, String p, View v) {
        this.event = e;
        this.para = p;
        this.view = v;
    }

    public BusEvent(int e, int p, Object o, View v) {
        this.event = e;
        this.paraInt = p;
        this.object = o;
        this.view = v;
    }

    public BusEvent(int e, Fragment f) {
        this.event = e;
        this.f = f;
    }

    public BusEvent(int e, Fragment f, String businessId, String custId) {
        this.event = e;
        this.f = f;
        this.businessId = businessId;
        this.custId = custId;
    }

}

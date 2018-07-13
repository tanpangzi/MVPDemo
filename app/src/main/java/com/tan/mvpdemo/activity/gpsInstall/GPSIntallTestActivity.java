package com.tan.mvpdemo.activity.gpsInstall;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.tan.mvpdemo.R;
import com.tan.mvpdemo.activity.MainActivity;
import com.tan.mvpdemo.activityMvp.contract.GpsInstallContract;
import com.tan.mvpdemo.activityMvp.presenter.GpsInstallPresenter;
import com.tan.mvpdemo.bean.gpsInstall.GPSBean;
import com.tan.mvpdemo.uitl.ComUtils;
import com.tan.mvpdemo.uitl.IntentUtil;
import com.tan.mvpdemo.uitl.LogUtil;
import com.tan.mvpdemo.uitl.TitleView;
import com.tan.mvpdemo.uitl.ToastUtil;
import com.tan.mvpdemo.uitl.amapUtils.GPS3DUtils;

import java.util.ArrayList;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * <br> Description gps信号检测
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activity.gpsInstall
 * <br> Date: 2018/6/14
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
@RuntimePermissions
public class GPSIntallTestActivity extends FragmentActivity implements
        com.amap.api.maps2d.AMap.InfoWindowAdapter
        ,GpsInstallContract.GpsInstallView
        ,View.OnClickListener
        ,GeocodeSearch.OnGeocodeSearchListener {

    /** 2d地图 */
    MapView mapView;
    /** 标题 */
    TitleView titleView;
    /** imei */
    TextView tv_imei;
    /** 接收时间 */
    TextView tv_receiveDate;
    /** 定位时间 */
    TextView tv_locationDate;
    /** 定位方式 */
    TextView tv_location_type;
    /** 当前位置 */
    TextView tv_location;

    /** 刷新位置 */
    Button btn_refresh_position;
    /** 拆除 */
    Button btn_remove;
    /** 锁油断电 */
    Button btn_lock_oil_elec;
    /** 刷新手机位置 */
    TextView tv_refresh_phone;
    /** 完成安装 */
    TextView tv_complete;

    private ArrayList<GPSBean.ReturnListBean> listbean;
    GPSBean.ReturnListBean bean;

    private String imeiId;
    private String custId;
    private String simId;

    private String receiveDate; //接收时间
    private String locationDate; //定位时间

    private double lon;
    private double lat;

    public double curLon = 0;//当前lon
    public double curLat = 0;//当前lat

    GpsInstallContract.GpsInstallPresenter presenter;

    private AMap aMap;
    private MyLocationStyle myLocationStyle;
    private ArrayList<Marker> listMarker = new ArrayList<>();

    private String location_type; //gps gms wifi
    private int type;//设备类型 0有线 1无线 2odb

    private String wayBillTitle; //气泡 标题
    Marker waybillMarker;

    /** 拆除 */
    private final int TearDown = 0;
    /** 安装完成  */
    private final int InstallComplete = 1;

    /** 小气泡 */
    private View infoWindow;

    private TextView snippetUi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installation_test);

        /** p层实例化 */
        presenter = new GpsInstallPresenter(this);
        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState); //必须重写

        findViewsById();
        initGetData();
        setOnClick();

    }

    /** 控件初始化 */
    private void findViewsById() {
        titleView = findViewById(R.id.title_view);
        tv_imei =  findViewById(R.id.tv_imei);
        tv_receiveDate = findViewById(R.id.tv_receiveDate);
        tv_locationDate = findViewById(R.id.tv_locationDate);
        tv_location_type = findViewById(R.id.tv_location_type);
        tv_location = findViewById(R.id.tv_location);

        btn_refresh_position = findViewById(R.id.btn_refresh_position);//刷新当前位置
        btn_remove = findViewById(R.id.btn_remove);//拆除
        btn_lock_oil_elec = findViewById(R.id.btn_lock_oil_elec);//锁油断电

        tv_refresh_phone = findViewById(R.id.tv_refresh_phone);//刷新手机位置
        tv_complete = findViewById(R.id.tv_complete);//完成安装

        initMap();
    }

    /**  初始化地图 */
    private void initMap() {
        myLocationStyle = new MyLocationStyle();
        myLocationStyle.showMyLocation(true);
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);
        aMap = mapView.getMap();
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationEnabled(true);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(18));
    }

    /** 解析当前地址 */
    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int i) {
        if (result != null) {
            String formatAddress = result.getRegeocodeAddress().getFormatAddress();
            tv_location.setText(formatAddress);
        } else {
            tv_location.setText("经纬度解析有误");
        }
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }

    /** 点击 */
    private void setOnClick() {
        titleView.setTitle(R.string.gps_signal_check);
        titleView.setLeftBtnImg(R.drawable.arrow_left_black, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endAndJump();
            }
        });

        btn_refresh_position.setOnClickListener(this);
        btn_remove.setOnClickListener(this);
        btn_lock_oil_elec.setOnClickListener(this);
        tv_refresh_phone.setOnClickListener(this);
        tv_complete.setOnClickListener(this);

        /**
         * 地图 marker点击事件
         */
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                wayBillTitle = marker.getTitle();
                if (TextUtils.isEmpty(wayBillTitle)){
                    return false;
                }else {
                    getLocationInfo(wayBillTitle,custId,false);
                    showToast(wayBillTitle);
                }

                return false;
            }
        });
    }

    /** 点击事件 */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_refresh_position: //刷新位置
                getLocationInfo(wayBillTitle, custId, false);
                break;

            case R.id.btn_remove: //拆除
                commonFun("是否拆除设备", TearDown);
                break;

            case R.id.btn_lock_oil_elec: //锁油断电
                IntentUtil.gotoActivity(this, LockOilActivity.class);
                break;

            case R.id.tv_complete: //安装完成
                commonFun("是否安装完成", InstallComplete);
                break;

            case R.id.tv_refresh_phone: //刷新手机位置
                reLoad();
                break;
        }
    }

    /** 获取上一个界面的信息 */
    private void initGetData() {
        Bundle bundle = getIntent().getExtras();
        listbean = (ArrayList<GPSBean.ReturnListBean>) bundle.getSerializable("gpsList");

        imeiId = bundle.getString("imeiId");
        custId = bundle.getString("custId");
        simId = bundle.getString("simId");

        /** 列表里第一辆的位置 */
        lon = Double.parseDouble(listbean.get(0).getLongitude());
        lat = Double.parseDouble(listbean.get(0).getLatitude());

        setData(0, listbean);
    }


    /** 返回的gps定位信息 */
    @Override
    public void getGpsLocationInfo(String subTitle , boolean isComplete, GPSBean bean) {
        listbean.clear();
        listbean = bean.getReturnList();
        if (!TextUtils.isEmpty(subTitle)){
            for (int i = 0; i < listbean.size(); i++) {
                imeiId = listbean.get(i).getImeiId();
                wayBillTitle = imeiId.substring(imeiId.length() - 5, imeiId.length()); //imei 后5位
                if (wayBillTitle.equals(subTitle)){
                    setData(i, listbean);
                }
            }
        }else {
            setData(0, listbean);
        }
        startLocation(isComplete);
    }

    @Override
    public void commonFun(String tip, final int type) {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage(tip)
                .setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                        if (type == TearDown){//拆除
                            presenter.tearDown(imeiId);
                        }else if (type == InstallComplete){//安装完成
                            getLocationInfo(wayBillTitle, custId, true);
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

    /** 安装完成 */
    @Override
    public void installComplete() {
        endAndJump();
    }

    /**
     * 数据设置
     * pos表示在listBean中的位置
     * @param listbean
     */
    private void setData(int pos, ArrayList<GPSBean.ReturnListBean> listbean) {
        lat = Double.parseDouble(listbean.get(pos).getLatitude());
        lon = Double.parseDouble(listbean.get(pos).getLongitude());

        receiveDate = listbean.get(pos).getReceiveDate();
        locationDate = listbean.get(pos).getLocationDate();

        location_type = listbean.get(pos).getGpsOrGms(); //gps gms wifi 直接显示文字
        imeiId = listbean.get(pos).getImeiId();
        type = listbean.get(pos).getType();//设备类型

        wayBillTitle= imeiId.substring(imeiId.length() - 5, imeiId.length()); //imei 后5位

        if (type == 2){
            btn_lock_oil_elec.setVisibility(View.VISIBLE); //显示锁油断电
        }else {
            btn_lock_oil_elec.setVisibility(View.GONE);
        }

        tv_imei.setText(imeiId);
        tv_receiveDate.setText(receiveDate);
        tv_locationDate.setText(locationDate);
        tv_location_type.setText("[" + location_type.toUpperCase() + "]");//gps wifi
        //setInfoWindow();
        /** 申请权限并定位 */
        GPSIntallTestActivityPermissionsDispatcher.showPermissionWithCheck(this);
        //GPS3DUtils.getInstance().getAddressByLatlng2D(this, new LatLng(lat, lon),this);
    }

    /**
     * 跳转到首页
     */
    private void endAndJump(){
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("type",4);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    /** 后退 */
    @Override
    public void onBackPressed() {
        endAndJump();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showToast(this, msg);
    }

    @Override
    public void onSuccess() {

    }

    /** 在地图上显示车信息 */
    public void addMarkersToMap(ArrayList<GPSBean.ReturnListBean> markerList) {
        try {
            listMarker.clear();
            Drawable drawable = null;
            List<Marker> mapScreenMarkers = aMap.getMapScreenMarkers();
            for (int i = 0; i < mapScreenMarkers.size(); i++) {
                Marker marker = mapScreenMarkers.get(i);
                String title = marker.getTitle();
                if (title != null) {
                    marker.remove();
                }
            }
            mapView.invalidate();

            for (int i = 0; i < markerList.size(); i++) {
                GPSBean.ReturnListBean addCodeBean = markerList.get(i);
                double lat = Double.parseDouble(addCodeBean.getLatitude());
                double lng = Double.parseDouble(addCodeBean.getLongitude());
                locationDate = addCodeBean.getLocationDate();
                receiveDate = addCodeBean.getReceiveDate();

                String strImei = markerList.get(i).getImeiId();
                if ((int) lat == 0 || (int) lng == 0 || TextUtils.isEmpty(locationDate) || TextUtils.isEmpty(receiveDate)) {
                    lat = curLat + 0.0001 * (i + 1);
                    lng = curLon + 0.0001 * (i + 1);
                    drawable = getResources().getDrawable(
                            R.drawable.red_car);
                } else {
                    drawable = getResources().getDrawable(
                            R.drawable.green_car);
                }
                type = markerList.get(i).getType(); //设备类型 有线0  无线1  odb 2

                Bitmap bitmap = ComUtils.convertDrawable2BitmapByCanvas(drawable);
                LatLng latlng = new LatLng(lat, lng);

                GPS3DUtils.getInstance().getAddressByLatlng2D(this, new LatLng(lat, lng),this);

                aMap.moveCamera(CameraUpdateFactory.changeLatLng(latlng));

                waybillMarker = aMap.addMarker(new MarkerOptions()
                        .position(latlng)
                        .title("")
                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                        .draggable(true));//添加
                waybillMarker = aMap.addMarker(new MarkerOptions()
                        .position(latlng)
                        .title("")
                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap)));

                strImei = strImei.substring(strImei.length() - 5, strImei.length());
                waybillMarker.setRotateAngle(0);// 设置marker旋转
                waybillMarker.setTitle(strImei);
                waybillMarker.setSnippet(lng + "");
                waybillMarker.setObject(latlng);
                listMarker.add(waybillMarker);
            }
        } catch (Exception e) {
            LogUtil.e(e.toString());
        }

    }

    /**
     * 开始定位 也用于完成
     * @param isCompleted 是否完成的判断
     */
    private void startLocation(final boolean isCompleted) {
        GPS3DUtils.getInstance().startLocation(this, new GPS3DUtils.LocationListener() {
            @Override
            public void onCallback(double latitude, double longitude, String address) {
                curLat = latitude;
                curLon = longitude;

                initMap();

                addMarkersToMap(listbean);

                if (isCompleted){
                    compareAndComplete();
                }
            }

            @Override
            public void onErr() {
                showToast("定位失败，请重新定位！");
            }
        });
    }

    /**
     * 比较并完成
     */
    private void compareAndComplete() {
        presenter.installComplete(custId);
    }

    /**
     * 刷新手机位置
     */
    private void reLoad() {
        GPS3DUtils.getInstance().startLocation(this, new GPS3DUtils.LocationListener() {

            @Override
            public void onCallback(double lat, double lon, String add) {
                MyLocationStyle myLocationStyle = new MyLocationStyle();
                myLocationStyle.showMyLocation(true);
                myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
                aMap.setMyLocationStyle(myLocationStyle);
                aMap.setMyLocationEnabled(true);
            }

            @Override
            public void onErr() {
                showToast("定位失败，请重新定位！");
            }
        });
    }

    /**
     * 获取最新定位数据 点击 刷新 完成时都要调用
     */
    private void getLocationInfo(String subTitle ,String custId, boolean isComplete){
        presenter.getLocationInfo(subTitle, custId, isComplete);
    }

    /** 小气泡 */
    @Override
    public View getInfoWindow(Marker marker) {
        infoWindow = LayoutInflater.from(this).inflate(R.layout.custom_info_window, null);
        WindowManager manager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();

        snippetUi = infoWindow.findViewById(R.id.tv_content);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width / 2 + 150, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 0, 0, 0);
        snippetUi.setLayoutParams(layoutParams);
        //气泡显示后5位imei
        snippetUi.setText(wayBillTitle = imeiId.substring(imeiId.length() - 5, imeiId.length()));

        return infoWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    /************************权限申请**********************************/
    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_PHONE_STATE})
    void showPermission() {
        startLocation(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        GPSIntallTestActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_PHONE_STATE})
    void showRation(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("权限申请")
                .setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.proceed();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.cancel();
                    }
                }).setCancelable(false)
                .show();
    }

    @OnPermissionDenied({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_PHONE_STATE})
    void showDenied() {
        showToast("您权限了该权限，可能导致此功能不可用！");
    }


}

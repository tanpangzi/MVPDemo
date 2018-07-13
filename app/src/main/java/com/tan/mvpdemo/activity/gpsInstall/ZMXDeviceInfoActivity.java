package com.tan.mvpdemo.activity.gpsInstall;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tan.mvpdemo.R;
import com.tan.mvpdemo.activity.BaseActivity;
import com.tan.mvpdemo.activity.ScanActivity;
import com.tan.mvpdemo.activityMvp.contract.ZMXDeviceInfoContract;
import com.tan.mvpdemo.activityMvp.presenter.ZMXDeviceInfoPresenter;
import com.tan.mvpdemo.bean.gpsInstall.ZMXGPSDeviceInfoBean;
import com.tan.mvpdemo.config.RequestCode;
import com.tan.mvpdemo.uitl.IntentUtil;
import com.tan.mvpdemo.uitl.TitleView;
import com.tan.mvpdemo.uitl.ToastUtil;


import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * <br> Description GPS安装 设备信息
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activity.gpsInstall
 * <br> Date: 2018/6/6
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
@RuntimePermissions
public class ZMXDeviceInfoActivity extends BaseActivity<ZMXDeviceInfoContract.ZMXDeviceInfoPresenter> implements ZMXDeviceInfoContract.ZMXDeviceInfoView {
    /**
     * 扫描
     */
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    /**
     * 查询按钮
     */
    @BindView(R.id.btn_search)
    Button btnSearch;
    /**
     * 下一步按钮
     */
    @BindView(R.id.btn_next)
    Button btnNext;

    /**
     * 标题
     */
    @BindView(R.id.title_view)
    TitleView titleView;
    /**
     * imei输入
     */
    @BindView(R.id.et_input_imei)
    EditText etInputImei;
    /**
     * sim卡号
     */
    @BindView(R.id.et_device_sim)
    EditText etDeviceSim;
    /**
     * 设备名称
     */
    @BindView(R.id.tv_device_name)
    TextView tv_device_name;
    /**
     * 设备类型
     */
    @BindView(R.id.tv_device_type)
    TextView tv_device_type;

    String custId;
    String zmxCustId;
    String isInput; //判断是否可输入 0不可以输入 1可以输入
    String simId;
    /**
     * 设备类型 设备名称
     */
    String deviceType;
    /**
     * 0是有线 1是无线 2是odb
     */
    String deviceName;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_zmx_device_info;
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void initGetData() {
        custId = getBundle.getString("custId");
    }

    @Override
    protected void init() {
        titleView.setLeftBtnImg();
        titleView.setTitle(R.string.device_info);
    }

    @Override
    protected void widgetListener() {
         /** imei输入框发生变化时清空 */
        etInputImei.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tv_device_name.setText("");
                tv_device_type.setText("");
                etDeviceSim.setText("");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public ZMXDeviceInfoContract.ZMXDeviceInfoPresenter initPresenter() {
        return new ZMXDeviceInfoPresenter(this);
    }


    @OnClick({R.id.iv_scan, R.id.btn_search, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:  //扫描
                ZMXDeviceInfoActivityPermissionsDispatcher.showRequestWithCheck(this);
                break;
            case R.id.btn_search://查询按钮
                mPresenter.getInfoByImei(custId);
                break;
            case R.id.btn_next://下一步
                mPresenter.detect(custId);

                break;
        }
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showToast(this, msg);
    }

    @Override
    public void onSuccess() {
        String imeiId = etInputImei.getText().toString().trim();
        String simId = etDeviceSim.getText().toString().trim();
        postBundle.putString("imeiId", imeiId);
        postBundle.putString("simId", simId);
        postBundle.putString("custId", custId);

        IntentUtil.gotoActivity(this, ZMXGpsLoadingActivity.class, postBundle);
    }

    @Override
    public String getImei() {
        return etInputImei.getText().toString().trim();
    }

    @Override
    public String getSimId() {
        return etDeviceSim.getText().toString().trim();
    }

    @Override
    public String getDeviceType() {
        return tv_device_type.getText().toString().trim();
    }

    @Override
    public String getDeviceName() {
        return tv_device_name.getText().toString().trim();
    }

    @Override
    public void getDeviceInfoByImei(ZMXGPSDeviceInfoBean infoBean) {
        zmxCustId = infoBean.getCustId();//custId
        isInput = infoBean.getIsInput();//是否可输入
        simId = infoBean.getSimId(); //sim卡

        deviceType = infoBean.getReturnList().get(0).getType();
        deviceName = infoBean.getReturnList().get(0).getVersionType();

        if (isInput.equals("0")) {//0不可输入
            etDeviceSim.setEnabled(false);
        } else if (isInput.equals("1")) {  //1可以输入
            etDeviceSim.setEnabled(true);
            etDeviceSim.requestFocus();
        }

        if (!TextUtils.isEmpty(simId)) {
            etInputImei.setText(simId);
        }

        if (deviceType.equals("0")) {//设备类型
            tv_device_type.setText("有线");
        } else if (deviceType.equals("1")) {
            tv_device_type.setText("无线");
        } else if (deviceType.equals("2")) {
            tv_device_type.setText("ODB");
        }

        tv_device_name.setText(deviceName);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == RequestCode.REQUEST_CODE_IMEI){
            String imei = data.getStringExtra("IMEI");

            if (imei.startsWith("8630")){
                etInputImei.setText(imei.substring(4,imei.length()));
            }else {
                etInputImei.setText(imei);
            }
            /** 扫描成功后查询 */
            mPresenter.getInfoByImei(custId);
        }
    }

    /******    权限处理    *******/
    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.BODY_SENSORS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showRequest() {
        Intent intent = new Intent(this, ScanActivity.class);
        startActivityForResult(intent, RequestCode.REQUEST_CODE_IMEI);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ZMXDeviceInfoActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.BODY_SENSORS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showRationale(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("权限提示")
                .setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.proceed();
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                request.cancel();
            }
        }).setCancelable(false)
                .show();
    }

    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.BODY_SENSORS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showDenied() {
        showToast("您拒绝该权限，可能会影响该功能的使用！");
    }

}

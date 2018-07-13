package com.tan.mvpdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;

import com.tan.mvpdemo.R;
import com.tan.mvpdemo.activityMvp.BasePresenter;
import com.tan.mvpdemo.config.RequestCode;
import com.tan.mvpdemo.uitl.TitleView;
import com.tan.mvpdemo.uitl.ToastUtil;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.QRCodeView;

/**
 * <br> Description 扫描二维码
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activity
 * <br> Date: 2018/6/7
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class ScanActivity extends BaseActivity implements QRCodeView.Delegate {

    /** 标题 */
    @BindView(R.id.title_view)
    TitleView titleView;
    QRCodeView mQRCodeView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_scan;
    }

    @Override
    protected void findViews() {
        mQRCodeView = findViewById(R.id.zbarview);
    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void init() {
        titleView.setLeftBtnImg();
        titleView.setTitle("扫描");
        mQRCodeView.setDelegate(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mQRCodeView.startSpot();
        mQRCodeView.startCamera();
        mQRCodeView.startSpotAndShowRect();
        mQRCodeView.changeToScanQRCodeStyle();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mQRCodeView.stopSpot();
        mQRCodeView.stopCamera();
        mQRCodeView.stopSpotAndHiddenRect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mQRCodeView.onDestroy();
    }

    @Override
    protected void widgetListener() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        vibrate();
        Intent intent = new Intent();
        intent.putExtra("IMEI", result);
        setResult(RequestCode.REQUEST_CODE_IMEI, intent);
        finish();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        ToastUtil.showToast(this, "打开相机出错");
    }

    /** 振动 */
    private void vibrate(){
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }
}

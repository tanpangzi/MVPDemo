package com.tan.mvpdemo.activity;

import android.os.Handler;
import android.text.TextUtils;

import com.tan.mvpdemo.BaseApplication;
import com.tan.mvpdemo.R;
import com.tan.mvpdemo.activityMvp.BasePresenter;
import com.tan.mvpdemo.uitl.Constant;
import com.tan.mvpdemo.uitl.IntentUtil;
import com.tan.mvpdemo.uitl.LogUtil;


/**
 * 引导页
 * <p>
 * <br> Author: 叶青
 * <br> Version: 6.0.0
 * <br> Date: 2018/1/5
 * <br> Copyright: Copyright © 2017 xTeam Technology. All rights reserved.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void init() {
        //startService(new Intent().setClass(this, AMapLocationService.class));
        handler.postDelayed(runnable, 1500);
    }

    @Override
    protected void widgetListener() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            LogUtil.e(Constant.LOG_TAG + "  " + BaseApplication.getInstance().getUserInfoBean());
            if (!TextUtils.isEmpty(BaseApplication.getInstance().getToken())) {
                IntentUtil.gotoActivityAndFinish(SplashActivity.this, MainActivity.class);
            } else {
                IntentUtil.gotoActivityAndFinish(SplashActivity.this, LoginActivity.class);
            }
        }
    };

    @Override
    protected void onDestroy() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        handler = null;
        super.onDestroy();
    }
}

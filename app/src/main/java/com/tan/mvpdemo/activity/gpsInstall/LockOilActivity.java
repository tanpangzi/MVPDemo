package com.tan.mvpdemo.activity.gpsInstall;


import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.iflytek.cloud.thirdparty.P;
import com.tan.mvpdemo.BaseApplication;
import com.tan.mvpdemo.R;
import com.tan.mvpdemo.activity.BaseActivity;
import com.tan.mvpdemo.activityMvp.contract.LockOilContract;
import com.tan.mvpdemo.activityMvp.presenter.LockOilPresenter;
import com.tan.mvpdemo.uitl.TitleView;
import com.tan.mvpdemo.uitl.ToastUtil;


/**
 * 锁油断电模式
 * created by tanjun
 */
public class LockOilActivity extends BaseActivity<LockOilContract.LockOilPresenter> implements View.OnClickListener,LockOilContract.LockOilView {

    TitleView title_view; //标题
    RadioButton rb_lock; //锁油断电
    RadioButton rb_restore; //恢复

    Button btn_confirm;//确认按钮

    RelativeLayout rl_lock; //锁油断电 0
    RelativeLayout rl_restore; //恢复 1

    /** 指令 */
    private String instruction = "-1";
    /** imei */
    private String imei;

    private String userId;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_lock_oil;
    }

    @Override
    protected void findViews() {
        title_view = findViewByIds(R.id.title_view);
        rl_lock = findViewByIds(R.id.rl_lock);
        rl_restore = findViewByIds(R.id.rl_restore);
        rb_lock = findViewByIds(R.id.rb_lock);
        rb_restore = findViewByIds(R.id.rb_restore);
        btn_confirm = findViewByIds(R.id.btn_confirm);
    }

    @Override
    protected void initGetData() {
        userId = BaseApplication.getInstance().getUserInfoBean().getUserId();
    }

    @Override
    protected void init() {
        title_view.setLeftBtnImg();
        title_view.setTitle(R.string.lock_oil_elect);
    }

    @Override
    protected void widgetListener() {
        rl_lock.setOnClickListener(this);
        rl_restore.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
    }

    @Override
    public LockOilContract.LockOilPresenter initPresenter() {
        return new LockOilPresenter(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_lock: //锁油断电
                rb_restore.setChecked(false);
                if (rb_lock.isChecked()){
                    rb_lock.setChecked(false);
                }else {
                    rb_lock.setChecked(true);
                }
                break;

            case R.id.rl_restore: //恢复
                rb_lock.setChecked(false);
                if (rb_restore.isChecked()){
                    rb_restore.setChecked(false);
                }else {
                    rb_restore.setChecked(true);
                }

                break;

            case R.id.btn_confirm: //点击确认
                if (!rb_lock.isChecked() && !rb_restore.isChecked()){
                    showToast("请选择操作方式！");
                    return;
                }
                mPresenter.lockInstruction(imei);
                break;
        }
    }

    @Override
    public String getLockStatus() {
        if (rb_lock.isChecked()){
            instruction = "0";
        }else if (rb_restore.isChecked()){
            instruction = "1";
        }
        return instruction;
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showToast(this, msg);
    }

    @Override
    public void onSuccess() {
        showToast("操作成功");
        finishActivity();
    }

}

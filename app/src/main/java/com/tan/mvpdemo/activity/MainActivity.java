package com.tan.mvpdemo.activity;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioGroup;

import com.tan.mvpdemo.R;
import com.tan.mvpdemo.activityMvp.contract.MainContract;
import com.tan.mvpdemo.activityMvp.presenter.MainPresenter;
import com.tan.mvpdemo.config.ConstantKey;
import com.tan.mvpdemo.fragment.IndexFragment;
import com.tan.mvpdemo.fragment.MineFragment;
import com.tan.mvpdemo.uitl.AppManagerUtil;
import com.tan.mvpdemo.uitl.LogUtil;
import com.tan.mvpdemo.uitl.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <br> Description app主页
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.fragment
 * <br> Date: 2018/6/1
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class MainActivity extends BaseActivity<MainContract.MainPresenter> implements MainContract.MainView{


    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;
    /**
     * 首页
     */
    private IndexFragment indexFragment = null;
    /** 展页 */
    //private OtherFragment otherFragment = null;
    /**
     * 我的
     */
    private MineFragment mineFragment = null;

    /**
     * fragment模块集合
     */
    private List<Fragment> listFragments = new ArrayList<>();

    /**
     * 记录退出按下时间
     */
    private long exitTime = 0;

    private ProgressDialog mProgressDialog;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void initGetData() {
        mPresenter.checkVersion();
    }

    @Override
    protected void init() {
        initFragment();
    }

    @Override
    protected void widgetListener() {
        // 功能菜单选择事件更改
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.main_radio_btn_index) {
                    switchView(0);

                } /*else if (checkedId == R.id.main_radio_btn_order) {
                    switchView(1);

                } */else if (checkedId == R.id.main_radio_btn_mine) {
                    setTintResource(R.color.title_bg_color);
                    switchView(1);
                    //mineFragment.mainInfo();
                }
            }
            //            }
        });
    }

    @Override
    public MainContract.MainPresenter initPresenter() {
        return new MainPresenter(this);
    }


    // ***********************************返回键事件处理*********************************
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                // 要执行的事件
                // DialogUtil.showExitsDg(MainActivity.this);对话框退出
                // 判断2次点击事件时间
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    ToastUtil.showToast(MainActivity.this, getString(R.string.tips_exit_time));
                    exitTime = System.currentTimeMillis();
                } else {
                    // 极光推送 BUG1 退出应用 不设置空alias，下次启动app设置alias，极光后台会提示找不到alias
                    // 极光推送 BUG2 在任务管理器 手动干掉APP进程  下次启动app设置alias，极光后台会提示找不到alias;未找到合适的解决方案
                    // JPushUtil.getInstance(this).setEmptyAlias();
                    // 退出程序
                    AppManagerUtil.getAppManager().exitApp();
                    // finish();
                }

            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    /**
     * 初始化子模块
     */
    private void initFragment() {

        //otherFragment = new OtherFragment();
        postBundle.putString(ConstantKey.INTENT_KEY_TYPE, "type");
        //otherFragment.setArguments(getBundle);
        indexFragment = new IndexFragment();
        mineFragment = new MineFragment();

        listFragments.add(indexFragment);
        //listFragments.add(otherFragment);
        listFragments.add(mineFragment);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.view_parent, indexFragment);
        //transaction.add(R.id.view_parent, otherFragment);
        transaction.add(R.id.view_parent, mineFragment);
        transaction.commit();

        switchView(0);
    }

    /**
     * 选择界面
     */
    public void switchView(int position) {
        try {

            if (listFragments.get(position).isVisible()) {
                return;
            }

            // 获取Fragment的操作对象
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            for (int i = 0; i < listFragments.size(); i++) {
                LogUtil.i(listFragments.get(i).isVisible() + "...");
                //                if (i != position) {
                transaction.hide(listFragments.get(i));
                //                }
            }

            transaction.show(listFragments.get(position));
            // 提交更改
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onShowDialog(String title, String message) {

    }

    @Override
    public void onCloseDialog() {

    }

    @Override
    public void onShowProgress(String title, String message) {

    }

    @Override
    public void onSetProgress(int max, int Progress) {

    }

    @Override
    public void onCloseProgress() {

    }

    @Override
    public void onPrompt(int type, String message) {

    }

    @Override
    public void onSetCountTotal(String countTotal) {

    }

    @Override
    public void installApk(String path) {

    }

    @Override
    public void onShowAlertDialog(String title, String message, boolean isForce) {

    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showToast(this, msg);
    }

    @Override
    public void onSuccess() {

    }
}

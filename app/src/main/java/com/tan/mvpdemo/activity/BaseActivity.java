package com.tan.mvpdemo.activity;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.tan.mvpdemo.BaseApplication;
import com.tan.mvpdemo.R;
import com.tan.mvpdemo.activityMvp.BasePresenter;
import com.tan.mvpdemo.bean.UserInfoLoginBean;
import com.tan.mvpdemo.broadcast.BroadCastFilter;
import com.tan.mvpdemo.uitl.AppManagerUtil;
import com.tan.mvpdemo.uitl.Constant;
import com.tan.mvpdemo.uitl.IntentUtil;
import com.tan.mvpdemo.uitl.KeyboardUtil;
import com.tan.mvpdemo.uitl.LogUtil;
import com.tan.mvpdemo.uitl.SystemUtil;
import com.tan.mvpdemo.uitl.ToastUtil;

import butterknife.ButterKnife;


/**
 * 基类Activity
 * <p>
 * 所有的Activity都继承自此Activity，并实现基类模板方法，本类的目的是为了规范团队开发项目时候的开发流程的命名， 基类也用来处理需要集中分发处理的事件、广播、动画等，如开发过程中有发现任何改进方案都可以一起沟通改进。
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年3月29日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public abstract class BaseActivity<P extends BasePresenter> extends FragmentActivity {

    /** 父视图 */
    protected View viewParent;
    /** 广播接收器 */
    protected BroadcastReceiver receiver;
    /** 广播过滤器 */
    protected IntentFilter filter;
    /** SystemBarTintManager 用于修改状态栏的颜色 */
    protected SystemBarTintManager tintManager;

    /** 获取别的界面传来的Bundle */
    protected Bundle getBundle = null;

    /** 传值用的Bundle */
    protected Bundle postBundle = null;

    private ProgressDialog mProgressDialog;
    private Dialog mDialog;

    private String mDialogTitle = "提示";

    /** 公共的P层*/
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        AppManagerUtil.getAppManager().addActivity(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        viewParent = View.inflate(this, getContentViewId(), null);
        setContentView(viewParent);
        getWindow().setBackgroundDrawable(null);
        /** 统一在父类中初始化 子类不用操作 */
        ButterKnife.bind(this);
        /** 取值用的Bundle */
        getBundle = getIntent().getExtras();
        /** 传值用的Bundle */
        postBundle = new Bundle();
        /** P层实例化 */
        mPresenter = initPresenter();

        try {
            findViews();
            initGetData();
            widgetListener();
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 注册广播
         */
        registerReceiver();

        SystemUtil.getChannelName();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);

        // 沉浸模式 会导致输入法与edittext不能顶起
        tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        // 设置状态栏背景色
        setTintResource(R.color.transparent);
        //  tintManager.setTintColor(Color.parseColor("#00838F"));
    }

    /**
     * 设置状态栏背景色
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateAuthor: 叶青
     * <br> CreateTime: 2017/1/22 9:50
     * <br> UpdateAuthor: 叶青
     * <br> UpdateTime: 2017/1/22 9:50
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param resId
     *         颜色资源ID
     */
    public void setTintResource(int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (tintManager != null) {
                // 设置状态栏背景色
                tintManager.setTintResource(resId);
            }
        }
    }

    /**
     * 获取显示view的xml文件ID
     * <p>
     * 在Activity的 {@link #onCreate(Bundle)}里边被调用
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年4月21日,下午2:31:19
     * <br> UpdateTime: 2016年4月21日,下午2:31:19
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @return xml文件ID
     */
    protected abstract int getContentViewId();

    /**
     * 控件查找
     * <p>
     * 在 {@link #getContentViewId()} 之后被调用
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年4月21日,下午1:58:20
     * <br> UpdateTime: 2016年4月21日,下午1:58:20
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    protected abstract void findViews();

    /**
     * 获取上一个界面传送过来的数据
     * <p>
     * 在{@link BaseActivity#init()}之前被调用
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年4月21日,下午2:42:56
     * <br> UpdateTime: 2016年4月21日,下午2:42:56
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    protected abstract void initGetData();

    /**
     * 初始化应用程序，设置一些初始化数据都获取数据等操作
     * <p>
     * 在{@link #widgetListener()}之后被调用
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年4月21日,下午1:55:15
     * <br> UpdateTime: 2016年4月21日,下午1:55:15
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    protected abstract void init();

    /**
     * 组件监听模块
     * <p>
     * 在{@link #findViews()}后被调用
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年4月21日,下午1:56:06
     * <br> UpdateTime: 2016年4月21日,下午1:56:06
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    protected abstract void widgetListener();

    /**
     * presenter初始化
     * add by tanjun
     * @return
     */
    public abstract P initPresenter();

    /**
     * 重置视图
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime:: 2016年9月14日,下午4:10:05
     * <br> UpdateTime: 2016年9月14日,下午4:10:05
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     */
    private void resetView() {
        viewParent = View.inflate(this, getContentViewId(), null);
        setContentView(viewParent);
        findViews();
        initGetData();
        widgetListener();
        init();
    }

    /**
     * 泛型:查找控件
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年5月22日,下午1:40:30
     * <br> UpdateTime: 2016年5月22日,下午1:40:30
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param id
     *         控件ID
     *
     * @return 控件view
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T findViewByIds(int id) {
        if (viewParent == null) {
            viewParent = View.inflate(this, getContentViewId(), null);
        }
        return (T) viewParent.findViewById(id);
    }

    /**
     * 注册广播
     */
    protected void registerReceiver(){
        setFilterActions();
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                BaseActivity.this.onReceive(intent);
            }
        };
        registerReceiver(receiver, filter);
    }

    /**
     * 设置广播过滤器
     */
    protected void setFilterActions(){
        // 添加广播过滤器，在此添加广播过滤器之后，所有的子类都将收到该广播
        filter = new IntentFilter();
        filter.addAction(BroadCastFilter.ACTION_TONKEN_EXPIRED); //token过期广播
    }

    /**
     * 广播监听回调
     * @param intent
     */
    protected void onReceive(Intent intent){
        if (intent.getAction().equals(BroadCastFilter.ACTION_TONKEN_EXPIRED)){
            if (!this.getClass().getName().equals("LoginActivity")){
                tokenInvalid();
            }
            /** 打印退出log */
            LogUtil.e("退出登陆" + Constant.LOG_TAG + this.getClass().getName());
        }
    }

    /**
     * 登录过期、Token无效 请重新登录 或者被抢登
     */
    public void tokenInvalid() {
        //        try {
        //            //            JPushUtil.getInstance(this).setAlias("");
        String token = BaseApplication.getInstance().getToken();
        if (!TextUtils.isEmpty(token)){
            ToastUtil.showToast(this, getString(R.string.activity_token_error));
            UserInfoLoginBean userInfoBean = BaseApplication.getInstance().getUserInfoBean();
            userInfoBean.setToken("");
            BaseApplication.getInstance().setUserInfoBean(userInfoBean);
            //            AppManagerUtil.getAppManager().finishAllActivity();
            //        } catch (Exception e) {
            //            e.printStackTrace();
            //        }
            IntentUtil.gotoActivityToTopAndFinish(this, LoginActivity.class);
        }else {
            IntentUtil.gotoActivityToTopAndFinish(this, LoginActivity.class);
        }

    }


    // ***********************************返回键事件处理*********************************
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                // 要执行的事件
                finishActivity();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    /**
     * 结束当前
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年5月22日,下午1:40:30
     * <br> UpdateTime: 2016年5月22日,下午1:40:30
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    protected void finishActivity() {
        IntentUtil.finish(this);
    }

    @Override
    protected void onStop() {
        ToastUtil.cancelToast(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        /**
         * 注销广播
         */
        unregisterReceiver(receiver);
        KeyboardUtil.fixFocusedViewLeak(viewParent);
        AppManagerUtil.getAppManager().removeActivity(this);
        ButterKnife.bind(this).unbind(); //统一在父类中管理 子类不用操作
        super.onDestroy();
    }

    /**
     * 回调获取授权结果，判断是否授权
     * 0 =PackageManager.PERMISSION_GRANTED 权限开启成功
     * -1=PackageManager.PERMISSION_DENIED  权限开启失败
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (permissions.length <= 0 || grantResults.length <= 0) {
            return;
        }
    }

    /************************对话框***********************************/

    public void showAlertDialog(String str) {
        new AlertDialog.Builder(this)
                .setTitle(mDialogTitle)
                .setMessage(str)
                .setPositiveButton(R.string.alertdialog_positive_button, null)
                .create().show();

    }

    public void showAlertDialog(String title, String str) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(str)
                .setPositiveButton(R.string.alertdialog_positive_button, null)
                .create().show();

    }

    public void showAlertDialog(String str, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(this)
                .setTitle(mDialogTitle)
                .setMessage(str)
                .setPositiveButton(R.string.alertdialog_positive_button, listener)
                .create()
                .show();
    }

    public void showAlertDialog(String str, DialogInterface.OnClickListener listener, DialogInterface.OnClickListener listener2) {
        new AlertDialog.Builder(this)
                .setTitle(mDialogTitle)
                .setMessage(str)
                .setPositiveButton(R.string.alertdialog_positive_button, listener)
                .setNegativeButton(R.string.alertdialog_negative_button, listener2)
                .setCancelable(false)
                .create()
                .show();

    }

    public void showAlertDialog(String str, String posButton, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(this)
                .setTitle(mDialogTitle)
                .setMessage(str)
                .setPositiveButton(posButton, listener)
                .setNegativeButton(R.string.alertdialog_negative_button, null)
                .create()
                .show();

    }

    public void showProgressDialog(String message) {
        if(mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setTitle(mDialogTitle);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage(message);
            mProgressDialog.setCanceledOnTouchOutside(false);
        }else{
            mProgressDialog.setMessage(message);
        }
        mProgressDialog.show();

    }

    public void showProgressDialog(String title, String message) {
        if(mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setTitle(title);
            mProgressDialog.setMessage(message);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setCanceledOnTouchOutside(false);
        }else{
            mProgressDialog.setMessage(message);
        }
        mProgressDialog.show();

    }

    public  void dismissDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    public boolean isProgressDialogshow() {
        if (mProgressDialog != null) {
            return mProgressDialog.isShowing();
        }
        return false;
    }

}
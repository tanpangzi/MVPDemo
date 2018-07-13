package com.tan.mvpdemo.activity;

import android.widget.Toast;

import com.tan.mvpdemo.R;
import com.tan.mvpdemo.activityMvp.contract.LoginContract;
import com.tan.mvpdemo.activityMvp.presenter.LoginPresenter;
import com.tan.mvpdemo.uitl.IntentUtil;
import com.tan.mvpdemo.widget.AutoBgButton;
import com.tan.mvpdemo.widget.ContainsEmojiEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity<LoginContract.LoginPresenter> implements LoginContract.LoginView {

    /** 用户名输入框 */
    @BindView(R.id.et_login_username)
    ContainsEmojiEditText etLoginUsername;
    /** 密码输入框 */
    @BindView(R.id.et_login_password)
    ContainsEmojiEditText etLoginPassword;
    /** 登录按钮 */
    @BindView(R.id.btn_login_submit)
    AutoBgButton btnLoginSubmit;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void findViews() {
    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected void widgetListener() {

    }

    @Override
    public LoginContract.LoginPresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getName() {
        return etLoginUsername.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etLoginPassword.getText().toString().trim();
    }

    /** 成功 */
    @Override
    public void onSuccess() {
        IntentUtil.gotoActivity(this, MainActivity.class);
    }

    @OnClick(R.id.btn_login_submit)
    public void onViewClicked() {
        mPresenter.login();
    }
}

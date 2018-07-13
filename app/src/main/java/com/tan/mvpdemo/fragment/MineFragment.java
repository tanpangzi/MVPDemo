package com.tan.mvpdemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tan.mvpdemo.R;
import com.tan.mvpdemo.activity.MainActivity;
import com.tan.mvpdemo.activity.SettingActivity;
import com.tan.mvpdemo.uitl.IntentUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * <br> Description "我的"界面
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.fragment
 * <br> Date: 2018/6/1
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class MineFragment extends BaseFragment {


    @BindView(R.id.tv_setting)
    TextView tvSetting;
    Unbinder unbinder;

    private MainActivity mActivity;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void findViews() {

    }

    @Override
    public void initGetData() {
        mActivity = (MainActivity) getActivity();

    }

    @Override
    protected void init() {

    }

    @Override
    protected void widgetListener() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /** 点击 */
    @OnClick(R.id.tv_setting)
    public void onViewClicked() {
        IntentUtil.gotoActivity(mActivity, SettingActivity.class);
    }

}

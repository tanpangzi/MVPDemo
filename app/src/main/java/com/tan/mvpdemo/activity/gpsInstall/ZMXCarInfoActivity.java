package com.tan.mvpdemo.activity.gpsInstall;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.lib.WheelView;
import com.tan.mvpdemo.R;
import com.tan.mvpdemo.activity.BaseActivity;
import com.tan.mvpdemo.activityMvp.BasePresenter;
import com.tan.mvpdemo.activityMvp.contract.ZMXCarContract;
import com.tan.mvpdemo.activityMvp.presenter.ZMXCarPresenter;
import com.tan.mvpdemo.bean.PickerCardBean;
import com.tan.mvpdemo.uitl.ComUtils;
import com.tan.mvpdemo.uitl.IntentUtil;
import com.tan.mvpdemo.uitl.TitleView;
import com.tan.mvpdemo.uitl.ToastUtil;
import com.tan.mvpdemo.widget.AutoBgTextView;
import com.tan.mvpdemo.widget.ContainsEmojiEditText;

import java.util.ArrayList;
import java.util.List;

/**
 * <br> Description 紫米星GPS安装第一个界面
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activity.gpsInstall
 * <br> Date: 2018/6/5
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class ZMXCarInfoActivity extends BaseActivity<ZMXCarContract.ZMXCarPresenter> implements ZMXCarContract.ZMXCarView, View.OnClickListener {

    /**
     * 标题
     */
    TitleView titleView;
    /**
     * 客户姓名输入框
     */
    ContainsEmojiEditText etConsumerName;
    /**
     * 车牌号输入框
     */
    ContainsEmojiEditText etCarNum;
    /**
     * 组织id
     */
    AutoBgTextView tvOrg;
    /**
     * 选择组织点击
     */
    LinearLayout llContainer;
    /**
     * 下一步（提交）
     */
    Button btnNext;

     /** 选中的组织名称和组织id */
    String orgName;
    String orgId;

    /** 提交信息返回的custId */
    String custId;

    /**
     * 组织选择器
     */
    private PickerCardBean datas;

    private List<String> orgList = new ArrayList<>();

    /**
     * 弹出选择
     */
    OptionsPickerView mPickerView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_zmxcarinfo;
    }

    @Override
    protected void findViews() {
        titleView =  findViewByIds(R.id.title_view);
        etConsumerName =  findViewByIds(R.id.et_consumer_name);
        etCarNum = findViewByIds(R.id.et_car_num);
        llContainer = findViewByIds(R.id.ll_container);
        tvOrg = findViewByIds(R.id.tv_org);
        btnNext =  findViewByIds(R.id.btn_next);
    }


    @Override
    protected void initGetData() {

        /** 获取列表 */
        mPresenter.getOrgInfo();
    }


    @Override
    protected void init() {
        titleView.setLeftBtnImg();
        titleView.setTitle(R.string.car_info);

        initPickerView();
    }

    @Override
    protected void widgetListener() {
        llContainer.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    public ZMXCarContract.ZMXCarPresenter initPresenter() {
        return new ZMXCarPresenter(this);
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showToast(this, msg);
    }

    @Override
    public String getCustomerName() {
        return etConsumerName.getText().toString().trim();
    }

    @Override
    public String getCarNum() {
        return etCarNum.getText().toString().trim();
    }

    @Override
    public String getOrg() {
        return tvOrg.getText().toString().trim();
    }

    /**
     * 获取组织列表
     */
    @Override
    public void getOrgList(PickerCardBean bean) {
        datas = bean;

        for (int i = 0; i < datas.getReturnList().size(); i++) {
            orgList.add(datas.getReturnList().get(i).getName());
        }

        mPickerView.setPicker(orgList);

    }

    /** 获取提交信息返回的custId */
    @Override
    public void getCustId(String custId) {
        this.custId = custId;
    }

    /** 提交成功 跳转其他界面*/
    @Override
    public void onSuccess() {
        postBundle.putString("custId", custId);
        IntentUtil.gotoActivity(this, ZMXDeviceInfoActivity.class, postBundle);
    }

    /**
     * 初始化选择器
     */
    private void initPickerView() {
        mPickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                List<PickerCardBean.ReturnListBean> returnList = datas.getReturnList();
                if (returnList.size() == 0){
                    return;
                }
                /** 选中的组织名称和id */
                orgName = returnList.get(options1).getTextContent();
                orgId = returnList.get(options1).getTextId();
                tvOrg.setText(orgName);
            }
        }).setDividerType(WheelView.DividerType.WRAP)
                .setTextColorCenter(Color.BLACK)
                .setContentTextSize(20)
                .build();
    }


    /**
     * 点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_container://选择组织
                 /** 关闭当前键盘 */
                ComUtils.closeKeyBorad(ZMXCarInfoActivity.this, view);
                if (mPickerView != null){
                    /** 显示弹出框 */
                    mPickerView.show();
                }
                break;
            case R.id.btn_next: //下一步（提交）
                mPresenter.createCustomerInfo();
                break;
        }
    }
}

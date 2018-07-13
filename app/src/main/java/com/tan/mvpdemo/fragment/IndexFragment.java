package com.tan.mvpdemo.fragment;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.donkingliang.banner.CustomBanner;
import com.tan.mvpdemo.R;
import com.tan.mvpdemo.activity.MainActivity;
import com.tan.mvpdemo.activity.gpsInstall.ZMXCarInfoActivity;
import com.tan.mvpdemo.activity.gpsMonitor.GpsMonitorHomeActivity;
import com.tan.mvpdemo.adapter.IndexItemAdapter;
import com.tan.mvpdemo.bean.ItemBean;
import com.tan.mvpdemo.config.ConstantKey;
import com.tan.mvpdemo.uitl.ComUtils;
import com.tan.mvpdemo.uitl.IntentUtil;
import com.tan.mvpdemo.uitl.TitleView;
import com.tan.mvpdemo.uitl.ToastUtil;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * <br> Description "首页"
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.fragment
 * <br> Date: 2018/6/1
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class IndexFragment extends BaseFragment {

    /** 标题 */
    TitleView titleView;
    RecyclerView recycler;

    GridLayoutManager manager;
    MainActivity mActivity;

    IndexItemAdapter mAdapter;

    private ArrayList<ItemBean> datas;
    ItemBean bean;

    private ArrayList<Integer> imgs = new ArrayList<>();

    CustomBanner banner;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_index;
    }

    @Override
    protected void findViews() {
        titleView = findViewByIds(R.id.title_view);
        banner = findViewByIds(R.id.banner);
        recycler = findViewByIds(R.id.recycler);

    }

    @Override
    public void initGetData() {
        mActivity = (MainActivity) getActivity();
        datas = new ArrayList<>();
        initList();

        titleView.setTitle("紫米星");
        manager = new GridLayoutManager(mActivity, 2);
        recycler.setLayoutManager(manager);
        mAdapter = new IndexItemAdapter(mActivity,datas);
        recycler.setAdapter(mAdapter);
    }

    @Override
    protected void init() {
    }

    @Override
    protected void widgetListener() {
        /** 指示器 */
        banner.setIndicatorRes(R.drawable.shape_point_select, R.drawable.shape_point_unselect);
        /** banner图片 */
        imgs.add(R.drawable.banner_new_one);
        imgs.add(R.drawable.banner_new_one);

        /** 可用于加载网络图片 */
        banner.setPages(new CustomBanner.ViewCreator() {
            @Override
            public View createView(Context context, int i) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }

            @Override
            public void updateUI(Context context, View view, int i, Object o) {
                /** 用glide加载 */
                Glide.with(context).load(o).into((ImageView) view);
            }
        }, imgs)
                //设置自动翻页
                .startTurning(3000);

        banner.setOnPageClickListener(new CustomBanner.OnPageClickListener() {
            @Override
            public void onPageClick(int i, Object o) {
                ToastUtil.showToast(mActivity, "当前点击" + i);
            }
        });


        /** recyclerView的点击事件 */
        mAdapter.setOnItemClickListener(new IndexItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ToastUtil.showToast(mActivity, "位置："+position);
                switch (datas.get(position).getTitle()){
                    case R.string.gps_monitor: //gps监控
                        /** gps监控 */
                        if (ComUtils.getPermissionKey(ConstantKey.PERMISSION_GPS_MONITOR)){
                             /** gps超级管理员 */
                            if (ComUtils.getPermissionKey(ConstantKey.PERMISSION_GPS_WARDEN)){
                                IntentUtil.gotoActivity(mActivity, GpsMonitorHomeActivity.class);
                            }else {

                            }

                        }else {
                            ToastUtil.showToast(mActivity, "没有GPS监控权限");
                            return;
                        }

                        break;

                    case R.string.gps_install: //gps安装
                        /** GPS安装权限 */
                        if (ComUtils.getPermissionKey(ConstantKey.PERMISSION_GPS_INSTALL)){
                            IntentUtil.gotoActivity(mActivity, ZMXCarInfoActivity.class);
                        }else {
                            ToastUtil.showToast(mActivity, "没有GPS安装权限");
                            return;
                        }
                        break;
                }
            }
        });


    }

    private void initList(){
        /** gps监控 */
        bean = new ItemBean();
        bean.setResId(R.mipmap.icon_gps);
        bean.setTitle(R.string.gps_monitor);
        datas.add(bean);

        /** gps安装 */
        bean = new ItemBean();
        bean.setResId(R.mipmap.icon_inst);
        bean.setTitle(R.string.gps_install);
        datas.add(bean);

    }

}

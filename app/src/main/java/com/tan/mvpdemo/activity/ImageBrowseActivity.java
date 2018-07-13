package com.tan.mvpdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

import com.squareup.picasso.Picasso;
import com.tan.mvpdemo.R;
import com.tan.mvpdemo.activityMvp.contract.ImageBrowseContract;
import com.tan.mvpdemo.activityMvp.presenter.ImageBrowsePresenter;
import com.tan.mvpdemo.adapter.ViewPagerAdapter;
import com.tan.mvpdemo.config.ConstantKey;
import com.tan.mvpdemo.uitl.Constant;
import com.tan.mvpdemo.uitl.PicassoUtil;
import com.tan.mvpdemo.uitl.TitleView;
import com.tan.mvpdemo.uitl.ToastUtil;
import com.tan.mvpdemo.widget.CustomViewPager4Lock;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * 图片浏览器
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class ImageBrowseActivity extends BaseActivity<ImageBrowseContract.ImageBrowsePresenter> implements ImageBrowseContract.ImageBrowseView{

    /** ViewPager 自定义控制左右滑动事件 */
    private CustomViewPager4Lock viewPager;
    /** TitleView */
    private TitleView titleview;
    /** 所有的图片view */
    private ArrayList<View> views = new ArrayList<>();
    /** 当前图片分类 */
    private String type = "";
    /** 当前位置 */
    private int position = 0;
    /** 图片全路径（或者本地路径） */
    private ArrayList<String> imgPath = new ArrayList<>();
    /** 样例图预览 TODO  businessId为空 */
    private String businessId = ""; //正在用不上这个值
    private String custId = "";

    /*************评估 gps安装 抵押 解押*************/
    private int imgType; //用来区别 车辆评估 gps安装 车辆抵押 车辆解押
    private String groupId; // 车辆评估 gps安装 车辆抵押 车辆解押的名字 也用来判断 删除判断

    private String imeiId = "";//只有gps安装界面的删除图片要用 其他时间不用

    @Override
    protected int getContentViewId() {
        return R.layout.activity_image_browse;
    }

    @Override
    protected void findViews() {
        titleview = findViewById(R.id.title_view);
        viewPager =  findViewById(R.id.view_pager);
    }

    @Override
    public void initGetData() {
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            groupId = bundle.getString("groupId"); //用于区别录单和 区别 车辆评估 gps安装 车辆抵押 车辆解押
            imgPath = bundle.getStringArrayList(ConstantKey.INTENT_KEY_DATAS);
            businessId = bundle.getString(ConstantKey.INTENT_KEY_BUSINESS_ID, "");
            position = bundle.getInt(ConstantKey.INTENT_KEY_POSITION);
            imeiId = bundle.getString("imeiId");
            if (TextUtils.isEmpty(groupId)){
                type = bundle.getString(ConstantKey.INTENT_KEY_TYPE, "");
                custId = bundle.getString(ConstantKey.INTENT_KEY_ID, "");
            } else {
                imgType = bundle.getInt("imgType");
            }

        }
    }

    @Override
    protected void init() {
        initViewPage();
    }

    /**
     * 初始化viewpage
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-20,上午11:24:49
     * <br> UpdateTime: 2016-1-20,上午11:24:49
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    private void initViewPage() {
        for (int i = 0; i < imgPath.size(); i++) {
            PhotoView photoView = new PhotoView(this);
            photoView.setBackgroundColor(getResources().getColor(R.color.black_30));
            if (TextUtils.isEmpty(imgPath.get(i))) {
                PicassoUtil.loadImage(this, R.drawable.icon_select_pic, photoView, R.drawable.icon_select_pic, R.drawable.icon_select_pic, null);
            } else {
                if (imgPath.get(i).contains("storage/emulated")) {
                    PicassoUtil.loadImage(this, imgPath.get(i), photoView);
                } else {
                    String path = imgPath.get(i);
                   /* if (path.startsWith("common")){
                        path = "/app/" + path;
                    }*/
                    int index = path.indexOf("common");
                    if (!path.contains("/app/") && index != -1){
                        path = Constant.BASE_URL + "/app/" + path.substring(index, path.length());
                    }

                    //                PicassoUtil.loadImage(this, imgPath.get(i), photoView);

                        Picasso.with(this).load(path)//
                                // .transform(new GlideRoundTransform(context, 15))// 圆角大小
                                .placeholder(R.drawable.img_default_grey_base)// 占位图 默认图片 一般可以设置成一个加载中的进度GIF图
                                .error(R.drawable.img_load_error_base)// 占位图 默认图片 一般可以设置成一个加载中的进度GIF图
                                //                    .crossFade()// 渐变切换
                                //                    .dontAnimate()// 不使用Glide的默认动画
                                //                    .diskCacheStrategy(DiskCacheStrategy.ALL)// 避免同一张图片加载两次
                                // .centerCrop()// 比例类型
                                .into(photoView);
                }
            }
            photoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                @Override
                public void onViewTap(View view, float v, float v1) {
                    finishActivity();
                }
            });
            views.add(photoView);
        }

        setViewPager(position);
    }

    private void setViewPager(int position) {

        //  用n/m形式显示
        titleview.setTitle((position + 1) + "/" + views.size());

        ViewPagerAdapter adapter = new ViewPagerAdapter(views);
        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(position);
        // 设置viewpager保留多少个显示界面
        viewPager.setOffscreenPageLimit(2);
        viewPager.setEnabled(true);
        setDel(position);
    }

    @Override
    protected void widgetListener() {

        titleview.setLeftBtnImg();

        viewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                //                for (int i = 0; i < views.size(); i++) {
                //                    PhotoViewAttacher mAttacher = new PhotoViewAttacher((PhotoView) views.get(i));
                //                    mAttacher.setScale(1);
                //                }

                //  用n/m形式显示
                titleview.setTitle((position + 1) + "/" + views.size());

                ((PhotoView) views.get(position)).setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                    @Override
                    public void onViewTap(View view, float v, float v1) {
                        finishActivity();
                    }
                });
                setDel(position);
            }

            /**
             * @param arg0
             *            :当前页面，及你点击滑动的页面
             * @param arg1
             *            arg1:当前页面偏移的百分比
             * @param arg2
             *            arg2:当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            /**
             * @param arg0
             *            有三种状态（0，1，2）。arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。
             */
            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

    }

    @Override
    public ImageBrowseContract.ImageBrowsePresenter initPresenter() {
        return new ImageBrowsePresenter(this);
    }


    /*private void deleteImg() {
        RequestExecutor.addTask(new BaseTask(ImageBrowseActivity.this, "删除中请稍候", false) {
            @Override
            public ResponseBean sendRequest() {
                int currentItem = viewPager.getCurrentItem();
                currentItem = currentItem + 1;
                String fileName = "";
                if (currentItem < 10) {
                    fileName = type + "_0" + currentItem;
                } else {
                    fileName = type + "_" + currentItem;
                }
                return OrderBiz.deleteImg(businessId, custId, fileName);
            }

            @Override
            public void onSuccess(ResponseBean result) {
                ToastUtil.showToast(ImageBrowseActivity.this, "删除成功！");
                // TODO  设置ID
                Bundle bundle = new Bundle();
                int currentItem = viewPager.getCurrentItem();
                bundle.putInt(ConstantKey.INTENT_KEY_POSITION, currentItem);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finishActivity();
            }

            @Override
            public void onFail(ResponseBean result) {
                ToastUtil.showToast(ImageBrowseActivity.this, result.getInfo());
            }
        });
    }

    *//**
     * 车辆评估 gps安装 抵押 解押 4个功能的删除
     *//*
    private void deleteImg4(){
        RequestExecutor.addTask(new BaseTask(ImageBrowseActivity.this, "删除中请稍候", false) {
            @Override
            public ResponseBean sendRequest() {
                int currentItem = viewPager.getCurrentItem();
                currentItem = currentItem + 1;
                String fileName = "";
                if (currentItem <= 9){
                    fileName = groupId + "_0" + currentItem;
                }else {
                    fileName = groupId + "_" + currentItem;
                }
                return InstallBiz.deleteImg(imgType, businessId, fileName, imeiId);
            }

            @Override
            public void onSuccess(ResponseBean result) {
                ToastUtil.showToast(ImageBrowseActivity.this, "删除成功！");
                Bundle bundle = new Bundle();
                int currentItem = viewPager.getCurrentItem();
                bundle.putInt(ConstantKey.INTENT_KEY_POSITION, currentItem);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finishActivity();
            }

            @Override
            public void onFail(ResponseBean result) {
                ToastUtil.showToast(ImageBrowseActivity.this, result.getInfo());
            }
        });
    }*/

    private void setDel(int position) {
        //if (!TextUtils.isEmpty(businessId)) {
            if (TextUtils.isEmpty(imgPath.get(position))) {
                titleview.setRightBtnImg(R.color.transparent, null);
            } else {
                titleview.setRightBtnImg(R.mipmap.delete_image, new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(groupId)){ //用groupId判断数据来源
                            //deleteImg(); //用来删除 录单图片
                        }else {
                            //deleteImg4();//车辆评估 gps安装 抵押 解压 图片删除
                            int currentItem = viewPager.getCurrentItem();
                            currentItem = currentItem + 1;
                            String fileName = "";
                            if (currentItem <= 9){
                                fileName = groupId + "_0" + currentItem;
                            }else {
                                fileName = groupId + "_" + currentItem;
                            }
                            mPresenter.deleteImg(Constant.GROUPID_POSITIONIMAGES, fileName, imeiId);
                        }
                    }
                });
            }
        //}
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showToast(this, msg);
    }

    /** 操作成功 */
    @Override
    public void onSuccess() {
        showToast("删除成功！");
        int currentItem = viewPager.getCurrentItem();
        postBundle.putInt(ConstantKey.INTENT_KEY_POSITION, currentItem);
        Intent intent = new Intent();
        intent.putExtras(postBundle);
        setResult(RESULT_OK, intent);
        finishActivity();
    }

}
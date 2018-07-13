package com.tan.mvpdemo.widget.pullview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tan.mvpdemo.R;

/**
 * 底部数据加载等待控件.
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年8月9日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class LoadFootView extends RelativeLayout {

    /** 上下文环境 */
    private Context context;
    /** 提示文本 */
    private TextView txt_tip;
    /** 等待动画 */
    //private LoadingView view_loading;
    /** 等地加载 */
    private String load_wait;
    /** 当前已是全部内容 */
    private String all;
    /** 当前页面没有数据 */
    private String not;
    /** 横向边距 */
    private int padding;
    /** 等待控件边长 */
    private int loadSide;

    public LoadFootView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public LoadFootView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public LoadFootView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    /**
     * 初始化.
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年8月9日,下午1:35:53
     * <br> UpdateTime: 2016年8月9日,下午1:35:53
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> updateInfo
     */
    private void init() {
        initParams();

        this.setPadding(padding, padding, padding, padding);
        this.setLayoutParams(new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, ListView.LayoutParams.WRAP_CONTENT));

        txt_tip = new TextView(context);
        txt_tip.setTextColor(getResources().getColor(R.color.font_gray));
        LayoutParams txtParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        txtParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        txt_tip.setLayoutParams(txtParams);
        this.addView(txt_tip);
        //		view_loading = new LoadingView(context);
        //		LayoutParams imgParams = new LayoutParams(loadSide, loadSide);
        //		imgParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        //		imgParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        //		view_loading.setLayoutParams(imgParams);
        //		this.addView(view_loading);

        android.widget.AbsListView.LayoutParams relativeParams = new android.widget.AbsListView.LayoutParams(
                android.widget.AbsListView.LayoutParams.MATCH_PARENT, android.widget.AbsListView.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(relativeParams);
    }

    /**
     * 初始化参数.
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年8月9日,上午11:25:38
     * <br> UpdateTime: 2016年8月9日,上午11:25:38
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <p>
     * <br> updateInfo
     */
    private void initParams() {
        load_wait = context.getString(R.string.wait);
        all = context.getString(R.string.no_more_data);
        not = context.getString(R.string.no_data);
        padding = getResources().getDimensionPixelOffset(R.dimen.distance_10);
        loadSide = getResources().getDimensionPixelOffset(R.dimen.distance_20);
    }

    /**
     * 显示加载等待.
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年8月9日,上午11:29:34
     * <br> UpdateTime: 2016年8月9日,上午11:29:34
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <p>
     * <br> updateInfo
     */
    public void loadWait() {
        txt_tip.setText(load_wait);
        //view_loading.setVisibility(View.VISIBLE);
    }

    /**
     * 当前已是全部内容.
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年8月9日,上午11:30:01
     * <br> UpdateTime: 2016年8月9日,上午11:30:01
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <p>
     * <br> updateInfo
     */
    public void all() {
        txt_tip.setText(all);
        //view_loading.setVisibility(View.INVISIBLE);
    }

    /**
     * 当前无数据.
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年9月4日,上午11:17:11
     * <br> UpdateTime: 2016年9月4日,上午11:17:11
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <p>
     * <br> updateInfo
     */
    public void not() {
        txt_tip.setText(not);
        //	view_loading.setVisibility(View.INVISIBLE);
    }

}

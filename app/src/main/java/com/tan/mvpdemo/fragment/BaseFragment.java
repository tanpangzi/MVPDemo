package com.tan.mvpdemo.fragment;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


/**
 * 基类Fragment
 * <p>
 * 所有的Fragment必须继承自此类
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年3月29日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public abstract class BaseFragment extends Fragment {

    /** 父视图 */
    protected View viewParent;
    /** 广播接收器 */
    protected BroadcastReceiver receiver;
    /** 广播过滤器 */
    protected IntentFilter filter;

    /** fragment里的所有传递的数据都可以拿 */
    protected Bundle bundle = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewParent = View.inflate(getActivity(), getContentViewId(), null);

        bundle = getArguments();
        try {
            findViews();
            initGetData();
            widgetListener();
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (viewParent.getParent() != null) {
            ((ViewGroup) viewParent.getParent()).removeView(viewParent);
        }
        return viewParent;
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
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年5月22日,上午10:03:58
     * <br> UpdateTime: 2016年5月22日,上午10:03:58
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    protected abstract void findViews();

    /**
     * 获取初始数据
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年6月7日,上午11:13:55
     * <br> UpdateTime: 2016年6月7日,上午11:13:55
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    public abstract void initGetData();

    /**
     * 初始化
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年5月22日,上午10:05:06
     * <br> UpdateTime: 2016年5月22日,上午10:05:06
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    protected abstract void init();

    /**
     * 组件监听
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年5月22日,上午10:05:39
     * <br> UpdateTime: 2016年5月22日,上午10:05:39
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    protected abstract void widgetListener();

    /**
     * 重置视图
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime:: 2016年9月14日,下午4:10:05
     * <br> UpdateTime: 2016年9月14日,下午4:10:05
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    private void resetView() {
        // getViews();
        viewParent = View.inflate(getActivity(), getContentViewId(), null);
        findViews();
        initGetData();
        widgetListener();
        init();
    }

    /**
     * 是否已经创建
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年6月6日,上午11:16:54
     * <br> UpdateTime: 2016年6月6日,上午11:16:54
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @return true已经创建，false未创建
     */
    public boolean isCreated() {
        return viewParent != null;
    }

    @Override
    public void onDestroy() {
        ButterKnife.bind(getActivity()).unbind(); //统一在fragment里操作
        super.onDestroy();
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
            viewParent = View.inflate(getActivity(), getContentViewId(), null);
        }
        return (T) viewParent.findViewById(id);
    }
}
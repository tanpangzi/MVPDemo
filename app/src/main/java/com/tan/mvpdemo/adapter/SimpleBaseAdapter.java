package com.tan.mvpdemo.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表--基类适配器
 * <p>
 * 适配器原则上都必须使用ViewHolder的设计模式
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public abstract class SimpleBaseAdapter<T> extends BaseAdapter {

    /** 上下文 */
    protected Context context;
    /** 数据源 */
    protected ArrayList<T> dataList;

    public SimpleBaseAdapter(Context context, ArrayList<T> datas) {
        this.context = context;
        this.dataList = datas;
        //this.dataList = dataList == null ? new ArrayList<T>() : new ArrayList<T>(dataList);
    }

    @Override
    public int getCount() {
        if (null == dataList) {
            return 0;
        }
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        if (null == dataList || position >= dataList.size()) {
            return null;
        }
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = View.inflate(context, getItemResource(), null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return getItemView(position, convertView, holder);
    }

    /**
     * ItemView控件容器类
     * <p>
     * <br> Author: 叶青
     * <br> Version: 1.0.0
     * <br> Date: 2016年12月11日
     * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
     */
    public class ViewHolder {
        private SparseArray<View> views = new SparseArray<>();
        private View convertView;

        ViewHolder(View convertView) {
            this.convertView = convertView;
        }

        public <Views extends View> Views getView(int resId) {
            View v = views.get(resId);
            if (null == v) {
                v = convertView.findViewById(resId);
                views.put(resId, v);
            }
            return (Views) v;
        }
    }

    /**
     * 增加数据源
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-28,下午3:23:21
     * <br> UpdateTime: 2016-11-28,下午3:23:21
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param elem
     *         待添加的数据
     */
    public void addAll(List<T> elem) {
        dataList.addAll(elem);
        notifyDataSetChanged();
    }

    /**
     * 移除数据源
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-28,下午3:23:41
     * <br> UpdateTime: 2016-11-28,下午3:23:41
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param elem
     *         待移除数据源
     */
    public void remove(T elem) {
        dataList.remove(elem);
        notifyDataSetChanged();
    }

    /**
     * 移除数据源
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-28,下午3:23:58
     * <br> UpdateTime: 2016-11-28,下午3:23:58
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param index
     *         待移除数据源
     */
    public void remove(int index) {
        dataList.remove(index);
        notifyDataSetChanged();
    }

    /**
     * 替换数据源
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-28,下午3:24:06
     * <br> UpdateTime: 2016-11-28,下午3:24:06
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param elem
     *         待替换的数据源
     */
    public void replaceAll(List<T> elem) {
        dataList.clear();
        dataList.addAll(elem);
        notifyDataSetChanged();
    }

    /**
     * 该方法需要子类实现，需要返回item布局的resource id
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-28,下午3:24:32
     * <br> UpdateTime: 2016-11-28,下午3:24:32
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @return item layout布局的ID
     */
    public abstract int getItemResource();

    /**
     * 使用该getItemView方法替换原来的getView方法，需要子类实现
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-11-28,下午3:24:38
     * <br> UpdateTime: 2016-11-28,下午3:24:38
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param position
     *         位置
     * @param convertView
     *         itemView
     * @param holder
     *         ItemView控件容器类
     */
    public abstract View getItemView(int position, View convertView, ViewHolder holder);

}
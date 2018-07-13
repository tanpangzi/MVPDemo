package com.tan.mvpdemo.uitl;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * ViewUtils：用于获取/设置 View/AbsListView）高度等
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class ViewUtil {

    /**
     * 得到ListView高度
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午5:48:12
     * <br> UpdateTime: 2016-1-23,下午5:48:12
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param view
     *         ListView
     */
    public static int getListViewHeight(ListView view) {
        int height = getAbsListViewHeight(view);
        ListAdapter adapter;
        int adapterCount;
        if (view != null && (adapter = view.getAdapter()) != null && (adapterCount = adapter.getCount()) > 0) {
            height += view.getDividerHeight() * (adapterCount - 1);
        }
        return height;
    }

    /**
     * 得到GridView高度
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午5:48:12
     * <br> UpdateTime: 2016-1-23,下午5:48:12
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param view
     *         GridView
     *
     * @return 高度
     */
    public static int getGridViewHeight(GridView view) {
        int height = getAbsListViewHeight(view);
        ListAdapter adapter;
        int adapterCount, numColumns = getGridViewNumColumns(view);
        if (view != null && (adapter = view.getAdapter()) != null && (adapterCount = adapter.getCount()) > 0 && numColumns > 0) {
            int rowCount = (int) Math.ceil(adapterCount / (double) numColumns);
            height = rowCount * (height / adapterCount + getGridViewVerticalSpacing(view));
        }
        return height;
    }

    /**
     * 根据 Item的高度 得到AbsListView高度
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午5:48:12
     * <br> UpdateTime: 2016-1-23,下午5:48:12
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param view
     *         AbsListView
     *
     * @return 高度
     */
    public static int getAbsListViewHeight(AbsListView view) {
        ListAdapter adapter;
        if (view == null || (adapter = view.getAdapter()) == null) {
            return 0;
        }

        int height = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View item = adapter.getView(i, null, view);
            if (item instanceof ViewGroup) {
                item.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            }
            item.measure(0, 0);
            height += item.getMeasuredHeight();
        }
        height += view.getPaddingTop() + view.getPaddingBottom();
        return height;
    }

    /**
     * 获取 GridView 列数
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午5:48:12
     * <br> UpdateTime: 2016-1-23,下午5:48:12
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param view
     *         GridView
     *
     * @return 列数
     */
    public static int getGridViewNumColumns(GridView view) {
        if (view == null || view.getChildCount() <= 0) {
            return 0;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return view.getNumColumns();

        } else {
            int columns = 0;
            int children = view.getChildCount();
            if (children > 0) {
                int width = view.getChildAt(0).getMeasuredWidth();
                if (width > 0) {
                    columns = view.getWidth() / width;
                }
            }
            return columns;
        }
    }

    /**
     * 获取 GridView item的垂直间距
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午5:48:12
     * <br> UpdateTime: 2016-1-23,下午5:48:12
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param view
     *         GridView
     *
     * @return 垂直间距
     */
    public static int getGridViewVerticalSpacing(GridView view) {
        Class<?> demo = null;
        int verticalSpacing = 0;
        try {
            demo = Class.forName("android.widget.GridView");
            Field field = demo.getDeclaredField("mVerticalSpacing");
            // Field field = demo.getDeclaredField("mRequestedNumColumns");
            // Field field = demo.getDeclaredField("mHorizontalSpacing");
            field.setAccessible(true);
            verticalSpacing = (Integer) field.get(view);
            return verticalSpacing;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verticalSpacing;
    }

    /**
     * 设置View的高度
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午5:48:12
     * <br> UpdateTime: 2016-1-23,下午5:48:12
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param view
     *         View
     * @param height
     *         高度
     */
    public static void setViewHeight(View view, int height) {
        if (view == null) {
            return;
        }

        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = height;
    }

    /**
     * 设置GridView的高度
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午5:48:12
     * <br> UpdateTime: 2016-1-23,下午5:48:12
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param view
     *         GridView
     */
    public static void setGridViewHeight(GridView view) {
        setViewHeight(view, getGridViewHeight(view));
    }

    /**
     * 设置ListView的高度
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午5:48:12
     * <br> UpdateTime: 2016-1-23,下午5:48:12
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param view
     *         ListView
     *
     * @return 高度
     */
    public static void setListViewHeight(ListView view) {
        setViewHeight(view, getListViewHeight(view));
    }

    /**
     * 设置AbsListView的高度
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午5:48:12
     * <br> UpdateTime: 2016-1-23,下午5:48:12
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param view
     *         AbsListView
     *
     * @return 高度
     */
    public static void setAbsListViewHeight(AbsListView view) {
        setViewHeight(view, getAbsListViewHeight(view));
    }

    /**
     * 根据父视图获取所有的子视图
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-23,下午5:48:12
     * <br> UpdateTime: 2016-1-23,下午5:48:12
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param parent
     *         父视图
     * @param filter
     *         （View） Type of views which will be returned.
     * @param includeSubClass
     *         (True) Whether returned list will include views which are
     *         subclass of filter or not.
     *
     * @return List<T>
     */
    public static <T extends View> List<T> getDescendants(ViewGroup parent, Class<T> filter, boolean includeSubClass) {
        List<T> descendedViewList = new ArrayList<T>();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            Class<? extends View> childsClass = child.getClass();
            if ((includeSubClass && filter.isAssignableFrom(childsClass)) || (!includeSubClass && childsClass == filter)) {
                descendedViewList.add(filter.cast(child));
            }
            if (child instanceof ViewGroup) {
                descendedViewList.addAll(getDescendants((ViewGroup) child, filter, includeSubClass));
            }
        }
        return descendedViewList;
    }

    /**
     * 回收继承自ViewGroup的类,如GridView,ListView,LinearLayout,RelativeLayout等
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-22,上午11:42:02
     * <br> UpdateTime: 2016-1-22,上午11:42:02
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param layout
     *         ViewGroup
     */
    public static void recycleViewGroup(ViewGroup layout) {
        if (layout == null)
            return;
        synchronized (layout) {
            for (int i = 0; i < layout.getChildCount(); i++) {
                View subView = layout.getChildAt(i);
                if (subView instanceof ViewGroup) {
                    recycleViewGroup((ViewGroup) subView);
                } else {
                    if (subView instanceof ImageView) {
                        recycleImageView((ImageView) subView);
                    }
                }
            }
        }
    }

    /**
     * 回收ImageView占用的图像内存;
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-22,上午11:42:08
     * <br> UpdateTime: 2016-1-22,上午11:42:08
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param view
     *         ImageView
     */
    public static void recycleImageView(View view) {
        if (view == null)
            return;
        if (view instanceof ImageView) {
            Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable instanceof BitmapDrawable) {
                Bitmap bmp = ((BitmapDrawable) drawable).getBitmap();
                if (bmp != null && !bmp.isRecycled()) {
                    ((ImageView) view).setImageBitmap(null);
                    bmp.recycle();
                    bmp = null;
                }
            }
        }
    }
}
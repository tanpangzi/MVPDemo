package com.tan.mvpdemo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 自定义的Srollview，提供OnSizeChangeListener监听
 * <p>
 * setInterceptTouch(true)可拦截子类事件
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年8月5日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class CustomScrollView extends ScrollView {

    /** 是否拦截子控件的touch事件 */
    private boolean isInterceptTouch = true;
    /** view大小更改监听 */
    private OnSizeChangeListener listener;
    /** view滑动更改监听 */
    private OnScrollChangeListener changeListener;

    public CustomScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context) {
        super(context);
    }

    @Override
    public void fling(int velocityY) {
        super.fling((int) (velocityY / 1));
    }

    @SuppressLint("NewApi")
    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    /**
     * 设置size大小更改监听
     *
     * <br> Version: 1.0.0
     * <br> CreateTime:: 2016-11-28,下午4:31:49
     * <br> UpdateTime: 2016-11-28,下午4:31:49
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     */
    public void setOnSizeChangListener(OnSizeChangeListener onSizeChangeListener) {
        this.listener = onSizeChangeListener;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (listener != null) {
            listener.onChange(w, h, oldw, oldh);
        }
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (changeListener != null) {
            changeListener.onChange(l, t, oldl, oldt);
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    /**
     * 设置滑动更改监听
     *
     * @param listener
     *
     * <br> Version: 1.0.0
     * <br> CreateTime:: 2016年8月16日,下午12:05:23
     * <br> UpdateTime: 2016年8月16日,下午12:05:23
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     */
    public void setOnScrollChangeListener(OnScrollChangeListener listener) {
        this.changeListener = listener;
    }

    /**
     * 滑动监听外部回调接口
     * <p>
     * <br> Author:叶青
     * <br> Version: 1.0.0
     * <br> Date: 2016年8月16日
     * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
     */
    public interface OnScrollChangeListener {

        /**
         * 滑动事件监听
         *
         * @param x
         *         更改前的x
         * @param y
         *         更改前的y
         * @param oldx
         *         更改后的x
         * @param oldy
         *         更改后的y
         *         <p>
         *         <br> Version: 1.0.0
         *         <br> CreateTime:: 2016年8月16日,下午12:00:03
         *         <br> UpdateTime: 2016年8月16日,下午12:00:03
         *         <br> CreateAuthor: 叶青
         *         <br> UpdateAuthor: 叶青
         *         <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
         */
        public void onChange(int x, int y, int oldx, int oldy);

    }

    /**
     * view 大小更改监听事件
     * <p>
     * <br> Author:叶青
     * <br> Version: 1.0.0
     * <br> Date: 2016年12月11日
     * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.  :
     */
    public interface OnSizeChangeListener {

        /**
         * View 大小更改监听事件
         * <p>
         * <br> Version: 1.0.0
         * <br> CreateTime:: 2016-11-28,下午4:18:33
         * <br> UpdateTime: 2016-11-28,下午4:18:33
         * <br> CreateAuthor: 叶青
         * <br> UpdateAuthor: 叶青
         * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
         *
         * @param w
         *         新的宽度
         * @param h
         *         新的高度
         * @param oldw
         *         旧的宽度
         * @param oldh
         *         新的高度
         */
        public void onChange(int w, int h, int oldw, int oldh);
    }

    /**
     * @param rect
     */
    @Override
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        return 0;
    }

    /** ********************是否拦截子类的touch事件******************************** */

    /**
     * 是否拦截子类的touch事件
     *
     * @return true 拦截，false 不拦截
     *
     * <br> Version: 1.0.0
     * <br> CreateTime:: 2016-11-27,下午3:34:21
     * <br> UpdateTime: 2016-11-27,下午3:34:21
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     */
    public boolean isInterceptTouch() {
        return isInterceptTouch;
    }

    /**
     * 设置是否拦截子类touch事件
     *
     * @param isInterceptTouch
     *         true 拦截，false 不拦截
     *
     * <br> Version: 1.0.0
     * <br> CreateTime:: 2016-11-27,下午3:34:55
     * <br> UpdateTime: 2016-11-27,下午3:34:55
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     */
    public void setInterceptTouch(boolean isInterceptTouch) {
        this.isInterceptTouch = isInterceptTouch;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        if (isInterceptTouch) {
            return super.onInterceptTouchEvent(ev);
        }
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            isInterceptTouch = true;
        }
        return isInterceptTouch;
    }
}
package com.tan.mvpdemo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * ViewPager 自定义控制左右滑动事件
 * 
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class CustomViewPager4Lock extends ViewPager {

	public CustomViewPager4Lock(Context context) {
		super(context);
	}

	public CustomViewPager4Lock(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/** false不滑动 */
	private boolean enabled = true;

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (this.enabled) {
			try {
				return super.onTouchEvent(event);
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		if (this.enabled) {
			try {
				return super.onInterceptTouchEvent(event);
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	public void setPagingEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
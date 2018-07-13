package com.tan.mvpdemo.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 去除listview headerview viewpage 嵌套 滑动 抖动问题
 * 
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class CustomViewPager4ScrollView extends ViewPager {

	public CustomViewPager4ScrollView(Context context) {
		super(context);
	}

	public CustomViewPager4ScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/** 获取该组件在屏幕的x坐标 */
	float deltaX = 0;
	/** 获取该组件在屏幕的y坐标 */
	float deltaY = 0;
	/** 获取x坐标 */
	float downX = 0;
	/** 获取y坐标 */
	float downY = 0;
	/** 在本次dispatchTouchEvent中 如果viewpage正在滑动 则锁定viewpage 获取焦点 不让listview滚动 */
	private boolean isLock = false;
	/** viewpage 页面数量 */
	private int itemSize = 0;

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		if (getItemSize() > 1) {
			downX = event.getRawX();
			downY = event.getRawY();

			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				getParent().requestDisallowInterceptTouchEvent(true);
				deltaX = event.getRawX();
				deltaY = event.getRawY();
				break;
			case MotionEvent.ACTION_MOVE:
				// Log.e("123456", (isLock) + "-------------");
				// Log.i("123456", (Math.abs(downY - deltaY) / Math.abs(downX -
				// deltaX)) + "-------------");
				// Log.i("123456",downX+","+downY+""+deltaX+","+deltaY);

				// 60度角
				if (Math.abs(downY - deltaY) / Math.abs(downX - deltaX) < 1.8) {
					// // 左上角下拉 或者右上角下来 需要锁定 不然会出现下拉 拉着拉着 突然出现刷新试图
					// if ((downY > deltaY && downX > deltaX) || (downY > deltaY
					// &&
					// downX < deltaX)) {
					// setLock(true);
					// }
					// 只需这句话，让父类不拦截触摸事件就可以了。执行本类类触摸事件
					getParent().requestDisallowInterceptTouchEvent(true);
				} else {
					if (!isLock()) {
						// 执行父类触摸事件
						getParent().requestDisallowInterceptTouchEvent(false);
					}
				}
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				setLock(false);
				// Log.e("123456", "ACTION_UP-------------");
				break;
			default:
				break;
			}
		}
		return super.dispatchTouchEvent(event);
	}

	public boolean isLock() {
		return isLock;
	}

	public void setLock(boolean isLock) {
		this.isLock = isLock;
	}

	public int getItemSize() {
		return itemSize;
	}

	public void setItemSize(int itemSize) {
		this.itemSize = itemSize;
	}
}
package com.tan.mvpdemo.widget.pullview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ListView;

import com.tan.mvpdemo.uitl.HandlerUtil;


/**
 * 下拉刷新列表控件
 *
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class PullToRefreshListView extends PullToRefreshAdapterViewBase<ListView> {

	/** 支持下拉刷新 */
	public static final int MODE_PULL_TO_DOWN = 0x01;
	/** 支持上拉拉刷新 */
	public static final int MODE_PULL_TO_UP = 0x02;
	/** 同时支持上拉和下拉 */
	public static final int MODE_BOTH_UP_AND_DOWN = 0x03;

	/** 移动Item */
	private final int WHAT_MOVE_ITEM_TO_LAST = 1;

	/** 当前列表模式 */
	public static int currentListMode = MODE_BOTH_UP_AND_DOWN;

	/** view大小更改监听 */
	private OnSizeChangeListener listener;
	private CustomListview listview;

	public PullToRefreshListView(Context context) {
		super(context);

	}

	public PullToRefreshListView(Context context, int mode) {
		super(context, mode);
	}

	public PullToRefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected final CustomListview createRefreshableView(Context context, AttributeSet attrs) {
		listview = new CustomListview(context);
		listview.setId(android.R.id.list);
		return listview;
	}

	@Override
	public ContextMenuInfo getContextMenuInfo() {
		return super.getContextMenuInfo();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		if (listener != null) {
			listener.onChange(w, h, oldw, oldh);
		}

		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				HandlerUtil.sendMessage(handler, WHAT_MOVE_ITEM_TO_LAST);
			}
		}, 100);
		super.onSizeChanged(w, h, oldw, oldh);
	}

	/**
	 * 设置size大小更改监听
	 *
	 * <br> Version: 1.0.0
	 * <br> CreateTime: 2016-11-28,下午4:31:49
	 * <br> UpdateTime: 2016-11-28,下午4:31:49
	 * <br> CreateAuthor: 叶青
	 * <br> UpdateAuthor: 叶青
	 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
	 */
	public void setOnSizeChangListener(OnSizeChangeListener listener) {
		this.listener = listener;
	}

	/**
	 * view 大小更改监听事件
	 *
	 * <br> Author: 叶青
	 * <br> Version: 1.0.0
	 * <br> Date: 2016年12月11日
	 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
	 */
	public interface OnSizeChangeListener {

		/**
		 * View 大小更改监听事件
		 *
		 * <br> Version: 1.0.0
		 * <br> CreateTime: 2016-11-28,下午4:18:33
		 * <br> UpdateTime: 2016-11-28,下午4:18:33
		 * <br> CreateAuthor: 叶青
		 * <br> UpdateAuthor: 叶青
		 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
		 *
		 * @param w
		 *            新的宽度
		 * @param h
		 *            新的高度
		 * @param oldw
		 *            旧的宽度
		 * @param oldh
		 *            新的高度
		 */
		public void onChange(int w, int h, int oldw, int oldh);
	}

	/** 异步处理对象 */
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
				case WHAT_MOVE_ITEM_TO_LAST:
					// PullToRefreshListView.this.getRefreshableView().setSelection(PullToRefreshListView.this.getRefreshableView().getCount()
					// - 1);
					break;
			}
		}

	};

	@Override
	protected void addFootLayout() {
		((CustomListview) getRefreshableView()).loadAll();
		CustomListview mListview = null;
		if (getRefreshableView() instanceof CustomListview) {
			mListview = (CustomListview) getRefreshableView();
		}
		if (null != mListview) {
			mListview.loadAll();
			mListview.setSelection(mListview.getCount() - 1);
		}
	}

	@Override
	protected void removeFootLayout() {
		((CustomListview) getRefreshableView()).loadAll();
		CustomListview mListview = null;
		if (getRefreshableView() instanceof CustomListview) {
			mListview = (CustomListview) getRefreshableView();
		}
		if (null != mListview) {
			mListview.loading();
		}

	}


	@Override
	protected void setNotData() {
		((CustomListview) getRefreshableView()).notData();
	}

}

package com.tan.mvpdemo.widget.pullview;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.tan.mvpdemo.R;

public class CustomListview extends ListView {

	/** 足控件 */
	private LoadFootView footView;
	/** 子控件点击监听 */
	private OnItemClickListener itemClickListener;

	public CustomListview(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		this.setCacheColorHint(context.getResources().getColor(R.color.transparent));
		this.setSelector(context.getResources().getDrawable(R.color.transparent));
		this.setDivider(getResources().getDrawable(R.color.transparent));
		footView = new LoadFootView(context);
		registerEvent();
		this.setFadingEdgeLength(0);
	}


	@Override
	public void setAdapter(ListAdapter adapter) {
		if (this.getFooterViewsCount() <= 0) {
			addFooterView(footView);
		}
		super.setAdapter(adapter);
		if (this.getFooterViewsCount() > 0) {
			removeFooterView(footView);
		}
	}

	@Override
	public void setOnItemClickListener(OnItemClickListener listener) {
		this.itemClickListener = listener;
	}

	private void registerEvent() {
		super.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (null != itemClickListener && 0 == getFooterViewsCount() && 0 == getHeaderViewsCount()) {
					itemClickListener.onItemClick(parent, view, position, id);
				} else if (null != itemClickListener && 0 < getFooterViewsCount() && position < (getCount() - 1)) {
					itemClickListener.onItemClick(parent, view, position, id);
				} else if (null != itemClickListener && 0 <= getHeaderViewsCount() && 0 != position && 0 >= getFooterViewsCount()) {
					itemClickListener.onItemClick(parent, view, position, id);
				}

			}
		});
	}

	/**
	 * 加载全部
	 *
	 * <br> Version: 1.0.0
	 * <br> CreateTime: 2016年1月6日,下午8:06:20
	 * <br> UpdateTime: 2016年1月6日,下午8:06:20
	 * <br> CreateAuthor: 叶青
	 * <br> UpdateAuthor: 叶青
	 * <br> UpdateInfo:
	 */
	public void loadAll() {
		if (null != footView && getFooterViewsCount() <= 0) {
			footView.all();
			addFooterView(footView);
		}
	}

	/**
	 * 加载中
	 *
	 * <br> Version: 1.0.0
	 * <br> CreateTime: 2016年1月7日,上午10:49:21
	 * <br> UpdateTime: 2016年1月7日,上午10:49:21
	 * <br> CreateAuthor: 叶青
	 * <br> UpdateAuthor: 叶青
	 * <br> UpdateInfo:
	 */
	public void loading() {
		if (null != footView && getFooterViewsCount() > 0) {
			removeFooterView(footView);
		}
	}

	/**
	 * 设置足控件提示语
	 *
	 * <br> Version: 1.0.0
	 * <br> CreateTime: 2016年1月12日,上午10:37:29
	 * <br> UpdateTime: 2016年1月12日,上午10:37:29
	 * <br> CreateAuthor: 叶青
	 * <br> UpdateAuthor: 叶青
	 * <br> UpdateInfo:
	 */
	public void notData() {
		if (null != footView) {
			footView.not();
		}
	}

}

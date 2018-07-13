package com.tan.mvpdemo.widget.pullview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tan.mvpdemo.R;


/**
 * 加载视图
 *
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 *
 */
public class LoadingLayout extends FrameLayout {

	static final int DEFAULT_ROTATION_ANIMATION_DURATION = 150;

	/** 刷新箭头 */
	private final RoundProgressBar roundProgressBar;
	/** 加载等待框 */
	private final ProgressBar headerProgress;
	/** 加载提示信息 */
	private final TextView headerText;

	private String pullLabel;
	private String refreshingLabel;
	private String releaseLabel;
	/** 当前已是全部内容 */
	private String all;
	/** 当前页面没有数据 */
	private String not;

	private final Animation rotateAnimation, resetRotateAnimation;

	public LoadingLayout(Context context, final int mode, String releaseLabel, String pullLabel, String refreshingLabel) {
		super(context);
		ViewGroup header = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header, this);
		headerText = (TextView) header.findViewById(R.id.pull_to_refresh_text);
		headerProgress = (ProgressBar) header.findViewById(R.id.pull_to_refresh_progress);
		roundProgressBar = (RoundProgressBar) header.findViewById(R.id.pull_to_refresh_roundprogressbar);

		final Interpolator interpolator = new LinearInterpolator();
		rotateAnimation = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotateAnimation.setInterpolator(interpolator);
		rotateAnimation.setDuration(DEFAULT_ROTATION_ANIMATION_DURATION);
		rotateAnimation.setFillAfter(true);

		resetRotateAnimation = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		resetRotateAnimation.setInterpolator(interpolator);
		resetRotateAnimation.setDuration(DEFAULT_ROTATION_ANIMATION_DURATION);
		resetRotateAnimation.setFillAfter(true);

		all = context.getString(R.string.no_more_data);
		not = context.getString(R.string.no_data);

		this.releaseLabel = releaseLabel;
		this.pullLabel = pullLabel;
		this.refreshingLabel = refreshingLabel;

		switch (mode) {
			case PullToRefreshBase.MODE_PULL_UP_TO_REFRESH:
				roundProgressBar.setArrowImage(R.drawable.pulltorefresh_up_arrow);
				// headerImage.setImageResource(R.drawable.pulltorefresh_up_arrow);
				break;
			case PullToRefreshBase.MODE_PULL_DOWN_TO_REFRESH:
			default:
				roundProgressBar.setArrowImage(R.drawable.pulltorefresh_down_arrow);
				// headerImage.setImageResource(R.drawable.pulltorefresh_down_arrow);
				break;
		}
	}

	public void reset() {
		headerText.setText(pullLabel);
		roundProgressBar.setVisibility(View.VISIBLE);
		roundProgressBar.resetProgress();
		// headerImage.setVisibility(View.VISIBLE);
		headerProgress.setVisibility(View.GONE);
	}

	public void releaseToRefresh() {
		headerText.setText(releaseLabel);
		// headerImage.clearAnimation();
		// headerImage.startAnimation(rotateAnimation);
	}

	public void setPullLabel(String pullLabel) {
		this.pullLabel = pullLabel;
	}

	public void setProgress(int progress) {
		roundProgressBar.setProgress(progress);
	}

	/**
	 * 显示刷新中的界面
	 *
	 * <br> Version: 1.0.0
	 * <br> CreateTime: 2016-12-28,下午1:40:00
	 * <br> UpdateTime: 2016-12-28,下午1:40:00
	 * <br> CreateAuthor: 叶青
	 * <br> UpdateAuthor: 叶青
	 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
	 *
	 * @param
	 */
	public void refreshing() {
		headerText.setText(refreshingLabel);
		// headerImage.clearAnimation();
		roundProgressBar.setVisibility(View.INVISIBLE);
		// headerImage.setVisibility(View.INVISIBLE);
		headerProgress.setVisibility(View.VISIBLE);
	}

	/**
	 * 加载全部
	 *
	 * <br> Version: 1.0.0
	 * <br> CreateTime: 2016年1月5日,下午4:10:59
	 * <br> UpdateTime: 2016年1月5日,下午4:10:59
	 * <br> CreateAuthor: 叶青
	 * <br> UpdateAuthor: 叶青
	 * <br> UpdateInfo:
	 */
	public void loadAll() {
		headerText.setText(all);
		roundProgressBar.setVisibility(View.GONE);
		headerProgress.setVisibility(View.GONE);
	}

	/**
	 * 设置刷新提示文字
	 *
	 * <br> Version: 1.0.0
	 * <br> CreateTime: 2016-12-28,下午1:41:12
	 * <br> UpdateTime: 2016-12-28,下午1:41:12
	 * <br> CreateAuthor: 叶青
	 * <br> UpdateAuthor: 叶青
	 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
	 *
	 * @param refreshingLabel
	 */
	public void setRefreshingLabel(String refreshingLabel) {
		this.refreshingLabel = refreshingLabel;
	}

	/**
	 * 显示松开刷新的文字
	 *
	 * <br> Version: 1.0.0
	 * <br> CreateTime: 2016-12-28,下午1:41:29
	 * <br> UpdateTime: 2016-12-28,下午1:41:29
	 * <br> CreateAuthor: 叶青
	 * <br> UpdateAuthor: 叶青
	 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
	 *
	 * @param releaseLabel
	 */
	public void setReleaseLabel(String releaseLabel) {
		this.releaseLabel = releaseLabel;
	}

	/**
	 * 显示推动刷新的文本
	 *
	 * <br> Version: 1.0.0
	 * <br> CreateTime: 2016-12-28,下午1:41:59
	 * <br> UpdateTime: 2016-12-28,下午1:41:59
	 * <br> CreateAuthor: 叶青
	 * <br> UpdateAuthor: 叶青
	 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
	 *
	 */
	public void pullToRefresh() {
		headerText.setText(pullLabel);
		// headerImage.clearAnimation();
		// headerImage.startAnimation(resetRotateAnimation);
	}

	public void setTextColor(int color) {
		headerText.setTextColor(color);
	}

}

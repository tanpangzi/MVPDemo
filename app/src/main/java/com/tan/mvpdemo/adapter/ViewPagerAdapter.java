package com.tan.mvpdemo.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * viewpager的适配器
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class ViewPagerAdapter extends PagerAdapter {

    /** view集合 */
    private ArrayList<View> views;

    public ViewPagerAdapter(ArrayList<View> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(View view, int position) {
        ViewPager viewPage = (ViewPager) view;
        viewPage.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup group, int position, Object object) {
        if (position < views.size()) {
            ViewPager viewPage = (ViewPager) group;
            viewPage.removeView(views.get(position));
        }
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }
}
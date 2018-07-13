package com.tan.mvpdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.tan.mvpdemo.R;


/**
 * 自定义ImageView自带点击效果
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016/12/12
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class AutoBgImageView extends ImageView {

    private boolean isClickAble = false;
    // 点击后的透明度
    private int bgAlpha;

    public AutoBgImageView(Context context) {
        super(context);
    }

    public AutoBgImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public AutoBgImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoBg);
        // 点击后的透明度
        bgAlpha = a.getInt(R.styleable.AutoBg_bgAlpha, 120);

        a.recycle();
    }


    @Override
    public void setPressed(boolean pressed) {
        updateView(pressed);
        super.setPressed(pressed);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        isClickAble = null != l;
        super.setOnClickListener(l);
    }

    /**
     * 根据是否按下去来刷新bg和src
     */
    private void updateView(boolean pressed) {
        //如果没有点击效果
        if (!isClickAble) {
            return;
        }

        if (pressed) {//点击
            setAlpha(bgAlpha);
        } else {//未点击
            setAlpha(255);
        }
    }
}
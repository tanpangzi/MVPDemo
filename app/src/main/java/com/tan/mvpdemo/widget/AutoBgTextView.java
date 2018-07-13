package com.tan.mvpdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tan.mvpdemo.R;


/**
 * 自定义TextView自带点击效果
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016/12/12
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class AutoBgTextView extends TextView {

    /** 是否可以点击 */
    private boolean isClickAble = false;
    /** 点击后的透明度 */
    private int bgAlpha;
    /** 当前textview的字体int颜色值 */
    private int colorInt = -1;

    public AutoBgTextView(Context context) {
        super(context);
    }

    public AutoBgTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public AutoBgTextView(Context context, AttributeSet attrs) {
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
     * 根据是否按下去来刷新字体颜色
     */
    private void updateView(boolean pressed) {
        //如果没有点击效果
        if (!isClickAble) {
            return;
        }

        if (colorInt == -1) {
            colorInt = this.getCurrentTextColor();
        }

        if (pressed) {//点击
            this.setTextColor(getColor(colorInt));  //文字透明度
        } else {//未点击
            this.setTextColor(colorInt); // 文字透明度
        }
    }

    /**
     * int颜色值转换为RGB颜色值
     *
     * @param color
     *         int颜色值
     *
     * @return RGB颜色值
     */
    public int getColor(int color) {
        int red = (color & 0xff0000) >> 16;
        int green = (color & 0x00ff00) >> 8;
        int blue = (color & 0x0000ff);
        return (Color.argb(bgAlpha, red, green, blue));
    }
}
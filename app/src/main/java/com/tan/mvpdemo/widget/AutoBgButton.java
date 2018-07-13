package com.tan.mvpdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.Button;

import com.tan.mvpdemo.R;
import com.tan.mvpdemo.uitl.BitmapUtil;


/**
 * 自定义Button自带点击效果
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016/12/12
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class AutoBgButton extends Button {

    private boolean isClickAble = false;
    private Drawable background;
    // 圆角的大小 默认为3dp
    private int bgRadius;
    // 点击后的透明度
    private int bgAlpha;

    public AutoBgButton(Context context) {
        super(context);
    }

    public AutoBgButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public AutoBgButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoBg);
        // 圆角的大小 默认为3dp
        bgRadius = a.getDimensionPixelSize(R.styleable.AutoBg_bgRadius,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics()));
        // 点击后的透明度
        bgAlpha = a.getInt(R.styleable.AutoBg_bgAlpha, 150);

        //LogUtil.i(bgRadius + "==" + bgAlpha);
        a.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (background != null && bgRadius > 0) {
            setBackgroundDrawable(background);
        }
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void setBackgroundDrawable(Drawable background) {
        this.background = background;
        if (getMeasuredWidth() > 0 && getMeasuredHeight() > 0 && bgRadius > 0) {
            Bitmap bitmap = drawableToBitmap(background);
            background = BitmapUtil.bitmapToDrawable(bitmap);
        }
        super.setBackgroundDrawable(background);
    }

    /**
     * drawable转bitmap
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-19,下午4:03:19
     * <br> UpdateTime: 2016-1-19,下午4:03:19
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param drawable
     *         Drawable
     */
    private Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            bitmap = bd.getBitmap();
        } else {
            bitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            drawable.draw(canvas);
        }

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = bgRadius;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        canvas.save();
        bitmap.recycle();
        return output;
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

        if (getBackground()!=null){
            if (pressed) {//点击
                getBackground().mutate().setAlpha(bgAlpha);
            } else {//未点击
                getBackground().mutate().setAlpha(255);
            }
        }
    }
}
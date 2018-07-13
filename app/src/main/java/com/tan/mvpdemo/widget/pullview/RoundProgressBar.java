package com.tan.mvpdemo.widget.pullview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.tan.mvpdemo.R;


/**
 * 带进度的进度条，线程安全的View，可直接在线程中更新进度
 *
 * <br> Author: 叶青
 * <br> Date: 2016年8月5日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class RoundProgressBar extends View {
    /** 画笔对象的引用 */
    private Paint paint;
    /** 圆环的颜色 */
    private int roundColor;
    /** 圆环进度的颜色 */
    private int roundProgressColor;
    /** 圆环的宽度 */
    private float roundWidth;
    /** 最大进度 */
    private int max;
    /** 当前进度 */
    private int progress;
    /** 箭头图标 */
    private int arrowDrawable;

    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        paint = new Paint();
        //        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);
        //        // 获取自定义属性和默认值
        //        roundColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundColor, getResources().getColor(R.color.transparent));
        //        roundProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundProgressColor, getResources().getColor(R.color.font_gray));
        //        roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, 2);
        // 获取自定义属性和默认值
        roundColor = getResources().getColor(R.color.transparent);
        roundProgressColor = getResources().getColor(R.color.font_gray);
        roundWidth = 2;
        arrowDrawable = R.drawable.pulltorefresh_down_arrow;
        max = 100;
        //        mTypedArray.recycle();// 必须Recycle
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /** 画最外层的圆环 */
        int centre = getWidth() / 2; // 获取圆心的x坐标
        int radius = (int) (centre - roundWidth / 2); // 圆环的半径
        paint.setColor(roundColor); // 设置圆环的颜色
        paint.setStyle(Paint.Style.STROKE); // 设置空心
        paint.setStrokeWidth(roundWidth); // 设置圆环的宽度
        paint.setAntiAlias(true); // 消除锯齿
        canvas.drawCircle(centre, centre, radius, paint); // 画出圆环
        /** 画圆弧 ，画圆环的进度 */
        // 设置进度是实心还是空心
        paint.setStrokeWidth(roundWidth); // 设置圆环的宽度
        paint.setColor(roundProgressColor); // 设置进度的颜色
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius); // 用于定义的圆弧的形状和大小的界限
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(oval, -80, progress * 350 / max, false, paint); // 根据进度画圆弧
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), arrowDrawable);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        canvas.drawBitmap(bitmap, centre - width / 2, centre - height / 2, paint);

    }

    public synchronized int getMax() {
        return max;
    }

    /**
     * 设置进度的最大值
     *
     * @param max
     */
    public synchronized void setMax(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = max;
    }

    /**
     * 获取进度.需要同步
     */
    public synchronized int getProgress() {
        return progress;
    }

    public synchronized void setArrowImage(int drawable) {
        this.arrowDrawable = drawable;
    }

    /**
     * 重置进度条状态
     *
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年8月4日, 下午5:58:03
     * <br> UpdateTime: 2016年8月4日, 下午5:58:03
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo:
     */
    public void resetProgress() {
        progress = 0;
        invalidate();
    }

    /**
     * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步 刷新界面调用postInvalidate()能在非UI线程刷新
     *
     * @param progress
     *
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年8月5日, 上午10:01:29
     * <br> UpdateTime: 2016年8月5日, 上午10:01:29
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo:
     */
    public synchronized void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (progress > max) {
            progress = max;
        }
        if (progress <= max) {
            this.progress = progress;
            //			LogUtil.i("setProgress===============>>" + this.progress);
            postInvalidate();
        }

    }

    public int getCricleColor() {
        return roundColor;
    }

    public void setCricleColor(int cricleColor) {
        this.roundColor = cricleColor;
    }

    public int getCricleProgressColor() {
        return roundProgressColor;
    }

    public void setCricleProgressColor(int cricleProgressColor) {
        this.roundProgressColor = cricleProgressColor;
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }

}

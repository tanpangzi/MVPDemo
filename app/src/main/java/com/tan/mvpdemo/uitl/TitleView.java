package com.tan.mvpdemo.uitl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tan.mvpdemo.R;


/**
 * 标题栏控件
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class TitleView extends LinearLayout {

    /** 上下文环境 */
    private Context context;
    /** 父视图 */
    private View viewParent;

    //    /** 左边按钮 */
    //    private View view_left;
    //    /** 左边按钮图标(左边开始算，第二个按钮) */
    //    private View view_left_new;

    //    /** 右边按钮 */
    //    public View view_right;
    //    /** 右边按钮 (右边开始算，第二个按钮) */
    //    private View view_right_new;

    /** 左边按钮图标 */
    private ImageView img_left;
    /** 左边按钮图标(左边开始算，第二个按钮) */
    private ImageView img_left_new;

    /** 右边按钮图标 */
    private ImageView img_right;
    /** 右边按钮图标(右边开始算，第二个按钮) */
    public ImageView img_right_new;

    /** 左边按钮文本 */
    private TextView txt_left;
    /** 右边按钮文本 */
    private TextView txt_right;

    /** 标题 */
    public TextView txt_title;
    /** 标题2 */
    private TextView txt_title2;

    public TitleView(Context context) {
        super(context);
        init(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    /**
     * 初始化.
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:04:12
     * <br> UpdateTime: 2016-12-23,下午4:04:12
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         Context
     */
    private void init(Context context) {
        this.context = context;

        //        this.setOrientation(VERTICAL);
        //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        //            RelativeLayout statusbar = new RelativeLayout(context);
        //            RelativeLayout.LayoutParams statusbarParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(
        //                    R.dimen.title_view_padding_top));
        //            statusbar.setBackgroundColor(context.getResources().getColor(R.color.title_bg_color));
        //            this.addView(statusbar, statusbarParams);
        //        }

        viewParent = View.inflate(context, R.layout.view_title, null);
        this.addView(viewParent);
        //        view_left = viewParent.findViewById(R.id.view_left);
        //        view_left_new = viewParent.findViewById(R.id.view_left_new);
        //
        //        view_right = viewParent.findViewById(R.id.view_right);
        //        view_right_new = viewParent.findViewById(R.id.view_right_new);

        img_left = (ImageView) viewParent.findViewById(R.id.img_left);
        img_left_new = (ImageView) viewParent.findViewById(R.id.img_left_new);

        img_right = (ImageView) viewParent.findViewById(R.id.img_right);
        img_right_new = (ImageView) viewParent.findViewById(R.id.img_right_new);

        txt_left = (TextView) viewParent.findViewById(R.id.txt_left);
        txt_right = (TextView) viewParent.findViewById(R.id.txt_right);

        txt_title = (TextView) viewParent.findViewById(R.id.txt_title);
        txt_title2 = (TextView) viewParent.findViewById(R.id.txt_title2);
    }

    // ********************************************************左边按钮

    /**
     * 设置左边图片按钮
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:06:09
     * <br> UpdateTime: 2016-12-23,下午4:06:09
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param resId
     *         图片资源id
     * @param listener
     *         返回按钮监听事件
     */
    public void setLeftBtnImg(int resId, OnClickListener listener) {
        img_left.setVisibility(View.VISIBLE);
        img_left.setImageResource(resId);
        img_left.setOnClickListener(listener);
    }

    /**
     * 设置左边图片按钮
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:06:52
     * <br> UpdateTime: 2016-12-23,下午4:06:52
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param resId
     *         图片资源id
     */
    public void setLeftBtnImg(int resId) {
        img_left.setVisibility(View.VISIBLE);
        img_left.setImageResource(resId);
        img_left.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                KeyboardUtil.hideKeyBord(viewParent);
                ((Activity) context).finish();
                ((Activity) context).overridePendingTransition(R.anim.exit_enter, R.anim.exit_exit);
            }
        });
    }

    /**
     * 设置返回按钮
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:12:05
     * <br> UpdateTime: 2016-12-23,下午4:12:05
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    public void setLeftBtnImg() {
        img_left.setVisibility(View.VISIBLE);
        img_left.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                KeyboardUtil.hideKeyBord(viewParent);
                ((Activity) context).finish();
                ((Activity) context).overridePendingTransition(R.anim.exit_enter, R.anim.exit_exit);
            }
        });
    }

    /**
     * 设置返回按钮
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:12:05
     * <br> UpdateTime: 2016-12-23,下午4:12:05
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    public void setLeftBtnTxt() {
        txt_left.setVisibility(View.VISIBLE);
        txt_left.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                KeyboardUtil.hideKeyBord(viewParent);
                ((Activity) context).finish();
                ((Activity) context).overridePendingTransition(R.anim.exit_enter, R.anim.exit_exit);
            }
        });
    }

    /**
     * 设置返回按钮：点击事件
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:12:54
     * <br> UpdateTime: 2016-12-23,下午4:12:54
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param listener
     *         返回按钮监听事件
     */
    public void setLeftBtnImg(OnClickListener listener) {
        img_left.setVisibility(View.VISIBLE);
        img_left.setOnClickListener(listener);
    }

    /**
     * 设置返回按钮：文本
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:13:05
     * <br> UpdateTime: 2016-12-23,下午4:13:05
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param text
     *         返回按钮文本
     */
    public void setLeftBtnTxt(String text) {
        txt_left.setVisibility(View.VISIBLE);
        txt_left.setText(text);
        txt_left.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                KeyboardUtil.hideKeyBord(viewParent);
                ((Activity) context).finish();
                ((Activity) context).overridePendingTransition(R.anim.exit_enter, R.anim.exit_exit);

            }
        });
    }

    /**
     * 设置返回按钮：文本和点击事件
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:13:16
     * <br> UpdateTime: 2016-12-23,下午4:13:16
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param text
     *         返回按钮文本
     * @param listener
     *         点击事件
     */
    public void setLeftBtnTxt(String text, OnClickListener listener) {
        txt_left.setVisibility(View.VISIBLE);
        txt_left.setText(text);
        txt_left.setOnClickListener(listener);
    }

    /**
     * 设置左边的字体颜色
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:11:09
     * <br> UpdateTime: 2016-12-23,下午4:11:09
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param resId
     *         颜色资源id
     */
    public void setLeftBtnTxtColor(int resId) {
        txt_left.setVisibility(View.VISIBLE);
        txt_left.setTextColor(resId);
    }

    /**
     * 设置左边按钮：图标(左边开始算，第二个按钮)和点击事件
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:15:15
     * <br> UpdateTime: 2016-12-23,下午4:15:15
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param resId
     *         图片资源id
     * @param listener
     *         按钮点击事件
     */
    public void setLeftBtnImgNew(int resId, OnClickListener listener) {
        img_left_new.setVisibility(View.VISIBLE);
        img_left_new.setImageResource(resId);
        img_left_new.setOnClickListener(listener);
    }

    // ********************************************************右边按钮

    /**
     * 设置右边按钮:图片
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:09:28
     * <br> UpdateTime: 2016-12-23,下午4:09:28
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param resId
     *         图片资源id
     */
    public void setRightBtnImg(int resId) {
        img_right.setVisibility(View.VISIBLE);
        img_right.setImageResource(resId);
    }

    /**
     * 设置右边按钮：图片和点击事件
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:08:18
     * <br> UpdateTime: 2016-12-23,下午4:08:18
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param resId
     *         图片资源id
     * @param listener
     *         点击事件
     */
    public void setRightBtnImg(int resId, OnClickListener listener) {
        setRightBtnImg(resId);
        img_right.setOnClickListener(listener);
    }

    /**
     * 设置右边文本按钮
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:10:07
     * <br> UpdateTime: 2016-12-23,下午4:10:07
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param text
     *         按钮文本内容
     * @param listener
     *         按钮监听事件
     */
    public void setRightBtnTxt(String text, OnClickListener listener) {
        setRightBtnTxt(text);
        txt_right.setOnClickListener(listener);
    }

    /**
     * 设置右边文本按钮
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:10:07
     * <br> UpdateTime: 2016-12-23,下午4:10:07
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param resId
     *         按钮文本内容
     * @param listener
     *         按钮监听件
     */
    public void setRightBtnTxt(int resId, OnClickListener listener) {
        setRightBtnTxt(context.getString(resId), listener);
    }


    /**
     * 设置右边的字体
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:11:09
     * <br> UpdateTime: 2016-12-23,下午4:11:09
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param resId
     *         资源id
     */
    public void setRightBtnTxt(int resId) {
        setRightBtnTxt(context.getString(resId));
    }

    /**
     * 设置右边按钮文本内容
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:12:41
     * <br> UpdateTime: 2016-12-23,下午4:12:41
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param text
     *         按钮文本内容
     */
    public void setRightBtnTxt(String text) {
        txt_right.setVisibility(View.VISIBLE);
        txt_right.setText(text);
    }

    /**
     * 设置右边的字体颜色
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:11:09
     * <br> UpdateTime: 2016-12-23,下午4:11:09
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param resId
     *         颜色资源id
     */
    public void setRightBtnTxtColor(int resId) {
        txt_right.setVisibility(View.VISIBLE);
        txt_right.setTextColor(resId);
    }

    /**
     * 设置右边按钮：图标(右边开始算，第二个按钮)和点击事件
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:17:23
     * <br> UpdateTime: 2016-12-23,下午4:17:23
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param resId
     *         图片资源id
     * @param listener
     *         按钮点击事件
     */
    public void setRightBtnImgNew(int resId, OnClickListener listener) {
        setRightBtnImgNew(resId);
        img_right_new.setOnClickListener(listener);
    }

    /**
     * 设置右边按钮：图标(右边开始算，第二个按钮)
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:17:34
     * <br> UpdateTime: 2016-12-23,下午4:17:34
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param resId
     *         图片资源id
     */
    public void setRightBtnImgNew(int resId) {
        img_right_new.setVisibility(View.VISIBLE);
        img_right_new.setImageResource(resId);
    }

    // ****************************************************设置标题

    /**
     * 设置标题
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:05:10
     * <br> UpdateTime: 2016-12-23,下午4:05:10
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param resId
     *         文本资源Id
     */
    public void setTitle(int resId) {
        setTitle(context.getString(resId));
    }

    /**
     * 设置标题
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:05:30
     * <br> UpdateTime: 2016-12-23,下午4:05:30
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param title
     *         标题文本内容
     */
    public void setTitle(String title) {
        txt_title.setVisibility(View.VISIBLE);
        txt_title.setText(title);
    }

    /**
     * 设置标题点击事件
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:05:41
     * <br> UpdateTime: 2016-12-23,下午4:05:41
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param listener
     *         点击事件
     */
    public void setOnTitleListener(OnClickListener listener) {
        Drawable drawable = getResources().getDrawable(R.mipmap.arrow_left_black);
        txt_title.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        txt_title.setCompoundDrawablePadding(ScreenUtil.dip2px(5));
        txt_title.setOnClickListener(listener);
    }

    /**
     * 设置标题点击事件的图标
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:05:54
     * <br> UpdateTime: 2016-12-23,下午4:05:54
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param resId
     *         图片资源id
     */
    public void setOnTitleRightImg(int resId) {
        Drawable drawable = getResources().getDrawable(resId);
        txt_title.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        txt_title.setCompoundDrawablePadding(ScreenUtil.dip2px(5));
    }

    /**
     * 显示副标题
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:18:42
     * <br> UpdateTime: 2016-12-23,下午4:18:42
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param title
     *         标题内容
     */
    public void setSecondTitle(String title) {
        txt_title2.setVisibility(View.VISIBLE);
        txt_title2.setText(title);
    }

    /**
     * 动态设置背景、同时需要设置标题颜色
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:18:07
     * <br> UpdateTime: 2016-12-23,下午4:18:07
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param bgColor
     *         背景颜色
     * @param textColor
     *         标题颜色
     */
    public void setBgColor(int bgColor, int textColor) {
        viewParent.setBackgroundColor(getResources().getColor(bgColor));
        txt_title.setTextColor(getResources().getColor(textColor));
        txt_title2.setTextColor(getResources().getColor(textColor));
        txt_right.setTextColor(getResources().getColor(textColor));
        txt_left.setTextColor(getResources().getColor(textColor));
    }

    /**
     * 动态设置背景透明度 不透明 是 白色
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:14:02
     * <br> UpdateTime: 2016-12-23,下午4:14:02
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param alpha
     *         透明度 0是全透明 1是不透明
     */
    public void setViewAlpha(float alpha) {
        int i = (int) (alpha * 255);
        // viewParent.getBackground().setAlpha(i);
        // 手机上出现设置了view 的background透明度，但是整个应用的background都会有透明度 ;
        // 解决方式mutate()
        viewParent.getBackground().mutate().setAlpha(i);
    }

    /**
     * 动态设置标题透明度
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-12-23,下午4:14:02
     * <br> UpdateTime: 2016-12-23,下午4:14:02
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param alpha
     *         透明度 0是全透明 1是不透明
     */
    public void setTitleAlpha(float alpha) {
        int colorInt = txt_title.getCurrentTextColor();
        int i = (int) (alpha * 255);
        txt_title.setTextColor(getColor(colorInt, i));
    }

    public void setTitleColor(int resId) {
        txt_title.setTextColor(getResources().getColor(resId));
    }

    /**
     * int颜色值转换为RGB颜色值
     *
     * @param color
     *         int颜色值
     *
     * @return RGB颜色值
     */
    public int getColor(int color, int alpha) {
        int red = (color & 0xff0000) >> 16;
        int green = (color & 0x00ff00) >> 8;
        int blue = (color & 0x0000ff);
        return (Color.argb(alpha, red, green, blue));
    }
}
package com.tan.mvpdemo.uitl.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.tan.mvpdemo.R;
import com.tan.mvpdemo.uitl.EmptyCheckUtil;
import com.tan.mvpdemo.uitl.LogUtil;
import com.tan.mvpdemo.uitl.ScreenUtil;
import com.tan.mvpdemo.uitl.ViewUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 自定义对话框.
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年9月15日
 * <br> Copyright: Copyright ©2016 xTeam Technology. All rights reserved.
 */
public class CustomDialog extends Dialog {

    /** 上下文 */
    private Context context;
    private Window window = null;

    public CustomDialog(Context context) {
        super(context, R.style.dialog_default);
        init(context);
    }

    /**
     * 初始化
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年5月27日, 下午5:00:06
     * <br> UpdateTime: 2016年5月27日, 下午5:00:06
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param context
     *         上下文
     */
    private void init(Context context) {
        this.context = context;
        this.setCancelable(false);
        this.setCanceledOnTouchOutside(false);
        window = getWindow(); // 得到对话框
    }

    /**
     * 显示消息提示对话框（可自定义设置按钮文字）
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年1月28日, 下午5:30:24
     * <br> UpdateTime: 2016年1月28日, 下午5:30:24
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param title
     *         标题
     * @param content
     *         内容
     * @param txt_left
     *         左边按钮文本
     * @param txt_right
     *         右边边按钮文本
     * @param leftClickListener
     *         按钮监听事件
     * @param rightClickListener
     *         按钮监听事件
     * @param gravity
     *         content显示位置
     */
    public void showMessageDialog(String title, String content, String txt_left, String txt_right, final OnDialogClickListener leftClickListener, final OnDialogClickListener rightClickListener, int gravity) {
        View view = View.inflate(context.getApplicationContext(), R.layout.view_dialog_parent, null);
        TextView txtTitle = (TextView) view.findViewById(R.id.view_dialog_txt_title);
        TextView txtContent = (TextView) view.findViewById(R.id.view_dialog_txt_content);
        Button btnLeft = (Button) view.findViewById(R.id.view_dialog_btn_left);
        Button btnRight = (Button) view.findViewById(R.id.view_dialog_btn_right);
        View view_line = view.findViewById(R.id.view_line);

        if (!EmptyCheckUtil.isEmpty(txt_left)) {
            btnLeft.setVisibility(View.VISIBLE);
            btnLeft.setText(txt_left);
        } else {
            btnLeft.setVisibility(View.GONE);
        }

        if (!EmptyCheckUtil.isEmpty(txt_right)) {
            btnRight.setVisibility(View.VISIBLE);
            btnRight.setText(txt_right);
        } else {
            btnRight.setVisibility(View.GONE);
        }

        if (!EmptyCheckUtil.isEmpty(txt_right) && !EmptyCheckUtil.isEmpty(txt_left)) {
            view_line.setVisibility(View.VISIBLE);
        } else {
            view_line.setVisibility(View.GONE);
        }

        if (!EmptyCheckUtil.isEmpty(title)) {
            txtTitle.setVisibility(View.VISIBLE);
            txtTitle.setText(title);
        } else {
            txtTitle.setVisibility(View.GONE);
        }

        if (!EmptyCheckUtil.isEmpty(content)) {
            txtContent.setVisibility(View.VISIBLE);
            txtContent.setText(Html.fromHtml(content));
        } else {
            txtContent.setVisibility(View.GONE);
        }

        txtContent.setGravity(gravity);

        int margin = context.getResources().getDimensionPixelSize(R.dimen.distance_15);
        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content)) {
            ((LinearLayout.LayoutParams) txtContent.getLayoutParams()).setMargins(0, margin, 0, 0);
        } else {
            ((LinearLayout.LayoutParams) txtContent.getLayoutParams()).setMargins(0, 0, 0, 0);
        }

        if (rightClickListener != null) {
            btnRight.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    rightClickListener.onClick(CustomDialog.this, v.getId(), null);
                }
            });
        } else {
            btnRight.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    CustomDialog.this.dismiss();
                }
            });
        }

        if (leftClickListener != null) {
            btnLeft.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    leftClickListener.onClick(CustomDialog.this, v.getId(), null);
                }
            });
        } else {
            btnLeft.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    CustomDialog.this.dismiss();
                }
            });
        }
        createDialog(view, false);
    }

    // ******************************************** 单选列表对话框 **************************************************

    /**
     * 设置单选列表对话框
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年5月27日, 下午6:07:17
     * <br> UpdateTime: 2016年5月27日, 下午6:07:17
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param title
     *         提示文本标题
     * @param checkItem
     *         选中的item
     * @param items
     *         选项组合
     * @param listener
     *         单击监听
     */
    public void setSingleSelectDialog(final ArrayList items, String title, int itemColor, int checkItem, final OnDialogClickListener listener) {
        View view = View.inflate(context, R.layout.view_dialog_single_opreation, null);
        TextView txtTitle = (TextView) view.findViewById(R.id.view_dialog_single_title);
        ListView listView = (ListView) view.findViewById(R.id.view_dialog_single_listview);
        //        LinearLayout linear_parent = (LinearLayout) view.findViewById(R.id.linear_parent);
        ImageButton imgClose = (ImageButton) view.findViewById(R.id.view_dialog_ibn_close);
        //        linear_parent.setBackgroundResource(R.drawable.shape_bg_dialog);
        txtTitle.setText(Html.fromHtml(title));

        DialogSingleItemAdapter adapter = new DialogSingleItemAdapter(context, itemColor, items, checkItem);
        listView.setAdapter(adapter);
        // if (checkItem > -1) {
        //     listView.setSelection(checkItem);
        // }
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                CustomDialog.this.dismiss();
                listener.onClick(CustomDialog.this, position, items.get(position));
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        LinearLayout.LayoutParams params;
        int lvHeight = 0;
        try {
            lvHeight = ViewUtil.getListViewHeight(listView);
            LogUtil.i(ViewUtil.getListViewHeight(listView) + ".....");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (lvHeight > ScreenUtil.getScreenHeightPx() / 3 * 2) {
            lvHeight = ScreenUtil.getScreenHeightPx() / 3 * 2;
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, lvHeight);

        } else {
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        }
        createDialog(view, true, params);
    }

    /**
     * 在指定位置显示单选列表对话框
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年5月18日, 上午10:44:34
     * <br> UpdateTime: 2016年5月18日, 上午10:44:34
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param items
     *         列表item
     * @param gravity
     *         Gravity
     * @param title
     *         标题
     * @param listener
     *         item点击事件
     */
    public void showSingleSelectDialog4Gravity(final ArrayList items, String title, int gravity, final OnDialogClickListener listener) {
        View view = View.inflate(context, R.layout.view_dialog_single_opreation, null);
        ListView listView = (ListView) view.findViewById(R.id.view_dialog_single_listview);
        TextView txtTitle = (TextView) view.findViewById(R.id.view_dialog_single_title);
        LinearLayout linear_parent = (LinearLayout) view.findViewById(R.id.linear_parent);
        ImageButton imgClose = (ImageButton) view.findViewById(R.id.view_dialog_ibn_close);
        linear_parent.setBackgroundResource(R.drawable.shape_bg_dialog);
        linear_parent.setPadding(0, 0, 0, 0);

        if (EmptyCheckUtil.isEmpty(title)) {
            txtTitle.setVisibility(View.GONE);
        } else {
            txtTitle.setVisibility(View.VISIBLE);
            txtTitle.setText(title);
        }
        DialogSingleItemAdapter adapter = new DialogSingleItemAdapter(context, -1, items, -1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomDialog.this.dismiss();
                listener.onClick(CustomDialog.this, position, items.get(position));
            }
        });
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        LinearLayout.LayoutParams params;
        int lvHeight = 0;
        try {
            lvHeight = ViewUtil.getListViewHeight(listView);
            LogUtil.i(ViewUtil.getListViewHeight(listView) + ".....");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (lvHeight > ScreenUtil.getScreenHeightPx() / 2) {
            lvHeight = ScreenUtil.getScreenHeightPx() / 2;
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, lvHeight);

        } else {
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        }
        createDialog(view, true, params);

        if (null != window) {
            window.setGravity(gravity);
        }
        if (null != window) {
            window.setGravity(gravity);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = ScreenUtil.getScreenWidthPx(); // 设置宽度
            window.setAttributes(lp);
            window.setWindowAnimations(R.style.popupwindow_view_anim);
        }
    }

    //***************************************** iOS底部彈出框 ***********************************************

    /**
     * 设置单选列表对话框（取消灰色字体）+ 选项颜色修改===样式：iOS底部彈出框
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年7月2日, 下午2:57:34
     * <br> UpdateTime: 2016年7月2日, 下午2:57:34
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     */
    public void showIosDialog(String title, final ArrayList items, int gravity,
                              int[] layout_margin, HashMap<Integer, Integer> positionColor, final OnDialogClickListener listener) {
        View view = View.inflate(context, R.layout.view_dialog_single_opreation_ios, null);
        TextView txtTitle = (TextView) view.findViewById(R.id.view_dialog_single_title);
        View line = view.findViewById(R.id.title_line);
        ListView listView = (ListView) view.findViewById(R.id.view_dialog_single_listview);
        LinearLayout viewCancel = (LinearLayout) view.findViewById(R.id.linear_cancel);
        //        LinearLayout linear_parent = (LinearLayout) view.findViewById(R.id.linear_parent);
        //        linear_parent.setBackgroundResource(R.drawable.shape_bg_dialog);
        if (EmptyCheckUtil.isEmpty(title)) {
            txtTitle.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        } else {
            txtTitle.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
            txtTitle.setText(title);
        }

        if (layout_margin != null && layout_margin.length != 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewCancel.getLayoutParams();
            layoutParams.setMargins(layout_margin[0], layout_margin[1], layout_margin[2], layout_margin[3]);
        }
        DialogIosAdapter adapter = new DialogIosAdapter(context, items, positionColor);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onClick(CustomDialog.this, position, items.get(position));
            }
        });

        viewCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CustomDialog.this.dismiss();
            }
        });

        createDialog(view, true);

        if (null != window) {
            window.setGravity(gravity);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = ScreenUtil.getScreenWidthPx() - context.getResources().getDimensionPixelSize(R.dimen.distance_20); // 设置宽度
            window.setAttributes(lp);
            window.setWindowAnimations(R.style.popupwindow_view_anim);
        }
    }

    /**
     * 设置单选列表对话框（取消灰色字体）+ 选项颜色修改===样式：iOS底部彈出框
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年7月2日, 下午2:57:34
     * <br> UpdateTime: 2016年7月2日, 下午2:57:34
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     */
    public void showIosDialogPurple(String title, final ArrayList items, int gravity,
                                    int[] layout_margin, HashMap<Integer, Integer> positionColor, final OnDialogClickListener listener) {
        View view = View.inflate(context, R.layout.view_dialog_single_opreation_ios_purple, null);
        TextView txtTitle = (TextView) view.findViewById(R.id.view_dialog_single_title);
        txtTitle.setTextColor(R.color.black);
        View line = view.findViewById(R.id.title_line);
        ListView listView = (ListView) view.findViewById(R.id.view_dialog_single_listview);
        LinearLayout viewCancel = (LinearLayout) view.findViewById(R.id.linear_cancel);
        //        LinearLayout linear_parent = (LinearLayout) view.findViewById(R.id.linear_parent);
        //        linear_parent.setBackgroundResource(R.drawable.shape_bg_dialog);
        if (EmptyCheckUtil.isEmpty(title)) {
            txtTitle.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        } else {
            txtTitle.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
            txtTitle.setText(title);
        }

        if (layout_margin != null && layout_margin.length != 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewCancel.getLayoutParams();
            layoutParams.setMargins(layout_margin[0], layout_margin[1], layout_margin[2], layout_margin[3]);
        }
        DialogIosAdapter adapter = new DialogIosAdapter(context, items, positionColor);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onClick(CustomDialog.this, position, items.get(position));
            }
        });

        viewCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CustomDialog.this.dismiss();
            }
        });

        createDialog(view, true);

        if (null != window) {
            window.setGravity(gravity);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = ScreenUtil.getScreenWidthPx() - context.getResources().getDimensionPixelSize(R.dimen.distance_20); // 设置宽度
            window.setAttributes(lp);
            window.setWindowAnimations(R.style.popupwindow_view_anim);
        }
    }


    /**
     * 创建单选对话框.
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年9月15日, 上午9:54:02
     * <br> UpdateTime: 2016年9月15日, 上午9:54:02
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
     *
     * @param view
     *         界面内容.
     * @param isCancel
     *         是否可以关闭.
     * @param params
     *         LayoutParams.
     */
    public void createDialog(View view, boolean isCancel, LinearLayout.LayoutParams params) {
        this.setCancelable(isCancel);
        this.setCanceledOnTouchOutside(isCancel);

        if (params != null) {
            this.setContentView(view, params);
        } else {
            this.setContentView(view);
        }
    }

    public void createDialog(View view, boolean isCancel) {
        createDialog(view, isCancel, null);
    }

    /**
     * 对话框按钮点击回调事件
     * <p>
     * <br> Author: 叶青
     * <br> Version: 1.0.0
     * <br> Date: 2016年5月27日
     * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
     */
    public interface OnDialogClickListener {
        /**
         * 对话框按钮被点击时候调用
         * <p>
         * <br> Version: 1.0.0
         * <br> CreateTime: 2016年5月27日, 下午5:26:46
         * <br> UpdateTime: 2016年5月27日, 下午5:26:46
         * <br> CreateAuthor: 叶青
         * <br> UpdateAuthor: 叶青
         * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
         *
         * @param dialog
         *         对话框对象
         * @param id
         *         按钮控件id
         * @param object
         *         附带信息
         */
        void onClick(CustomDialog dialog, int id, Object object);
    }

    //    废弃方法
    //    /**
    //     * 显示文本提示对话框 ----知道了
    //     * <p>
    //     * <br> Version: 1.0.0
    //     * <br> CreateTime: 2016年7月22日, 上午10:35:51
    //     * <br> UpdateTime: 2016年7月22日, 上午10:35:51
    //     * <br> CreateAuthor: 叶青
    //     * <br> UpdateAuthor: 叶青
    //     * <br> UpdateInfo: (此处输入修改内容, 若无修改可不写.)
    //     *
    //     * @param title
    //     *         标题
    //     * @param content
    //     *         提示内容
    //     * @param clickListener
    //     *         点击监听
    //     * @param gravity
    //     *         content显示位置
    //     * @param btnStr
    //     *         按钮文字
    //     */
    //    public void showKnowDialog(String title, String content, final OnDialogClickListener clickListener, int gravity, String btnStr) {
    //        View view = View.inflate(context, R.layout.view_dialog_parent, null);
    //        TextView txtTitle = (TextView) view.findViewById(R.id.view_dialog_txt_title);
    //        TextView txtContent = (TextView) view.findViewById(R.id.view_dialog_txt_content);
    //        Button btnEnter = (Button) view.findViewById(R.id.view_dialog_btn_single_text);
    //        View viewBtnParent = view.findViewById(R.id.view_btn_parent);
    //        viewBtnParent.setVisibility(View.GONE);
    //        btnEnter.setVisibility(View.VISIBLE);
    //
    //        if (!EmptyCheckUtil.isEmpty(title)) {
    //            txtTitle.setVisibility(View.VISIBLE);
    //            txtTitle.setText(title);
    //        } else {
    //            txtTitle.setVisibility(View.GONE);
    //        }
    //
    //        if (!EmptyCheckUtil.isEmpty(content)) {
    //            txtContent.setVisibility(View.VISIBLE);
    //            txtContent.setText(Html.fromHtml(content));
    //        } else {
    //            txtContent.setVisibility(View.GONE);
    //        }
    //
    //        if (!EmptyCheckUtil.isEmpty(btnStr)) {
    //            btnEnter.setText(btnStr);
    //        }
    //
    //        txtContent.setGravity(gravity);
    //
    //        int margin = context.getResources().getDimensionPixelSize(R.dimen.distance_15);
    //        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content)) {
    //            ((LinearLayout.LayoutParams) txtContent.getLayoutParams()).setMargins(0, margin, 0, 0);
    //        } else {
    //            ((LinearLayout.LayoutParams) txtContent.getLayoutParams()).setMargins(0, 0, 0, 0);
    //        }
    //
    //        btnEnter.setOnClickListener(new View.OnClickListener() {
    //
    //            @Override
    //            public void onClick(View v) {
    //                clickListener.onClick(CustomDialog.this, v.getId(), null);
    //            }
    //        });
    //        createDialog(view, false);
    //    }
}
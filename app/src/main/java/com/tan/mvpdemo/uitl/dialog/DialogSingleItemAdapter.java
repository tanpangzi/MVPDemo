package com.tan.mvpdemo.uitl.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tan.mvpdemo.R;
import com.tan.mvpdemo.adapter.SimpleBaseAdapter;

import java.util.ArrayList;

/**
 * 对话框单选列表适配器
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class DialogSingleItemAdapter extends SimpleBaseAdapter<String> {

    private int checkItem = -1;
    /** item颜色 */
    private int itemColor = -1;

    public DialogSingleItemAdapter(Context context, int itemColor, ArrayList<String> datas, int checkItem) {
        super(context, datas);
        this.itemColor = itemColor;
        this.checkItem = checkItem;
    }

    @Override
    public int getItemResource() {
        return R.layout.item_dialog_listview;
    }

    @Override
    public View getItemView(int position, View convertView, ViewHolder holder) {
        TextView txt_name = holder.getView(R.id.item_dialog_listview_txt_name);
        ImageView img_Check = holder.getView(R.id.item_dialog_listview_img_check);
        View view_line = holder.getView(R.id.view_line);

        if (position == dataList.size() - 1) {
            view_line.setVisibility(View.GONE);
        } else {
            view_line.setVisibility(View.VISIBLE);
        }

        if (itemColor != -1) {
            txt_name.setTextColor(itemColor);
        }

        // 根据传入参数是数组还是列表展示相关内容
        txt_name.setText(dataList.get(position));

        // 显示选中项
        if (checkItem > -1 && position == checkItem) {
            img_Check.setVisibility(View.VISIBLE);
        } else {
            img_Check.setVisibility(View.GONE);
        }

        return convertView;
    }
}
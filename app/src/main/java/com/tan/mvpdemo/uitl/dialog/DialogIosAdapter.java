package com.tan.mvpdemo.uitl.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


import com.tan.mvpdemo.R;
import com.tan.mvpdemo.adapter.SimpleBaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * 指定显示位置多选对话框列表适配器
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年5月21日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class DialogIosAdapter extends SimpleBaseAdapter<String> {

    /**
     * 添加位置颜色 （键 ：位置 ，值：颜色）
     */
    private HashMap<Integer, Integer> positionColor = null;

    public DialogIosAdapter(Context context, ArrayList<String> datas, HashMap<Integer, Integer> positionColor) {
        super(context, datas);
        this.positionColor = positionColor;
    }

    @Override
    public int getItemResource() {
        return R.layout.item_dialog_ios_listview;
    }

    @Override
    public View getItemView(int position, View convertView, SimpleBaseAdapter<String>.ViewHolder holder) {
        TextView txt_name =  holder.getView(R.id.item_group_dialog_button);
        View view_line = holder.getView(R.id.view_line);

        if (position == dataList.size() - 1) {
            view_line.setVisibility(View.GONE);
        } else {
            view_line.setVisibility(View.VISIBLE);
        }

        txt_name.setText(dataList.get(position));
        if (null != positionColor && positionColor.size() > 0) {
            if (null != positionColor.get(position)) {
                int color = positionColor.get(position);
                txt_name.setTextColor(color);
            } else {
                txt_name.setTextColor(context.getResources().getColor(R.color.font_green));
            }
        }
        return convertView;
    }
}
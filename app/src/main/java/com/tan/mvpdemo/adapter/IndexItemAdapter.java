package com.tan.mvpdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tan.mvpdemo.R;
import com.tan.mvpdemo.bean.ItemBean;

import java.util.ArrayList;

/**
 * <br> Description 首页adapeter recycler
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.adapter
 * <br> Date: 2018/6/4
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class IndexItemAdapter extends RecyclerView.Adapter<IndexItemAdapter.MyHolder>{

    private Context mContext;
    private ArrayList<ItemBean> datas;

    public OnItemClickListener onItemClickListener;

    public IndexItemAdapter(Context mContext, ArrayList<ItemBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_index, null);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.tv_item.setText(datas.get(position).getTitle());
        holder.iv_item.setImageResource(datas.get(position).getResId());
        holder.ll_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private ImageView iv_item;
        private TextView tv_item;
        private LinearLayout ll_container;

        public MyHolder(View view) {
            super(view);
            ll_container = view.findViewById(R.id.ll_container);
            iv_item = view.findViewById(R.id.iv_index);
            tv_item = view.findViewById(R.id.tv_index);
        }
    }

    /** item的点击接口 */
    public interface OnItemClickListener{
        /** 回调方法 */
        void onItemClick(int position);
    }


}

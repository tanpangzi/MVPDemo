package com.tan.mvpdemo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tan.mvpdemo.R;
import com.tan.mvpdemo.activity.gpsInstall.ZMXGpsLoadingActivity;
import com.tan.mvpdemo.bean.ImgBean;
import com.tan.mvpdemo.uitl.Constant;
import com.tan.mvpdemo.uitl.OtherUtils;
import com.tan.mvpdemo.uitl.PicassoUtil;
import com.tan.mvpdemo.uitl.ScreenUtil;

import java.util.ArrayList;

/**
 * Created by tanjun on 2018/1/22.
 * 车辆评估 gps安装 车辆抵押 车辆解押的适配器
 */

public class PhotoGridAdapter extends SimpleBaseAdapter<ImgBean.ImgListBean>{

    public PhotoGridAdapter(Context context, ArrayList<ImgBean.ImgListBean> datas) {
        super(context, datas);
    }

    @Override
    public int getItemResource() {
        return R.layout.item_select_pic;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public View getItemView(final int position, View convertView, ViewHolder holder) {
        ImageView iv_select_pic = holder.getView(R.id.iv_select_pic);
        TextView tv_select_txt = holder.getView(R.id.tv_select_txt);

        int width = (int) ((ScreenUtil.getScreenWidthPx() - ScreenUtil.dip2px(80)) / 3f);
        iv_select_pic.getLayoutParams().width = width;
        iv_select_pic.getLayoutParams().height = width;

        final ImgBean.ImgListBean bean = dataList.get(position);

        if (TextUtils.isEmpty(bean.getImgDesc())){
            tv_select_txt.setVisibility(View.GONE);
        }else {
            tv_select_txt.setVisibility(View.VISIBLE);
            tv_select_txt.setText(bean.getImgDesc());
        }

        if (TextUtils.isEmpty(bean.getUrl())) {
            PicassoUtil.loadImage(context, R.drawable.icon_select_pic, iv_select_pic, R.drawable.icon_select_pic, R.drawable.icon_select_pic, null);
        } else {
            String path = bean.getUrl();
            if (!path.startsWith("/")){
                path = "/" +  path;
            }
            if (!path.startsWith("/storage/emulated") && !path.startsWith("http")) {
                path = Constant.BASE_URL + path;
            }
            PicassoUtil.loadImage(context, path, iv_select_pic);
        }

        iv_select_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (OtherUtils.getInstance().isFastClick(view)) {
                    return;
                }

                positionItem=position;
                if (TextUtils.isEmpty(bean.getUrl())) {
                    /*if (context instanceof CarAssememtActivity) {//车辆评估
                        ((CarAssememtActivity) context).showChoseDialog();
                    }else if (context instanceof CarMortgageActivity){//车辆抵押
                        ((CarMortgageActivity) context).showChoseDialog();
                    }else if (context instanceof ReleaseExplanationActivity){//车辆解押
                        ((ReleaseExplanationActivity) context).showChoseDialog();
                    }else*/ if (context instanceof ZMXGpsLoadingActivity){
                        ((ZMXGpsLoadingActivity) context).showChoseDialog(); //选择对话框
                    }
                } else {
                    /*if (context instanceof CarAssememtActivity) { //车辆评估
                        ((CarAssememtActivity) context).gotoPreviewPhoto(position);
                    }else if (context instanceof CarMortgageActivity){ //车辆抵押
                        ((CarMortgageActivity) context).gotoPreviewPhoto(position);
                    }else if (context instanceof ReleaseExplanationActivity){//车辆解押
                        ((ReleaseExplanationActivity) context).gotoPreviewPhoto(position);
                    }else */if (context instanceof ZMXGpsLoadingActivity){
                        ((ZMXGpsLoadingActivity) context).gotoPreviewPhoto(position);
                    }
                }
            }
        });

        return convertView;
    }

    private int positionItem=-1;

    public int getPositionItem() {
        return positionItem;
    }
}

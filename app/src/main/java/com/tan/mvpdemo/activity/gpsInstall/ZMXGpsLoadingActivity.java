package com.tan.mvpdemo.activity.gpsInstall;

import android.Manifest;
import android.app.admin.DeviceAdminInfo;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.tan.mvpdemo.BaseApplication;
import com.tan.mvpdemo.R;
import com.tan.mvpdemo.activity.BaseActivity;
import com.tan.mvpdemo.activity.ImageBrowseActivity;
import com.tan.mvpdemo.activityMvp.contract.GpsLoadingContract;
import com.tan.mvpdemo.activityMvp.presenter.GpsLoadingPresenter;
import com.tan.mvpdemo.adapter.PhotoGridAdapter;
import com.tan.mvpdemo.bean.ImgBean;
import com.tan.mvpdemo.bean.gpsInstall.FiledBean;
import com.tan.mvpdemo.bean.gpsInstall.GPSBean;
import com.tan.mvpdemo.config.ConfigFile;
import com.tan.mvpdemo.config.ConstantKey;
import com.tan.mvpdemo.config.RequestCode;
import com.tan.mvpdemo.uitl.Constant;
import com.tan.mvpdemo.uitl.FileUtil;
import com.tan.mvpdemo.uitl.IntentUtil;
import com.tan.mvpdemo.uitl.TitleView;
import com.tan.mvpdemo.uitl.ToastUtil;
import com.tan.mvpdemo.uitl.dialog.CustomDialog;
import com.tan.mvpdemo.uitl.dialog.DialogUtil;
import com.tan.mvpdemo.uitl.imageutils.GetPathUtil;
import com.tan.mvpdemo.widget.AutoSizeGridView;
import com.tan.mvpdemo.widget.ContainsEmojiEditText;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * <br> Description gps图片安装位置
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.activity.gpsInstall
 * <br> Date: 2018/6/7
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
@RuntimePermissions
public class ZMXGpsLoadingActivity extends BaseActivity<GpsLoadingContract.GpsLoadingPresenter> implements GpsLoadingContract.GpsLoadingView{

    /** 标题 */
    @BindView(R.id.title_view)
    TitleView titleView;
    /** 装车位置描述 */
    @BindView(R.id.et_install_pos)
    ContainsEmojiEditText etInstallPos;
    @BindView(R.id.grid_view_pic)
    AutoSizeGridView gridViewPic;
    @BindView(R.id.btn_continue_install)
    Button btnContinueInstall;
    @BindView(R.id.btn_check_signal)
    Button btnCheckSignal;

    private String imeiId, custId;
    private String simId;

    private String userName;

    private String posDesc;//安装位置 描述

    /** 图片 */
    private ArrayList<ImgBean.ImgListBean> datasCarInfo;

    private PhotoGridAdapter adapterCarInfo; // 适配器

    private final int MAX_SIZE = 1;

    /**
     * 上传图片
     * @param path
     */
    String fileName = "";

    /** 上传成功后返回的 filedId */
    String fileId = "";

    @Override
    protected int getContentViewId() {
        return R.layout.activity_gps_loading;
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void initGetData() {

        imeiId = getBundle.getString("imeiId");
        simId = getBundle.getString("simId");
        custId = getBundle.getString("custId");
        userName = BaseApplication.getInstance().getUserInfoBean().getUserName();

        mPresenter.queryAllImg(imeiId);
    }

    @Override
    protected void init() {
        titleView.setLeftBtnImg();
        titleView.setTitle("装车位置");
    }

    @Override
    protected void widgetListener() {

    }

    @Override
    public GpsLoadingContract.GpsLoadingPresenter initPresenter() {
        return new GpsLoadingPresenter(this);
    }

    //继续安装传0
    //信号检测传1
    @OnClick({R.id.btn_continue_install, R.id.btn_check_signal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_continue_install: //继续安装
                mPresenter.newBinding(adapterCarInfo, userName, custId, simId, imeiId, "0");

                break;
            case R.id.btn_check_signal: //信号检测
                mPresenter.newBinding(adapterCarInfo,userName, custId, simId, imeiId, "1");
                break;
        }
    }

    /**
     * 选择相册 相机对话框
     */
    public void showChoseDialog() {
        DialogUtil.showIosDialog(this, null, getResources().getStringArray(R.array.image_operation), Gravity.BOTTOM, null, new CustomDialog.OnDialogClickListener() {

            @Override
            public void onClick(CustomDialog dialog, int id, Object object) {
                dialog.dismiss();
                switch (id) {
                    case 1:// 相册
                        checkPhoto();
                        break;
                    case 0:// 相机
                        tackPic();
                        break;
                }
            }
        });
    }

    /** 拍照 */
    private void tackPic() {
        ZMXGpsLoadingActivityPermissionsDispatcher.showCameraWithCheck(this);
    }

    /** 相册 */
    private void checkPhoto() {
        ZMXGpsLoadingActivityPermissionsDispatcher.showPermissionWithCheck(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            if (requestCode == RequestCode.REQUEST_CODE_PIC){
                if (data != null) {
                    Uri uri = data.getData();
                    String picPath;//相册路径
                    if (uri.toString().startsWith("file:///storage/emulated")){
                        picPath = uri.toString().replace("file:///storage/emulated", "/storage/emulated");
                    }else {
                        //判断手机系统版本号
                        if (Build.VERSION.SDK_INT >= 19) {
                            //4.4及以上系统使用这个方法处理图片
                            picPath = GetPathUtil.getPath(ZMXGpsLoadingActivity.this, uri);
                        } else {
                            //4.4以下系统使用这个方法处理图片
                            picPath = null;
                            //通过Uri和selection来获取真实的图片路径
                            Cursor cursor = ZMXGpsLoadingActivity.this.getContentResolver().query(uri, null, null, null, null);
                            if (cursor != null) {
                                if (cursor.moveToFirst()) {
                                    picPath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                                }
                                cursor.close();
                            }
                        }
                    }
                    if (!TextUtils.isEmpty(picPath)){
                        // 解析本地图片，获得图片尺寸
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        // 不生成Bitmap 只是获取bitmap的宽高，相对省内存
                        options.inJustDecodeBounds = true;
                        BitmapFactory.decodeFile(picPath, options);
                        if (options.outWidth == -1 || options.outHeight == -1){
                            ToastUtil.showToast(ZMXGpsLoadingActivity.this, "图片已损坏，请重新选择");
                            return;
                        }
                        /** 上传图片 */
                        mPresenter.upLoadImg(adapterCarInfo, Constant.GROUPID_POSITIONIMAGES, datasCarInfo,picPath ,imeiId);
                    }
                }
            }
            else if (requestCode == RequestCode.REQUEST_CODE_PHOTO){ //相机
                mPresenter.upLoadImg(adapterCarInfo, Constant.GROUPID_POSITIONIMAGES, datasCarInfo,tempPath ,imeiId);
            }else if (requestCode == RequestCode.REQUEST_CODE_PREVIEW){ //预览
                mPresenter.queryAllImg(imeiId);
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 点击预览
     */
    public void gotoPreviewPhoto(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantKey.INTENT_KEY_POSITION, position);
        bundle.putString("groupId", Constant.GROUPID_POSITIONIMAGES);
        bundle.putString("imeiId", imeiId);
        bundle.putInt("imgType", Constant.positionImages); //类型
        ArrayList<ImgBean.ImgListBean> datas = datasCarInfo;
        ArrayList<String> arrayList;

        arrayList = new ArrayList<>();
        if (datas != null) {
            for (int i = 0; i < datas.size(); i++) {
                String path = datas.get(i).getUrl();
                if (!TextUtils.isEmpty(path) && !path.startsWith("/storage/emulated")) {
                    path = Constant.BASE_URL + path.replaceFirst("app/", "");
                }
                arrayList.add(path);
            }
        }
        bundle.putStringArrayList(ConstantKey.INTENT_KEY_DATAS, arrayList);
        IntentUtil.gotoActivityForResult(ZMXGpsLoadingActivity.this,
                ImageBrowseActivity.class, bundle, RequestCode.REQUEST_CODE_PREVIEW);

    }

    /**
     * 拍照 选择图片后 刷新数据源
     */
    private void setData(String path, String fileName){
        setPhotoData(datasCarInfo, path, fileName, Constant.GROUPID_POSITIONIMAGES);
        adapterCarInfo.notifyDataSetChanged();
    }

    private void setPhotoData(ArrayList<ImgBean.ImgListBean> datas, String path, String fileName, String groupId){
        boolean isLast = false;
        int positionItem=adapterCarInfo.getPositionItem();
//        for (int i = 0; i < datas.size(); i++) {
        datas.get(positionItem).setUrl(path);
        datas.get(positionItem).setGroup_id(groupId);
        datas.get(positionItem).setImage_name(fileName);
        if (positionItem == datas.size() -1){
            isLast = true;
        }
//        }

        if (isLast && datas.size() != MAX_SIZE && !TextUtils.isEmpty(path)){
            ImgBean.ImgListBean bean = new ImgBean.ImgListBean();
            datas.add(bean);
        }
    }

    /** 获取返回的位置描述 */
    @Override
    public String getDescrption() {
        return etInstallPos.getText().toString().trim();
    }

    /** 获取返回的图片列表 */
    @Override
    public void queryImgInfo(ImgBean imgBean) {
        datasCarInfo = new ArrayList<>();
        ImgBean.ImgListBean bean = new ImgBean.ImgListBean();
        datasCarInfo.add(bean);

        adapterCarInfo = new PhotoGridAdapter(this, datasCarInfo);
        gridViewPic.setAdapter(adapterCarInfo);
        if (imgBean != null){
            Map<Integer, ImgBean.ImgListBean> map = new HashMap<>();
            int maxItem = 0; //图片最大下标值
            ArrayList<ImgBean.ImgListBean> imgList = imgBean.getImgList();
            for (int i = 0; i < imgList.size(); i++) {
                ImgBean.ImgListBean dataBean = imgList.get(i);
                String imgName = dataBean.getImage_name();
                int curPos = Integer
                        .parseInt(imgName.substring(imgName.length() - 2, imgName.length()));//图片的位置
                if (curPos > maxItem){
                    maxItem = curPos;
                }
                map.put(curPos, dataBean);
            }
            addImg(maxItem, MAX_SIZE, map, datasCarInfo);
            adapterCarInfo.notifyDataSetChanged();
        }
    }


    /**
     * 添加图片
     * @param maxItem 最大下标
     * @param maxSize 最大数量
     * @param map
     * @param datas
     */
    private void addImg(int maxItem, int maxSize,
                        Map<Integer, ImgBean.ImgListBean> map, ArrayList<ImgBean.ImgListBean> datas){
        int add = maxItem - datas.size();
        for (int i = 0; i < add; i++) {
            ImgBean.ImgListBean bean = new ImgBean.ImgListBean();
            datas.add(bean);
        }
        Set<Map.Entry<Integer, ImgBean.ImgListBean>> set = map.entrySet();
        for (Map.Entry<Integer, ImgBean.ImgListBean> entry : set) {
            int position = entry.getKey();
            ImgBean.ImgListBean imageInfo = entry.getValue();
            if (position <= maxSize) {
                datas.get(position - 1).setUrl(imageInfo.getUrl());
                datas.get(position - 1).setImage_name(imageInfo.getImage_name());
            }
        }

        if (maxItem < maxSize && datas.size() != 0){
            if (!TextUtils.isEmpty(datas.get(datas.size() - 1).getUrl())) {
                ImgBean.ImgListBean imageInfoBean = new ImgBean.ImgListBean();
                datas.add(imageInfoBean);
            }
        }
    }

    /** 显示消息 */
    @Override
    public void showToast(String msg) {
        ToastUtil.showToast(this, msg);
    }

    /** 操作成功 */
    @Override
    public void onSuccess() {

    }

    /** 获取fileId */
    @Override
    public void getFiledId(FiledBean filedBean) {
        fileId = filedBean.getFiledId();
        showToast("图片上传成功");
        setData("app/common/captcha/task/readImg?fileId="+ fileId, fileName);
    }

    /** 绑定成功后跳转 */
    @Override
    public void onBindSuccess(int bindCode) {
        if (bindCode == 0){ //跳转继续安装
            postBundle.putString("custId", custId);
            postBundle.putString("simId", simId);
            postBundle.putString("imeiId", imeiId);
            IntentUtil.gotoActivity(this, ZMXDeviceInfoActivity.class, postBundle);
            finishActivity();
        }else if (bindCode == 1){//跳转信号检测
            getGpsLocationInfo();
        }
    }

    /** gps定位信息 */
    @Override
    public void getGpsLocationInfo() {
        mPresenter.getGpsLocationInfo(custId);
    }

    /** 获取GPS返回的信息 */
    @Override
    public void getGpsReturnInfo(GPSBean bean) {
        if (bean != null){
            ArrayList<GPSBean.ReturnListBean> datas =  bean.getReturnList();
            postBundle.putSerializable("gpsList", datas);
            postBundle.putString("imeiId", imeiId);
            postBundle.putString("custId", custId);
            postBundle.putString("simId", simId);

            IntentUtil.gotoActivity(this, GPSIntallTestActivity.class, postBundle);
        }
    }

    /** 拍照权限 */
    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showPermission() {
        FileUtil.createAllFile();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);//Pick an item from the data
        intent.setType("image/*");
        startActivityForResult(intent, RequestCode.REQUEST_CODE_PIC);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ZMXGpsLoadingActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showRationale(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("权限申请")
                .setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.proceed();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.cancel();
                    }
                }).setCancelable(false)
                .show();
    }


    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showDenied() {
        showToast("存储权限被拒绝");
    }

    /**
     * 拍照 保存图片的本地缓存路径
     */
    private String tempPath = "";
    /** 相机权限 可以的存储权限写一起 后面整理 */
    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);

        tempPath = ConfigFile.PATH_IMAGES + "/dk_" + System.currentTimeMillis() + ".jpg";

        tempPath = ConfigFile.PATH_IMAGES + "/dk_" + System.currentTimeMillis() + ".jpg";
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            uri = FileProvider.getUriForFile(this, "com.hxyd.dyt.fileprovider", new File(tempPath));
            //								uri = FileProvider.getUriForFile(RecordedOrderActvityThree.this, BuildConfig.APPLICATION_ID + ".fileProvider", new File(tempPath));
        } else {
            uri = Uri.fromFile(new File(tempPath));
        }
        //							Uri uri = Uri.fromFile(new File(tempPath));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, RequestCode.REQUEST_CODE_PHOTO);

    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void showDialog(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("权限申请")
                .setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.proceed();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.cancel();
                    }
                }).setCancelable(false)
                .show();
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void showCameraDenied() {
        showToast("相机权限被拒绝");
    }

}

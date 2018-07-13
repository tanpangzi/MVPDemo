package com.tan.mvpdemo.uitl;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.util.Util;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;
import com.tan.mvpdemo.BuildConfig;
import com.tan.mvpdemo.R;

import java.io.File;

/**
 * Picasso图片加载
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class PicassoUtil {


    /**
     * 加载图片
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime:
     * <br> UpdateTime
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         上下文
     * @param imgPath
     *         路径
     * @param imageView
     *         控件ImageView
     */
    public static void loadImage(Context context, Object imgPath, ImageView imageView) {
        loadImage(context, imgPath, imageView, R.drawable.img_default_grey_base, R.drawable.img_load_error_base, null);
    }


    /**
     * 加载图片
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016/5/9 17:36
     * <br> UpdateTime: 2016/5/9 17:36
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param context
     *         上下文
     * @param imgPath
     *         路径
     * @param imageView
     *         控件ImageView
     * @param resDefaultId
     *         占位图 默认图片 一般可以设置成一个加载中的进度GIF图
     */
    public static void loadImage(Context context, Object imgPath, ImageView imageView, int resDefaultId, int resErrorId, Transformation transformation) {

        //ImageView的scaleType设置不当，导致使用Glide时出现OOM，不能使用fitXY
        if (!Util.isOnMainThread()) {
            return;
        }
        //
        //        //TODO  方式1
        //        Picasso.Builder builder = new Picasso.Builder(context);
        //        //配置下载器
        //        builder.downloader(new Downloader() {
        //            @Override
        //            public Response load(Uri uri, int i) throws IOException {
        //                return null;
        //            }
        //
        //            @Override
        //            public void shutdown() {
        //
        //            }
        //        });
        //
        //        //配置线程池
        //        ExecutorService executorService = Executors.newFixedThreadPool(8);
        //        builder.executor(executorService);
        //
        //        //配置缓存
        //        LruCache cache = new LruCache(5 * 1024 * 1024);// 设置缓存大小
        //        builder.memoryCache(cache);
        //
        //        //构造一个Picasso
        //        Picasso picasso = builder.build();
        //
        //        //        //加载图片
        //        //        picasso.load(imgPath).into(imageView);
        //        RequestCreator requestCreator = picasso.load(imgPath);
        //        if (transformation != null) {
        //            requestCreator.transform(transformation);
        //        }
        //        requestCreator.into(imageView);
        //
        //
        //                // 设置全局单列instance
        //                Picasso.setSingletonInstance(picasso);
        //                然后应用这些自定义配置加载图片
        //                Picasso.with(context).load(imgPath).into(imageView);

        //TODO  方式2
        Picasso.with(context).setIndicatorsEnabled(BuildConfig.DEBUG);//显示指示器 它会在图片的左上角出现一个带色块的三角形标示，有3种颜色，绿色表示从内存加载、蓝色表示从磁盘加载、红色表示从网络加载
        Picasso.with(context).setLoggingEnabled(BuildConfig.DEBUG);//开启日志打印

        RequestCreator requestCreator;
        if (imgPath instanceof Integer) {
            int imgPathStr = (Integer) imgPath;
            requestCreator = Picasso.with(context).load(imgPathStr);//
        } else {
            String imgPathStr = String.valueOf(imgPath);
            if (imgPathStr.contains("storage/emulated")) {
                requestCreator = Picasso.with(context).load(new File(imgPathStr));//
            } else {
                requestCreator = Picasso.with(context).load(imgPathStr);//
            }
        }

        requestCreator.error(resErrorId)
                .placeholder(resDefaultId)// 占位图 默认图片 一般可以设置成一个加载中的进度GIF图
                //                .noPlaceholder()// 不要占位图
                //                .noFade()// 渐变切换
                //                .resize(400, 200)// .resizeDimen(R.dimen.image_width,R.dimen.image_height) 设置图片尺寸(Resize)、缩放(Scale)和裁剪(Crop)
                //                .onlyScaleDown()//当调用了resize 方法重新设置图片尺寸的时候，调用onlyScaleDown 方法，只有当原始图片的尺寸大于我们指定的尺寸时，resize才起作用
                .fit()// 单独调用fit 图片会拉伸 it & centerCrop (不会拉伸): fit 它会自动测量我们的View的大小，然后内部调用reszie方法把图片裁剪到View的大小，这就帮我们做了计算size和调用resize 这2步
                .centerCrop()// 比例类型 充满ImageView 的边界，居中裁剪
                //                .centerInside() //上面的centerCrop是可能看不到全部图片的，如果你想让View将图片展示完全，可以用centerInside，但是如果图片尺寸小于View尺寸的话，是不能充满View边界的。
                //                .rotate(180)//图片旋转Rotation()
                //                .transform(new CropTransformation(CropTransformation.CropType.CIRCLE))/比如你要做图片高斯模糊、添加圆角、做度灰处理、圆形图片等等都可以通过Transformation来完成。
                //                .priority(Picasso.Priority.HIGH)//Picasso 为请求设置有优先级，有三种优先级，LOW、NORMAL、HIGH
                //                .tag("PhotoTag")//  cancelTag(Object tag) 取消设置了给定tag的所有请求        pauseTag(Object tag) 暂停设置了给定tag 的所有请求        resumeTag(Object tag) resume 被暂停的给定tag的所有请求
                //                .memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE) //NO_CACHE：表示处理请求的时候跳过检查内存缓存    **NO_STORE: ** 表示请求成功之后，不将最终的结果存到内存。
                //                .networkPolicy(NetworkPolicy.NO_CACHE)//NO_CACHE跳过磁盘缓存;OFFLINE如果networkPolicy方法用的是这个参数，那么Picasso会强制这次请求从缓存中获取结果，不会发起网络请求，不管缓存中能否获取到结果
                .tag(imgPath);

        if (transformation != null) {
            requestCreator.transform(transformation);
        }
        requestCreator.into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                //加载成功
                //LogUtil.i("图片加载成功");
            }

            @Override
            public void onError() {
                LogUtil.i("图片加载失败");
                //加载失败
            }
        });

        //        8. 同步加载图片
        //        Bitmap bitmap =  Picasso.with(context).load(imgPath).get();
        //        9. 异步加载图片
        // // fetch()方法将会在后台异步加载图片，但是它既不会在ImageView中显示出来，也不会返回bitmap。它只是会将图片下载下来。目的是为了你之后用这个图片时减少加载时间，当然，后面的Callback可以不传
        //        Picasso.with(context).load(imgPath).fetch(new Callback() {
        //        @Override
        //        public void onSuccess() {
        //            //加载成功
        //            LogUtil.i("图片加载成功");
        //        }
        //
        //        @Override
        //        public void onError() {
        //            LogUtil.i("图片加载失败");
        //            //加载失败
        //        }
        //        });
        //        10.下载图片并且给一个带图片的回调：
        //        如果一个view实现了target接口，那么view的生命周期就会被target影响，造成内存泄漏。
        //        比如：在图片加载期间，View可能已经离开了屏幕，将要被回收；或者Activity将要被销毁。
        //        但是由于picasso还没有加载完成，持有着view的引用，而view又持有Activity的引用，造成View和Activity都无法被回收。
        //        Picasso.with(context).load(imgPath).into(new Target() {
        //            @Override
        //            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        //                LogUtil.e("加载成功");
        //            }
        //
        //            @Override
        //            public void onBitmapFailed(Drawable errorDrawable) {
        //                LogUtil.e("图片加载失败");
        //            }
        //
        //            @Override
        //            public void onPrepareLoad(Drawable placeHolderDrawable) {
        //                LogUtil.e("开始加载");
        //            }
        //        });

        //        Picasso.with(this).resumeTag("tag");//  使用pauseTag()pause 请求
        //        Picasso.with(this).pauseTag("tag");//  使用resumeTag() resume请求
        //        Picasso.with(this).cancelTag("tag");// 使用cancelTag() cancel请求
    }


}
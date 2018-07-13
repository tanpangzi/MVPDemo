package com.tan.mvpdemo.common.http;

import android.os.Looper;
import android.os.Message;

/**
 * Created by tanjun on 2017/5/31.
 * 下载进度
 */
public abstract class DownloadProgressHandler extends ProgressHandler{

    private static final int DOWNLOAD_PROGRESS = 1;
    protected ResponseHandler mHandler = new ResponseHandler(this, Looper.getMainLooper());

    @Override
    protected void sendMessage(ProgressBean progressBean) {
        if(mHandler!=null) {
            mHandler.obtainMessage(DOWNLOAD_PROGRESS, progressBean).sendToTarget();
        }

    }

    @Override
    protected void handleMessage(Message message){
        switch (message.what){
            case DOWNLOAD_PROGRESS:
                ProgressBean progressBean = (ProgressBean)message.obj;
                onProgress(progressBean.getBytesRead(),progressBean.getContentLength(),progressBean.isDone());
        }
    }
}
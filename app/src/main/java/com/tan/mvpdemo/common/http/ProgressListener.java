package com.tan.mvpdemo.common.http;

/**
 * Created by tanjun on 2017/5/31.
 */

public interface ProgressListener {
    /**
     * @param progress     已经下载或上传字节数
     * @param total        总字节数
     * @param done         是否完成
     */
    void onProgress(long progress, long total, boolean done);
}

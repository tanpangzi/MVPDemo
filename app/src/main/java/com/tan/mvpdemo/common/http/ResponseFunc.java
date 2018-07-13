package com.tan.mvpdemo.common.http;


import android.content.Intent;

import com.orhanobut.logger.Logger;
import com.tan.mvpdemo.BaseApplication;
import com.tan.mvpdemo.broadcast.BroadCastFilter;
import com.tan.mvpdemo.uitl.Constant;

import rx.functions.Func1;

/**
 * Created by tanjun on 2018/5/31.
 * 从服务器返回的数据（非list）
 */

public class ResponseFunc<T> implements Func1<Result<T>, T> {
    private static final String TAG = "ResponseFunc";

    @Override
    public T call(Result<T> tResult) {
        if (tResult.getCode() == 506){ //先写成这样 不然没有imei用
            return tResult.getData();
        }

        if (tResult.getCode() != 0) {
            /** 如果没有返回成功 */
            Logger.e(Constant.LOG_TAG + tResult.getCode());
            /** 被抢登 */
            if(tResult.getCode() == 101){
                Constant.setToken(null);
                Intent intent = new Intent();
                intent.setAction(BroadCastFilter.ACTION_TONKEN_EXPIRED);
                BaseApplication.getInstance().sendBroadcast(intent);
            }
            throw new ApiException(tResult.getMsg());
        }
        return tResult.getData();
    }


}

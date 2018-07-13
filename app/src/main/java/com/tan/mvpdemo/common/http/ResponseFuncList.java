package com.tan.mvpdemo.common.http;


import android.content.Intent;

import com.orhanobut.logger.Logger;
import com.tan.mvpdemo.BaseApplication;
import com.tan.mvpdemo.broadcast.BroadCastFilter;
import com.tan.mvpdemo.config.ConstantKey;
import com.tan.mvpdemo.uitl.Constant;

import java.util.List;

import rx.functions.Func1;

/**
 * Created by tajun on 2018/5/31.
 * 从服务器返回的list数据
 */

public class ResponseFuncList<T> implements Func1<ResultList<T>, List<T>> {
    @Override
    public List<T> call(ResultList<T> listResultT) {
        Logger.e("ResponseFuncList ===> " +listResultT.toString());
        if(listResultT.getCode()!=0){

            if(listResultT.getCode() == ConstantKey.RESPONSE_STATUS_TOKEN_ERROR){
                /**
                 * token过期
                 */
                Constant.setToken(null);
                Intent intent = new Intent();
                intent.setAction(BroadCastFilter.ACTION_TONKEN_EXPIRED);
                BaseApplication.getInstance().sendBroadcast(intent);
            }
            throw new ApiException(listResultT.getMsg());
        }
        return listResultT.getData();
    }

}

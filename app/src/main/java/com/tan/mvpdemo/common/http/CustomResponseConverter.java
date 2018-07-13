package com.tan.mvpdemo.common.http;

import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.orhanobut.logger.Logger;
import com.tan.mvpdemo.BaseApplication;
import com.tan.mvpdemo.broadcast.BroadCastFilter;
import com.tan.mvpdemo.config.ConstantKey;
import com.tan.mvpdemo.uitl.Constant;
import com.tan.mvpdemo.uitl.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by tanjun on 2017/5/31.
 * 将请求到的结果返回并打印
 */

public final class CustomResponseConverter<T> implements Converter<ResponseBody, T> {
    /**
     * Gson
     */
    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private Type type;

    CustomResponseConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    CustomResponseConverter(Gson gson, TypeAdapter<T> adapter, Type type) {
        this.gson = gson;
        this.adapter = adapter;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        Result result = new Result();
        JSONObject json = null;
        try {
            json = new JSONObject(response);
            /**
             * 打印返回的所有数据并格式代
             */
            LogUtil.json(response);
            //Result res = gson.fromJson(response, Result.class);
            int code = json.optInt("code");
            String msg = json.optString("msg");
            Object array = json.optJSONObject("data");
            /** 请求成功 */
            if (code == 0) {
                /**
                 * 打印返回内容的data并格式化
                 */
                //LogUtil.json(json.optString("data"));
                return gson.fromJson(response, type);
            } else if (code == ConstantKey.RESPONSE_STATUS_TOKEN_ERROR) {
                /** token过期 */
                result.setCode(code);
                result.setMsg(json.optString("msg"));
                Constant.setToken(null);
                Intent intent = new Intent();
                intent.setAction(BroadCastFilter.ACTION_TONKEN_EXPIRED);
                BaseApplication.getInstance().sendBroadcast(intent);
                return (T) result;

            } else {
                /** 请求不成功 */
                result.setCode(code);
                result.setMsg(json.optString("msg"));
                return (T) result;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            value.close();
        }
    }
}




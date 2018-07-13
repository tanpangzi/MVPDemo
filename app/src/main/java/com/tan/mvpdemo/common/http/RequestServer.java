package com.tan.mvpdemo.common.http;


import android.os.Build;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;
import com.tan.mvpdemo.uitl.Constant;
import com.tan.mvpdemo.uitl.Tools;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * created by tanjun
 * retrofit 请求服务
 */

public class RequestServer {

    /**
     * TAG
     */
    private static final String TAG = "RequestServer";
    /**
     * Host地址
     */
    private static String BASE_URL = Constant.BASE_URL;
    /**
     * 超时时长
     */
    private static final int DEFAULT_TIMEOUT = 20;

    private static Gson createGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
    }

    /**
     * 获取OkHttpClient
     * @return
     */
    private static OkHttpClient.Builder getOkHttpClient() {
        /** 日志打印 */
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        //设置超时时间
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //使用拦截器
        httpClientBuilder.addInterceptor(new RequestInterceptor());
        httpClientBuilder.addInterceptor(loggingInterceptor);
        httpClientBuilder.sslSocketFactory(createSSLSocketFactory());
        httpClientBuilder.hostnameVerifier(new TrustAllHostnameVerifier());
        return httpClientBuilder;
    }

    /**
     * 信任所有证书
     */
    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }


    /**
     * 默认信任所有的证书
     * TODO 最好加上证书认证，主流App都有自己的证书
     * created by tanjun
     * @return
     */
    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory sslSocketFactory = null;

        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new MyTrustAllManager()}, new SecureRandom());
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sslSocketFactory;
    }

    /**
     * 证书信任
     */
    private static class MyTrustAllManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }


    /**
     * 日志管理
     */
    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            //打印retrofit日志
            Logger.i(TAG + message);
        }
    });

    /**
     * 请求头 请求时上传
     */
    private static class RequestInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {

            Request request = chain.request()
                    .newBuilder()
                    .addHeader(Constant.TOKEN_MAP_KEY, Constant.getToken())
                    .addHeader("tenantId", "0000100001")
                    .addHeader("platform", Constant.PLATFORM)
                    .addHeader("osVersion", String.valueOf(android.os.Build.VERSION.SDK_INT))
                    .addHeader("model", android.os.Build.MODEL)
                    .addHeader("appVersion", Tools.getVersionName())
                    .addHeader("channel", Constant.CHANNEL)
                    .addHeader("ABI", Build.CPU_ABI)
                    .build();
            Logger.e("request = " + request.headers().toString());
            return chain.proceed(request);
        }
    }

    /**
     * 新建retrofit
     * @return
     */
    public static RequestBiz createRetrofit() {
        return createRetrofit(getOkHttpClient().build());
    }

    public static RequestBiz creatRetrofitProgress() {
        return createRetrofit(ProgressHelper.addProgress(getOkHttpClient()).build());
    }

    /**
     * 创建retrofit
     *
     * @param client
     * @return
     */
    private static RequestBiz createRetrofit(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(CustomGsonConverterFactory.create(createGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(RequestBiz.class);
    }


    public static void printResponseBody(Call<ResponseBody> call) {
        try {
            Response<ResponseBody> response = call.execute();
            if (response.isSuccessful()) {
                Logger.i("OK =======> " + response.body().string());
            } else {
                Logger.e("HttpCode:" + response.code());
                Logger.e("Message:" + response.message());
                Logger.e("errorBody:" + response.errorBody().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void enqueue(Call<ResponseBody> call) {
        Call<ResponseBody> call2 = call.clone();
        call2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        Logger.e("HttpCode:" + response.code());
                        Logger.e("Message:" + response.message());
                        String body = response.body().string();
                        Logger.i("OK =======> " + body);
                        Result result = createGson().fromJson(body, Result.class);
                        Logger.i("result =======> " + result.toString());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                } else {
                    Logger.e("HttpCode:" + response.code());
                    Logger.e("Message:" + response.message());
                    Logger.e("errorBody:" + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}

package com.antcloud.network;

import android.content.Context;

import com.antcloud.interceptor.LoggingInterceptor;
import com.antcloud.operate.AntCloudSubscription;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author liyuan
 * @description 1.init 实例化retrofit 2.向外开放动态注册service
 * @email thisuper@qq.com
 * @date 17/6/23 上午10:37
 */

public class AntCloud {

    private Retrofit mRetrofit;
    private static final int DEFAULT_TIMEOUT = 30;
    private OkHttpClient.Builder mHttpClientBuilder;
    private int timeout = 0;
    private AntCloudSubscription mManager;

    /**
     * @param context
     * @param baseUrl
     * @return
     */
    public AntCloud init(Context context, String baseUrl) {
        mManager = new AntCloudSubscription();
        mHttpClientBuilder = new OkHttpClient.Builder();
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));
        mHttpClientBuilder.connectTimeout(timeout == 0 ? DEFAULT_TIMEOUT : timeout, TimeUnit.SECONDS);
        mHttpClientBuilder.cookieJar(cookieJar);//添加cookie 持久化管理
        addInterceptor(new LoggingInterceptor());//添加日志打印
        mRetrofit = new Retrofit.Builder()
                .client(mHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        return this;
    }

    /**
     * @param context
     * @param baseUrl 基础url
     * @param timeout 超时时间
     * @return
     */
    public AntCloud init(Context context, String baseUrl, int timeout) {
        this.timeout = timeout;
        init(context, baseUrl);
        return this;
    }

    /**
     * @param serviceClass
     * @param <T>
     * @return
     */
    public <T> T createService(Class<T> serviceClass) {
        return mRetrofit.create(serviceClass);
    }

    /**
     * @param interceptor
     */
    public void addInterceptor(Interceptor interceptor) {
        if (mHttpClientBuilder == null) {
            throw new NullPointerException("You need init AntCloud");
        }
        mHttpClientBuilder.addInterceptor(interceptor);
    }

    /**
     * @param interceptor
     */
    public void addNetwozrkInterceptor(Interceptor interceptor) {
        if (mHttpClientBuilder == null) {
            throw new NullPointerException("You need init AntCloud");
        }
        mHttpClientBuilder.addNetworkInterceptor(interceptor);
    }

    public AntCloudSubscription getSubcription() {
        return mManager == null ? new AntCloudSubscription() : mManager;
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final AntCloud INSTANCE = new AntCloud();
    }

    //获取单例
    public static AntCloud getInstance() {
        return SingletonHolder.INSTANCE;
    }
}

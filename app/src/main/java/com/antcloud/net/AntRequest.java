package com.antcloud.net;

import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.json.JSONException;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author liyuan
 * @description 请求类
 * @email thisuper@qq.com
 * @date 16/11/3 下午7:39
 */

public class AntRequest {
    private Retrofit retrofit;


    private AntRequest() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static class SingletonHolder {
        private static final AntRequest INSTANCE = new AntRequest();
    }

    public static AntRequest getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public <T> T getApiService(Class<T> tClass) {
        return retrofit.create(tClass);
    }

    /**
     * 发出网络请求,如果没有网络的情况会抛出异常
     *
     * @param call
     */
    public   <T> Subscriber<HTTPResult<T>> execute(Flowable<HTTPResult<T>> call, IResponse response) {

        return call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Subscriber<HTTPResult<T>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(HTTPResult<T> thttpResult) {
                        response.onResponse(getResult(thttpResult));
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (t instanceof SocketTimeoutException
                                || t instanceof InterruptedIOException) {
                            response.onResponse(getTimeout(t));
                        } else if (t instanceof UnknownHostException
                                || t instanceof HttpException
                                || t instanceof ConnectException) {
                            response.onResponse(getNoNet(t));
                        } else if (t instanceof JSONException
                                || t instanceof JsonParseException
                                || t instanceof ParseException) {
                            response.onResponse(getParseError(t));
                        } else {
                            response.onResponse(getError(t));
                        }

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private <T> Flowable<HTTPResult<T>> getResult(HTTPResult<T> result) {
        return new Flowable<HTTPResult<T>>() {
            @Override
            protected void subscribeActual(Subscriber<? super HTTPResult<T>> s) {
                s.onNext(result);
            }
        };
    }

    private <T> Flowable<HTTPResult<T>> getNoNet(Throwable throwable) {
        return new Flowable<HTTPResult<T>>() {
            @Override
            protected void subscribeActual(Subscriber<? super HTTPResult<T>> s) {
                HTTPResult result = new HTTPResult();
                result.setTitle("网络没有了:" + throwable.getMessage());
                s.onNext(result);
            }
        };
    }

    private <T> Flowable<HTTPResult<T>> getError(Throwable throwable) {
        return new Flowable<HTTPResult<T>>() {
            @Override
            protected void subscribeActual(Subscriber<? super HTTPResult<T>> s) {
                HTTPResult result = new HTTPResult();
                result.setTitle("其他错误:" + throwable.getMessage());
                s.onNext(result);
            }
        };
    }

    private <T> Flowable<HTTPResult<T>> getTimeout(Throwable throwable) {
        return new Flowable<HTTPResult<T>>() {
            @Override
            protected void subscribeActual(Subscriber<? super HTTPResult<T>> s) {
                HTTPResult result = new HTTPResult();
                result.setTitle("链接超时:" + throwable.getMessage());
                s.onNext(result);
            }
        };
    }

    private <T> Flowable<HTTPResult<T>> getParseError(Throwable throwable) {
        return new Flowable<HTTPResult<T>>() {
            @Override
            protected void subscribeActual(Subscriber<? super HTTPResult<T>> s) {
                HTTPResult result = new HTTPResult();
                result.setTitle("解析错误:" + throwable.getMessage());
                s.onNext(result);
            }
        };
    }

}

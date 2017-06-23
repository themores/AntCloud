package com.antcloud.network;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author liyuan
 * @description
 * @email thisuper@qq.com
 * @date 17/6/23 下午3:33
 */

public class AntSchedulers {

    /**
     * subscribeOn Schedulers.io()
     * <br/>
     * observeOn AndroidSchedulers.mainThread()
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> defaultSchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}

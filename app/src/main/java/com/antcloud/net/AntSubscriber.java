package com.antcloud.net;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @author liyuan
 * @description
 * @email thisuper@qq.com
 * @date 17/4/20 下午7:54
 */

public class AntSubscriber<T extends HTTPResult> implements Subscriber<T> {
    @Override
    public void onSubscribe(Subscription s) {

    }

    @Override
    public void onNext(HTTPResult httpResult) {

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}

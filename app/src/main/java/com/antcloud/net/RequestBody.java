package com.antcloud.net;

import org.reactivestreams.Subscription;


/**
 * @author liyuan
 * @description 请求封装
 * @email thisuper@qq.com
 * @date 16/11/15 下午7:42
 */

public class RequestBody {

    private IResponse callBack;
    private Subscription subscription;

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public IResponse getCallBack() {
        return callBack;
    }

    public void setCallBack(IResponse callBack) {
        this.callBack = callBack;
    }

}

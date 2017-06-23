package com.antcloud.operate;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author liyuan
 * @description
 * @email thisuper@qq.com
 * @date 17/6/23 下午6:41
 */

public class AntCloudSubscription {
    private CompositeSubscription mSubscription;

    public AntCloudSubscription() {
        this.mSubscription = new CompositeSubscription();
    }

    public void addSubscription(Subscription subscription) {
        mSubscription.add(subscription);
    }

    public void unSubscribe() {
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

}

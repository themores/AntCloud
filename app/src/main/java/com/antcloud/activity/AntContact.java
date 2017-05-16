package com.antcloud.activity;

import com.antcloud.net.IResponse;

import io.reactivex.disposables.Disposable;

/**
 * @author liyuan
 * @description
 * @email thisuper@qq.com
 * @date 17/4/20 下午12:07
 */

public class AntContact {
    interface IAntView {
        /**
         * 提供外部view 进行 取消订阅操作
         *
         * @param subscription
         */
        void setDisposable(Disposable subscription);
    }

    interface IAntPresenter extends IResponse {
    }
}

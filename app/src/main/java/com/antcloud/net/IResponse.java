package com.antcloud.net;


import io.reactivex.Flowable;

/**
 * @author liyuan
 * @description 网络请求回调接口
 * @email thisuper@qq.com
 * @date 16/11/3 下午6:52
 */

public interface IResponse {

    /**
     * @param resultFlowable
     * @param <T>
     */
    <T> void onResponse(Flowable<HTTPResult<T>> resultFlowable);

}

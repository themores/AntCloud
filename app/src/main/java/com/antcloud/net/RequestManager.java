package com.antcloud.net;


import io.reactivex.Flowable;

/**
 * @author liyuan
 * @description
 * @email thisuper@qq.com
 * @date 16/12/1 下午12:14
 */

public class RequestManager {
    private static RequestManager requestManager = new RequestManager();

    private RequestManager() {
    }

    public static RequestManager getInstance() {
        return requestManager;
    }

    private Object call;
    private RequestBody requestBody;

    public <T> Flowable<HTTPResult<T>> getCall() {
        return (Flowable<HTTPResult<T>>) call;
    }

    public <T> RequestManager setCall(Flowable<HTTPResult<T>> call) {
        this.call = call;
        return requestManager;
    }

    public RequestBody getRequestBody() {
        return requestBody;
    }

    public RequestManager setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
        return requestManager;
    }

    public AntRequest getRequest() {
        return AntRequest.getInstance();
    }
}

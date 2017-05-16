package com.antcloud.activity;

import com.antcloud.net.HTTPResult;
import com.antcloud.net.RequestBody;

import io.reactivex.Flowable;

/**
 * @author liyuan
 * @description
 * @email thisuper@qq.com
 * @date 17/4/19 下午6:38
 */

public class AntPresenter implements AntContact.IAntPresenter {
    AntView mAntView;


    private RequestBody mBody;

    public AntPresenter(AntView antView) {
        this.mAntView = antView;
        initRequestBody();
    }

    private RequestBody initRequestBody() {
        mBody = new RequestBody();
        mBody.setCallBack(this);
        return mBody;
    }

    public RequestBody getBody() {
        return mBody;
    }

    @Override
    public <T> void onResponse(Flowable<HTTPResult<T>> resultFlowable) {

    }

}

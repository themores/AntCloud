package com.antcloud.activity;

import android.util.Log;

import com.antcloud.net.AntRequest;
import com.antcloud.net.HTTPResult;
import com.antcloud.net.IResponse;
import com.google.gson.Gson;

import io.reactivex.Flowable;

/**
 * @author liyuan
 * @description
 * @email thisuper@qq.com
 * @date 17/4/19 下午5:36
 */

public class AntSubPresenter extends AntPresenter {


    public AntSubPresenter(AntView antView) {
        super(antView);
    }

    public void getData() {
        AntRequest.getInstance().execute(AntRequest.getInstance().getApiService(ApiService.class).getTopMovie(0, 10), new IResponse() {
            @Override
            public <T> void onResponse(Flowable<HTTPResult<T>> resultFlowable) {
                mAntView.setDisposable(resultFlowable.subscribe(result -> {
                    Log.e("tag", new Gson().toJson(result));
                    mAntView.showView();
                }));

            }
        });
    }
}

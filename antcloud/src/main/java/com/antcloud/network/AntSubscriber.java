package com.antcloud.network;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.HttpException;
import rx.Subscriber;

/**
 * @author liyuan
 * @description
 * @email thisuper@qq.com
 * @date 17/6/23 下午3:27
 */

public abstract class AntSubscriber<T> extends Subscriber<T> {
    private Context mContext;
    private boolean mIsOpenToast; //控制toast 是否显示

    public AntSubscriber() {
    }

    public AntSubscriber(Context context) {
        this.mContext = context;
    }

    public AntSubscriber(Context context, boolean mIsOpenToast) {
        this.mContext = context;
        this.mIsOpenToast = mIsOpenToast;
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable t) {
        if (t instanceof SocketTimeoutException
                || t instanceof InterruptedIOException) {
            error(AntError.TIMEOUT_ERROR_CODE, "链接超时", t);
        } else if (t instanceof UnknownHostException
                || t instanceof HttpException
                || t instanceof ConnectException) {
            error(AntError.NETWORK_ERROR_CODE, "网络异常", t);
        } else if (t instanceof JSONException
                || t instanceof JsonParseException
                || t instanceof ParseException) {
            error(AntError.PARSE_ERROR_CODE, "解析异常", t);
        } else {
            error(AntError.OTHER_ERROR_CODE, "其他异常", t);
        }
    }

    @Override
    public void onNext(T t) {
        call(t);
    }


    /**
     * @param errorCode 错误code;
     * @param des       错误描述
     * @param throwable 异常
     */
    private void error(int errorCode, String des, Throwable throwable) {
        if (mIsOpenToast) {
            Toast.makeText(mContext, des, Toast.LENGTH_LONG).show();
        }
        error(new AntError(throwable, des, errorCode));
    }

    public void error(AntError error) {

    }

    public abstract void call(T t);


}

package com.antcloud.activity;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import io.reactivex.disposables.Disposable;

/**
 * @author liyuan
 * @description
 * @email thisuper@qq.com
 * @date 17/4/20 下午12:06
 */

public class AntView extends Activity implements AntContact.IAntView {
    private Disposable mDisposable;


    @Override
    public void setDisposable(Disposable mDisposable) {
        this.mDisposable = mDisposable;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("===>>>>onDestroy", "YES");
        if (mDisposable != null)
            mDisposable.dispose();
    }
    public void  showView(){
    }
}

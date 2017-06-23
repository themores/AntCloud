package com.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.antcloud.network.AntCloud;
import com.antcloud.network.AntError;
import com.antcloud.network.AntSchedulers;
import com.antcloud.network.AntSubscriber;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApiService apiService = AntCloud.getInstance().init(this, "https://api.douban.com/v2/movie/").createService(ApiService.class);
        OtherService other = AntCloud.getInstance().init(this, "https://api.douban.com/v2/movie/").createService(OtherService.class);
        apiService.getTopMovie(0, 10).compose(AntSchedulers.<HttpResult<List<Subject>>>defaultSchedulers())
                .subscribe(new AntSubscriber<HttpResult<List<Subject>>>(this, true) {
                    @Override
                    public void call(HttpResult<List<Subject>> listHttpResult) {

                    }

                    @Override
                    public void error(AntError error) {
                        Log.e("error", new Gson().toJson(error));
                    }
                });
        AntCloud.getInstance().getSubcription().addSubscription(other.getTopMovie(0, 5).compose(AntSchedulers.<HttpResult<List<Subject>>>defaultSchedulers())
                .subscribe(new AntSubscriber<HttpResult<List<Subject>>>(this, true) {
                    @Override
                    public void call(HttpResult<List<Subject>> listHttpResult) {

                    }

                    @Override
                    public void error(AntError error) {
                        Log.e("error", new Gson().toJson(error));
                    }
                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AntCloud.getInstance().getSubcription().unSubscribe();
    }
}

package com.antcloud.activity;


import com.antcloud.net.HTTPResult;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author liyuan
 * @description retrofit 中使用的service
 * @email thisuper@qq.com
 * @date 16/11/3 下午8:30
 */

public interface ApiService {
    @GET("top250")
    Flowable<HTTPResult<List<TestBean>>> getTopMovie(@Query("start") int start, @Query("count") int count);
}

package com.example.day05;

import com.example.day05.bean.FuliBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface Apiservice {
    String FuliURL="http://gank.io/";
    @GET("api/data/福利/20/1")
    Observable<ResponseBody>getFuli();
}

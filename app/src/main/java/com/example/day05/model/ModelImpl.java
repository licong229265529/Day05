package com.example.day05.model;

import com.example.day05.Apiservice;
import com.example.day05.bean.FuliBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.parallel.ParallelRunOn;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModelImpl implements IModel {
    @Override
    public void getFuLI(final CallBack callBack) {
        new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Apiservice.FuliURL)
                .build()
                .create(Apiservice.class)
                .getFuli()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String result=responseBody.string();
                            FuliBean fuliBean = new Gson().fromJson(result, FuliBean.class);
                            List<FuliBean.ResultsBean> results = fuliBean.getResults();
                            callBack.updateSucces(results);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.updateError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

package com.example.day_02_git.mvp;

import android.annotation.SuppressLint;

import com.example.day_02_git.bean.InfoDataBean;
import com.example.day_02_git.util.ApiService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model implements MvpInterface.IModel {
    @SuppressLint("CheckResult")
    @Override
    public void updateInfoDate(final MvpInterface.CallBack callBack) {
        new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiService.url)
                .build()
                .create(ApiService.class)
                .getListData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<InfoDataBean>() {
                    @Override
                    public void onNext(InfoDataBean infoDataBean) {
                        callBack.onSucceed(infoDataBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        callBack.onFiled(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

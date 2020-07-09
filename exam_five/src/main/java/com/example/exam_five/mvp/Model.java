package com.example.exam_five.mvp;

import com.example.exam_five.bean.InfoListBean;
import com.example.exam_five.util.ApiService;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model implements MvpInterface.IModel {
    @Override
    public void updateListData(final MvpInterface.CallBack callBack) {
        new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.listUrl)
                .build()
                .create(ApiService.class)
                .getDataData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Subscriber<InfoListBean>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(InfoListBean infoListBean) {
                        callBack.onSucceed(infoListBean);
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

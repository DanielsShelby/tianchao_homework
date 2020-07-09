package com.example.exam_two.mvp;

import com.example.exam_two.bean.InfoListBean;
import com.example.exam_two.util.ApiServices;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model implements MvpInterface.IModel {
    @Override
    public void updateInfoDate(final MvpInterface.CallBack callBack) {
        new  Retrofit.Builder()
                .baseUrl(ApiServices.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiServices.class)
                .getListData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<InfoListBean>() {
                    @Override
                    public void onNext(InfoListBean infoDataBean) {
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

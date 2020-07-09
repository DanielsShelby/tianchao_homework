package com.example.exam_two.util;

import com.example.exam_two.bean.InfoListBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ApiServices {
    String url = "https://www.wanandroid.com/project/";

    @GET("list/1/json?cid=294")
    Flowable<InfoListBean> getListData();
}

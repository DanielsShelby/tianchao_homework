package com.example.exam_five.util;

import com.example.exam_five.bean.InfoListBean;
import com.example.exam_five.bean.InfoProjectBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;


public interface ApiService {
    //项目类别
    String listUrl = "https://www.wanandroid.com/project/";

    @GET("tree/json")
    Flowable<InfoListBean> getDataData();

    //项目
    String tabUrl = "https://www.wanandroid.com/project/list/";
    @GET("1/json?cid=294")
    Flowable<InfoProjectBean> getProjectData();
}

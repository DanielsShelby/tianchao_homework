package com.example.day_02_git.util;

import com.example.day_02_git.bean.InfoDataBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ApiService {
    String url = "https://www.wanandroid.com/project/list/";

    @GET("1/json?cid=294")
    Flowable<InfoDataBean> getListData();

    String dlFile = "http://yun918.cn/study/public/res/UnknowApp-1.0.apk";
}

package com.example.exam_five.mvp;

import com.example.exam_five.bean.InfoListBean;

public interface MvpInterface {
    interface CallBack {
        void  onSucceed(InfoListBean infoListBean);
        void onFiled(String error);
    }

    interface IModel {
        void updateListData(CallBack callBack);
    }

    interface IPresenter {
        void getListData();
    }

    interface IView {
        void  onSucceed(InfoListBean infoListBean);
        void onFiled(String error);
    }
}

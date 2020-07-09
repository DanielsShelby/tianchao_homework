package com.example.exam_two.mvp;

import com.example.exam_two.bean.InfoListBean;

public interface MvpInterface {
    interface IView {
        void onSucceed(InfoListBean infoDataBean);

        void onFiled(String error);
    }

    interface IPresenter {
        void getInfoData();
    }

    interface IModel {
        void updateInfoDate(CallBack callBack);
    }

    interface CallBack {
        void onSucceed(InfoListBean infoDataBean);

        void onFiled(String error);
    }

}

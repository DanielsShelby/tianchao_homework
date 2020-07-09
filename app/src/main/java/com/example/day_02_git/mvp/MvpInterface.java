package com.example.day_02_git.mvp;

import com.example.day_02_git.bean.InfoDataBean;

public interface MvpInterface {
    interface IView {
        void onSucceed(InfoDataBean infoDataBean);

        void onFiled(String error);
    }

    interface IModel {
        void updateInfoDate(CallBack callBack);
    }

    interface IPresenter {
        void getInfoData();
    }

    interface CallBack {
        void onSucceed(InfoDataBean infoDataBean);

        void onFiled(String error);
    }
}

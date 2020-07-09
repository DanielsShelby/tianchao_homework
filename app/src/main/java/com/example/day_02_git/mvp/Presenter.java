package com.example.day_02_git.mvp;

import com.example.day_02_git.bean.InfoDataBean;

public class Presenter implements MvpInterface.IPresenter, MvpInterface.CallBack {
    Model mModel;
    MvpInterface.IView mIView;

    public Presenter(MvpInterface.IView IView) {
        mModel = new Model();
        mIView = IView;
    }

    @Override
    public void getInfoData() {
        mModel.updateInfoDate(this);
    }

    @Override
    public void onSucceed(InfoDataBean infoDataBean) {
        mIView.onSucceed(infoDataBean);
    }

    @Override
    public void onFiled(String error) {
        mIView.onFiled(error);
    }
}

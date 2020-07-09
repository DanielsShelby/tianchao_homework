package com.example.exam_two.mvp;

import com.example.exam_two.bean.InfoListBean;

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
    public void onSucceed(InfoListBean infoDataBean) {
        mIView.onSucceed(infoDataBean);
    }

    @Override
    public void onFiled(String error) {
        mIView.onFiled(error);
    }
}

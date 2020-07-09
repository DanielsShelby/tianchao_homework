package com.example.exam_five.mvp;

import com.example.exam_five.bean.InfoListBean;

public class Presenter implements MvpInterface.CallBack, MvpInterface.IPresenter {
    private MvpInterface.IModel mIModel;
    private MvpInterface.IView mIView;

    public Presenter(MvpInterface.IView IView) {
        mIModel = new Model();
        mIView = IView;
    }

    @Override
    public void onSucceed(InfoListBean infoListBean) {
        mIView.onSucceed(infoListBean);

    }

    @Override
    public void onFiled(String error) {
        mIView.onFiled(error);
    }

    @Override
    public void getListData() {
        mIModel.updateListData(this);
    }
}

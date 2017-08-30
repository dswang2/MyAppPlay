package com.dbstar.myappplay.presenter;

import com.dbstar.myappplay.ui.BaseView;

/**
 * Created by dswang on 2017/8/20.
 */
public abstract class BasePresenter<M,V extends BaseView> {

    protected M mModel;
    protected V mView;

    public BasePresenter(M model, V view) {
        mModel = model;
        mView = view;
    }

    protected abstract void requestDatas();
}

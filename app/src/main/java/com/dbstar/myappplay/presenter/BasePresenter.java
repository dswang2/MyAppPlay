package com.dbstar.myappplay.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.dbstar.myappplay.ui.BaseView;

/**
 * Created by dswang on 2017/8/20.
 */
public abstract class BasePresenter<M,V extends BaseView> {

    protected M mModel;
    protected V mView;
    protected Context mContext;

    public BasePresenter(M model, V view) {
        mModel = model;
        mView = view;
        initContext();
    }

    //protected abstract void requestDatas();

    private void initContext(){
        if(mView instanceof Fragment){
            mContext = ((Fragment)mView).getActivity();
        }
        else {
            mContext = (Activity) mView;
        }
    }
}

package com.dbstar.myappplay.common.rx.subscriber;

import android.content.Context;

import com.dbstar.myappplay.ui.BaseView;

/**
 * Created by wh on 2017/9/7.
 */

public abstract class ProgressErrorHandledSubscriber<T> extends ErrorHandlerSubscriber<T> {

    private BaseView mBaseView;

    public ProgressErrorHandledSubscriber(Context context, BaseView baseView) {
        super(context);
        mBaseView = baseView;
    }

    public boolean isShowProgressDialog() {
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(isShowProgressDialog()){
            mBaseView.showLoading();
        }
    }

    @Override
    public void onCompleted() {
        if (isShowProgressDialog()) {
            mBaseView.dismissLoading();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (isShowProgressDialog()) {
            mBaseView.dismissLoading();
        }
    }


}

package com.dbstar.myappplay.common.rx.subscriber;

import android.content.Context;

import com.dbstar.myappplay.common.util.ProgressDialogHandler;


/**
 * Created by wh on 2017/9/7.
 */

public abstract class ProgressErrorHandledSubscriber<T> extends ErrorHandlerSubscriber<T> implements ProgressDialogHandler.OnProgressCancelListener {


    private ProgressDialogHandler mProgressDialogHandler;
    protected Context mContext;

    public ProgressErrorHandledSubscriber(Context context) {
        super(context);
        mContext = context;
        mProgressDialogHandler = new ProgressDialogHandler(mContext, true, this);
    }

    public boolean isShowProgressDialog() {
        return true;
    }

    @Override
    public void onStart() {
        if (isShowProgressDialog()) {
            mProgressDialogHandler.showProgressDialog();
        }
    }

    @Override
    public void onCompleted() {
        if (isShowProgressDialog()) {
            mProgressDialogHandler.dismissProgressDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (isShowProgressDialog()) {
            mProgressDialogHandler.dismissProgressDialog();
        }
    }

    @Override
    public void onCancelProgress() {
        unsubscribe();
    }
}

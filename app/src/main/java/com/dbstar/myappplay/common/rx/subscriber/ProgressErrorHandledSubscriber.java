package com.dbstar.myappplay.common.rx.subscriber;

import android.content.Context;

import com.dbstar.myappplay.common.exception.BaseException;
import com.dbstar.myappplay.common.util.ProgressDialogHandler;
import com.dbstar.myappplay.ui.BaseView;


/**
 * Created by wh on 2017/9/7.
 */

public abstract class ProgressErrorHandledSubscriber<T> extends ErrorHandlerSubscriber<T> implements ProgressDialogHandler.OnProgressCancelListener {


    private ProgressDialogHandler mProgressDialogHandler;
    protected Context mContext;
    protected BaseView mBaseView;


    public ProgressErrorHandledSubscriber(Context context, BaseView mView) {
        super(context);
        mContext = context;
        mBaseView = mView;
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
        BaseException baseException =  mRxErrorHandler.handleError(e);
        mBaseView.showError(baseException.getDisplayMessage());
    }

    @Override
    public void onCancelProgress() {
        unsubscribe();
    }
}

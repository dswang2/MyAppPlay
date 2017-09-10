package com.dbstar.myappplay.common.rx.subscriber;

import android.content.Context;

import com.dbstar.myappplay.common.exception.BaseException;
import com.dbstar.myappplay.common.rx.RxErrorHandler;
import com.dbstar.myappplay.common.util.ProgressDialogHandler;
import com.dbstar.myappplay.ui.BaseView;


/**
 * Created by wh on 2017/9/7.
 */

public abstract class ProgressHandledSubscriber<T> extends ErrorHandlerSubscriber<T> implements ProgressDialogHandler.OnProgressCancelListener {


    protected Context mContext;
    protected BaseView mBaseView;

    public ProgressHandledSubscriber(Context context, BaseView mView) {
        super(context);
        mContext = context;
        this.mBaseView = mView;
    }

    public boolean isShowProgressDialog() {
        return true;
    }

    @Override
    public void onStart() {
        if (isShowProgressDialog()) {
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
        RxErrorHandler mRxErrorHandler = new RxErrorHandler(mContext);
        BaseException baseException = mRxErrorHandler.handleError(e);
        if(baseException==null){
            mBaseView.showError(e.toString());
        }
        else {
            mBaseView.showError(baseException.getDisplayMessage());
        }
    }


    @Override
    public void onCancelProgress() {
        unsubscribe();
    }
}

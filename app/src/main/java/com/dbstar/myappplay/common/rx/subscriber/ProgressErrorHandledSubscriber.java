package com.dbstar.myappplay.common.rx.subscriber;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by wh on 2017/9/7.
 */

public abstract class ProgressErrorHandledSubscriber<T> extends ErrorHandlerSubscriber<T> {

    protected Context mContext;
    private ProgressDialog mProgressDialog;

    public ProgressErrorHandledSubscriber(Context context) {
        super(context);
        mContext = context;
        initProgressDialog();
    }

    public boolean isShowProgressDialog() {
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(isShowProgressDialog()){
            mProgressDialog.show();
        }
    }

    @Override
    public void onCompleted() {
        if (mProgressDialog.isShowing()) {
            dismissProgressDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (mProgressDialog.isShowing()) {
            dismissProgressDialog();
        }
    }

    public void initProgressDialog() {
        // 创建dialog，需要 activity
        mProgressDialog = new ProgressDialog(mContext);
    }

    public void showProgressDialog() {
        mProgressDialog.show();
    }

    public void dismissProgressDialog() {
        mProgressDialog.dismiss();
    }
}

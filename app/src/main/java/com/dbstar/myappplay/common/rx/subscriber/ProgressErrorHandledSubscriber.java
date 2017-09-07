package com.dbstar.myappplay.common.rx.subscriber;

import android.content.Context;

/**
 * Created by wh on 2017/9/7.
 */

public abstract class ProgressErrorHandledSubscriber<T> extends ErrorHandlerSubscriber<T> {

    protected Context mContext;

    public ProgressErrorHandledSubscriber(Context context) {
        super(context);
        mContext = context;
    }

    public boolean isShowProgressDialog() {
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(isShowProgressDialog()){
        }
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);

    }

}

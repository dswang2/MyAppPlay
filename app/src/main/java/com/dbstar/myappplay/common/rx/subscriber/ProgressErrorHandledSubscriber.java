package com.dbstar.myappplay.common.rx.subscriber;

import android.content.Context;

import com.dbstar.myappplay.ui.BaseView;

/**
 * Created by wh on 2017/9/7.
 */

public abstract class ProgressErrorHandledSubscriber<T> extends ErrorHandlerSubscriber<T> {

<<<<<<< HEAD
    protected Context mContext;
=======
    private BaseView mBaseView;
>>>>>>> 865499dd79ffce3b7f301022890bff8c07ea7cb1

    public ProgressErrorHandledSubscriber(Context context, BaseView baseView) {
        super(context);
<<<<<<< HEAD
        mContext = context;
=======
        mBaseView = baseView;
>>>>>>> 865499dd79ffce3b7f301022890bff8c07ea7cb1
    }

    public boolean isShowProgressDialog() {
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(isShowProgressDialog()){
<<<<<<< HEAD
=======
            mBaseView.showLoading();
>>>>>>> 865499dd79ffce3b7f301022890bff8c07ea7cb1
        }
    }

    @Override
    public void onCompleted() {
<<<<<<< HEAD

=======
        if (isShowProgressDialog()) {
            mBaseView.dismissLoading();
        }
>>>>>>> 865499dd79ffce3b7f301022890bff8c07ea7cb1
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
<<<<<<< HEAD

    }
=======
        if (isShowProgressDialog()) {
            mBaseView.dismissLoading();
        }
    }

>>>>>>> 865499dd79ffce3b7f301022890bff8c07ea7cb1

}

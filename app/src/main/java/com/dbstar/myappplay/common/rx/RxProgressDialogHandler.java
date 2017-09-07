package com.dbstar.myappplay.common.rx;

import android.content.Context;

/**
 * Created by dswang on 2017/9/7.
 */

public class RxProgressDialogHandler {

    private Context mContext;
    private boolean cancelable;
    private OnProgressCancelListener mProgressCancelListener;

    public RxProgressDialogHandler(Context context) {
        this(context,false,null);
    }

    public RxProgressDialogHandler(Context context,
                Boolean cancelable, OnProgressCancelListener progressCancelListener) {
        this.mContext = context;
        this.mProgressCancelListener = progressCancelListener;
        this.cancelable = cancelable;


    }

    public interface OnProgressCancelListener {

        void onCancelProgress();
    }
}

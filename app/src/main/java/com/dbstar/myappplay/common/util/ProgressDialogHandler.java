package com.dbstar.myappplay.common.util;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by wh on 2017/9/8.
 */

public class ProgressDialogHandler extends Handler{

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 0;

    private Context mContext;
    private SweetAlertDialog mSweetAlertDialog;
    private boolean cancelable;
    private OnProgressCancelListener mOnProgressCancelListener;

    public ProgressDialogHandler(Context mContext) {
        this(mContext,false,null);
    }

    public ProgressDialogHandler(Context mContext, boolean cancelable, ProgressDialogHandler.OnProgressCancelListener mOnProgressCancelListener) {
        this.mContext = mContext;
        this.cancelable = cancelable;
        this.mOnProgressCancelListener = mOnProgressCancelListener;
        initDialog();
    }

    public void initDialog(){
        if(mSweetAlertDialog == null){
            mSweetAlertDialog = new SweetAlertDialog(mContext,SweetAlertDialog.PROGRESS_TYPE);
            mSweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            mSweetAlertDialog.setTitleText("加载中……");

            if(cancelable){
                mSweetAlertDialog.setCancelText("关闭");
                mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        if(mOnProgressCancelListener!=null){
                            mOnProgressCancelListener.onCancelProgress();
                        }
                    }
                });
            }

        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                showProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }

    public void showProgressDialog(){
        if(mSweetAlertDialog!=null && !mSweetAlertDialog.isShowing()){
            mSweetAlertDialog.show();
        }
    }

    public void dismissProgressDialog() {
        if(mSweetAlertDialog!=null && mSweetAlertDialog.isShowing()){
            mSweetAlertDialog.dismiss();
            mSweetAlertDialog = null;
        }
    }

    public interface OnProgressCancelListener{
        void onCancelProgress();
    }
}
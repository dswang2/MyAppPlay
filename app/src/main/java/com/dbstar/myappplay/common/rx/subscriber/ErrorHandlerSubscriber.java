package com.dbstar.myappplay.common.rx.subscriber;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.dbstar.myappplay.common.exception.BaseException;
import com.dbstar.myappplay.common.rx.RxErrorHandler;

/**
 * Created by wh on 2017/9/7.
 */

public abstract class ErrorHandlerSubscriber<T> extends DefualtSubscriber<T> {
    private Context mContext;
    protected RxErrorHandler mRxErrorHandler;

    public ErrorHandlerSubscriber(Context context) {
        mContext = context;
        mRxErrorHandler = new RxErrorHandler(mContext);
    }

    /**
     * 参数 Throwable e ，是上游 Observable.error(e) 发送的，具体而言，发送语句在 RxHttpReponseCompat.java 中
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        // 处理错误信息
        // 将各种Exception 转换为 统一的 BaseException 好做处理
        BaseException baseException = mRxErrorHandler.handleError(e);
        if(baseException==null){
            e.printStackTrace();
            Log.d("ErrorHandlerSubscriber",e.getMessage());
        }
        else {
            Toast.makeText(mContext,baseException.getDisplayMessage(),Toast.LENGTH_LONG).show();

            mRxErrorHandler.showErrorMessage(baseException);
//            if(baseException.getCode() == BaseException.ERROR_TOKEN){
//                toLogin();
//            }

        }

    }

}

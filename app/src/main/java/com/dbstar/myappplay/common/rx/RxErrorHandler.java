package com.dbstar.myappplay.common.rx;

import android.content.Context;
import android.widget.Toast;

import com.dbstar.myappplay.common.exception.ApiException;
import com.dbstar.myappplay.common.exception.BaseException;
import com.google.gson.JsonParseException;

import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by wh on 2017/9/7.
 */

public class RxErrorHandler {
    private Context mContext;

    public RxErrorHandler(Context mContext) {
        this.mContext = mContext;
    }


    public BaseException handleError(Throwable e){
        BaseException exception = new BaseException();
        if(e instanceof ApiException){
            exception.setCode(((ApiException)e).getCode());
        }
        else if (e instanceof JsonParseException){
            exception.setCode(BaseException.JSON_ERROR);
        }
        else  if(e instanceof HttpException){
            exception.setCode(((HttpException)e).code());
        }
        else  if(e instanceof SocketTimeoutException){
            exception.setCode(BaseException.SOCKET_TIMEOUT_ERROR);
        }
        else {
            exception.setCode(BaseException.UNKNOWN_ERROR);
        }
        // 设置错误状态码以后，更新状态信息
        exception.refreshDisplayMessage(mContext);

        return exception;
    }

    public void showErrorMessage(BaseException e){
        Toast.makeText(mContext,e.getDisplayMessage(),Toast.LENGTH_LONG).show();
    }
}

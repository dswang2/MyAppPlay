package com.dbstar.myappplay.common.exception;

import android.content.Context;

import com.dbstar.myappplay.R;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.common.exception
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public class BaseException extends Exception {



    /*API错误*/
    public static final int API_ERROR = 0x0;

    /*网络错误*/
    public static final int NETWORD_ERROR = 0x1;
    /*http_错误*/
    public static final int HTTP_ERROR = 0x2;
    /*json错误*/
    public static final int JSON_ERROR = 0x3;
    /*未知错误*/
    public static final int UNKNOWN_ERROR = 0x4;
    /*运行时异常-包含自定义异常*/
    public static final int RUNTIME_ERROR = 0x5;
    /*无法解析该域名*/
    public static final int UNKOWNHOST_ERROR = 0x6;

    /*连接网络超时*/
    public static final int SOCKET_TIMEOUT_ERROR = 0x7;

    /*无网络连接*/
    public static final int SOCKET_ERROR = 0x8;


    //Token 失效
    public static final int  ERROR_TOKEN=10010;


    //    api /////////////////////////////////////////

    // 服务器错误
    public static final int  ERROR_API_SYSTEM=10000;

    // 登录错误，用户名密码错误
    public static final int  ERROR_API_LOGIN=10001;

    //调用无权限的API
    public static final int  ERROR_API_NO_PERMISSION=10002;

    //账户被冻结
    public static final int  ERROR_API_ACCOUNT_FREEZE=10003;



    // http

    public static final int ERROR_HTTP_400=400;

    public static final int ERROR_HTTP_404=404;

    public static final int ERROR_HTTP_405=405;

    public static final int ERROR_HTTP_500=500;





    private int code;

    private String displayMessage;

    public BaseException() {

    }

    public BaseException(int code, String displayMessage) {
        this.code = code;
        this.displayMessage = displayMessage;
    }

    public BaseException(String message, int code, String displayMessage) {
        super(message);
        this.code = code;
        this.displayMessage = displayMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    /**
     * 根据最新的错误码，加载资源文件中的，错误提示信息
     * 加载资源文件中的信息，用到context
     * @param context
     */
    public void refreshDisplayMessage(Context context){
        // 根据 this.code ,得到message
        String errorMsg = null ;
        switch (this.getCode()){
            case BaseException.HTTP_ERROR:

                errorMsg =  context.getResources().getString(R.string.error_http);

                break;

            case BaseException.SOCKET_TIMEOUT_ERROR:

                errorMsg =  context.getResources().getString(R.string.error_socket_timeout);

                break;
            case BaseException.SOCKET_ERROR:

                errorMsg =  context.getResources().getString(R.string.error_socket_unreachable);

                break;


            case BaseException.ERROR_HTTP_400:

                errorMsg =  context.getResources().getString(R.string.error_http_400);

                break;


            case BaseException.ERROR_HTTP_404:

                errorMsg =  context.getResources().getString(R.string.error_http_404);

                break;

            case BaseException.ERROR_HTTP_500:

                errorMsg =  context.getResources().getString(R.string.error_http_500);

                break;



            case ApiException.ERROR_API_SYSTEM:
                errorMsg = context.getResources().getString(R.string.error_system);
                break;

            case ApiException.ERROR_API_ACCOUNT_FREEZE:
                errorMsg = context.getResources().getString(R.string.error_account_freeze);
                break;


            case ApiException.ERROR_API_NO_PERMISSION:
                errorMsg = context.getResources().getString(R.string.error_api_no_perission);
                break;

            case ApiException.ERROR_API_LOGIN:
                errorMsg = context.getResources().getString(R.string.error_login);
                break;

            case ApiException.ERROR_TOKEN:
                errorMsg = context.getResources().getString(R.string.error_token);
                break;


            default:
                errorMsg=context.getResources().getString(R.string.error_unkown);
                break;
        }
        this.setDisplayMessage(errorMsg);
    }
}

package com.dbstar.myappplay.bean;

/**
 * Created by wh on 2017/9/5.
 */

public class BaseBean<T> {

    private static final int SUCCESS = 1;
    /**{
     "data": { },
     "status": 1,
     "message": "success"
     }
     */


    private int status;
    private String message;
    private T data;

    public boolean success(){
        return (status == SUCCESS) ;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

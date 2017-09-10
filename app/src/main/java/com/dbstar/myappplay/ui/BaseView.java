package com.dbstar.myappplay.ui;

/**
 * Created by dswang on 2017/8/20.
 */
public interface BaseView {
    public void showLoading();
    public void dismissLoading();
    public void showError(String msg);
}

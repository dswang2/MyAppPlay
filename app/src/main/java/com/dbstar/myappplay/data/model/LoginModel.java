package com.dbstar.myappplay.data.model;

import com.dbstar.myappplay.bean.BaseBean;
import com.dbstar.myappplay.bean.LoginBean;
import com.dbstar.myappplay.bean.requestbean.LoginRequestBean;
import com.dbstar.myappplay.data.api.ApiService;
import com.dbstar.myappplay.presenter.contract.LoginContract;

import rx.Observable;

/**
 * Created by wh on 2017/9/27.
 */

public class LoginModel implements LoginContract.ILoginModel {

    private ApiService mApiService;

    public LoginModel(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<LoginBean>> login(String email, String password) {
        LoginRequestBean loginRequestBean = new LoginRequestBean();
        loginRequestBean.setEmail(email);
        loginRequestBean.setPassword(password);
        return mApiService.login(loginRequestBean);
    }
}

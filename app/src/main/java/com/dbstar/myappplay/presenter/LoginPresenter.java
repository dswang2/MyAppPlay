package com.dbstar.myappplay.presenter;

import com.dbstar.myappplay.presenter.contract.LoginContract;

/**
 * Created by wh on 2017/9/27.
 */

public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel,LoginContract.ILoginView> {

    public LoginPresenter(LoginContract.ILoginModel model, LoginContract.ILoginView view) {
        super(model, view);
    }
}

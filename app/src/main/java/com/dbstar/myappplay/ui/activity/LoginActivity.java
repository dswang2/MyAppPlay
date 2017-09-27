package com.dbstar.myappplay.ui.activity;

import com.dbstar.myappplay.R;
import com.dbstar.myappplay.di.component.AppComponent;
import com.dbstar.myappplay.di.component.DaggerLoginComponent;
import com.dbstar.myappplay.di.module.LoginModule;
import com.dbstar.myappplay.presenter.LoginPresenter;
import com.dbstar.myappplay.presenter.contract.LoginContract;

/**
 * Created by wh on 2017/9/27.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.ILoginView {

    @Override
    int setLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent.builder()
                .loginModule(new LoginModule(this))
                .appComponent(appComponent)
                .build().inject(this);
    }

    @Override
    protected void init() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showError(String msg) {

    }
}

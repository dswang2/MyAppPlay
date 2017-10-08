package com.dbstar.myappplay.di.module;

import com.dbstar.myappplay.data.api.ApiService;
import com.dbstar.myappplay.data.model.LoginModel;
import com.dbstar.myappplay.presenter.LoginPresenter;
import com.dbstar.myappplay.presenter.contract.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wh on 2017/9/27.
 */
@Module
public class LoginModule {
    private LoginContract.ILoginView mView;
    public LoginModule(LoginContract.ILoginView view) {
        this.mView = view;
    }
    @Provides
    public LoginContract.ILoginView provideView(){
        return mView;
    }

    @Provides
    public LoginPresenter provideLoginPresenter(LoginContract.ILoginModel model, LoginContract.ILoginView view){
        return new LoginPresenter(model,view);
    }

    @Provides
    public LoginContract.ILoginModel provideLoginModel(ApiService apiService){
        return new LoginModel(apiService);
    }
}

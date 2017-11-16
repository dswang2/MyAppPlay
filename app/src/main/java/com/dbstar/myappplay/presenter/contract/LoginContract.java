package com.dbstar.myappplay.presenter.contract;

import com.dbstar.myappplay.bean.BaseBean;
import com.dbstar.myappplay.bean.LoginBean;
import com.dbstar.myappplay.ui.BaseView;

import io.reactivex.Observable;


/**
 * Created by wh on 2017/9/27.
 */

public interface LoginContract {
    interface ILoginModel{
        Observable<BaseBean<LoginBean>> login(String email, String password);
    }
    interface ILoginView extends BaseView{
        void checkPhoneError();
        void checkPhoneSuccess();
        void loginSuccess(LoginBean loginBean);
    }
}

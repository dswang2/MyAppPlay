package com.dbstar.myappplay.presenter;

import android.util.Log;

import com.dbstar.myappplay.bean.LoginBean;
import com.dbstar.myappplay.common.rx.RxHttpReponseCompat;
import com.dbstar.myappplay.common.util.ACache;
import com.dbstar.myappplay.common.util.Constant;
import com.dbstar.myappplay.common.util.LogUtils;
import com.dbstar.myappplay.common.util.VerificationUtils;
import com.dbstar.myappplay.presenter.contract.LoginContract;
import com.hwangjr.rxbus.RxBus;

import rx.Subscriber;

/**
 * Created by wh on 2017/9/27.
 */

public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel, LoginContract.ILoginView> {


    public LoginPresenter(LoginContract.ILoginModel model, LoginContract.ILoginView view) {
        super(model, view);
    }

    public void login(String email, String password) {
        LogUtils.e("dswang", "LoginPresenter.login.email = " + email);
        LogUtils.e("dswang", "LoginPresenter.login.password = " + password);

        if (VerificationUtils.matcherPhoneNum(email)) {
            mView.checkPhoneSuccess();
        } else {
            mView.checkPhoneError();
            return;
        }


        mModel.login(email, password)
                .compose(RxHttpReponseCompat.<LoginBean>compatResult())
                .subscribe(new Subscriber<LoginBean>() {

                    @Override
                    public void onStart() {
                        Log.e("dswang","LoginPresenter.onStart(LoginPresenter.java:43)");
                        mView.showLoading();
                    }

                    @Override
                    public void onCompleted() {
                        LogUtils.e("dswang", "LoginPresenter.onCompleted. = ");
                        mView.dismissLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("dswang", "LoginPresenter.onError. = " + e.getStackTrace() + e.toString());
                        mView.dismissLoading();
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        LogUtils.e("dswang", "LoginPresenter.onNext. = " + loginBean.toString());
                        mView.loginSuccess(loginBean);
                        saveUser(loginBean);
                        // 利用RxBus 通知侧滑导航栏的头部更新登陆成功的头像
                        RxBus.get().post(loginBean.getUser());
                    }
                });
    }

    private void saveUser(LoginBean loginBean) {
        ACache aCache = ACache.get(mContext);
        aCache.put(Constant.USER, loginBean.getUser());
        aCache.put(Constant.TOKEN, loginBean.getToken());
    }
}

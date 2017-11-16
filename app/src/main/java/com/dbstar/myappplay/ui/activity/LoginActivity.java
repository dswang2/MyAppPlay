package com.dbstar.myappplay.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.dbstar.myappplay.R;
import com.dbstar.myappplay.bean.LoginBean;
import com.dbstar.myappplay.common.util.ACache;
import com.dbstar.myappplay.common.util.Constant;
import com.dbstar.myappplay.common.util.LogUtils;
import com.dbstar.myappplay.di.component.AppComponent;
import com.dbstar.myappplay.di.component.DaggerLoginComponent;
import com.dbstar.myappplay.di.module.LoginModule;
import com.dbstar.myappplay.presenter.LoginPresenter;
import com.dbstar.myappplay.presenter.contract.LoginContract;
import com.dbstar.myappplay.ui.widget.LoadingButton;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;


/**
 * Created by wh on 2017/9/27.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.ILoginView {

    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;
    @BindView(R.id.txt_mobi)
    EditText txtMobi;
    @BindView(R.id.view_mobi_wrapper)
    TextInputLayout viewMobiWrapper;
    @BindView(R.id.txt_password)
    EditText txtPassword;
    @BindView(R.id.view_password_wrapper)
    TextInputLayout viewPasswordWrapper;
    @BindView(R.id.btn_login)
    LoadingButton btnLogin;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;

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
        initData();
        initView();
    }

    private void initData() {
        String email = ACache.get(this).getAsString(Constant.EMAIL);
        String passw = ACache.get(this).getAsString(Constant.PASSWORD);
        if(email!=null){
            txtMobi.setText(email);
        }
        if(passw!=null){
            txtPassword.setText(passw);
        }


    }

    private void initView() {
        mainToolbar.setNavigationIcon(new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_arrow_back)
                .sizeDp(16)
                .color(getResources().getColor(R.color.md_white_1000)));

        mainToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });

        Observable<CharSequence> txtMobiObservable = RxTextView.textChanges(txtMobi);
        Observable<CharSequence> txtPasswordObservable = RxTextView.textChanges(txtPassword);
        Observable.combineLatest(txtMobiObservable, txtPasswordObservable, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(@NonNull CharSequence mobi, @NonNull CharSequence passwd) throws Exception {
                return isPhoneValid(mobi.toString()) && isPasswordValid(passwd.toString());
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean aBoolean) throws Exception {
                Log.e("LoginActivity","call(LoginActivity.java:89)"+ (aBoolean?"登录允许":""));
                RxView.enabled(btnLogin).accept(aBoolean);
            }
        });

        RxView.clicks(btnLogin).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                LogUtils.e("dswang_LoginActivity", "LoginActivity.call. = ");
                mPresenter.login(txtMobi.getText().toString().trim(),txtPassword.getText().toString().trim());
            }
        });
        
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    private boolean isPhoneValid(String phone) {
        return phone.length() == 11;
    }


    @Override
    public void showLoading() {
        btnLogin.showLoading();
    }

    @Override
    public void dismissLoading() {
        btnLogin.showButtonText();
    }

    @Override
    public void showError(String msg) {
        btnLogin.showButtonText();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void checkPhoneError() {
        viewMobiWrapper.setError("手机号格式不正确");
    }

    @Override
    public void checkPhoneSuccess() {
        viewMobiWrapper.setError("");
        viewMobiWrapper.setErrorEnabled(false);
    }

    @Override
    public void loginSuccess(LoginBean loginBean) {
        this.finish();
    }
}

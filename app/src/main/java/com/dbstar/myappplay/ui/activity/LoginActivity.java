package com.dbstar.myappplay.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.dbstar.myappplay.R;
import com.dbstar.myappplay.di.component.AppComponent;
import com.dbstar.myappplay.di.component.DaggerLoginComponent;
import com.dbstar.myappplay.di.module.LoginModule;
import com.dbstar.myappplay.presenter.LoginPresenter;
import com.dbstar.myappplay.presenter.contract.LoginContract;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

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
    Button btnLogin;
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
        initView();
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
        Observable.combineLatest(txtMobiObservable, txtPasswordObservable, new Func2<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence mobi, CharSequence passwd) {
                return isPhoneValid(mobi.toString()) && isPasswordValid(passwd.toString());
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                Log.e("LoginActivity","call(LoginActivity.java:89)"+ (aBoolean?"登录允许":""));
                RxView.enabled(btnLogin).call(aBoolean);
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

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

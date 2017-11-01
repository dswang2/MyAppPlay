package com.dbstar.myappplay.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dbstar.myappplay.AppApplication;
import com.dbstar.myappplay.di.component.AppComponent;
import com.dbstar.myappplay.presenter.BasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by dswang on 2017/8/30.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    // 子类必须实现 setupActivityComponent ，集成dagger2，否则 mPresenter 不能被实例化
    @Inject
    public T mPresenter;

    protected AppApplication mAppApplication;
    private Unbinder mButterKnifeUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        // 图标字体
        //LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));

        super.onCreate(savedInstanceState);

        setContentView(setLayoutID());
        mButterKnifeUnbinder = ButterKnife.bind(this);
        mAppApplication = (AppApplication) getApplication();
        setupActivityComponent(mAppApplication.getAppComponent());
        init();
    }

    // 返回Activity的布局文件ID
    abstract int setLayoutID();

    // 集成dagger2
    protected abstract void setupActivityComponent(AppComponent appComponent);

    //初始化
    protected abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mButterKnifeUnbinder != Unbinder.EMPTY){
            mButterKnifeUnbinder.unbind();
        }
    }
}

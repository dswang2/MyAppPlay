package com.dbstar.myappplay.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dbstar.myappplay.AppApplication;
import com.dbstar.myappplay.di.component.AppComponent;
import com.dbstar.myappplay.presenter.BasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by dswang on 2017/8/31.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {


    protected View mRootView;

    /** 子类必须实现 setupActivityComponent ，集成dagger2，否则 mPresenter 不能被实例化
     *  另外，子类集成的dagger2中的module中，必须有provide BasePresenter子类的方法
     *      比如，RecommendFragment 中的 RecommendModule 中有 provide RecommendPresenter 方法
     */
    @Inject
    public T mPresenter;


    protected AppApplication mAppApplication;

    private Unbinder mMButterKnifeUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayoutId(),container,false);
        mMButterKnifeUnbinder = ButterKnife.bind(this, mRootView);
        mAppApplication = (AppApplication) getActivity().getApplication();
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupActivityComponent(mAppApplication.getAppComponent());
        init();
    }

    // 设置Fragment的标题
    public abstract String getTitle();

    // 设置布局ID
    protected abstract int setLayoutId();

    // 初始化方法
    protected abstract void init();

    // 集成dagger2
    protected abstract void setupActivityComponent(AppComponent appComponent);



    @Override
    public void onDestroy() {
        super.onDestroy();
        mMButterKnifeUnbinder.unbind();
    }
}

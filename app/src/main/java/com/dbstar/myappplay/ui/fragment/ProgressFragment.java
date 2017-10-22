package com.dbstar.myappplay.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dbstar.myappplay.AppApplication;
import com.dbstar.myappplay.R;
import com.dbstar.myappplay.di.component.AppComponent;
import com.dbstar.myappplay.presenter.BasePresenter;
import com.dbstar.myappplay.ui.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by dswang on 2017/9/9.
 */

public abstract class ProgressFragment<T extends BasePresenter> extends Fragment implements BaseView {



    private FrameLayout mRootView;

    // 三种状态界面
    private View mViewProgress;
    private FrameLayout mViewContent;
    private View mViewEmpty;

    private TextView mTextError;

    @Inject
    T mPresenter;

    private Unbinder mUnbinder;

    private AppApplication mApplication;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 注意，第三个参数为 false
        mRootView = (FrameLayout) inflater.inflate(R.layout.frag_progress,container,false);

        mViewProgress = mRootView.findViewById(R.id.view_progress);
        mViewContent = (FrameLayout) mRootView.findViewById(R.id.view_content);
        mViewEmpty = mRootView.findViewById(R.id.view_empty);

        mTextError = (TextView) mRootView.findViewById(R.id.text_tip);
        mViewEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEmptyClick();
            }
        });
        
        Log.e("ProgressFragment","onCreateView(ProgressFragment.java:63)" + this.getClass().getSimpleName());
        
        return mRootView;
    }

    protected abstract void onEmptyClick();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRealContentView();

        // dagger2的注入，示例是在 setRealContentView() 之前
        this.mApplication = (AppApplication) getActivity().getApplication();
        setupActivityComponent(mApplication.getAppComponent());

        init();
    }

    // 设置Fragment的标题
    public abstract String getTitle();

    protected abstract int setLayoutId();

    protected abstract void init();

    private void setRealContentView() {
        // 注意，第三个参数为 true
        View realContentView = LayoutInflater.from(getActivity()).inflate(setLayoutId(),mViewContent,true);
        mUnbinder = ButterKnife.bind(this, realContentView);
    }

    // 要求子类实现，从而集成dagger2
    protected abstract void setupActivityComponent(AppComponent appComponent);

    public void showProgressView(){
        showView(R.id.view_progress);
    }

    public void showContentView(){
        showView(R.id.view_content);
    }

    public void showEmptyView(int resId){
        showView(R.id.view_empty);
        mTextError.setText(resId);
    }

    public void showEmptyView(String msg){
        mTextError.setText(msg);
        showView(R.id.view_empty);
    }

    private void showView(int view_Id) {
        for(int i=0; i<mRootView.getChildCount();i++){
            if(mRootView.getChildAt(i).getId() == view_Id){
                mRootView.getChildAt(i).setVisibility(View.VISIBLE);
            }else {
                mRootView.getChildAt(i).setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void showLoading() {
        this.showProgressView();
    }

    @Override
    public void showError(String msg) {
        this.showEmptyView(msg);
    }

    @Override
    public void dismissLoading() {
        this.showContentView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}

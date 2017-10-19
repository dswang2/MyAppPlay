package com.dbstar.myappplay.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dbstar.myappplay.AppApplication;
import com.dbstar.myappplay.R;
import com.dbstar.myappplay.di.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wh on 2017/10/19.
 */

public class CategoryAppFragment extends Fragment {


    @BindView(R.id.frag_games_txt)
    TextView fragGamesTxt;

    private Unbinder mUnbinder;

    private AppApplication mApplication;

    private View mRootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(setLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);

        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        this.mApplication = (AppApplication) getActivity().getApplication();
        setupActivityComponent(mApplication.getAppComponent());

        init();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
    }

//    @Override
//    public void showLoading() {
//    }
//
//    @Override
//    public void showError(String msg) {
//    }
//
//    @Override
//    public void dismissLoading() {
//    }

//    public abstract int setLayout();
//
//    public abstract  void setupAcitivtyComponent(AppComponent appComponent);
//
//
//    public abstract void  init();


    public String getTitle() {
        return "精品";
    }


    protected int setLayoutId() {
        return R.layout.frag_games;
    }


    protected void init() {
        fragGamesTxt.setText("分类，待实现");
    }


    protected void setupActivityComponent(AppComponent appComponent) {

    }

}

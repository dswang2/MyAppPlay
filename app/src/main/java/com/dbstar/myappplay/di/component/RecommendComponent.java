package com.dbstar.myappplay.di.component;

import android.app.ProgressDialog;

import com.dbstar.myappplay.di.module.RecommendModule;
import com.dbstar.myappplay.di.qualifier.RecommendFramentScope;
import com.dbstar.myappplay.presenter.contract.RecommendContract;
import com.dbstar.myappplay.ui.fragment.RecommendFragment;

import dagger.Component;

/**
 * Created by wh on 2017/8/24.
 */
@RecommendFramentScope
@Component(modules = RecommendModule.class,dependencies = AppComponent.class)
public interface RecommendComponent {
    public RecommendContract.Presenter providePresenter();
    public ProgressDialog provideProgressDialog();

    void  inject(RecommendFragment recommendFragment);
}

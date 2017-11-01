package com.dbstar.myappplay.di.module;

import com.dbstar.myappplay.data.model.AppInfoModel;
import com.dbstar.myappplay.presenter.AppDetailPresenter;
import com.dbstar.myappplay.presenter.contract.AppDetailContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wh on 2017/11/1.
 * 主要是个 详情模块 提供 Model 实例、Presenter实例、View实例等
 */

@Module(includes = AppModelModule.class)
public class AppDetaiModule {
    private AppDetailContract.IDetailView mView;
    public AppDetaiModule(AppDetailContract.IDetailView view){
        mView = view;
    }

    @Provides
    public AppDetailContract.IDetailView provideDetailView(){
        return mView;
    }

    @Provides
    public AppDetailPresenter provideDetailPresenter(AppInfoModel model,AppDetailContract.IDetailView view){
        return new AppDetailPresenter(model,view);
    }
}

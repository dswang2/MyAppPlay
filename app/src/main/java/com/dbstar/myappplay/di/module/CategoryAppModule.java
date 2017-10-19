package com.dbstar.myappplay.di.module;

import com.dbstar.myappplay.data.api.ApiService;
import com.dbstar.myappplay.data.model.CategoryAppModel;
import com.dbstar.myappplay.presenter.CategoryAppPresenter;
import com.dbstar.myappplay.presenter.contract.CategoryAppContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wh on 2017/10/19.
 */

@Module
public class CategoryAppModule {
    private CategoryAppContract.IView mView;

    public CategoryAppModule(CategoryAppContract.IView view) {
        this.mView = view;
    }

    @Provides
    public CategoryAppContract.IView provideCategoryAppView(){
        return mView;
    }

    @Provides
    public CategoryAppContract.IModel provideCategoryAppModel(ApiService apiService){
        return new CategoryAppModel(apiService);
    }

    @Provides
    public CategoryAppPresenter provideCategoryAppPresenter(CategoryAppContract.IModel model,CategoryAppContract.IView view){
        return new CategoryAppPresenter(model,view);
    }
}

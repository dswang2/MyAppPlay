package com.dbstar.myappplay.di.module;

import com.dbstar.myappplay.data.api.ApiService;
import com.dbstar.myappplay.data.model.CategoryModel;
import com.dbstar.myappplay.presenter.CategoryPresenter;
import com.dbstar.myappplay.presenter.contract.CategoryContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wh on 2017/10/16.
 */

@Module
public class CategoryModule {
    private CategoryContract.ICategoryView mView;
    public CategoryModule(CategoryContract.ICategoryView view) {
        this.mView = view;
    }

    @Provides
    public CategoryContract.ICategoryView provideView(){
        return mView;
    }

    @Provides
    public CategoryContract.ICategoryModel provideModel(ApiService apiService){
        return new CategoryModel(apiService);
    }

    @Provides
    public CategoryPresenter provideCategoryPresenter(CategoryContract.ICategoryView mView, CategoryContract.ICategoryModel mModel){
        return new CategoryPresenter(mModel,mView);
    }
}

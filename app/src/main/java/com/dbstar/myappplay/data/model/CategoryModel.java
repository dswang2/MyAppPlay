package com.dbstar.myappplay.data.model;

import com.dbstar.myappplay.bean.BaseBean;
import com.dbstar.myappplay.bean.Category;
import com.dbstar.myappplay.data.api.ApiService;
import com.dbstar.myappplay.presenter.contract.CategoryContract;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by wh on 2017/10/16.
 */

public class CategoryModel implements CategoryContract.ICategoryModel{
    private ApiService apiService;
    public CategoryModel(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<Category>>> getCategories() {
        return apiService.categories();
    }
}

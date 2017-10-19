package com.dbstar.myappplay.data.model;

import com.dbstar.myappplay.data.api.ApiService;
import com.dbstar.myappplay.presenter.contract.CategoryAppContract;

/**
 * Created by wh on 2017/10/19.
 */

public class CategoryAppModel implements CategoryAppContract.IModel {
    private ApiService apiService;

    public CategoryAppModel(ApiService apiService) {
        this.apiService = apiService;
    }
}

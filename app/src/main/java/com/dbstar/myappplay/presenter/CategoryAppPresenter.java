package com.dbstar.myappplay.presenter;

import com.dbstar.myappplay.presenter.contract.CategoryAppContract;

/**
 * Created by wh on 2017/10/19.
 */

public class CategoryAppPresenter extends BasePresenter<CategoryAppContract.IModel,CategoryAppContract.IView> {
    public CategoryAppPresenter(CategoryAppContract.IModel model, CategoryAppContract.IView view) {
        super(model, view);
    }
}

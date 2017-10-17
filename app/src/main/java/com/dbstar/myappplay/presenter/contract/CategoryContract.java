package com.dbstar.myappplay.presenter.contract;

import com.dbstar.myappplay.bean.BaseBean;
import com.dbstar.myappplay.bean.Category;
import com.dbstar.myappplay.ui.BaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by wh on 2017/10/16.
 */

public interface CategoryContract {
    interface ICategoryModel {
        Observable<BaseBean<List<Category>>> getCategories();
    }

    interface ICategoryView extends BaseView{
        public void showResults(List<Category> categories);
    }
}

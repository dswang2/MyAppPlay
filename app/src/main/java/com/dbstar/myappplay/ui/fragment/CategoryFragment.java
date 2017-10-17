package com.dbstar.myappplay.ui.fragment;

import android.widget.TextView;

import com.dbstar.myappplay.R;
import com.dbstar.myappplay.bean.Category;
import com.dbstar.myappplay.di.component.AppComponent;
import com.dbstar.myappplay.di.component.DaggerCategoryComponent;
import com.dbstar.myappplay.di.module.CategoryModule;
import com.dbstar.myappplay.presenter.CategoryPresenter;
import com.dbstar.myappplay.presenter.contract.CategoryContract;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wh on 2017/6/6.
 */
public class CategoryFragment extends ProgressFragment<CategoryPresenter> implements CategoryContract.ICategoryView{


    @BindView(R.id.category_tv_title)
    TextView categoryTvTitle;

    @Override
    protected void init() {
        mPresenter.requestDatas();
    }

    @Override
    protected void onEmptyClick() {

    }

    @Override
    public String getTitle() {
        return "分类";
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerCategoryComponent.builder()
                .categoryModule(new CategoryModule(this))
                .appComponent(appComponent)
                .build().inject(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.frag_category;
    }


    @Override
    public void showResults(List<Category> categories) {
        categoryTvTitle.setText(new Gson().toJson(categories));
    }
}

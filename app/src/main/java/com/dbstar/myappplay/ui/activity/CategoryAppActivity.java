package com.dbstar.myappplay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dbstar.myappplay.R;
import com.dbstar.myappplay.bean.Category;
import com.dbstar.myappplay.common.util.Constant;
import com.dbstar.myappplay.di.component.AppComponent;
import com.dbstar.myappplay.di.component.DaggerCategoryAppComponent;
import com.dbstar.myappplay.di.module.CategoryAppModule;
import com.dbstar.myappplay.presenter.CategoryAppPresenter;
import com.dbstar.myappplay.presenter.contract.CategoryAppContract;
import com.dbstar.myappplay.ui.adapter.CategoryViewPager;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wh on 2017/10/19.
 */

public class CategoryAppActivity extends BaseActivity<CategoryAppPresenter> implements CategoryAppContract.IView {


    @BindView(R.id.category_tab)
    TabLayout categoryTab;
    @BindView(R.id.category_toolbar)
    Toolbar categoryToolbar;
    @BindView(R.id.category_viewpager)
    ViewPager categoryViewpager;

    private Category mCategory;

    @Override
    int setLayoutID() {
        return R.layout.activity_category;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerCategoryAppComponent.builder().categoryAppModule(new CategoryAppModule(this)).appComponent(appComponent).build().inject(this);
    }

    @Override
    protected void init() {
        // 初始化数据
        initData();

        // 初始化Toolbar
        initToolbar();

        if (null != mCategory) {
            //初始化 TabLayout 和 ViewPager
            initTabLayout();
        }
    }

    private void initData() {
        Intent intent = getIntent();
        mCategory = (Category) intent.getSerializableExtra(Constant.CATEGORY);
    }

    private void initTabLayout() {
        CategoryViewPager mCategoryViewPager = new CategoryViewPager(getSupportFragmentManager(),mCategory.getId());
        categoryViewpager.setOffscreenPageLimit(mCategoryViewPager.getCount());
        categoryViewpager.setAdapter(mCategoryViewPager);
        categoryTab.setupWithViewPager(categoryViewpager);
    }

    private void initToolbar() {
        if (null != mCategory && null != mCategory.getName()) {
            categoryToolbar.setTitle(mCategory.getName());
        }
        categoryToolbar.setNavigationIcon(new IconicsDrawable(this).icon(Ionicons.Icon.ion_ios_arrow_back).sizeDp(16).color(getResources().getColor(R.color.md_white_1000)));

        categoryToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryAppActivity.this.finish();
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

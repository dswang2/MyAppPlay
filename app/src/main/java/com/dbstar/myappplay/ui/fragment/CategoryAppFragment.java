package com.dbstar.myappplay.ui.fragment;

import android.annotation.SuppressLint;

import com.dbstar.myappplay.common.util.Constant;
import com.dbstar.myappplay.di.component.AppComponent;
import com.dbstar.myappplay.di.component.DaggerAppInfoComponent;
import com.dbstar.myappplay.di.module.AppInfoModule;
import com.dbstar.myappplay.ui.adapter.AppInfoAdapter;

/**
 * Created by wh on 2017/10/19.
 */

@SuppressLint("ValidFragment")
public class CategoryAppFragment extends BaseAppInfoFragment {

    private int categoryId;
    private int flagType;

    @SuppressLint("ValidFragment")
    public CategoryAppFragment(int categoryId, int flagType) {
        super();
        this.categoryId = categoryId;
        this.flagType = flagType;
    }


    @Override
    protected void init() {
        mPresenter.requestCategoryApps(categoryId,page,flagType);
        initRecyclerView();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.requestCategoryApps(categoryId,page,flagType);
    }

    @Override
    protected int type() {
        return Constant.FRAG_TYPE_CATEGORY;

    }

    @Override
    protected AppInfoAdapter initAppInfoAdapter() {
        // 测试时使用
        return AppInfoAdapter.builder().showPosition(true).showCategoryName(false).showBrief(true).build();
    }

    // 分类页面的通用Fragment，无法提供标题
    @Override
    public String getTitle() {
        return "Frag_";
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appInfoModule(new AppInfoModule(this)).appComponent(appComponent).build().injectCategoryAppFragment(this);
    }
}

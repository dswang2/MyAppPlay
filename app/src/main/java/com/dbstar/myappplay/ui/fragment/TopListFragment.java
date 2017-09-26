package com.dbstar.myappplay.ui.fragment;

import com.dbstar.myappplay.di.component.AppComponent;
import com.dbstar.myappplay.di.component.DaggerAppInfoComponent;
import com.dbstar.myappplay.di.module.AppInfoModule;
import com.dbstar.myappplay.presenter.AppInfoPresenter;
import com.dbstar.myappplay.ui.adapter.AppInfoAdapter;

/**
 * Created by wh on 2017/6/6.
 */
public class TopListFragment extends BaseAppInfoFragment {

    @Override
    protected int type() {
        return AppInfoPresenter.TOP_LIST;
    }

    @Override
    protected AppInfoAdapter initAppInfoAdapter() {
        return AppInfoAdapter.builder().showPosition(true).showCategoryName(true).showBrief(true).build();
    }

    @Override
    public String getTitle() {
        return "排行榜";
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder()
                .appInfoModule(new AppInfoModule(this))
                .appComponent(appComponent)
                .build().injectTopListFragment(this);
    }



}

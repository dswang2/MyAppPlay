package com.dbstar.myappplay.ui.fragment;

import com.dbstar.myappplay.common.util.Constant;
import com.dbstar.myappplay.di.component.AppComponent;
import com.dbstar.myappplay.di.component.DaggerAppInfoComponent;
import com.dbstar.myappplay.di.module.AppInfoModule;
import com.dbstar.myappplay.ui.adapter.AppInfoAdapter;

/**
 * Created by wh on 2017/6/6.
 */
public class GamesFragment   extends BaseAppInfoFragment {


    @Override
    protected int type() {
        return Constant.FRAG_TYPE_GAME;
    }

    @Override
    protected AppInfoAdapter initAppInfoAdapter() {
        return AppInfoAdapter.builder().showPosition(true).showCategoryName(true).showBrief(true).build();
    }

    @Override
    public String getTitle() {
        return "游戏";
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder()
                .appInfoModule(new AppInfoModule(this))
                .appComponent(appComponent)
                .build().injectGamesFragment(this);
    }

}

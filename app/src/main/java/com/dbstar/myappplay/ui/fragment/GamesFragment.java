package com.dbstar.myappplay.ui.fragment;

import com.dbstar.myappplay.R;
import com.dbstar.myappplay.di.component.AppComponent;

/**
 * Created by wh on 2017/6/6.
 */
public class GamesFragment   extends BaseFragment {


    @Override
    protected void init() {

    }

    @Override
    public String getTitle() {
        return "游戏";
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.frag_games;
    }
}

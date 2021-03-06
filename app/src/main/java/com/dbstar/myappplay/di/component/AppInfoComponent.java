package com.dbstar.myappplay.di.component;

import com.dbstar.myappplay.di.module.AppInfoModule;
import com.dbstar.myappplay.di.qualifier.FramentScope;
import com.dbstar.myappplay.ui.fragment.CategoryAppFragment;
import com.dbstar.myappplay.ui.fragment.GamesFragment;
import com.dbstar.myappplay.ui.fragment.TopListFragment;

import dagger.Component;

/**
 * Created by wh on 2017/8/24.
 */
@FramentScope
@Component(modules = AppInfoModule.class,dependencies = AppComponent.class)
public interface AppInfoComponent {

    void  injectTopListFragment(TopListFragment topListFragment);
    void  injectGamesFragment(GamesFragment gamesFragment);
    void  injectCategoryAppFragment(CategoryAppFragment categoryAppFragment);
}

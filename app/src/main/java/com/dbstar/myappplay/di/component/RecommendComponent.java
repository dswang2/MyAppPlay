package com.dbstar.myappplay.di.component;

import com.dbstar.myappplay.di.module.RecommendModule;
import com.dbstar.myappplay.di.qualifier.FramentScope;
import com.dbstar.myappplay.ui.fragment.RecommendFragment;

import dagger.Component;

/**
 * Created by wh on 2017/8/24.
 */
@FramentScope
@Component(modules = RecommendModule.class,dependencies = AppComponent.class)
public interface RecommendComponent {

    void  inject(RecommendFragment recommendFragment);
}

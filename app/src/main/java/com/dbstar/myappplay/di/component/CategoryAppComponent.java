package com.dbstar.myappplay.di.component;

import com.dbstar.myappplay.di.module.CategoryAppModule;
import com.dbstar.myappplay.di.qualifier.FramentScope;
import com.dbstar.myappplay.ui.activity.CategoryAppActivity;

import dagger.Component;

/**
 * Created by wh on 2017/10/19.
 */

@FramentScope
@Component(modules = CategoryAppModule.class,dependencies = AppComponent.class)
public interface CategoryAppComponent {
    void inject(CategoryAppActivity categoryAppActivity);
}

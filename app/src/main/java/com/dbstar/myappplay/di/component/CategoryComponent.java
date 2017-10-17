package com.dbstar.myappplay.di.component;

import com.dbstar.myappplay.di.module.CategoryModule;
import com.dbstar.myappplay.di.qualifier.FramentScope;
import com.dbstar.myappplay.ui.fragment.CategoryFragment;

import dagger.Component;

/**
 * Created by wh on 2017/10/16.
 */

@FramentScope
@Component(modules = CategoryModule.class,dependencies = AppComponent.class)
public interface CategoryComponent {
    void inject(CategoryFragment categoryFragment);
}

package com.dbstar.myappplay.di.component;

import com.dbstar.myappplay.di.module.AppDetaiModule;
import com.dbstar.myappplay.di.qualifier.FramentScope;
import com.dbstar.myappplay.ui.activity.AppDetailActivity;

import dagger.Component;

/**
 * Created by wh on 2017/11/1.
 */

@FramentScope
@Component(modules = AppDetaiModule.class,dependencies = AppComponent.class)
public interface AppDetailComponent {
    public void inject(AppDetailActivity activity);
}

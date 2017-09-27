package com.dbstar.myappplay.di.component;

import com.dbstar.myappplay.di.module.LoginModule;
import com.dbstar.myappplay.di.qualifier.FramentScope;
import com.dbstar.myappplay.ui.activity.LoginActivity;

import dagger.Component;

/**
 * Created by wh on 2017/9/27.
 */

@FramentScope
@Component(modules = LoginModule.class,dependencies = AppComponent.class)
public interface LoginComponent {
    void  inject(LoginActivity loginActivity);
}

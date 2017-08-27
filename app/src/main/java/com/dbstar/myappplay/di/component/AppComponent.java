package com.dbstar.myappplay.di.component;

import com.dbstar.myappplay.data.api.ApiService;
import com.dbstar.myappplay.di.module.AppModule;
import com.dbstar.myappplay.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by wh on 2017/8/24.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    public ApiService provideApiService();
}

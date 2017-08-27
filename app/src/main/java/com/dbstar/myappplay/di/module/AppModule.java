package com.dbstar.myappplay.di.module;

import com.dbstar.myappplay.AppApplication;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wh on 2017/8/24.
 */

@Module
public class AppModule {

    private AppApplication mAppApplication;

    public AppModule(AppApplication appApplication){
        mAppApplication = appApplication;
    }

    @Singleton
    @Provides
    public Gson provideGson(){
        return new Gson();
    }

    @Singleton
    @Provides
    public AppApplication provideAppApplication(){
        return mAppApplication;
    }

}

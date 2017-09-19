package com.dbstar.myappplay.di.module;

import android.app.Application;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wh on 2017/8/24.
 */

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application appApplication){
        mApplication = appApplication;
    }

    @Singleton
    @Provides
    public Gson provideGson(){
        return new Gson();
    }

    @Singleton
    @Provides
    public Application provideApplication(){
        return mApplication;
    }

}

package com.dbstar.myappplay.di.module;

import com.dbstar.myappplay.data.api.ApiService;
import com.dbstar.myappplay.data.model.AppInfoModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wh on 2017/11/1.
 * 提供 AppInfoModel 实例
 */
@Module
public class AppModelModule {

    public AppModelModule(){
    }

    @Provides
    public AppInfoModel provideAppInfoModel(ApiService apiService){
        return new AppInfoModel(apiService);
    }
}

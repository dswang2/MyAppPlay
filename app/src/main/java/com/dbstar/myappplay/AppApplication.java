package com.dbstar.myappplay;

import android.app.Application;
import android.view.View;

import com.dbstar.myappplay.di.component.AppComponent;
import com.dbstar.myappplay.di.component.DaggerAppComponent;
import com.dbstar.myappplay.di.module.AppModule;
import com.dbstar.myappplay.di.module.HttpModule;

/**
 * Created by wh on 2017/8/24.SE哇啊我·
 */

public class AppApplication extends Application {

    private View mView;
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        //mAppComponent = DaggerAppComponent.builder()
            // .appModule(new AppModule(this)).build();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule())
                .build();
    }

    public void setViewCache(View view){
        mView = view;
    }

    public View getView(){
        return mView;
    }

    public AppComponent getAppComponent(){
        return  mAppComponent;
    }
}

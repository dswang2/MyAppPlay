package com.dbstar.myappplay.data.model;

import com.dbstar.myappplay.bean.AppInfo;
import com.dbstar.myappplay.bean.BaseBean;
import com.dbstar.myappplay.bean.PageBean;
import com.dbstar.myappplay.data.api.ApiService;

import rx.Observable;

/**
 * Created by dswang on 2017/8/20.
 */

public class RecommendModel {
    private ApiService mApiService;

    public RecommendModel(ApiService apiService){
        mApiService = apiService;
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getApps(){
        return mApiService.getApps("{'page':'0'}");
    }
}

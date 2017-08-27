package com.dbstar.myappplay.data.model;

import com.dbstar.myappplay.bean.AppInfo;
import com.dbstar.myappplay.bean.PageBean;
import com.dbstar.myappplay.data.api.ApiService;

import retrofit2.Callback;

/**
 * Created by dswang on 2017/8/20.
 */

public class RecommendModel {
    private ApiService mApiService;

    public RecommendModel(ApiService apiService){
        mApiService = apiService;
    }

    public void getApps(Callback<PageBean<AppInfo>> callback){
        mApiService.getApps("{'page':'0'}").enqueue(callback);
    }
}

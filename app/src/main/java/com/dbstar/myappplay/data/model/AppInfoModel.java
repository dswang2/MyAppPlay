package com.dbstar.myappplay.data.model;

import com.dbstar.myappplay.bean.AppInfo;
import com.dbstar.myappplay.bean.BaseBean;
import com.dbstar.myappplay.bean.IndexBean;
import com.dbstar.myappplay.bean.PageBean;
import com.dbstar.myappplay.data.api.ApiService;

import rx.Observable;

/**
 * Created by dswang on 2017/8/20.
 */

public class AppInfoModel {
    private ApiService mApiService;

    public AppInfoModel(ApiService apiService) {
        mApiService = apiService;
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getApps() {
        return mApiService.getApps("{'page':'0'}");
    }

    public Observable<BaseBean<IndexBean>> getIndex() {
        return mApiService.getIndex();
    }

    public Observable<BaseBean<PageBean<AppInfo>>> topList(int page) {
        return mApiService.topList(page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> games(int page) {
        return mApiService.games(page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getFeaturedAppsByCategory(int categoryid, int page) {

        return mApiService.getFeaturedAppsByCategory(categoryid, page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getTopListAppsByCategory(int categoryid, int page) {

        return mApiService.getTopListAppsByCategory(categoryid, page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getNewListAppsByCategory(int categoryid, int page) {

        return mApiService.getNewListAppsByCategory(categoryid, page);
    }

}

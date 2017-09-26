package com.dbstar.myappplay.data.api;

import com.dbstar.myappplay.bean.AppInfo;
import com.dbstar.myappplay.bean.BaseBean;
import com.dbstar.myappplay.bean.IndexBean;
import com.dbstar.myappplay.bean.PageBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dswang on 2017/8/12.
 */

public interface ApiService {

    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

    // http://112.124.22.238:8081/course_api/cniaoplay/featured?p={"page":"0"}
    // 返回的数据见文档“interface.txt”
    // PageBean<AppInfo> 是接口的返回值，
    // @GET("featured")
    // public Observable<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);

    //注意，这里是feature2
    @GET("featured2")
    public Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);

    @GET("index")
    public Observable<BaseBean<IndexBean>> getIndex();

    @GET("toplist")
    public Observable<BaseBean<PageBean<AppInfo>>> topList(@Query("page") int page);

}

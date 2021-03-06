package com.dbstar.myappplay.data.api;

import com.dbstar.myappplay.bean.AppDetail;
import com.dbstar.myappplay.bean.AppInfo;
import com.dbstar.myappplay.bean.BaseBean;
import com.dbstar.myappplay.bean.Category;
import com.dbstar.myappplay.bean.IndexBean;
import com.dbstar.myappplay.bean.LoginBean;
import com.dbstar.myappplay.bean.PageBean;
import com.dbstar.myappplay.bean.requestbean.LoginRequestBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @GET("game")
    public Observable<BaseBean<PageBean<AppInfo>>> games(@Query("page") int page);

    @POST("login")
    public Observable<BaseBean<LoginBean>> login(@Body LoginRequestBean param);

    @GET("category")
    public Observable<BaseBean<List<Category>>> categories();

    @GET("category/featured/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getFeaturedAppsByCategory(@Path("categoryid") int categoryid, @Query("page") int page);

    @GET("category/toplist/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getTopListAppsByCategory(@Path("categoryid") int categoryid,@Query("page") int page);

    @GET("category/newlist/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getNewListAppsByCategory(@Path("categoryid") int categoryid,@Query("page") int page);

    @GET("app/{id}")
    Observable<BaseBean<AppDetail>> getAppDetail(@Path("id") int appid);
    // 详情：http://112.124.22.238:8081/course_api/cniaoplay/app/{id}

}

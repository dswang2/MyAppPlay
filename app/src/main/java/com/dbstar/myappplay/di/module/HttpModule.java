package com.dbstar.myappplay.di.module;

import android.app.Application;

import com.dbstar.myappplay.common.http.CommonParamsInterceptor;
import com.dbstar.myappplay.data.api.ApiService;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wh on 2017/8/24.
 */

@Module
public class HttpModule {
    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                // 默认将返回的json数据转换为javabean
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient);
        return builder.build();
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(Application application, Gson gson){
        // log用拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // 开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        // 如果使用到HTTPS，我们需要创建SSLSocketFactory，并设置到client
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                // 添加公共参数
                .addInterceptor(new CommonParamsInterceptor(application,gson))
                // 连接超时时间设置
                .connectTimeout(10, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    public ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }
}

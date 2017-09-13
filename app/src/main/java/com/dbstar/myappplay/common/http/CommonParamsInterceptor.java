package com.dbstar.myappplay.common.http;

import android.content.Context;

import com.dbstar.myappplay.common.util.Constant;
import com.dbstar.myappplay.common.util.DensityUtil;
import com.dbstar.myappplay.common.util.DeviceUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wh on 2017/9/13.
 */

public class CommonParamsInterceptor implements Interceptor{


    Context mContext;
    public CommonParamsInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {



        Request request = chain.request();

        // 封装公共参数到 HashMap 对象中
        HashMap<String,Object> commomParamsMap = new HashMap<>();
        commomParamsMap.put(Constant.IMEI, DeviceUtils.getIMEI(mContext));
        commomParamsMap.put(Constant.MODEL,DeviceUtils.getModel());
        commomParamsMap.put(Constant.LANGUAGE,DeviceUtils.getLanguage());
        commomParamsMap.put(Constant.os,DeviceUtils.getBuildVersionIncremental());
        commomParamsMap.put(Constant.RESOLUTION, DensityUtil.getScreenW(mContext)+"*" + DensityUtil.getScreenH(mContext));
        commomParamsMap.put(Constant.SDK,DeviceUtils.getBuildVersionSDK()+"");
        commomParamsMap.put(Constant.DENSITY_SCALE_FACTOR,mContext.getResources().getDisplayMetrics().density+"");


        // 处理请求

        // 请求方式
        String method = request.method();
        // 保存请求参数的HasMap：rootMap
        HashMap<String,Object> rootMap = new HashMap<>();
        if("GET".equals(method)){
            HttpUrl httpUrl = request.url();
            // 读取url中的请求参数
            Set<String> paramNames = httpUrl.queryParameterNames();
            for (String key: paramNames){
                // 请求参数已经被json形式封装过一次，是 ?p={}的形式 ，要解封装并保存与rootMap中
                if(Constant.PARAM.equals(key)){




                }
                // 请求参数没有被封装过，是 ?page=2&name="hello"等形式，直接保存与rootMap中
                else {
                    rootMap.put(key,httpUrl.queryParameter(key));
                }
            }

        }
        else if("POST".equals(method)){

        }
        else {
        }


        return chain.proceed(request);
    }
}

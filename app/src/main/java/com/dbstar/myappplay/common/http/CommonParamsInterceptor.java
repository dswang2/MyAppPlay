package com.dbstar.myappplay.common.http;

import android.content.Context;

import com.dbstar.myappplay.common.util.Constant;
import com.dbstar.myappplay.common.util.DensityUtil;
import com.dbstar.myappplay.common.util.DeviceUtils;
import com.dbstar.myappplay.common.util.LogUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by wh on 2017/9/13.
 */

public class CommonParamsInterceptor implements Interceptor {


    private Gson mGson;
    private Context mContext;

    public CommonParamsInterceptor(Context context, Gson gson) {
        mGson = gson;
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {


        Request request = chain.request();

        // 封装公共参数到 HashMap 对象中
        HashMap<String, Object> commomParamsMap = new HashMap<>();
        commomParamsMap.put(Constant.IMEI, DeviceUtils.getIMEI(mContext));
        commomParamsMap.put(Constant.MODEL, DeviceUtils.getModel());
        commomParamsMap.put(Constant.LANGUAGE, DeviceUtils.getLanguage());
        commomParamsMap.put(Constant.os, DeviceUtils.getBuildVersionIncremental());
        commomParamsMap.put(Constant.RESOLUTION, DensityUtil.getScreenW(mContext) + "*" + DensityUtil.getScreenH(mContext));
        commomParamsMap.put(Constant.SDK, DeviceUtils.getBuildVersionSDK() + "");
        commomParamsMap.put(Constant.DENSITY_SCALE_FACTOR, mContext.getResources().getDisplayMetrics().density + "");


        // 处理请求

        // 请求方式
        String method = request.method();

        if ("GET".equals(method)) {
            // 保存请求参数的HasMap：rootMap ,rootMap用来保存所有的请求参数
            HashMap<String, Object> rootMap = new HashMap<>();

            HttpUrl httpUrl = request.url();
            // 读取url中的请求参数
            Set<String> paramNames = httpUrl.queryParameterNames();
            for (String key : paramNames) {
                // 请求参数已经被json形式封装过一次，是 ?p={}的形式 ，要解封装并保存与rootMap中
                if (Constant.PARAM.equals(key)) {
                    // 获取 key 为 p 时，json 格式的参数
                    String oldJsonParameters = httpUrl.queryParameter(key);
                    if (oldJsonParameters != null) {
                        HashMap<String, Object> pMap = mGson.fromJson(oldJsonParameters, HashMap.class);
                        if (pMap != null) {
                            for (Map.Entry<String, Object> entry : pMap.entrySet()) {
                                rootMap.put(entry.getKey(), entry.getValue());
                            }
                        }
                    }
                }
                // 请求参数没有被封装过，是 ?page=2&name="hello"等形式，直接保存与rootMap中
                else {
                    rootMap.put(key, httpUrl.queryParameter(key));
                }
            }

            // 将公共参数添加到rootMap中：
            rootMap.put("publicParams", commomParamsMap);
            String newJsonParameters = mGson.toJson(rootMap);

            //参数拼接
            String url = httpUrl.toString();
            int index = url.indexOf("?");
            // 原url 中含有字符 "?"
            if (index > 0) {
                url = url.substring(0, index);
            }

            url = url + "?" + Constant.PARAM + "=" + newJsonParameters;

            LogUtils.e("dswang_CommonParamsInterceptor", "CommonParamsInterceptor.intercept.url = " + url);
            
            request = request.newBuilder().url(url).build();

        } else if ("POST".equals(method)) {
            HashMap<String, Object> rootMap = new HashMap<>();
            RequestBody requestBody = request.body();
            // Post方式、且以表单形式提交
            if (requestBody instanceof FormBody) {
                FormBody formBody = (FormBody) requestBody;
                FormBody.Builder builder = new FormBody.Builder();
                for(int i =0; i< formBody.size();i++){
                    builder.add(formBody.name(i),formBody.value(i));
                }
                // 拼接common参数
                String commonParam = mGson.toJson(commomParamsMap);
                builder.add("publicParams", commonParam);
                formBody = builder.build();
                request = request.newBuilder().post(formBody).build();
            }
            // Post方式、但不以表单形式提交
            else {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                String oldParams = buffer.readUtf8();
                if(oldParams != null){
                    // 拼接参数
                    rootMap = mGson.fromJson(oldParams,HashMap.class);
                    rootMap.put("publicParams", commomParamsMap);
                    String newParamJsons = mGson.toJson(rootMap);
                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                    request = request.newBuilder().post(RequestBody.create(JSON,newParamJsons)).build();
                }
            }
        }
        // GET POST 以外的请求方式
        else {
        }

        
        return chain.proceed(request);
    }
}

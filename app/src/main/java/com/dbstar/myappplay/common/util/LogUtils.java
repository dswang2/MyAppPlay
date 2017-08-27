package com.dbstar.myappplay.common.util;

import android.util.Log;

/**
 * Created by dswang on 2017/8/13.
 */

public class LogUtils {

    private static final boolean ENABLE = true;
    /** 打印一个debug等级的log */
    public static void d(String tag,String msg){
        if (ENABLE){
            Log.d(tag,msg);
        }
    }
    /** 打印一个error等级的log */
    public static void e(String tag,String msg){
        if (ENABLE){
            Log.e(tag, msg);
        }
    }
    /* 打印一个error等级的log */
    public static void e(Class cls,String msg){
        if (ENABLE){
            Log.e(cls.getSimpleName(),msg);
        }
    }

}

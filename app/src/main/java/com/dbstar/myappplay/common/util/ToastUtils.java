package com.dbstar.myappplay.common.util;

/**
 * Created by wh on 2017/5/27.
 */

import android.app.Activity;
import android.widget.Toast;

public class ToastUtils {
    public static Toast sToast;
    /**
     * 展示一个安全的土司
     * @param activity
     * @param msg
     */
    public static void showSafeToast(final Activity activity,final String msg){
        if(Thread.currentThread().getName().equals("main")){
            if(sToast == null) {
                sToast = Toast.makeText(activity, msg, Toast.LENGTH_SHORT);  //正常执行
            }
            else {
                sToast.setText(msg);  //用于覆盖前面未消失的提示信息
            }
            sToast.show();
        }else{
            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    if(sToast == null) {
                        sToast = Toast.makeText(activity, msg, Toast.LENGTH_SHORT);  //正常执行
                    }
                    else {
                        sToast.setText(msg);  //用于覆盖前面未消失的提示信息
                    }
                    sToast.show();
                }
            });
        }

    }
}

package com.dbstar.myappplay.common.util;

/**
 * Created by wh on 2017/9/1.
 */

public class Constant {
    public static  final String BASE_IMG_URL="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

    public static final String IS_SHOW_GUIDE = "is_show_guide";

    public static final String MODEL = "model";
    public static final String IMEI = "imei";
    public static final String LANGUAGE = "la";
    public static final String os = "os";
    public static final String RESOLUTION = "resolution";
    public static final String SDK = "sdk";
    public static final String DENSITY_SCALE_FACTOR = "densityScaleFactor";
    public static final String PARAM ="p";

    public static final String TOKEN = "token";
    public static final String USER = "user";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    public static final String CATEGORY = "category";

    // 类型，虽然都是详情页，返回的额数据差不多，但不同类型的API接口不一致，请求网络时调用的方法不一样
    // 详情页，排行榜Fragment
    public static final int  FRAG_TYPE_TOP_LIST=1;
    // 详情页，游戏榜 Fragment
    public static final int  FRAG_TYPE_GAME=2;
    // 详情页，分类Fragment
    public static final int  FRAG_TYPE_CATEGORY=3;

    // “分类”这一个类型API接口下的子类型
    // 分类之精品
    public static final int CATEGORY_FEATURED=0;
    // 分类之排行
    public static final int CATEGORY_TOPLIST=1;
    // 分类之新品
    public static final int CATEGORY_NEWLIST=2;

    // 应用详情
    public static final String APPINFO = "appinfo";

    public static final String APK_DOWNLOAD_DIR = "apk_dl_dir";


}

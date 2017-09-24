package com.dbstar.myappplay.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dbstar.myappplay.R;
import com.dbstar.myappplay.bean.AppInfo;
import com.dbstar.myappplay.common.imageloader.ImageLoader;

import java.util.List;

/**
 * Created by dswang on 2017/9/21.
 */

public class AppInfoAdapter extends BaseQuickAdapter<AppInfo,BaseViewHolder>{

    String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";


    public AppInfoAdapter(@LayoutRes int layoutResId, @Nullable List<AppInfo> data) {
        super(R.layout.template_app, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {
        ImageLoader.load(baseImgUrl+item.getIcon(), (ImageView) helper.getView(R.id.img_icons));

        helper.setText(R.id.text_titles,item.getDisplayName())
                .setText(R.id.text_sizes,(item.getApkSize()/1024/1024)+"MB");

    }


}

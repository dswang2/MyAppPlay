package com.dbstar.myappplay.ui.adapter;

/**
 * Created by wh on 2017/9/22.
 */

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dbstar.myappplay.R;
import com.dbstar.myappplay.bean.AppInfo;
import com.dbstar.myappplay.common.imageloader.ImageLoader;
import com.dbstar.myappplay.ui.widget.DownloadButtonController;
import com.dbstar.myappplay.ui.widget.DownloadProgressButton;

import zlc.season.rxdownload2.RxDownload;

/**
 * Created by dswang on 2017/9/21.
 */

public class AppInfoAdapter extends BaseQuickAdapter<AppInfo,BaseViewHolder>{

    String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";
    private Builder builder;
    private DownloadButtonController mDownloadButtonController;


    public static Builder builder(){
        return  new Builder();
    }

    private AppInfoAdapter(Builder builder) {
        super(R.layout.template_app);
        this.builder = builder;

        mDownloadButtonController = new DownloadButtonController(builder.mRxDownload);

        openLoadAnimation();
    }

    private AppInfoAdapter(Builder builder,int templateID) {
        super(templateID);
        this.builder = builder;
        openLoadAnimation();
    }

    @Override
    protected void convert(BaseViewHolder helper, AppInfo appInfo) {
        ImageLoader.load(baseImgUrl+appInfo.getIcon(), (ImageView) helper.getView(R.id.img_icons));

        helper.setText(R.id.text_titles,appInfo.getDisplayName())
                .setText(R.id.text_sizes,(appInfo.getApkSize()/1024/1024)+"MB");

        TextView textViewPosition = (TextView)helper.getView(R.id.txt_position);
        textViewPosition.setVisibility(builder.isShowPosition? View.VISIBLE:View.GONE);
        textViewPosition.setText(appInfo.getPosition()+1+".");

        TextView textViewGategory = helper.getView(R.id.txt_category);
        textViewGategory.setVisibility(builder.isShowCategoryName?View.VISIBLE:View.GONE);
        textViewGategory.setText(appInfo.getLevel1CategoryName());

        TextView textViewBrief = helper.getView(R.id.txt_brief);
        textViewBrief.setVisibility(builder.isShowBrief?View.VISIBLE:View.GONE);
        textViewBrief.setText(appInfo.getBriefShow());

        helper.addOnClickListener(R.id.btn_dls);
        View viewButton = helper.getView(R.id.btn_dls);
        if(viewButton instanceof DownloadProgressButton){
            DownloadProgressButton btn = (DownloadProgressButton) viewButton;
            mDownloadButtonController.handClick(btn,appInfo);
        }


    }

    public static class Builder{
        private boolean isShowPosition;
        private boolean isShowCategoryName;
        private boolean isShowBrief;

        private RxDownload mRxDownload;

        public Builder showPosition(boolean b){
            this.isShowPosition = b;
            return this;
        }

        public Builder showCategoryName(boolean b){

            this.isShowCategoryName =b;
            return this;
        }


        public Builder showBrief(boolean b){

            this.isShowBrief =b;
            return this;
        }

        public Builder rxDownload(RxDownload rxDownload){
            this.mRxDownload = rxDownload;
            return this;
        }

        public AppInfoAdapter build(){
            return new AppInfoAdapter(this);
        }

        public AppInfoAdapter build(int templateID){
            return new AppInfoAdapter(this,templateID);
        }
    }

}

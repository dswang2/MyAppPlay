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

/**
 * Created by dswang on 2017/9/21.
 */

public class AppInfoAdapter extends BaseQuickAdapter<AppInfo,BaseViewHolder>{

    String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";
    private Builder builder;

    public static Builder builder(){
        return  new Builder();
    }

    private AppInfoAdapter(Builder builder) {
        super(R.layout.template_app);
        this.builder = builder;
        openLoadAnimation();
    }

    private AppInfoAdapter(Builder builder,int templateID) {
        super(templateID);
        this.builder = builder;
        openLoadAnimation();
    }

    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {
        ImageLoader.load(baseImgUrl+item.getIcon(), (ImageView) helper.getView(R.id.img_icons));

        helper.setText(R.id.text_titles,item.getDisplayName())
                .setText(R.id.text_sizes,(item.getApkSize()/1024/1024)+"MB");

        TextView textViewPosition = (TextView)helper.getView(R.id.txt_position);
        textViewPosition.setVisibility(builder.isShowPosition? View.VISIBLE:View.GONE);
        textViewPosition.setText(item.getPosition()+1+".");

        TextView textViewGategory = helper.getView(R.id.txt_category);
        textViewGategory.setVisibility(builder.isShowCategoryName?View.VISIBLE:View.GONE);
        textViewGategory.setText(item.getLevel1CategoryName());

        TextView textViewBrief = helper.getView(R.id.txt_brief);
        textViewBrief.setVisibility(builder.isShowBrief?View.VISIBLE:View.GONE);
        textViewBrief.setText(item.getBriefShow());
    }

    public static class Builder{
        private boolean isShowPosition;
        private boolean isShowCategoryName;
        private boolean isShowBrief;

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

        public AppInfoAdapter build(){
            return new AppInfoAdapter(this);
        }

        public AppInfoAdapter build(int templateID){
            return new AppInfoAdapter(this,templateID);
        }
    }

}

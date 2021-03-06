package com.dbstar.myappplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dbstar.myappplay.R;
import com.dbstar.myappplay.bean.AppInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dswang on 2017/8/19.
 */

public class RecommendAppAdapter extends RecyclerView.Adapter<RecommendAppAdapter.ViewHolder> {

    private Context mContext;
    private List<AppInfo> mAppInfos;
    private LayoutInflater mLayoutInflater;

    public RecommendAppAdapter(Context context, List<AppInfo> appInfos) {
        mContext = context;
        mAppInfos = appInfos;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(mLayoutInflater.inflate(R.layout.template_recomend_app, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppInfo appInfo = mAppInfos.get(position);
        String baseUrl = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";
        Picasso.with(mContext).load(baseUrl + appInfo.getIcon()).into(holder.mImgIcon);
        holder.mTextTitle.setText(appInfo.getDisplayName());
        holder.mTextTitle.setText((appInfo.getApkSize()/1024/1024)+"MB");
    }

    @Override
    public int getItemCount() {
        return mAppInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_icon)
        ImageView mImgIcon;
        @BindView(R.id.text_title)
        TextView mTextTitle;
        @BindView(R.id.text_size)
        TextView mTextSize;
        @BindView(R.id.btn_dl)
        Button mBtnDl;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

package com.dbstar.myappplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dbstar.myappplay.R;
import com.dbstar.myappplay.bean.BannersBean;
import com.dbstar.myappplay.bean.IndexBean;
import com.dbstar.myappplay.common.imageloader.ImageLoader;
import com.dbstar.myappplay.ui.widget.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wh on 2017/9/19.
 */

public class IndexMultiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private static final int TYPE_BANNER = 591;
    private static final int TYPE_ICON = 592;
    private static final int TYPE_APPS = 593;
    private static final int TYPE_GAMES = 594;



    private Context mContext;
    private IndexBean mIndexData;
    private LayoutInflater layoutInflater;

    public IndexMultiAdapter(IndexBean indexBean, Context context) {
        mContext = context;
        mIndexData = indexBean;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        } else if (position == 1) {
            return TYPE_ICON;
        } else if (position == 2) {
            return TYPE_APPS;
        } else if (position == 3) {
            return TYPE_GAMES;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER) {
            // 返回ViewHolder对象，需要传入 view 对象
            // 1、利用item的布局文件,创建View对象
            View view = layoutInflater.inflate(R.layout.template_banner, parent, false);
            // 2、创建ViewHolder对象
            return new BannerViewHolder(view);
        } else if (viewType == TYPE_ICON) {
            View view = layoutInflater.inflate(R.layout.template_nav_icon, parent, false);
            return new NavIconViewHolder(view);
        } else if (viewType == TYPE_APPS) {
            View view = layoutInflater.inflate(R.layout.template_recyleview_with_title, parent, false);
            return new AppViewHolder(view, TYPE_APPS);
        } else if (viewType == TYPE_GAMES) {
            View view = layoutInflater.inflate(R.layout.template_recyleview_with_title, parent, false);
            return new AppViewHolder(view, TYPE_GAMES);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            List<BannersBean> banners = mIndexData.getBanners();
            List<String> urls = new ArrayList<>(banners.size());
            for (BannersBean banner : banners) {
                urls.add(banner.getThumbnail());
            }
            bannerViewHolder.banner.setViewUrls(urls);
            bannerViewHolder.banner.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
                @Override
                public void onItemClick(int position) {

                }
            });
        } else if (position == 1) {
            NavIconViewHolder bannerViewHolder = (NavIconViewHolder) holder;
            bannerViewHolder.layoutHotApp.setOnClickListener(this);
            bannerViewHolder.layoutHotGame.setOnClickListener(this);
            bannerViewHolder.layoutHotSubject.setOnClickListener(this);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public void onClick(View v) {

    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.banner)
        BannerLayout banner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            banner.setImageLoader(new BannerLayout.ImageLoader() {
                @Override
                public void displayImage(Context context, String path, ImageView imageView) {
                    ImageLoader.load(path, imageView);
                }
            });
        }
    }

    class NavIconViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.layout_hot_app)
        LinearLayout layoutHotApp;
        @BindView(R.id.layout_hot_game)
        LinearLayout layoutHotGame;
        @BindView(R.id.layout_hot_subject)
        LinearLayout layoutHotSubject;

        public NavIconViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class AppViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text)
        TextView text;
        @BindView(R.id.recycler_view)
        RecyclerView recyclerView;

        int type;

        public AppViewHolder(View itemView, int type) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.type = type;
            initRecycleView();
        }

        private void initRecycleView() {

        }
    }
}

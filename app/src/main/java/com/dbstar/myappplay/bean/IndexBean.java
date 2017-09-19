package com.dbstar.myappplay.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wh on 2017/9/19.
 */

public class IndexBean {
    private List<BannersBean> banners;
    private List<AppInfo> recommendApps;
    private List<AppInfo> recommendGames;

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public List<AppInfo> getRecommendApps() {
        return recommendApps;
    }

    public void setRecommendApps(List<AppInfo> recommendApps) {
        this.recommendApps = recommendApps;
    }

    public List<AppInfo> getRecommendGames() {
        return recommendGames;
    }

    public void setRecommendGames(List<AppInfo> recommendGames) {
        this.recommendGames = recommendGames;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

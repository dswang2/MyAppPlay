package com.dbstar.myappplay.bean;

import java.io.Serializable;

/**
 * Created by dswang on 2017/8/12.
 */
public class AppInfo implements Serializable{

    /**
     * addTime : 0
     * hasSameDevApp : false
     * videoId : 84
     * source :
     * versionName : 9.1.5.10
     * hdIcon : {"main":"AppStore/00ae44ad3f452a5b961015b11273a99455041916e"}
     * ratingScore : 4.0
     * briefShow : 集搜索 小说 新闻 明星 视频于一体
     * developerId : 0
     * fitness : 0
     * id : 318
     * level1CategoryId : 11
     * releaseKeyHash : c88a99a35ad93936b292ab74cc04d781
     * relateAppHasMore : false
     * rId : 0
     * suitableType : 0
     * briefUseIntro : false
     * ads : 0
     * publisherName : 百度在线网络技术（北京）有限公司
     * level2CategoryId : 193
     * position : 0
     * favorite : false
     * isFavorite : false
     * appendSize : 0
     * level1CategoryName : 新闻资讯
     * samDevAppHasMore : false
     * displayName : 手机百度
     * icon : AppStore/090d25635f8574e9f367b5c23b82ffc163b42bd41
     * screenshot : AppStore/0e67fc553f96f4db103a84fadaccb6e1b252275cf,AppStore/0e8a346e2a73bff8e34df4875d510fad9de42ac80,AppStore/0888d51657ad5c01a6127270f70ff39b3ec42bdbc,AppStore/0393454938d3002e684ee31b3939a07016140787c,AppStore/0188dc565ca75401d2127e70fd4ffb9831da90bb8
     * ratingTotalCount : 0
     * adType : 0
     * apkSize : 50650872
     * packageName : com.baidu.searchbox
     * updateTime : 1502936897918
     * grantCode : 0
     * versionCode : 38032640
     * diffFileSize : 0
     */

    private int addTime;
    private boolean hasSameDevApp;
    private int videoId;
    private String source;
    private String versionName;
    private HdIconBean hdIcon;
    private double ratingScore;
    private String briefShow;
    private int developerId;
    private int fitness;
    private int id;
    private int level1CategoryId;
    private String releaseKeyHash;
    private boolean relateAppHasMore;
    private int rId;
    private int suitableType;
    private boolean briefUseIntro;
    private int ads;
    private String publisherName;
    private int level2CategoryId;
    private int position;
    private boolean favorite;
    private boolean isFavorite;
    private int appendSize;
    private String level1CategoryName;
    private boolean samDevAppHasMore;
    private String displayName;
    private String icon;
    private String screenshot;
    private int ratingTotalCount;
    private int adType;
    private int apkSize;
    private String packageName;
    private long updateTime;
    private int grantCode;
    private int versionCode;
    private int diffFileSize;

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public boolean isHasSameDevApp() {
        return hasSameDevApp;
    }

    public void setHasSameDevApp(boolean hasSameDevApp) {
        this.hasSameDevApp = hasSameDevApp;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public HdIconBean getHdIcon() {
        return hdIcon;
    }

    public void setHdIcon(HdIconBean hdIcon) {
        this.hdIcon = hdIcon;
    }

    public double getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(double ratingScore) {
        this.ratingScore = ratingScore;
    }

    public String getBriefShow() {
        return briefShow;
    }

    public void setBriefShow(String briefShow) {
        this.briefShow = briefShow;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel1CategoryId() {
        return level1CategoryId;
    }

    public void setLevel1CategoryId(int level1CategoryId) {
        this.level1CategoryId = level1CategoryId;
    }

    public String getReleaseKeyHash() {
        return releaseKeyHash;
    }

    public void setReleaseKeyHash(String releaseKeyHash) {
        this.releaseKeyHash = releaseKeyHash;
    }

    public boolean isRelateAppHasMore() {
        return relateAppHasMore;
    }

    public void setRelateAppHasMore(boolean relateAppHasMore) {
        this.relateAppHasMore = relateAppHasMore;
    }

    public int getRId() {
        return rId;
    }

    public void setRId(int rId) {
        this.rId = rId;
    }

    public int getSuitableType() {
        return suitableType;
    }

    public void setSuitableType(int suitableType) {
        this.suitableType = suitableType;
    }

    public boolean isBriefUseIntro() {
        return briefUseIntro;
    }

    public void setBriefUseIntro(boolean briefUseIntro) {
        this.briefUseIntro = briefUseIntro;
    }

    public int getAds() {
        return ads;
    }

    public void setAds(int ads) {
        this.ads = ads;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public int getLevel2CategoryId() {
        return level2CategoryId;
    }

    public void setLevel2CategoryId(int level2CategoryId) {
        this.level2CategoryId = level2CategoryId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public int getAppendSize() {
        return appendSize;
    }

    public void setAppendSize(int appendSize) {
        this.appendSize = appendSize;
    }

    public String getLevel1CategoryName() {
        return level1CategoryName;
    }

    public void setLevel1CategoryName(String level1CategoryName) {
        this.level1CategoryName = level1CategoryName;
    }

    public boolean isSamDevAppHasMore() {
        return samDevAppHasMore;
    }

    public void setSamDevAppHasMore(boolean samDevAppHasMore) {
        this.samDevAppHasMore = samDevAppHasMore;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public int getRatingTotalCount() {
        return ratingTotalCount;
    }

    public void setRatingTotalCount(int ratingTotalCount) {
        this.ratingTotalCount = ratingTotalCount;
    }

    public int getAdType() {
        return adType;
    }

    public void setAdType(int adType) {
        this.adType = adType;
    }

    public int getApkSize() {
        return apkSize;
    }

    public void setApkSize(int apkSize) {
        this.apkSize = apkSize;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getGrantCode() {
        return grantCode;
    }

    public void setGrantCode(int grantCode) {
        this.grantCode = grantCode;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public int getDiffFileSize() {
        return diffFileSize;
    }

    public void setDiffFileSize(int diffFileSize) {
        this.diffFileSize = diffFileSize;
    }

    public static class HdIconBean {
        /**
         * main : AppStore/00ae44ad3f452a5b961015b11273a99455041916e
         */

        private String main;

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }
    }

}

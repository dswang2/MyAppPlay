package com.dbstar.myappplay.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dbstar.myappplay.R;
import com.dbstar.myappplay.bean.AppDetail;
import com.dbstar.myappplay.bean.AppInfo;
import com.dbstar.myappplay.common.imageloader.ImageLoader;
import com.dbstar.myappplay.common.util.Constant;
import com.dbstar.myappplay.common.util.DateUtils;
import com.dbstar.myappplay.common.util.DensityUtil;
import com.dbstar.myappplay.di.component.AppComponent;
import com.dbstar.myappplay.di.component.DaggerAppDetailComponent;
import com.dbstar.myappplay.di.module.AppDetaiModule;
import com.dbstar.myappplay.di.module.AppModelModule;
import com.dbstar.myappplay.presenter.AppDetailPresenter;
import com.dbstar.myappplay.presenter.contract.AppDetailContract;
import com.dbstar.myappplay.ui.adapter.AppInfoAdapter;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import butterknife.BindView;

/**
 * Created by wh on 2017/10/30.
 */

public class AppDetailActivity extends BaseActivity<AppDetailPresenter> implements AppDetailContract.IDetailView {

    @BindView(R.id.view_cache)
    public View view_cache;

    @BindView(R.id.view_coordinator)
    public CoordinatorLayout view_coordinator;

    @BindView(R.id.detail_toolbar)
    public Toolbar detail_toolbar;

    @BindView(R.id.img_icon)
    ImageView img_icon;

    @BindView(R.id.linear_screenshot)
    LinearLayout linear_screenshot;

    @BindView(R.id.view_introduction)
    ExpandableTextView view_introduction;

    @BindView(R.id.txt_update_time)
    TextView mTxtUpdateTime;
    @BindView(R.id.txt_version)
    TextView mTxtVersion;
    @BindView(R.id.txt_apk_size)
    TextView mTxtApkSize;
    @BindView(R.id.txt_publisher)
    TextView mTxtPublisher;
    @BindView(R.id.txt_publisher2)
    TextView mTxtPublisher2;

    @BindView(R.id.recycler_view_same_dev)
    RecyclerView recycler_view_same_dev;

    @BindView(R.id.recycler_view_similar_dev)
    RecyclerView recycler_view_similar_dev;

    private LayoutInflater inflater;
    private ObjectAnimator animator;
    private AppInfo mAppInfo;
    private AppInfoAdapter mAdapter;
    private AppInfoAdapter mAdapter4Similar;


    @Override
    int setLayoutID() {
        return R.layout.activity_app_detail;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerAppDetailComponent.builder()
                .appComponent(appComponent)
                .appModelModule(new AppModelModule())
                .appDetaiModule(new AppDetaiModule(this))
                .build().inject(this);
    }

    @Override
    protected void init() {
        initVars();
        getAppInfos();
        initToolbar();
        initAnimitor();
        initRecyclerView();
        initData();
    }

    private void initVars() {
        inflater = LayoutInflater.from(this);
    }

    private void getAppInfos() {
        mAppInfo = (AppInfo) getIntent().getSerializableExtra(Constant.APPINFO);
        if (mAppInfo != null) {
            String urlIcon = mAppInfo.getIcon();
            ImageLoader.load(Constant.BASE_IMG_URL + urlIcon, img_icon);
        }
    }

    private void initData() {
        // 暖暖环游世界助手（id：85723）
        if (mAppInfo != null) {
            mPresenter.requestAppDetails(mAppInfo.getId());
        }
    }

    private void initAnimitor() {
        View view = mAppApplication.getView();
        Bitmap bitmap = getViewImageCache(view);
        if (bitmap != null) {
            view_cache.setBackgroundDrawable(new BitmapDrawable(bitmap));
        }

        // 获取到View的位置，距离屏幕左、上距离
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        //  view.getLocationInWindow(location);     // 也获取不到 View 与状态栏的距离
        int left = location[0];
        // top，是View 与屏幕顶端的距离(OnScreen)
        int top = location[1];

        // 定义 view_cache 的布局位置信息：MarginLayoutParams
//        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(view_cache.getLayoutParams());
//        // marginLayoutParams.topMargin ，设置的是View上边沿和状态栏底部的距离
//        marginLayoutParams.topMargin = top - DensityUtil.getStatusBarH(this);
//        marginLayoutParams.leftMargin = left;
//        marginLayoutParams.width = view.getWidth();
//        marginLayoutParams.height = view.getHeight();
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(marginLayoutParams);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(view_cache.getLayoutParams());
        layoutParams.topMargin = top - DensityUtil.getStatusBarH(this);
        layoutParams.leftMargin = left;
        layoutParams.width = view.getWidth();
        layoutParams.height = view.getHeight();


        // 设置 view_cache 的布局位置信息
        view_cache.setLayoutParams(layoutParams);

        open();
    }

    private void initToolbar() {
        detail_toolbar.setNavigationIcon(new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_arrow_back)
                .sizeDp(16)
                .color(getResources().getColor(R.color.md_white_1000)));

        detail_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDetailActivity.this.finish();
            }
        });
    }


    private void open() {
        // 屏幕高度
        int screenH = DensityUtil.getScreenH(this);
        // 原高度
        int height = view_cache.getLayoutParams().height;
        // 缩放比例
        float f = 2 * screenH / height;
        animator = ObjectAnimator.ofFloat(view_cache, "scaleY", 1f, f);
        animator.setStartDelay(500);
        animator.setDuration(500);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view_cache.setVisibility(View.GONE);
                view_coordinator.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                view_cache.setBackgroundColor(getResources().getColor(R.color.md_white_1000));
            }
        });
        animator.start();

    }

    // 根绝缓存View绘制Bitmap
    private Bitmap getViewImageCache(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        if (null == bitmap) {
            return null;
        }
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());
        view.destroyDrawingCache();
        return bitmap;
    }

    @Override
    protected void onDestroy() {
        animator.cancel();
        super.onDestroy();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showDetail(AppDetail detail) {
        // 运行截图
        showScreenshot(detail);
        // 详情介绍
        view_introduction.setText(detail.getIntroduction());

        // 详细信息
        mTxtUpdateTime.setText(DateUtils.formatDate(detail.getUpdateTime()));
        mTxtApkSize.setText((detail.getApkSize() / 1014 / 1024) + " Mb");
        mTxtVersion.setText(detail.getVersionName());
        mTxtPublisher.setText(detail.getPublisherName());
        mTxtPublisher2.setText(detail.getPublisherName());

        // 相同开发者的应用列表
        mAdapter.addData(detail.getSameDevAppInfoList());

        // 相关应用列表
        mAdapter4Similar.addData(detail.getRelateAppInfoList());
    }

    private void initRecyclerView() {
        //相同开发者应用列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_view_same_dev.setLayoutManager(layoutManager);
//        recycler_view_same_dev.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recycler_view_same_dev.setItemAnimator(new DefaultItemAnimator());
        // 相同开发者应用列表 数据适配器
        mAdapter = AppInfoAdapter.builder().showPosition(false).showCategoryName(false).showBrief(false).build(R.layout.template_app_horizontal);
        recycler_view_same_dev.setAdapter(mAdapter);

        //　相关应用列表
        LinearLayoutManager layoutManager4Similar = new LinearLayoutManager(this);
        layoutManager4Similar.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_view_similar_dev.setLayoutManager(layoutManager4Similar);
//        recycler_view_same_dev.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recycler_view_same_dev.setItemAnimator(new DefaultItemAnimator());
        // 相关应用列表 数据适配器
        mAdapter4Similar = AppInfoAdapter.builder().showPosition(false).showCategoryName(false).showBrief(false).build(R.layout.template_app_horizontal);
        recycler_view_similar_dev.setAdapter(mAdapter4Similar);


    }

    private void showScreenshot(AppDetail detail) {
        String urlScreenshot = detail.getScreenshot();
        String[] arrUrlScreenshot = urlScreenshot.split(",");
        int length = arrUrlScreenshot.length;
        for (int i = 0; i < length; i++) {
//            arrUrlScreenshot[i]
            ImageView view = (ImageView) inflater.inflate(R.layout.template_image, linear_screenshot, false);
            ImageLoader.load(Constant.BASE_IMG_URL + arrUrlScreenshot[i], view);
            linear_screenshot.addView(view);
        }
    }
}

package com.dbstar.myappplay.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dbstar.myappplay.R;
import com.dbstar.myappplay.bean.AppDetail;
import com.dbstar.myappplay.common.util.DensityUtil;
import com.dbstar.myappplay.di.component.AppComponent;
import com.dbstar.myappplay.di.component.DaggerAppDetailComponent;
import com.dbstar.myappplay.di.module.AppDetaiModule;
import com.dbstar.myappplay.di.module.AppModelModule;
import com.dbstar.myappplay.presenter.AppDetailPresenter;
import com.dbstar.myappplay.presenter.contract.AppDetailContract;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

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

    @BindView(R.id.tv_detail)
    public TextView tv_detail;

    private ObjectAnimator animator;


    @Override
    int setLayoutID() {
        return R.layout.app_detail_activity;
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

        initToolbar();
        initAnimitor();
        initData();
    }

    private void initData() {
        // 暖暖环游世界助手（id：85723）
        mPresenter.requestAppDetails(85723);
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
        tv_detail.setText(detail.toString());
    }
}

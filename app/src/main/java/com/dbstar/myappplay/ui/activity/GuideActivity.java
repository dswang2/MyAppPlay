package com.dbstar.myappplay.ui.activity;

import android.support.v4.view.ViewPager;

import com.dbstar.myappplay.R;
import com.dbstar.myappplay.di.component.AppComponent;

import butterknife.BindView;

/**
 * Created by wh on 2017/8/31.
 */

public class GuideActivity extends BaseActivity {

    @BindView(R.id.guide_viewpager)
    ViewPager  mViewPager;

    @Override
    int setLayoutID() {
        return R.layout.activity_guide;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
    }

    @Override
    protected void init() {
        // 设置ViewPager数据

    }
}

package com.dbstar.myappplay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.dbstar.myappplay.R;
import com.dbstar.myappplay.common.util.ACache;
import com.dbstar.myappplay.common.util.Constant;
import com.dbstar.myappplay.di.component.AppComponent;
import com.dbstar.myappplay.ui.adapter.GuideFragmentAdapter;
import com.dbstar.myappplay.ui.fragment.BaseFragment;
import com.dbstar.myappplay.ui.fragment.GuideFragment;
import com.dbstar.myappplay.ui.widget.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wh on 2017/8/31.
 */

public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.guide_viewpager)
    ViewPager mViewPager;

    @BindView(R.id.btn_enter)
    Button mBtnEnter;

    @BindView(R.id.guide_indicator)
    CircleIndicator mCircleIndicator;

    private GuideFragmentAdapter mAdapter;


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
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(GuideFragment.newInstance(R.drawable.guide_1, R.color.color_bg_guide1, R.string.guide_1));
        fragments.add(GuideFragment.newInstance(R.drawable.guide_2, R.color.color_bg_guide2, R.string.guide_2));
        fragments.add(GuideFragment.newInstance(R.drawable.guide_3, R.color.color_bg_guide3, R.string.guide_3));

        mAdapter = new GuideFragmentAdapter(getSupportFragmentManager());
        mAdapter.setFragments(fragments);

        // 设置 ViewPager 默认页面
        mViewPager.setCurrentItem(0);
        // 设置 ViewPager 预加载页面的数量，默认为 1
        mViewPager.setOffscreenPageLimit(1);
        // 设置 ViewPager 滑动监听
        mViewPager.addOnPageChangeListener(this);

        mViewPager.setAdapter(mAdapter);

        mCircleIndicator.setViewPager(mViewPager);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mBtnEnter.setVisibility((position == mAdapter.getCount() - 1) ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_enter)
    public void onClick() {
        ACache.get(this).put(Constant.IS_SHOW_GUIDE,"0");
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }
}

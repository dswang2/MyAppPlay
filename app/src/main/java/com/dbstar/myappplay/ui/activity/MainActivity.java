package com.dbstar.myappplay.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dbstar.myappplay.R;
import com.dbstar.myappplay.bean.User;
import com.dbstar.myappplay.common.imageloader.GlideCircleTransform;
import com.dbstar.myappplay.common.util.ToastUtils;
import com.dbstar.myappplay.di.component.AppComponent;
import com.dbstar.myappplay.ui.adapter.ViewPagerAdapter;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_nav)
    NavigationView mainNav;
    @BindView(R.id.main_drawer)
    DrawerLayout mainDrawer;
    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;
    @BindView(R.id.main_tab_layout)
    TabLayout mainTabLayout;
    @BindView(R.id.main_view_pager)
    ViewPager mainViewPager;
    private View headerView;
    private ImageView header_iv;


    @Override
    protected void init() {

        // 注册RxBus
        RxBus.get().register(this);

        // 初始化侧滑菜单
        initDrawLayout();

        // 初始化Toolbar
        initToolbar();

        //初始化 TabLayout
        initTabLayout();

        initUser();
    }

    @Subscribe
    public void getUser(User user){
        Log.e("dswang","MainActivity.getUser(MainActivity.java:60)"+user.toString());
        // 利用user更新头像
        // .transform(new GlideCircleTransform(this)), glide 加载圆形ImageView图像
        Glide.with(this).load(user.getLogo_url()).transform(new GlideCircleTransform(this)).into(header_iv);
    }

    private void initUser() {
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                //mainDrawer.closeDrawers();
            }
        });

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        ;
    }

    @Override
    int setLayoutID() {
        return R.layout.activity_main;
    }

    private void initTabLayout() {
        PagerAdapter mainPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // 设置所有Fragment都进入缓存，这样的话，应用对内存要求高一些，但应用也更快一些
        mainViewPager.setOffscreenPageLimit(mainPagerAdapter.getCount());

        mainViewPager.setAdapter(mainPagerAdapter);
        mainTabLayout.setupWithViewPager(mainViewPager);
    }

    private void initToolbar() {
        //  ToolBar 与侧滑菜单联动
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mainDrawer, mainToolbar, R.string.open, R.string.close);
        drawerToggle.syncState();
        mainDrawer.addDrawerListener(drawerToggle);


        // ToolBar 菜单添加
        mainToolbar.inflateMenu(R.menu.toolbar_menu);
        // ToolBar 菜单监听
        mainToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_search:
                        ToastUtils.showSafeToast(MainActivity.this, "搜索");
                        break;
                    case R.id.action_download:
                        ToastUtils.showSafeToast(MainActivity.this, "下载");
                        break;
                }
                return true;
            }
        });
    }

    private void initDrawLayout() {
        // 侧滑菜单头部点击监听
        headerView = mainNav.getHeaderView(0);
        // 用户头像
        header_iv = (ImageView) headerView.findViewById(R.id.header_iv);

        // 侧滑菜单菜单点击监听
        mainNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.main_nav_menu_appAndGame:
                        ToastUtils.showSafeToast(MainActivity.this, "应用和游戏");
                        //mainDrawer.closeDrawers();
                        break;
                    case R.id.main_nav_menu_download:
                        ToastUtils.showSafeToast(MainActivity.this, "下载");
                        //mainDrawer.closeDrawers();
                        break;
                    case R.id.main_nav_menu_update:
                        ToastUtils.showSafeToast(MainActivity.this, "更新");
                        //mainDrawer.closeDrawers();
                        break;
                    case R.id.main_nav_menu_message:
                        ToastUtils.showSafeToast(MainActivity.this, "消息");
                        //mainDrawer.closeDrawers();
                        break;
                    case R.id.main_nav_menu_setting:
                        ToastUtils.showSafeToast(MainActivity.this, "设置");
                        //mainDrawer.closeDrawers();
                        break;
                    case R.id.main_nav_menu_help:
                        ToastUtils.showSafeToast(MainActivity.this, "帮助和反馈");
                        //mainDrawer.closeDrawers();
                        break;
                    case R.id.main_nav_menu_about:
                        ToastUtils.showSafeToast(MainActivity.this, "关于");
                        //mainDrawer.closeDrawers();
                        break;
                }
                return false;
            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }
}

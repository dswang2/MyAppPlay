package com.dbstar.myappplay.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.dbstar.myappplay.R;
import com.dbstar.myappplay.ui.adapter.ViewPagerAdapter;
import com.dbstar.myappplay.common.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // 初始化侧滑菜单
        initDrawLayout();

        // 初始化Toolbar
        initToolbar();

        //初始化 TabLayout
        initTabLayout();
    }

    private void initTabLayout() {
        PagerAdapter mainPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
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
        View headerView = mainNav.getHeaderView(0);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showSafeToast(MainActivity.this, "点击了侧滑菜单头部");
                mainDrawer.closeDrawers();
            }
        });
        // 侧滑菜单菜单点击监听
        mainNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.main_nav_menu_appAndGame:
                        ToastUtils.showSafeToast(MainActivity.this, "应用和游戏");
                        mainDrawer.closeDrawers();
                        break;
                    case R.id.main_nav_menu_download:
                        ToastUtils.showSafeToast(MainActivity.this, "下载");
                        mainDrawer.closeDrawers();
                        break;
                    case R.id.main_nav_menu_update:
                        ToastUtils.showSafeToast(MainActivity.this, "更新");
                        mainDrawer.closeDrawers();
                        break;
                    case R.id.main_nav_menu_message:
                        ToastUtils.showSafeToast(MainActivity.this, "消息");
                        mainDrawer.closeDrawers();
                        break;
                    case R.id.main_nav_menu_setting:
                        ToastUtils.showSafeToast(MainActivity.this, "设置");
                        mainDrawer.closeDrawers();
                        break;
                    case R.id.main_nav_menu_help:
                        ToastUtils.showSafeToast(MainActivity.this, "帮助和反馈");
                        mainDrawer.closeDrawers();
                        break;
                    case R.id.main_nav_menu_about:
                        ToastUtils.showSafeToast(MainActivity.this, "关于");
                        mainDrawer.closeDrawers();
                        break;
                }
                return false;
            }
        });


    }

}
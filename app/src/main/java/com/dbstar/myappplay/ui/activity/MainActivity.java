package com.dbstar.myappplay.ui.activity;

import android.Manifest;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dbstar.myappplay.R;
import com.dbstar.myappplay.bean.User;
import com.dbstar.myappplay.common.imageloader.GlideCircleTransform;
import com.dbstar.myappplay.common.util.ACache;
import com.dbstar.myappplay.common.util.Constant;
import com.dbstar.myappplay.common.util.LogUtils;
import com.dbstar.myappplay.common.util.ToastUtils;
import com.dbstar.myappplay.di.component.AppComponent;
import com.dbstar.myappplay.ui.adapter.ViewPagerAdapter;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import rx.functions.Action1;

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

        // 申请权限
        rxpermissionTest();

        initUser();
    }

    @Subscribe
    public void getUser(User user) {
        Log.e("dswang", "MainActivity.getUser(MainActivity.java:60)" + user.toString());
        initUserHeadView(user);
    }

    private void initUserHeadView(User user) {
        // 利用user更新头像
        // .transform(new GlideCircleTransform(this)), glide 加载圆形ImageView图像
        Glide.with(this).load(user.getLogo_url()).transform(new GlideCircleTransform(this)).into(header_iv);
    }

    private void initUser() {

        // 再登录

        // 从Acache中读到登录信息，如果已经登录，显示用户头像；如果没有登录，则跳转到登录页面
        Object objUser = ACache.get(this).getAsObject(Constant.USER);
        String objToken = ACache.get(this).getAsString(Constant.TOKEN);

        if (objUser == null || objToken == null || "".equals(objToken)) {
            headerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    //mainDrawer.closeDrawers();
                }
            });
        } else {
            User user = (User) objUser;
            LogUtils.e("dswang", "MainActivity.initUser.user = " + user.toString());
            initUserHeadView(user);
        }


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
                    case R.id.main_nav_menu_quit:
                        ToastUtils.showSafeToast(MainActivity.this, "退出登录");
                        logout();
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

    private void logout() {
        ACache aCache = ACache.get(this);
        aCache.put(Constant.USER, "");
        aCache.put(Constant.TOKEN, "");
        // 设置默认头像
//        header_iv.setImageDrawable(new IconicsDrawable(this, Cniao5Font.Icon.cniao_head).colorRes(R.color.white));
        header_iv.setImageDrawable(null);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                //mainDrawer.closeDrawers();
            }
        });
        Toast.makeText(MainActivity.this, "您已退出登录", Toast.LENGTH_LONG).show();
    }


    private void rxpermissionTest() {
        RxPermissions rxPermission = new RxPermissions(MainActivity.this);
        rxPermission
                .requestEach(
//                        Manifest.permission.ACCESS_FINE_LOCATION,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
//                        Manifest.permission.READ_CALENDAR,
//                        Manifest.permission.READ_CALL_LOG,
//                        Manifest.permission.READ_CONTACTS,
//                        Manifest.permission.READ_PHONE_STATE,
//                        Manifest.permission.READ_SMS,
//                        Manifest.permission.RECORD_AUDIO,
//                        Manifest.permission.CAMERA,
//                        Manifest.permission.CALL_PHONE,
//                        Manifest.permission.SEND_SMS
                )
                .subscribe(new Action1<Permission>() {
                    @Override
                    public void call(Permission permission) {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            Log.e("CategoryFragment", "accept(CategoryFragment.java:52) " + permission.name + " is granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            Log.e("CategoryFragment", "accept(CategoryFragment.java:56) " + permission.name + " is denied. More info should be provided.");
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            Log.e("CategoryFragment", "accept(CategoryFragment.java:56) " + permission.name + " is denied.");
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }
}

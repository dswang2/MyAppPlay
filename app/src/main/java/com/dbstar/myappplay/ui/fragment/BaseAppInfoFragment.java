package com.dbstar.myappplay.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dbstar.myappplay.R;
import com.dbstar.myappplay.bean.AppInfo;
import com.dbstar.myappplay.bean.PageBean;
import com.dbstar.myappplay.common.util.Constant;
import com.dbstar.myappplay.di.component.AppComponent;
import com.dbstar.myappplay.presenter.AppInfoPresenter;
import com.dbstar.myappplay.presenter.contract.AppInfoContract;
import com.dbstar.myappplay.ui.activity.AppDetailActivity;
import com.dbstar.myappplay.ui.adapter.AppInfoAdapter;

import butterknife.BindView;

/**
 * Created by wh on 2017/6/6.
 */
public abstract class BaseAppInfoFragment extends ProgressFragment<AppInfoPresenter> implements AppInfoContract.AppInfoView, BaseQuickAdapter.RequestLoadMoreListener {

    int page =0;

    @BindView(R.id.recom_rv_list)

    RecyclerView mRecyclerView;
    private AppInfoAdapter appInfoAdapter;

    @Override
    protected void init() {
        // 如果，没有在
        // MainActivity中设置ViewPager适配器的缓存数目 mainViewPager.setOffscreenPageLimit(mainPagerAdapter.getCount());
        // 就有必要在此设置 page = 0
        mPresenter.requestDatas(type(),page);
        initRecyclerView();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    protected abstract int type();

    protected void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 将 pageBeam填充到RecycleList中
        appInfoAdapter = initAppInfoAdapter();
        appInfoAdapter.setOnLoadMoreListener(this);
        appInfoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                // 设置view作为缓存
                mApplication.setViewCache(view);

                // 传递数据并跳转
                AppInfo appInfo = (AppInfo) adapter.getItem(position);
                Intent intent = new Intent(getActivity(), AppDetailActivity.class);
                intent.putExtra(Constant.APPINFO,appInfo);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(appInfoAdapter);
    }

    protected abstract AppInfoAdapter initAppInfoAdapter();

    @Override
    protected void onEmptyClick() {
    }

    // BaseInfoFragment 作为 ViewPager 的子页面，提供标题，显示在Tablayout上
    // ViewPagerAdapter 的 getPageTitle() 会调用到这个标题
    public abstract String getTitle();

    @Override
    protected int setLayoutId() {
        return R.layout.frag_recommend;
    }

    @Override
    public void showResults(PageBean<AppInfo> pageBean) {
        appInfoAdapter.addData(pageBean.getDatas());
        if(pageBean.isHasMore()){
            page ++;
        }
        appInfoAdapter.setEnableLoadMore(pageBean.isHasMore());
    }

    @Override
    public void onLoadMoreComplete() {
        // 非常非常重要，标记加载更多的完成
        appInfoAdapter.loadMoreComplete();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.requestDatas(type(),page);
    }
}

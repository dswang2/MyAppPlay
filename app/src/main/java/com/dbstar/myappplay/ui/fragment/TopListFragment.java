package com.dbstar.myappplay.ui.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dbstar.myappplay.R;
import com.dbstar.myappplay.bean.AppInfo;
import com.dbstar.myappplay.bean.PageBean;
import com.dbstar.myappplay.di.component.AppComponent;
import com.dbstar.myappplay.di.component.DaggerAppInfoComponent;
import com.dbstar.myappplay.di.module.AppInfoModule;
import com.dbstar.myappplay.presenter.AppInfoPresenter;
import com.dbstar.myappplay.presenter.contract.AppInfoContract;
import com.dbstar.myappplay.ui.adapter.AppInfoAdapter;

import butterknife.BindView;

/**
 * Created by wh on 2017/6/6.
 */
public class TopListFragment extends ProgressFragment<AppInfoPresenter> implements AppInfoContract.AppInfoView {

    int page =0;

    @BindView(R.id.recom_rv_list)

    RecyclerView recomRvList;
    private AppInfoAdapter appInfoAdapter;

    @Override
    protected void init() {
        mPresenter.requestDatas(page);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recomRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recomRvList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        recomRvList.setItemAnimator(new DefaultItemAnimator());
        // 将 pageBeam填充到RecycleList中
        appInfoAdapter = AppInfoAdapter.builder().showPosition(true).showCategoryName(true).showBrief(true).build();
        appInfoAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.requestDatas(page);
            }
        });
        recomRvList.setAdapter(appInfoAdapter);
    }

    @Override
    protected void onEmptyClick() {

    }

    @Override
    public String getTitle() {
        return "排行榜";
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder()
                .appInfoModule(new AppInfoModule(this))
                .appComponent(appComponent)
                .build().inject(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.frag_recommend;
    }

    @Override
    public void showResults(PageBean<AppInfo> pageBean) {
        appInfoAdapter.addData(pageBean.getDatas());
        if(pageBean.isHasMore()){
            page ++;
            appInfoAdapter.setEnableLoadMore(pageBean.isHasMore());
        }else {
            appInfoAdapter.setEnableLoadMore(false);
        }
    }

    @Override
    public void onLoadMoreComplete() {
        // 非常非常重要，标记加载更多的完成
        appInfoAdapter.loadMoreComplete();
    }
}

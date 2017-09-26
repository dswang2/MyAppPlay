package com.dbstar.myappplay.ui.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dbstar.myappplay.R;
import com.dbstar.myappplay.bean.AppInfo;
import com.dbstar.myappplay.bean.PageBean;
import com.dbstar.myappplay.presenter.AppInfoPresenter;
import com.dbstar.myappplay.presenter.contract.AppInfoContract;
import com.dbstar.myappplay.ui.adapter.AppInfoAdapter;

import butterknife.BindView;

/**
 * Created by wh on 2017/6/6.
 */
public abstract class BaseAppInfoFragment extends ProgressFragment<AppInfoPresenter> implements AppInfoContract.AppInfoView {

    int page =0;

    @BindView(R.id.recom_rv_list)

    RecyclerView recomRvList;
    private AppInfoAdapter appInfoAdapter;

    @Override
    protected void init() {
        // 如果，没有在
        // MainActivity中设置ViewPager适配器的缓存数目 mainViewPager.setOffscreenPageLimit(mainPagerAdapter.getCount());
        // 就有必要在此设置 page = 0
        mPresenter.requestDatas(type(),page);
        initRecyclerView();
    }

    protected abstract int type();

    private void initRecyclerView() {
        recomRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recomRvList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        recomRvList.setItemAnimator(new DefaultItemAnimator());
        // 将 pageBeam填充到RecycleList中
        appInfoAdapter = initAppInfoAdapter();
        appInfoAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.requestDatas(type(),page);
            }
        });
        recomRvList.setAdapter(appInfoAdapter);
    }

    protected abstract AppInfoAdapter initAppInfoAdapter();

    @Override
    protected void onEmptyClick() {
    }

    @Override
    public String getTitle() {
        return "排行榜";
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
        }
        appInfoAdapter.setEnableLoadMore(pageBean.isHasMore());
    }

    @Override
    public void onLoadMoreComplete() {
        // 非常非常重要，标记加载更多的完成
        appInfoAdapter.loadMoreComplete();
    }
}

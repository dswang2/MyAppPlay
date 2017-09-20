package com.dbstar.myappplay.ui.fragment;

import android.app.ProgressDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dbstar.myappplay.R;
import com.dbstar.myappplay.bean.IndexBean;
import com.dbstar.myappplay.common.util.ToastUtils;
import com.dbstar.myappplay.di.component.AppComponent;
import com.dbstar.myappplay.di.component.DaggerRecommendComponent;
import com.dbstar.myappplay.di.module.RecommendModule;
import com.dbstar.myappplay.presenter.RecommendPresenter;
import com.dbstar.myappplay.presenter.contract.RecommendContract;
import com.dbstar.myappplay.ui.adapter.IndexMultiAdapter;
import com.dbstar.myappplay.ui.adapter.RecommendAppAdapter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by wh on 2017/6/6.
 */
public class RecommendFragment extends ProgressFragment<RecommendPresenter> implements RecommendContract.View {
    @BindView(R.id.recom_rv_list)
    RecyclerView mRecomRvList;

    private RecommendAppAdapter mRecommendAppAdapter;
    private IndexMultiAdapter mIndexMultiAdapter;

    @Inject
    ProgressDialog mProgressDialog;


    @Override
    protected void init() {
        // 父类定义了 mPresenter
        mPresenter.requestDatas();
    }

    private void initRecyclerView(IndexBean indexBean) {
        mRecomRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecomRvList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        mRecomRvList.setItemAnimator(new DefaultItemAnimator());

        mIndexMultiAdapter = new IndexMultiAdapter(indexBean,getActivity());
        mRecomRvList.setAdapter(mIndexMultiAdapter);

//        mRecommendAppAdapter = new RecommendAppAdapter(getActivity(), appInfos);
//        mRecomRvList.setAdapter(mRecommendAppAdapter);


    }


    @Override
    protected void onEmptyClick() {
        mPresenter.requestDatas();
    }

    @Override
    public String getTitle() {
        return "推荐";
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerRecommendComponent.builder()
                .recommendModule(new RecommendModule(this))
                .appComponent(appComponent)
                .build().inject(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.frag_recommend;
    }


    @Override
    public void showResults(IndexBean indexBean) {
        initRecyclerView(indexBean);
    }

    @Override
    public void showNoData() {
        ToastUtils.showSafeToast(getActivity(), "暂时无数据");
    }

//    @Override
//    public void showError(String message) {
//        ToastUtils.showSafeToast(getActivity(), "数据获取出错");
//    }

//    @Override
//    public void showLoading() {
//        mProgressDialog.show();
//    }
//
//    @Override
//    public void dismissLoading() {
//        mProgressDialog.dismiss();
//    }

}

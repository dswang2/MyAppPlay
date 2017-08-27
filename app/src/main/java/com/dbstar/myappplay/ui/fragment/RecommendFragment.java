package com.dbstar.myappplay.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dbstar.myappplay.AppApplication;
import com.dbstar.myappplay.R;
import com.dbstar.myappplay.bean.AppInfo;
import com.dbstar.myappplay.di.component.DaggerRecommendComponent;
import com.dbstar.myappplay.di.module.RecommendModule;
import com.dbstar.myappplay.presenter.contract.RecommendContract;
import com.dbstar.myappplay.ui.adapter.RecommendAppAdapter;
import com.dbstar.myappplay.common.util.ToastUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wh on 2017/6/6.
 */
public class RecommendFragment extends MyBaseFragment implements RecommendContract.View {
    @BindView(R.id.recom_rv_list)
    RecyclerView mRecomRvList;
    Unbinder unbinder;

    private RecommendAppAdapter mRecommendAppAdapter;

    @Inject
    ProgressDialog mProgressDialog;

    @Inject
    RecommendContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_recommend, container, false);
        unbinder = ButterKnife.bind(this, view);
        DaggerRecommendComponent.builder()
                .appComponent(((AppApplication) (this.getActivity().getApplication())).getAppComponent())
                .recommendModule(new RecommendModule(this)).build().inject(this);

        initData();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 依赖注入
    }

    private void initData() {
        mPresenter.requestDatas();
    }

    private void initRecyclerView(List<AppInfo> appInfos) {
        mRecomRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecomRvList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        mRecomRvList.setItemAnimator(new DefaultItemAnimator());
        mRecommendAppAdapter = new RecommendAppAdapter(getActivity(), appInfos);
        mRecomRvList.setAdapter(mRecommendAppAdapter);
    }


    @Override
    public String getTitle() {
        return "推荐";
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showResults(List<AppInfo> appInfos) {
        initRecyclerView(appInfos);
    }

    @Override
    public void showNoData() {
        ToastUtils.showSafeToast(getActivity(), "暂时无数据");
    }

    @Override
    public void showError(String message) {
        ToastUtils.showSafeToast(getActivity(), "数据获取出错");
    }

    @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void dismissLoading() {
        mProgressDialog.dismiss();
    }
}

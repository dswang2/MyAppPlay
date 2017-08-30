package com.dbstar.myappplay.presenter;

import com.dbstar.myappplay.bean.AppInfo;
import com.dbstar.myappplay.bean.PageBean;
import com.dbstar.myappplay.data.model.RecommendModel;
import com.dbstar.myappplay.presenter.contract.RecommendContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dswang on 2017/8/20.
 */

public class RecommendPresenter extends BasePresenter<RecommendModel,RecommendContract.View> {


    public RecommendPresenter(RecommendModel model, RecommendContract.View view) {
        super(model, view);
    }

    public void requestDatas(){
        mView.showLoading();
        mModel.getApps(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                if(response!=null){
                    mView.showResults(response.body().getDatas());
                }
                else {
                    mView.showNoData();
                }

                mView.dismissLoading();
            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
                mView.dismissLoading();
                mView.showError(t.getMessage());
            }
        });
    }


}

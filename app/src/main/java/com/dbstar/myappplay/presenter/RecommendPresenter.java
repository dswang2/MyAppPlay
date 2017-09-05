package com.dbstar.myappplay.presenter;

import com.dbstar.myappplay.bean.AppInfo;
import com.dbstar.myappplay.bean.PageBean;
import com.dbstar.myappplay.common.rx.RxHttpReponseCompat;
import com.dbstar.myappplay.data.model.RecommendModel;
import com.dbstar.myappplay.presenter.contract.RecommendContract;

import rx.Subscriber;

/**
 * Created by dswang on 2017/8/20.
 */

public class RecommendPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {


    public RecommendPresenter(RecommendModel model, RecommendContract.View view) {
        super(model, view);
    }

    public void requestDatas() {
        mModel.getApps()
            .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
            .subscribe(new Subscriber<PageBean<AppInfo>>() {
                @Override
                public void onStart() {
                    super.onStart();
                    mView.showLoading();
                }

                @Override
                public void onCompleted() {
                    mView.dismissLoading();
                }

                @Override
                public void onError(Throwable e) {
                    mView.dismissLoading();
                    mView.showError(e.getMessage());
                }

                @Override
                public void onNext(PageBean<AppInfo> appInfos) {
                    mView.showResults(appInfos.getDatas());
                }

            });
    }


}

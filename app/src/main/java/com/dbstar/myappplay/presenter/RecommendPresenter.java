package com.dbstar.myappplay.presenter;

import com.dbstar.myappplay.bean.IndexBean;
import com.dbstar.myappplay.common.rx.RxHttpReponseCompat;
import com.dbstar.myappplay.common.rx.subscriber.ProgressHandledSubscriber;
import com.dbstar.myappplay.data.model.AppInfoModel;
import com.dbstar.myappplay.presenter.contract.AppInfoContract;

/**
 * Created by dswang on 2017/8/20.
 */

public class RecommendPresenter extends BasePresenter<AppInfoModel, AppInfoContract.View> {


    public RecommendPresenter(AppInfoModel model, AppInfoContract.View view) {
        super(model, view);
    }

    public void requestDatas() {

        // 获取首页数据，主要是为了检测公共参数的添加是否成功
        mModel.getIndex()
                .compose(RxHttpReponseCompat.<IndexBean>compatResult())
                .subscribe(new ProgressHandledSubscriber<IndexBean>(mContext,mView) {
                    @Override
                    public void onNext(IndexBean indexBean) {
                        mView.showResults(indexBean);
                    }
                });

//        new ProgressErrorHandledSubscriber<PageBean<AppInfo>>(mContext) {
//            @Override
//            public void onNext(PageBean<AppInfo> appInfos) {
//                mView.showResults(appInfos.getDatas());
//            }
//        }

//        new ErrorHandlerSubscriber<PageBean<AppInfo>>(mContext) {
//
//            @Override
//            public void onStart() {
//                super.onStart();
//                super.onStart();
//                mBaseView.showLoading();
//
//            }
//
//            @Override
//            public void onCompleted() {
//                mBaseView.dismissLoading();
//            }
//
//            @Override
//            public void onNext(PageBean<AppInfo> appInfos) {
//                mBaseView.showResults(appInfos.getDatas());
//            }
//        }


//        new Subscriber<PageBean<AppInfo>>() {
//            @Override
//            public void onStart() {
//                super.onStart();
//                mBaseView.showLoading();
//            }
//
//            @Override
//            public void onCompleted() {
//                mBaseView.dismissLoading();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                mBaseView.dismissLoading();
//                mBaseView.showError(e.getMessage());
//            }
//
//            @Override
//            public void onNext(PageBean<AppInfo> appInfos) {
//                mBaseView.showResults(appInfos.getDatas());
//            }
//
//        }
    }


}

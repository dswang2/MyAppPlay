package com.dbstar.myappplay.presenter;

import android.util.Log;

import com.dbstar.myappplay.bean.AppInfo;
import com.dbstar.myappplay.bean.BaseBean;
import com.dbstar.myappplay.bean.PageBean;
import com.dbstar.myappplay.common.rx.RxHttpReponseCompat;
import com.dbstar.myappplay.common.rx.subscriber.ErrorHandlerSubscriber;
import com.dbstar.myappplay.common.rx.subscriber.ProgressErrorHandledSubscriber;
import com.dbstar.myappplay.data.model.AppInfoModel;
import com.dbstar.myappplay.presenter.contract.AppInfoContract;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by wh on 2017/9/26.
 */

public class AppInfoPresenter extends BasePresenter<AppInfoModel, AppInfoContract.AppInfoView> {

    public static final int  TOP_LIST=1;
    public static final int  GAME=2;

    public AppInfoPresenter(AppInfoModel model, AppInfoContract.AppInfoView view) {
        super(model, view);
    }


    public void requestDatas(int type, int page) {
        Subscriber subscriber = null;
        if (page == 0) {
            subscriber = new ProgressErrorHandledSubscriber<PageBean<AppInfo>>(mContext) {
                @Override
                public void onNext(PageBean<AppInfo> pageBean) {
                    Log.e("AppInfoPresenter", "onNext(AppInfoPresenter.java:29)" + pageBean.toString());
                    mView.showResults(pageBean);
                }
            };
        } else if (page > 0) {
            subscriber = new ErrorHandlerSubscriber<PageBean<AppInfo>>(mContext){
                @Override
                public void onCompleted() {
                    // 加载更多完成时 的 动作 ，非常非常的重要
                    // appInfoAdapter.loadMoreComplete();
                    mView.onLoadMoreComplete();
                }

                @Override
                public void onNext(PageBean<AppInfo> pageBean) {
                    mView.showResults(pageBean);
                }
            };
        }
        getAppsObservable(type,page)
                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subscriber);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getAppsObservable(int type, int page){
        switch (type){
            case TOP_LIST:
                return  mModel.topList(page);
            case GAME:
                return mModel.games(page);
            default:
                return Observable.empty();
        }
    }


}

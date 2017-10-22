package com.dbstar.myappplay.presenter;

import android.util.Log;

import com.dbstar.myappplay.bean.AppInfo;
import com.dbstar.myappplay.bean.BaseBean;
import com.dbstar.myappplay.bean.PageBean;
import com.dbstar.myappplay.common.rx.RxHttpReponseCompat;
import com.dbstar.myappplay.common.rx.subscriber.ErrorHandlerSubscriber;
import com.dbstar.myappplay.common.rx.subscriber.ProgressErrorHandledSubscriber;
import com.dbstar.myappplay.common.util.Constant;
import com.dbstar.myappplay.data.model.AppInfoModel;
import com.dbstar.myappplay.presenter.contract.AppInfoContract;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by wh on 2017/9/26.
 */

public class AppInfoPresenter extends BasePresenter<AppInfoModel, AppInfoContract.AppInfoView> {

    public AppInfoPresenter(AppInfoModel model, AppInfoContract.AppInfoView view) {
        super(model, view);
    }


    public void request(int type,int page,int categoryId,int flagType) {
        Subscriber subscriber = null;
        if (page == 0) {
            subscriber = new ProgressErrorHandledSubscriber<PageBean<AppInfo>>(mContext,mView) {
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
        getAppsObservable(type,page,categoryId,flagType)
                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subscriber);
    }

    public void requestDatas(int type, int page){
        request(type,page,0,0);
    }

    public void requestCategoryApps(int categoryId,int page,int flagType){
        request(Constant.FRAG_TYPE_CATEGORY,page,categoryId,flagType);
    }



    public Observable<BaseBean<PageBean<AppInfo>>> getAppsObservable(int type,int page,int categoryId,int flagType){
        switch (type){
            case Constant.FRAG_TYPE_TOP_LIST:
                return  mModel.topList(page);
            case Constant.FRAG_TYPE_GAME:
                return mModel.games(page);
            case Constant.FRAG_TYPE_CATEGORY:
                if (flagType == Constant.CATEGORY_FEATURED){
                    return mModel.getFeaturedAppsByCategory(categoryId,page);
                }
                else if (flagType == Constant.CATEGORY_TOPLIST){
                    return mModel.getTopListAppsByCategory(categoryId,page);
                }
                else if (flagType == Constant.CATEGORY_NEWLIST){
                    return mModel.getNewListAppsByCategory(categoryId,flagType);
                }
            default:
                return Observable.empty();
        }
    }


}

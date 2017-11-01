package com.dbstar.myappplay.presenter;

import android.util.Log;

import com.dbstar.myappplay.bean.AppDetail;
import com.dbstar.myappplay.common.rx.RxHttpReponseCompat;
import com.dbstar.myappplay.common.rx.subscriber.ErrorHandlerSubscriber;
import com.dbstar.myappplay.data.model.AppInfoModel;
import com.dbstar.myappplay.presenter.contract.AppDetailContract;

/**
 * Created by wh on 2017/11/1.
 */

public class AppDetailPresenter extends BasePresenter<AppInfoModel,AppDetailContract.IDetailView> {

    public AppDetailPresenter(AppInfoModel model, AppDetailContract.IDetailView view) {
        super(model, view);
    }

    public void requestAppDetails(int appID){
        mModel.getAppDetai(appID).compose(RxHttpReponseCompat.<AppDetail>compatResult()).subscribe(new ErrorHandlerSubscriber<AppDetail>(mContext) {
            @Override
            public void onCompleted() {
                Log.e("dsw","AppDetailPresenter.onCompleted(AppDetailPresenter.java:25): "+"");
            }

            @Override
            public void onNext(AppDetail detail) {
                Log.e("dsw","AppDetailPresenter.onNext(AppDetailPresenter.java:31): "+detail.toString());
                mView.showDetail(detail);
            }
        });
    }
}

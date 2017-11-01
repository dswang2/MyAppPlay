package com.dbstar.myappplay.di.module;

import android.app.ProgressDialog;

import com.dbstar.myappplay.data.model.AppInfoModel;
import com.dbstar.myappplay.presenter.AppInfoPresenter;
import com.dbstar.myappplay.presenter.contract.AppInfoContract;
import com.dbstar.myappplay.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wh on 2017/8/24.
 */

@Module(includes = {AppModelModule.class})
public class AppInfoModule {

    private AppInfoContract.AppInfoView mView;
    public AppInfoModule(AppInfoContract.AppInfoView mView) {
        this.mView = mView;
    }

    @Provides
    public AppInfoContract.AppInfoView provideView(){
        return mView;
    }

    @Provides
    public AppInfoPresenter provideAppInfoPresenter(AppInfoContract.AppInfoView view, AppInfoModel model){
        return new AppInfoPresenter(model,view);
    }

    @Provides
    public ProgressDialog provideProgressDialog(AppInfoContract.AppInfoView view){
        return new ProgressDialog(((RecommendFragment)view).getActivity());
    }
}

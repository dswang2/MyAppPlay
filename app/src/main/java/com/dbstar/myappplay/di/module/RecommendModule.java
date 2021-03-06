package com.dbstar.myappplay.di.module;

import android.app.ProgressDialog;

import com.dbstar.myappplay.data.api.ApiService;
import com.dbstar.myappplay.data.model.AppInfoModel;
import com.dbstar.myappplay.presenter.RecommendPresenter;
import com.dbstar.myappplay.presenter.contract.AppInfoContract;
import com.dbstar.myappplay.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wh on 2017/8/24.
 */

@Module
public class RecommendModule {

    private AppInfoContract.View mView;
    public RecommendModule(AppInfoContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public AppInfoContract.View provideView(){
        return mView;
    }

    @Provides
    public RecommendPresenter provideRecommendPresenter(AppInfoContract.View view, AppInfoModel model){
        return new RecommendPresenter(model,view);
    }

    @Provides
    public AppInfoModel provideRecommendModel(ApiService apiService){
        return new AppInfoModel(apiService);
    }

    @Provides
    public ProgressDialog provideProgressDialog(AppInfoContract.View view){
        return new ProgressDialog(((RecommendFragment)view).getActivity());
    }
}

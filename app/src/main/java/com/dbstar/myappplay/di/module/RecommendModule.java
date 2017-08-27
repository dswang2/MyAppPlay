package com.dbstar.myappplay.di.module;

import android.app.ProgressDialog;

import com.dbstar.myappplay.data.api.ApiService;
import com.dbstar.myappplay.data.model.RecommendModel;
import com.dbstar.myappplay.presenter.RecommendPresenter;
import com.dbstar.myappplay.presenter.contract.RecommendContract;
import com.dbstar.myappplay.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wh on 2017/8/24.
 */

@Module
public class RecommendModule {

    private RecommendContract.View mView;
    public RecommendModule(RecommendContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public RecommendContract.View provideView(){
        return mView;
    }

    @Provides
    public RecommendContract.Presenter providePresenter(RecommendContract.View view, RecommendModel model){
        return new RecommendPresenter(view,model);
    }

    @Provides
    public RecommendModel provideRecommendModel(ApiService apiService){
        return new RecommendModel(apiService);
    }

    @Provides
    public ProgressDialog provideProgressDialog(RecommendContract.View view){
        return new ProgressDialog(((RecommendFragment)view).getActivity());
    }
}

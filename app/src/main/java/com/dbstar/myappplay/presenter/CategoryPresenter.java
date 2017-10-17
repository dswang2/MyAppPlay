package com.dbstar.myappplay.presenter;

import android.util.Log;

import com.dbstar.myappplay.bean.Category;
import com.dbstar.myappplay.common.rx.RxHttpReponseCompat;
import com.dbstar.myappplay.common.rx.subscriber.ProgressErrorHandledSubscriber;
import com.dbstar.myappplay.presenter.contract.CategoryContract;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wh on 2017/10/16.
 */

public class CategoryPresenter extends BasePresenter<CategoryContract.ICategoryModel, CategoryContract.ICategoryView> {
    public CategoryPresenter(CategoryContract.ICategoryModel model, CategoryContract.ICategoryView view) {
        super(model, view);
    }

    public void requestDatas() {
        mModel.getCategories()
                .compose(RxHttpReponseCompat.<List<Category>>compatResult())
                .subscribe(new ProgressErrorHandledSubscriber<List<Category>>(mContext,mView) {
                    @Override
                    public void onNext(List<Category> categories) {
                        Log.e("dsw","CategoryPresenter.onNext(CategoryPresenter.java:30): "+ new Gson().toJson(categories));
                        mView.showResults(categories);
                    }
                });


//                .subscribe(new ErrorHandlerSubscriber<List<Category>>(mContext) {
//
//                    @Override
//                    public void onStart() {
//                        super.onStart();
//                    }
//
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onNext(List<Category> categories) {
//                        Log.e("dsw","CategoryPresenter.onNext(CategoryPresenter.java:30): "+ new Gson().toJson(categories));
//                    }
//                });
    }
}

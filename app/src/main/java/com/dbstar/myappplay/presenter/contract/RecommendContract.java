package com.dbstar.myappplay.presenter.contract;

import com.dbstar.myappplay.bean.IndexBean;
import com.dbstar.myappplay.ui.BaseView;

/**
 * Created by dswang on 2017/8/20.
 */

public interface RecommendContract {
    interface View extends BaseView {
        public void showResults(IndexBean indexBean);
        public void showNoData();
        public void showError(String message);
    }
//    interface Presenter extends BasePresenter {
//        public void requestDatas();
//    }
}

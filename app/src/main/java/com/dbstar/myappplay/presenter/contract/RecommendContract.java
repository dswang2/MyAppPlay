package com.dbstar.myappplay.presenter.contract;

import com.dbstar.myappplay.bean.AppInfo;
import com.dbstar.myappplay.presenter.BasePresenter;
import com.dbstar.myappplay.ui.BaseView;

import java.util.List;

/**
 * Created by dswang on 2017/8/20.
 */

public interface RecommendContract {
    interface View extends BaseView {
        public void showResults(List<AppInfo> appInfos);
        public void showNoData();
        public void showError(String message);
    }
    interface Presenter extends BasePresenter {
        public void requestDatas();
    }
}

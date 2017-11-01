package com.dbstar.myappplay.presenter.contract;

import com.dbstar.myappplay.bean.AppDetail;
import com.dbstar.myappplay.ui.BaseView;

/**
 * Created by wh on 2017/11/1.
 */

public interface AppDetailContract {

    public interface IDetailView extends BaseView{
        void showDetail(AppDetail detail);
    }
}

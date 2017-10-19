package com.dbstar.myappplay.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.dbstar.myappplay.R;
import com.dbstar.myappplay.bean.Category;
import com.dbstar.myappplay.common.util.ToastUtils;
import com.dbstar.myappplay.di.component.AppComponent;
import com.dbstar.myappplay.di.component.DaggerCategoryComponent;
import com.dbstar.myappplay.di.module.CategoryModule;
import com.dbstar.myappplay.presenter.CategoryPresenter;
import com.dbstar.myappplay.presenter.contract.CategoryContract;
import com.dbstar.myappplay.ui.activity.CategoryAppActivity;
import com.dbstar.myappplay.ui.adapter.CategoryAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wh on 2017/6/6.
 */
public class CategoryFragment extends ProgressFragment<CategoryPresenter> implements CategoryContract.ICategoryView{


    @BindView(R.id.category_tv_title)
    TextView categoryTvTitle;
    @BindView(R.id.category_rv_list)
    RecyclerView category_rv_list;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void init() {
        mPresenter.requestDatas();
        initRecyclerView();
    }

    private void initRecyclerView() {
        category_rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        category_rv_list.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        category_rv_list.setItemAnimator(new DefaultItemAnimator());
        categoryAdapter = new CategoryAdapter();
        category_rv_list.setAdapter(categoryAdapter);
        category_rv_list.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showSafeToast(getActivity(),categoryAdapter.getData().get(position).getName());
                startActivity(new Intent(getActivity(), CategoryAppActivity.class));
            }
        });
    }

    @Override
    protected void onEmptyClick() {

    }

    @Override
    public String getTitle() {
        return "分类";
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerCategoryComponent.builder()
                .categoryModule(new CategoryModule(this))
                .appComponent(appComponent)
                .build().inject(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.frag_category;
    }


    @Override
    public void showResults(List<Category> categories) {
        // 刷新RecyclerView
        // categoryTvTitle.setText(new Gson().toJson(categories));
        categoryAdapter.addData(categories);
    }
}

package com.dbstar.myappplay.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dbstar.myappplay.R;
import com.dbstar.myappplay.di.component.AppComponent;

import butterknife.BindView;

/**
 * Created by wh on 2017/9/1.
 */

public class GuideFragment extends BaseFragment {

    public static final String IMG_ID = "IMG_ID";
    public static final String COLOR_ID = "COLOR_ID";
    public static final String TEXT_ID = "TEXT_ID";

    @BindView(R.id.imgView)
    ImageView mImgView;
    @BindView(R.id.text)
    TextView mText;

    // 静态实例化方法
    public static BaseFragment newInstance(int imgResId, int bgColorResId, int textResId) {
        GuideFragment fragment = new GuideFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(IMG_ID,imgResId);
        bundle.putInt(COLOR_ID,bgColorResId);
        bundle.putInt(TEXT_ID,textResId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = super.onCreateView(inflater, container, savedInstanceState);
        initData();
        return mRootView;
    }

    // 设置 Fragment 的 背景、图片、文字
    private void initData() {
        Bundle bundle = getArguments();
        mRootView.setBackgroundColor(getResources().getColor(bundle.getInt(COLOR_ID)));
//        mRootView.setBackgroundColor(bundle.getInt(COLOR_ID));
        mImgView.setImageResource(bundle.getInt(IMG_ID));
        mText.setText(bundle.getInt(TEXT_ID));
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_guide;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

}

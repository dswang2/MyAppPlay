package com.dbstar.myappplay.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dbstar.myappplay.R;

/**
 * Created by wh on 2017/6/6.
 */
public class CategoryFragment  extends MyBaseFragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_category, container, false);
        return view;
    }

    @Override
    public String getTitle() {
        return "分类";
    }
}

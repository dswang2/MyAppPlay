package com.dbstar.myappplay.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dbstar.myappplay.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wh on 2017/8/31.
 */

public class GuideFragmentAdapter extends FragmentPagerAdapter {

    private FragmentManager fm;
    private List<BaseFragment> fragments;

    public GuideFragmentAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    public void setFragments(List<BaseFragment> fragments) {
        if (fragments == null) {
            this.fragments = new ArrayList<>();
        } else
            this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}

package com.dbstar.myappplay.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dbstar.myappplay.ui.fragment.CategoryFragment;
import com.dbstar.myappplay.ui.fragment.GamesFragment;
import com.dbstar.myappplay.ui.fragment.ProgressFragment;
import com.dbstar.myappplay.ui.fragment.TopListFragment;
import com.dbstar.myappplay.ui.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wh on 2017/6/6.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    List<ProgressFragment> mFragments = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    private void initFragments() {
        mFragments.add(new RecommendFragment());
        mFragments.add(new TopListFragment());
        mFragments.add(new GamesFragment());
        mFragments.add(new CategoryFragment());
    }

    @Override
    public Fragment getItem(int position) {
            return (Fragment) mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }
}

package com.dbstar.myappplay.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dbstar.myappplay.ui.fragment.CategoryAppFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wh on 2017/10/19.
 */

public class CategoryViewPager extends FragmentPagerAdapter {
    private  List<CategoryAppFragment> fragments = new ArrayList<>();;

    public CategoryViewPager(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    private void initFragments() {
        fragments.add(new CategoryAppFragment());
        fragments.add(new CategoryAppFragment());
        fragments.add(new CategoryAppFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = fragments.get(position).getTitle();
        switch (position){
            case 0:
                title = "精品";
                break;
            case 1:
                title = "排行";
                break;
            case 2:
                title = "新品";
                break;
        }
        return title;
    }
}

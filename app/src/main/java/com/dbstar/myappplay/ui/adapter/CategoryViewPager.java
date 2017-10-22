package com.dbstar.myappplay.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dbstar.myappplay.common.util.Constant;
import com.dbstar.myappplay.ui.fragment.CategoryAppFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wh on 2017/10/19.
 */

public class CategoryViewPager extends FragmentPagerAdapter {
    private int mCategoryId;
    private  List<CategoryAppFragment> fragments = new ArrayList<>();;

    public CategoryViewPager(FragmentManager fm, int categoryId) {
        super(fm);
        mCategoryId = categoryId;
        initFragments();
    }

    private void initFragments() {
        fragments.add(new CategoryAppFragment(mCategoryId, Constant.CATEGORY_FEATURED));
        fragments.add(new CategoryAppFragment(mCategoryId, Constant.CATEGORY_TOPLIST));
        fragments.add(new CategoryAppFragment(mCategoryId, Constant.CATEGORY_NEWLIST));
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
        return fragments.get(position).getTitle() + position ;
    }
}

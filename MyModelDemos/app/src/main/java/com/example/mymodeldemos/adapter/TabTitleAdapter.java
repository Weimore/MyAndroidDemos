package com.example.mymodeldemos.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by 吴城林 on 2017/7/6.
 */

public class TabTitleAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;
    private String [] mTitles;

    public TabTitleAdapter(FragmentManager fm,List<Fragment> fragments,String []titles) {
        super(fm);
        mFragments=fragments;
        mTitles=titles;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    //将fragment与title进行绑定
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}

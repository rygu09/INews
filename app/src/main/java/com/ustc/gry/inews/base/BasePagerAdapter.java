package com.ustc.gry.inews.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/22
 */

public class BasePagerAdapter<T extends BaseFragment> extends FragmentPagerAdapter {

    private FragmentManager mFragmentManager;
    private List<T> mFragments;
    private List<String> mTitles;

    public BasePagerAdapter(FragmentManager fm, List<T> fragments, List<String> titles) {
        super(fm);
        mFragmentManager = fm;
        mFragments = fragments;
        mTitles = titles;
    }

    /**
     * 更新频道，前面固定的不更新，后面一律更新
     *
     * @param fragments
     * @param titles
     */
    public void updateFragments(List<T> fragments, List<String> titles) {

        for (int i = 0; i < mFragments.size(); i++) {
            final T fragment = mFragments.get(i);
            final FragmentTransaction ft = mFragmentManager.beginTransaction();
            if (i > 2) {
                ft.remove(fragment);
                mFragments.remove(i);
                i--;
            }
            ft.commit();
        }

        for (int i = 0; i < fragments.size(); i++) {//0，1，2为固定频道，从3开始
            if(i>2){
                mFragments.add(fragments.get(i));
            }
        }
        this.mTitles = titles;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

}

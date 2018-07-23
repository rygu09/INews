package com.ustc.gry.inews.base;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.ustc.gry.inews.module.news.ui.NewsListFragment;

import java.util.List;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/4
 */

public class BaseListFragmentAdapter extends FragmentPagerAdapter {

    private FragmentManager mFragmentManager;
    private List<NewsListFragment> mFragments;
    private List<String> mTitles;

    /**
     * 更新频道，前面固定的不更新，后面一律更新
     *  @param fragments
     * @param titles
     */
    public void updateFragments(List<NewsListFragment> fragments, List<String> titles) {
        for (int i = 0; i < mFragments.size(); i++) {
            final NewsListFragment fragment = mFragments.get(i);
            final FragmentTransaction ft = mFragmentManager.beginTransaction();
            if (i > 2) {
                ft.remove(fragment);
                mFragments.remove(i);
                i--;
            }
            ft.commit();
        }
        for (int i = 0; i < fragments.size(); i++) {
            if (i > 2) {
                mFragments.add(fragments.get(i));
            }
        }
        this.mTitles = titles;
        notifyDataSetChanged();
    }

    public BaseListFragmentAdapter(FragmentManager fm, List<NewsListFragment> fragments, List<String> titles) {
        super(fm);
        mFragmentManager = fm;
        mFragments = fragments;
        mTitles = titles;
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

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }


}

package com.ustc.gry.inews.module.picture.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.ustc.gry.inews.R;
import com.ustc.gry.inews.base.BaseFragment;
import com.ustc.gry.inews.base.BasePagerAdapter;
import com.ustc.gry.inews.module.news.ui.adapter.NewsListAdapter;
import com.ustc.gry.inews.module.picture.presenter.IPicturePresenterImpl;
import com.ustc.gry.inews.module.picture.view.IPictureView;
import com.ustc.gry.inews.utils.ViewUtil;
import com.ustc.gry.inews.utils.http.Api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/8
 */

public class PictureFragment extends BaseFragment implements IPictureView{

    private static final String TAG = "PictureFragment";


    private IPicturePresenterImpl mPresenter;

    NewsListAdapter testAdapter;
    @BindView(R.id.tab_picture_fragment)
    TabLayout mTablayout;
    @BindView(R.id.viewpager_picture)
    ViewPager mViewpager;

    public static PictureFragment newInstance(String title) {
        PictureFragment f = new PictureFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        f.setArguments(args);
        return f;
    }

    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_picture;
    }

    @Override
    protected void initData() {
//
    }

    @Override
    protected void initView() {
        mPresenter=new IPicturePresenterImpl(this);
    }



    @Override
    public void initViewPager() {
        List<BaseFragment> fragments = new ArrayList<>();
        final List<String> title = Arrays.asList("校花", "萌女", "清纯");

        fragments.add(PictureListFragment.newInstance(Api.Picture_XIAOHUA_ID, 0));
        fragments.add(PictureListFragment.newInstance(Api.Picture_MENGNV_ID, 1));
        fragments.add(PictureListFragment.newInstance(Api.Picture_QINGCHUN_ID, 2));

        BasePagerAdapter adapter = new BasePagerAdapter(getChildFragmentManager(), fragments, title);
        mViewpager.setAdapter(adapter);

        mTablayout.setupWithViewPager(mViewpager);

        ViewUtil.dynamicSetTabLayoutMode(mTablayout);
    }
}

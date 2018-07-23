package com.ustc.gry.inews.module.video.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.ustc.gry.inews.R;
import com.ustc.gry.inews.base.BaseFragment;
import com.ustc.gry.inews.base.BasePagerAdapter;
import com.ustc.gry.inews.module.video.presenter.IVideoPresenterImpl;
import com.ustc.gry.inews.module.video.view.IVideoView;
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

public class VideoFragment extends BaseFragment implements IVideoView{

    private static final String TAG = "VideoFragment";
    @BindView(R.id.tab_video_fragment)
    TabLayout mTablayout;
    @BindView(R.id.viewpager_video)
    ViewPager mViewPager;

    private IVideoPresenterImpl mPresenter;

    public static VideoFragment newInstance(String title) {
        VideoFragment f = new VideoFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        f.setArguments(args);
        return f;
    }

    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initData() {
        mPresenter = new IVideoPresenterImpl(this);
    }

    @Override
    protected void initView() {

    }


    @Override
    public void initViewPager() {
        List<BaseFragment> fragments = new ArrayList<>();
        final List<String> title = Arrays.asList("娱乐", "搞笑", "精品");

        fragments.add(VideoListFragment.newInstance(Api.VIDEO_ENTERTAINMENT_ID, 0));
        fragments.add(VideoListFragment.newInstance(Api.VIDEO_FUN_ID, 1));
        fragments.add(VideoListFragment.newInstance(Api.VIDEO_CHOICE_ID, 2));

        BasePagerAdapter adapter = new BasePagerAdapter(getChildFragmentManager(), fragments, title);
        mViewPager.setAdapter(adapter);

        mTablayout.setupWithViewPager(mViewPager);

        ViewUtil.dynamicSetTabLayoutMode(mTablayout);

    }



}

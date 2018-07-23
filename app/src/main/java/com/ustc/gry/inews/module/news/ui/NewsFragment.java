package com.ustc.gry.inews.module.news.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.ustc.gry.inews.R;
import com.ustc.gry.inews.app.App;
import com.ustc.gry.inews.app.MainActivity;
import com.ustc.gry.inews.base.BaseFragment;
import com.ustc.gry.inews.event.ChannelChangeMessageEvent;
import com.ustc.gry.inews.greendao.NewsChannelTable;
import com.ustc.gry.inews.module.news.presenter.INewsPresenter;
import com.ustc.gry.inews.module.news.presenter.INewsPresenterImpl;
import com.ustc.gry.inews.module.news.view.INewsView;
import com.ustc.gry.inews.utils.DensityUtil;
import com.ustc.gry.inews.utils.ToastUtil;
import com.ustc.gry.inews.utils.ViewUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/3
 */

public class NewsFragment<T extends BaseFragment> extends BaseFragment implements INewsView {

    @BindView(R.id.tab_news_fragment)
    TabLayout mTablayout;
    @BindView(R.id.viewpager_news)
    ViewPager mViewPager;
    @BindView(R.id.add_btn)
    Button addBtn;

    private INewsPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);//绑定
    }

    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        Logger.i("开始请求数据库");
        mPresenter = new INewsPresenterImpl(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void initViewPager(List<NewsChannelTable> newsChannels) {
        List<T> fragments = new ArrayList<>();
        final List<String> title = new ArrayList<>();

        //根据List<NewsChannelTable> 初始化 NewsListFragment
        if (newsChannels != null) {
            // 有除了固定的其他频道被选中，添加
            for (NewsChannelTable news : newsChannels) {
                final NewsListFragment fragment = NewsListFragment
                        .newInstance(news.getNew_channel_id(), news.getNew_channel_type(),
                                news.getNew_channel_index());

                fragments.add((T)fragment);
                title.add(news.getNew_channel_name());
            }
            //初始化ViewPager
            if (mViewPager.getAdapter() == null) {
                MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager(),
                        fragments, title);
                mViewPager.setAdapter(adapter);
            } else {
                final MyPagerAdapter adapter = (MyPagerAdapter) mViewPager.getAdapter();
                adapter.updateFragments(fragments, title);
            }
//            mViewPager.setCurrentItem(0, false);
            mTablayout.setupWithViewPager(mViewPager);
//            mTablayout.setScrollPosition(0, 0, true);
            // 根据Tab的长度动态设置TabLayout的模式
            ViewUtil.dynamicSetTabLayoutMode(mTablayout);

            //        setOnTabSelectEvent(mViewPager, tabLayout);

        } else

        {
            toast("数据异常");
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEvent(ChannelChangeMessageEvent channelChangeMessageEvent ){
        if(channelChangeMessageEvent.getChannelChange()){
            Logger.e("event执行了");
            mPresenter.operateChannelDb();
        }
    }

    /**
     * 跳转到NewsChannelActivity
     */
    @OnClick(R.id.add_btn)
    public void onViewClicked() {
        Intent intent=new Intent();
        intent.setClass(App.getContext(),NewsChannelActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);//解绑
        }
        mPresenter.onDestory();
        super.onDestroy();
    }

    @Override
    public void toast(String msg) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        private FragmentManager mFragmentManager;
        private List<T> mFragments;
        private List<String> mTitles;

        public MyPagerAdapter(FragmentManager fm, List<T> fragments, List<String> titles) {
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



}

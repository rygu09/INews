package com.ustc.gry.inews.module.news.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.ustc.gry.inews.R;
import com.ustc.gry.inews.base.BaseFragment;
import com.ustc.gry.inews.bean.news.NewsBean;
import com.ustc.gry.inews.callback.OnItemClickAdapter;
import com.ustc.gry.inews.callback.OnItemClickListener;
import com.ustc.gry.inews.common.DataLoadType;
import com.ustc.gry.inews.module.news.presenter.INewsListPresenter;
import com.ustc.gry.inews.module.news.presenter.INewsListPresenterImpl;
import com.ustc.gry.inews.module.news.ui.adapter.NewsListAdapter;
import com.ustc.gry.inews.module.news.view.INewsListView;

import java.util.List;

import butterknife.BindView;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/3
 */

public class NewsListFragment extends BaseFragment implements INewsListView,SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.newslist_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.newslist_swipe_fresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private NewsListAdapter mAdapter;
    private INewsListPresenter mPresenter;
    private LinearLayoutManager mLayoutManager;


    protected static final String NEWS_ID = "news_channel_id";
    protected static final String NEWS_TYPE = "news_channel_type";
    protected static final String POSITION = "news_channel_index";

    protected String mNewsType;//host、list
    protected String mNewsId;//T.....
    protected int mPosition;

    private boolean isInitialed=false;


    public static NewsListFragment newInstance(String newsId, String newsType, int position) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NEWS_ID, newsId);
        bundle.putString(NEWS_TYPE, newsType);
        bundle.putInt(POSITION, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNewsId = getArguments().getString(NEWS_ID);
            mNewsType = getArguments().getString(NEWS_TYPE);
            mPosition = getArguments().getInt(POSITION);
        }
    }

    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_news_list;
    }

    @Override
    protected void initView() {
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);//不调用notifyDataSetChanged()就不重新计算item宽高
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new NewsListAdapter(getContext());
        mAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        showProgress();

        mPresenter = new INewsListPresenterImpl(this, mNewsType, mNewsId);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onRefresh() {
        mPresenter.refreshData();
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        private int lastVisibleItem;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem +1 == mAdapter.getItemCount()
                    ) {
                //加载更多
                Logger.e( "loading more data");
                mPresenter.loadMoreData();
            }
        }
    };

    @Override
    public void updateNewsList(List<NewsBean> data, String errorMsg, int type) {

        switch (type){
            case DataLoadType.TYPE_REFRESH_SUCCESS:
                hideProgress();
                mAdapter.setData(data);
                break;
            case DataLoadType.TYPE_LOAD_MORE_SUCCESS:
                mAdapter.addMoreData(data);
                break;
        }
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }


    //跳转到新闻详情
    private OnItemClickListener mOnItemClickListener=new OnItemClickAdapter() {
        @Override
        public void onItemClick(View view, int position) {

            NewsBean news = mAdapter.getItem(position);
            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
            intent.putExtra("news", news);
            startActivity(intent);
        }
    };

}

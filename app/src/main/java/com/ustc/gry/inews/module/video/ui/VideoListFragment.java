package com.ustc.gry.inews.module.video.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.ustc.gry.inews.R;
import com.ustc.gry.inews.base.BaseFragment;
import com.ustc.gry.inews.bean.VideoBean;
import com.ustc.gry.inews.common.DataLoadType;
import com.ustc.gry.inews.module.video.presenter.IVideoListPresenter;
import com.ustc.gry.inews.module.video.presenter.IVideoListPresenterImpl;
import com.ustc.gry.inews.module.video.ui.adapter.VideoListAdapter;
import com.ustc.gry.inews.module.video.view.IVideoListView;

import java.util.List;

import butterknife.BindView;
import cn.jzvd.JZMediaManager;
import cn.jzvd.JZUtils;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerManager;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/3
 */

public class VideoListFragment extends BaseFragment implements IVideoListView, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.videolist_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.videolist_swipe_fresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private VideoListAdapter mAdapter;
    private IVideoListPresenter mPresenter;
    private LinearLayoutManager mLayoutManager;

    protected static final String VIDEO_ID = "video_id";
    protected static final String POSITION = "position";

    protected String mVideoId;
    protected int mPosition;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mVideoId = getArguments().getString(VIDEO_ID);
            mPosition = getArguments().getInt(POSITION);
        }
    }

    public static VideoListFragment newInstance(String videoId, int position) {
        VideoListFragment fragment = new VideoListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(VIDEO_ID, videoId);
        bundle.putInt(POSITION, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_video_list;
    }

    @Override
    protected void initView() {
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);//不调用notifyDataSetChanged()就不重新计算item宽高
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new VideoListAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        mRecyclerView.addOnChildAttachStateChangeListener(mOnChildAttachStateChangeListener);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        showProgress();

        mPresenter = new IVideoListPresenterImpl(this, mVideoId, 0);

    }

    @Override
    protected void initData() {
    }

    @Override
    public void onRefresh() {
        mPresenter.refreshData();
    }

    @Override
    public void updateVideoList(List<VideoBean> data, String errorMsg, int type) {
        switch (type) {
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
                    && lastVisibleItem + 1 == mAdapter.getItemCount()
                    ) {
                //加载更多
                Logger.e("loading more data");
                mPresenter.loadMoreData();
            }
        }
    };

    private RecyclerView.OnChildAttachStateChangeListener mOnChildAttachStateChangeListener=new RecyclerView.OnChildAttachStateChangeListener(){

        @Override
        public void onChildViewAttachedToWindow(View view) {

        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {
            JZVideoPlayer jzvd = view.findViewById(R.id.videoplayer);
            if (jzvd != null && JZUtils.dataSourceObjectsContainsUri(jzvd.dataSourceObjects, JZMediaManager.getCurrentDataSource())) {
                JZVideoPlayer currentJzvd = JZVideoPlayerManager.getCurrentJzvd();
                if (currentJzvd != null && currentJzvd.currentScreen != JZVideoPlayer.SCREEN_WINDOW_FULLSCREEN) {
                    JZVideoPlayer.releaseAllVideos();
                }
            }
        }
    };
}

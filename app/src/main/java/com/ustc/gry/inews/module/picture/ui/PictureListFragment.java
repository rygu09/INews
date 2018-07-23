package com.ustc.gry.inews.module.picture.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.ustc.gry.inews.R;
import com.ustc.gry.inews.base.BaseFragment;
import com.ustc.gry.inews.base.BaseSpacesItemDecoration;
import com.ustc.gry.inews.bean.pic.PictureBean;
import com.ustc.gry.inews.callback.OnItemClickAdapter;
import com.ustc.gry.inews.callback.OnItemClickListener;
import com.ustc.gry.inews.common.DataLoadType;
import com.ustc.gry.inews.module.picture.presenter.IPictureListPresenter;
import com.ustc.gry.inews.module.picture.presenter.IPictureListPresenterImpl;
import com.ustc.gry.inews.module.picture.ui.adapter.PictureListAdapter;
import com.ustc.gry.inews.module.picture.view.IPictureListView;
import com.ustc.gry.inews.utils.MeasureUtil;

import java.util.List;

import butterknife.BindView;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/22
 */

public class PictureListFragment extends BaseFragment implements IPictureListView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.picturelist_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.picturelist_swipe_fresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;


    private PictureListAdapter mAdapter;
    private IPictureListPresenter mPresenter;
    private StaggeredGridLayoutManager mLayoutManager;

    protected static final String PICTURE_ID = "picture_id";
    protected static final String POSITION = "position";

    protected String mPictureId;
    protected int mPosition;

    public static PictureListFragment newInstance(String videoId, int position) {
        PictureListFragment fragment = new PictureListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PICTURE_ID, videoId);
        bundle.putInt(POSITION, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPictureId = getArguments().getString(PICTURE_ID);
            mPosition = getArguments().getInt(POSITION);
        }
    }

    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_picture_list;
    }

    @Override
    protected void initView() {
        mLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);//不调用notifyDataSetChanged()就不重新计算item宽高
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new BaseSpacesItemDecoration(MeasureUtil.dip2px(getActivity(),10)));
        mAdapter = new PictureListAdapter(getContext());
        mAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.addOnScrollListener(mOnScrollListener);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        showProgress();


        mPresenter=new IPictureListPresenterImpl(this,mPictureId,0);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onRefresh() {

    }

    @Override
    public void updatePictureList(List<PictureBean> data, String errorMsg, int type) {
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

    //跳转到新闻详情
    private OnItemClickListener mOnItemClickListener=new OnItemClickAdapter() {
        @Override
        public void onItemClick(View view, int position) {
            PictureBean pictureBean = mAdapter.getItem(position);
            mAdapter.showDialog(pictureBean.getList().get(0).getBig());
        }
    };

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

}

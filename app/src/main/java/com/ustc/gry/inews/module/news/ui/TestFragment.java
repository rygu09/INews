package com.ustc.gry.inews.module.news.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.ustc.gry.inews.R;
import com.ustc.gry.inews.base.BaseFragment;
import com.ustc.gry.inews.bean.news.NewsBean;
import com.ustc.gry.inews.callback.OnItemClickAdapter;
import com.ustc.gry.inews.callback.OnItemClickListener;
import com.ustc.gry.inews.module.news.presenter.INewsListPresenter;
import com.ustc.gry.inews.module.news.presenter.INewsListPresenterImpl;
import com.ustc.gry.inews.module.news.ui.adapter.NewsListAdapter;
import com.ustc.gry.inews.module.news.view.INewsListView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class TestFragment extends BaseFragment implements INewsListView
{

    protected static final String NEWS_ID = "news_channel_id";
    protected static final String NEWS_TYPE = "news_channel_type";
    protected static final String POSITION = "news_channel_index";

    protected String mNewsType;//host、list
    protected String mNewsId;//T.....
    protected int mPosition;

    List<NewsBean> data;
    NewsListAdapter testAdapter;

    private INewsListPresenter mPresenter;


    @BindView(R.id.txv)
    TextView txv;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private boolean isInitialed = false;


    public static TestFragment newInstance(String newsId, String newsType, int position) {
        TestFragment fragment = new TestFragment();
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
        return R.layout.fragment_test;
    }

    @Override
    protected void initView() {
        txv.setText(mNewsType + "\n" + mNewsId + "\n" + mPosition);
    }

    @Override
    protected void initData() {
        if(testAdapter==null){
            Logger.e("adapter = null  "+mNewsType+"  "+mNewsId);
            testAdapter=new NewsListAdapter(getContext());
//            testAdapter=new NewsListAdapter(getContext(),data);
        }
//        testAdapter.setOnItemClickListen(mOnItemClickListener);
        testAdapter.setOnItemClickListener(mOnItemClickListener);
        recyclerView.setAdapter(testAdapter);
//        data=new ArrayList<>();
//        for(int i=0;i<20;i++){
//            data.add(mNewsType+"  "+mNewsId+"  第"+i);
//        }
//        testAdapter.setData(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mPresenter = new INewsListPresenterImpl(this, mNewsType,mNewsId);

    }

    private OnItemClickListener mOnItemClickListener=new OnItemClickAdapter() {
        @Override
        public void onItemClick(View view, int position) {
            NewsBean s = testAdapter.getItem(position);

            Logger.e(s.getTitle());

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }




    @OnClick(R.id.btn)
    public void onViewClicked() {
        Logger.e("点了"+mPosition);
    }



    @Override
    public void updateNewsList(List<NewsBean> data, String errorMsg, int type) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}

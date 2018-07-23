package com.ustc.gry.inews.module.news.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import com.ustc.gry.inews.R;
import com.ustc.gry.inews.base.BaseActivity;
import com.ustc.gry.inews.base.BaseSpacesItemDecoration;
import com.ustc.gry.inews.callback.OnItemClickAdapter;
import com.ustc.gry.inews.callback.SimpleItemTouchHelperCallback;
import com.ustc.gry.inews.greendao.NewsChannelTable;
import com.ustc.gry.inews.module.news.presenter.INewsChannelPresenter;
import com.ustc.gry.inews.module.news.presenter.INewsChannelPresenterImpl;
import com.ustc.gry.inews.module.news.ui.adapter.NewsChannelAdapter;
import com.ustc.gry.inews.module.news.view.INewsChannelView;
import com.ustc.gry.inews.utils.ClickUtils;
import com.ustc.gry.inews.utils.MeasureUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsChannelActivity extends BaseActivity implements INewsChannelView{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_my_channel)
    TextView tvMyChannel;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView1;
    @BindView(R.id.tv_more_channel)
    TextView tvMoreChannel;
    @BindView(R.id.recycler_view2)
    RecyclerView recyclerView2;

    private INewsChannelPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_channel);
        ButterKnife.bind(this);
        initToolbar();
        mPresenter=new INewsChannelPresenterImpl(this);
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            setToolbarTitle("频道管理");
            setToolbarIndicator(R.drawable.ic_menu_back);
        }

    }

    @Override
    public void initTwoRecyclerView(List<NewsChannelTable> selectChannels, List<NewsChannelTable> unSelectChannels) {
        // 初始化我的频道RecyclerView
        final RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView1.setLayoutManager(
                new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        recyclerView1.addItemDecoration(new BaseSpacesItemDecoration(MeasureUtil.dip2px(this, 8)));

        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.getItemAnimator().setAddDuration(200);
        recyclerView1.getItemAnimator().setMoveDuration(200);
        recyclerView1.getItemAnimator().setChangeDuration(200);
        recyclerView1.getItemAnimator().setRemoveDuration(200);

        final NewsChannelAdapter mRecyclerAdapter1 = new NewsChannelAdapter(this, selectChannels);
        recyclerView1.setAdapter(mRecyclerAdapter1);

        // 只有我的频道可以拖拽排序
        SimpleItemTouchHelperCallback callback1 = new SimpleItemTouchHelperCallback(
                mRecyclerAdapter1);
        ItemTouchHelper itemTouchHelper1 = new ItemTouchHelper(callback1);
        itemTouchHelper1.attachToRecyclerView(recyclerView1);
        mRecyclerAdapter1.setItemTouchHelper(callback1);

        mRecyclerAdapter1.setItemMoveListener(new NewsChannelAdapter.OnItemMoveListener() {
            @Override
            public void onItemMove(int fromPosition, int toPosition) {
                // 拖拽交换位置的时候通知代理更新数据库
                mPresenter.onItemSwap(fromPosition, toPosition);
            }
        });

        // 初始化更多频道RecyclerView
        final RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.recycler_view2);
        recyclerView2.setLayoutManager(
                new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        recyclerView2.addItemDecoration(new BaseSpacesItemDecoration(MeasureUtil.dip2px(this, 8)));
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.getItemAnimator().setAddDuration(200);
        recyclerView2.getItemAnimator().setMoveDuration(200);
        recyclerView2.getItemAnimator().setChangeDuration(200);
        recyclerView2.getItemAnimator().setRemoveDuration(200);

        final NewsChannelAdapter mRecyclerAdapter2 = new NewsChannelAdapter(this, unSelectChannels);
        recyclerView2.setAdapter(mRecyclerAdapter2);

        // 设置两个RecyclerView的点击监听，进行Item相应的增删操作
        mRecyclerAdapter1.setOnItemClickListener(new OnItemClickAdapter() {
            @Override
            public void onItemClick(View view, int position) {

                if (ClickUtils.isFastDoubleClick()){
                    return;
                }

                if (!mRecyclerAdapter1.getData().get(position).getNew_channel_fixed()) {
                    // 点击我的频道，不是固定的就删除，更多频道添加

                    // 通知代理把频道从数据库删掉
                    mPresenter.onItemAddOrRemove(
                            mRecyclerAdapter1.getData().get(position).getNew_channel_name(), false);

                    mRecyclerAdapter2.add(mRecyclerAdapter2.getItemCount(),
                            mRecyclerAdapter1.getData().get(position));

                    mRecyclerAdapter1.delete(position);

                }
            }
        });

        mRecyclerAdapter2.setOnItemClickListener(new OnItemClickAdapter() {
            @Override
            public void onItemClick(View view, int position) {

                if (ClickUtils.isFastDoubleClick()){
                    return;
                }

                // 点击更多频道，更多频道删除，我的频道添加

                // 通知代理把频道添加到数据库
                mPresenter.onItemAddOrRemove(
                        mRecyclerAdapter2.getData().get(position).getNew_channel_name(), true);


                mRecyclerAdapter1.add(mRecyclerAdapter1.getItemCount(),
                        mRecyclerAdapter2.getData().get(position));

                mRecyclerAdapter2.delete(position);
            }
        });
    }

    @Override
    protected void onStop() {
        mPresenter.onDestory();//该方法中发送event
        super.onStop();
    }
}

package com.ustc.gry.inews.module.news.presenter;

import com.ustc.gry.inews.callback.RequestCallback;
import com.ustc.gry.inews.event.ChannelChangeMessageEvent;
import com.ustc.gry.inews.greendao.NewsChannelTable;
import com.ustc.gry.inews.module.news.model.INewsChannelInteractor;
import com.ustc.gry.inews.module.news.model.INewsChannelInteractorImpl;
import com.ustc.gry.inews.module.news.ui.NewsFragment;
import com.ustc.gry.inews.module.news.view.INewsChannelView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;

import rx.Subscription;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/9
 */

public class INewsChannelPresenterImpl implements INewsChannelPresenter,RequestCallback<Map<Boolean, List<NewsChannelTable>>> {

    Subscription mSubscription;
    INewsChannelView mView;

    private INewsChannelInteractor<Map<Boolean, List<NewsChannelTable>>> mNewsChannelInteractor;

    private static boolean mChannelChange;

    public INewsChannelPresenterImpl(INewsChannelView newsChannelView) {
        mView=newsChannelView;
        mNewsChannelInteractor = new INewsChannelInteractorImpl();
        // 初始化
        mSubscription = mNewsChannelInteractor.channelDbOperate(this, "", null);
    }


    @Override
    public void onItemAddOrRemove(String channelName, boolean selectState) {
        mChannelChange = true;
        // 增删操作
        mSubscription = mNewsChannelInteractor.channelDbOperate(this, channelName, selectState);
    }

    @Override
    public void onItemSwap(int fromPos, int toPos) {
        mChannelChange = true;
        mSubscription = mNewsChannelInteractor.channelDbSwap(this, fromPos, toPos);
    }

    @Override
    public void onDestory() {
        EventBus.getDefault().post(new ChannelChangeMessageEvent(mChannelChange));
    }

    @Override
    public void requestSuccess(Map<Boolean, List<NewsChannelTable>> data) {
        // 只有初始化才调用到
        mView.initTwoRecyclerView(data.get(true), data.get(false));
    }

    @Override
    public void requestError(String msg) {

    }
}

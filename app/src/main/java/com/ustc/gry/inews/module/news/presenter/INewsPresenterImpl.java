package com.ustc.gry.inews.module.news.presenter;

import com.orhanobut.logger.Logger;
import com.ustc.gry.inews.base.BasePresenter;
import com.ustc.gry.inews.base.BasePresenterImpl;
import com.ustc.gry.inews.callback.RequestCallback;
import com.ustc.gry.inews.greendao.NewsChannelTable;
import com.ustc.gry.inews.module.news.model.INewsInteractor;
import com.ustc.gry.inews.module.news.model.INewsInteractorImpl;
import com.ustc.gry.inews.module.news.view.INewsView;

import java.util.List;

import rx.Subscription;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/3
 */

public class INewsPresenterImpl implements INewsPresenter,RequestCallback<List<NewsChannelTable>> {

    private INewsInteractor<List<NewsChannelTable>> mNewsInteractor;
    private INewsView mView;
    Subscription mSubscription;

    public INewsPresenterImpl(INewsView newsView) {
        mView=newsView;
        mNewsInteractor = new INewsInteractorImpl();
        mSubscription = mNewsInteractor.operateChannelDb(this);
    }

    @Override
    public void operateChannelDb() {
        mNewsInteractor.operateChannelDb(this);
    }

    @Override
    public void requestSuccess(List<NewsChannelTable> data) {
        mView.initViewPager(data);
        Logger.i("请求成功，initViewPager");
    }

    @Override
    public void requestError(String msg) {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestory(){
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mView = null;
    }
}

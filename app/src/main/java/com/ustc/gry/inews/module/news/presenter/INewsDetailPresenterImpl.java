package com.ustc.gry.inews.module.news.presenter;

import com.ustc.gry.inews.bean.news.NewsDetailBean;
import com.ustc.gry.inews.callback.RequestCallback;
import com.ustc.gry.inews.module.news.model.INewsDetailInteractor;
import com.ustc.gry.inews.module.news.model.INewsDetailInteractorImpl;
import com.ustc.gry.inews.module.news.view.INewsDetailView;

import rx.Subscription;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/8
 */

public class INewsDetailPresenterImpl implements INewsDetailPresenter,RequestCallback<NewsDetailBean> {

    private INewsDetailView mView;
    private INewsDetailInteractor<NewsDetailBean> newsDetailInteractor;
    Subscription mSubscription;

    public INewsDetailPresenterImpl(INewsDetailView newsDetailView, String docId) {
        mView=newsDetailView;
        newsDetailInteractor = new INewsDetailInteractorImpl();
        mSubscription = newsDetailInteractor.requestNewsDetail(this, docId);
    }

    @Override
    public void requestSuccess(NewsDetailBean data) {
        mView.initNewsDetail(data);
    }

    @Override
    public void requestError(String msg) {
    }
}

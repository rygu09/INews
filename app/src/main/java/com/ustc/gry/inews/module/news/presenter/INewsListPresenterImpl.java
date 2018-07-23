package com.ustc.gry.inews.module.news.presenter;

import com.orhanobut.logger.Logger;
import com.ustc.gry.inews.bean.news.NewsBean;
import com.ustc.gry.inews.callback.RequestCallback;
import com.ustc.gry.inews.common.DataLoadType;
import com.ustc.gry.inews.module.news.model.INewsListInteractor;
import com.ustc.gry.inews.module.news.model.INewsListInteractorImpl;
import com.ustc.gry.inews.module.news.view.INewsListView;

import java.util.List;

import rx.Subscription;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/5
 */

public class INewsListPresenterImpl implements INewsListPresenter,RequestCallback<List<NewsBean>> {

    private INewsListInteractor<List<NewsBean>> mNewsListInteractor;
    private INewsListView mView;
    Subscription mSubscription;

    private String mNewsType;
    private String mNewsId;
    private int mStartPage;

    private boolean mIsRefresh=true;

    public INewsListPresenterImpl(INewsListView iNewsListView, String newsType, String newsId){
        mView=iNewsListView;
        mNewsListInteractor=new INewsListInteractorImpl();
        mNewsId=newsId;
        mNewsType=newsType;
        mSubscription = mNewsListInteractor.requestNewsList(this, newsType, newsId, mStartPage);
    }

    @Override
    public void requestSuccess(List<NewsBean> data) {
        if (data != null) {
            mStartPage += 20;
        }
        mView.updateNewsList(data, "", mIsRefresh ? DataLoadType.TYPE_REFRESH_SUCCESS : DataLoadType.TYPE_LOAD_MORE_SUCCESS);
        Logger.i("请求新闻列表成功，让view增加news");
//        mView.updateNewsList(data, "", mIsRefresh ? DataLoadType.TYPE_REFRESH_SUCCESS : DataLoadType.TYPE_LOAD_MORE_SUCCESS);
    }

    @Override
    public void requestError(String msg) {
        mView.hideProgress();
        mView.updateNewsList(null, msg, mIsRefresh ? DataLoadType.TYPE_REFRESH_FAIL : DataLoadType.TYPE_LOAD_MORE_FAIL);
    }

    @Override
    public void refreshData() {
        mStartPage = 0;
        mIsRefresh = true;
        mSubscription = mNewsListInteractor.requestNewsList(this, mNewsType, mNewsId, mStartPage);
    }

    @Override
    public void loadMoreData() {
        mIsRefresh = false;
        mSubscription = mNewsListInteractor.requestNewsList(this, mNewsType, mNewsId, mStartPage);
    }
}

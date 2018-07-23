package com.ustc.gry.inews.module.video.presenter;

import com.orhanobut.logger.Logger;
import com.ustc.gry.inews.bean.VideoBean;
import com.ustc.gry.inews.callback.RequestCallback;
import com.ustc.gry.inews.common.DataLoadType;
import com.ustc.gry.inews.module.video.model.IVideoListInteractor;
import com.ustc.gry.inews.module.video.model.IVideoListInteractorImpl;
import com.ustc.gry.inews.module.video.view.IVideoListView;

import java.io.IOException;
import java.util.List;

import rx.Subscription;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/22
 */

public class IVideoListPresenterImpl implements IVideoListPresenter,RequestCallback<List<VideoBean>>{
    private IVideoListInteractor<List<VideoBean>> mVideoListInteractor;
    private IVideoListView mView;
    Subscription mSubscription;

    private String mVideoId;
    private int mStartPage;

    private boolean mIsRefresh=true;

    public IVideoListPresenterImpl(IVideoListView iVideoListView, String videoId, int startPage){
        mView=iVideoListView;
        mVideoListInteractor=new IVideoListInteractorImpl();
        mVideoId=videoId;
        mStartPage=startPage;
        mSubscription = mVideoListInteractor.requestVideoList(this, mVideoId, mStartPage);
    }

    @Override
    public void requestSuccess(List<VideoBean> data) throws IOException {
        if (data != null) {
            mStartPage += 10;
        }
        mView.updateVideoList(data, "", mIsRefresh ? DataLoadType.TYPE_REFRESH_SUCCESS : DataLoadType.TYPE_LOAD_MORE_SUCCESS);
        Logger.i("视频列表请求成功，让view增加videos");
//        mView.updateNewsList(data, "", mIsRefresh ? DataLoadType.TYPE_REFRESH_SUCCESS : DataLoadType.TYPE_LOAD_MORE_SUCCESS);
    }

    @Override
    public void requestError(String msg) {
        mView.hideProgress();
        mView.updateVideoList(null, msg, mIsRefresh ? DataLoadType.TYPE_REFRESH_FAIL : DataLoadType.TYPE_LOAD_MORE_FAIL);
    }

    @Override
    public void refreshData() {
        mStartPage = 0;
        mIsRefresh = true;
        mSubscription = mVideoListInteractor.requestVideoList(this, mVideoId, mStartPage);
    }

    @Override
    public void loadMoreData() {
        mIsRefresh = false;
        mSubscription = mVideoListInteractor.requestVideoList(this,mVideoId, mStartPage);
    }
}

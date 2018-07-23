package com.ustc.gry.inews.module.picture.presenter;

import com.orhanobut.logger.Logger;
import com.ustc.gry.inews.bean.pic.PictureBean;
import com.ustc.gry.inews.callback.RequestCallback;
import com.ustc.gry.inews.common.DataLoadType;
import com.ustc.gry.inews.module.picture.model.IPictureListInteractor;
import com.ustc.gry.inews.module.picture.model.IPictureListInteractorImpl;
import com.ustc.gry.inews.module.picture.view.IPictureListView;

import java.io.IOException;
import java.util.List;

import rx.Subscription;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/22
 */

public class IPictureListPresenterImpl
        implements IPictureListPresenter,RequestCallback<List<PictureBean>> {


    private IPictureListInteractor<List<PictureBean>> mPictureListInteractor;
    private IPictureListView mView;
    Subscription mSubscription;

    private String mPictureId;
    private int mStartPage;

    private boolean mIsRefresh=true;

    public IPictureListPresenterImpl(IPictureListView iPictureListView, String pictureId, int startPage){
        mView=iPictureListView;
        mPictureListInteractor=new IPictureListInteractorImpl();
        mPictureId=pictureId;
        mStartPage=startPage;
        mSubscription = mPictureListInteractor.requestPictureList(this, mPictureId, mStartPage);
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void loadMoreData() {

    }

    @Override
    public void requestSuccess(List<PictureBean> data) throws IOException {
        if (data != null) {
            mStartPage += 1;
        }
        mView.updatePictureList(data, "", mIsRefresh ? DataLoadType.TYPE_REFRESH_SUCCESS : DataLoadType.TYPE_LOAD_MORE_SUCCESS);
        Logger.i("请求图片列表成功，让view增加pictures");
    }

    @Override
    public void requestError(String msg) {

    }


}

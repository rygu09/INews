package com.ustc.gry.inews.module.picture.model;

import com.orhanobut.logger.Logger;
import com.ustc.gry.inews.bean.pic.PictureBean;
import com.ustc.gry.inews.bean.pic.ShowApiPictures;
import com.ustc.gry.inews.bean.pic.ShowApiResponse;
import com.ustc.gry.inews.callback.RequestCallback;
import com.ustc.gry.inews.utils.http.HostType;
import com.ustc.gry.inews.utils.http.RetrofitManager;

import java.io.IOException;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/22
 */

public class IPictureListInteractorImpl implements IPictureListInteractor<List<PictureBean>> {
    @Override
    public Subscription requestPictureList(final RequestCallback callback, final String id, int startPage) {
        return RetrofitManager.getInstance(HostType.BAIDU_API_PHOTO)
                .getPictureListObservable(id,startPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ShowApiResponse<ShowApiPictures>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.getMessage());
                    }

                    @Override
                    public void onNext(ShowApiResponse<ShowApiPictures> showApiPicturesShowApiResponse) {
                        try {
                            List<PictureBean> pictureBeanList= showApiPicturesShowApiResponse.showapi_res_body.pagebean.contentlist;
                            callback.requestSuccess(pictureBeanList);
                            Logger.i("IVideoListInteractorImpl请求成功"+pictureBeanList.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }
}

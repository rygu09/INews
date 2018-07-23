package com.ustc.gry.inews.module.video.model;

import com.orhanobut.logger.Logger;
import com.ustc.gry.inews.bean.VideoBean;
import com.ustc.gry.inews.callback.RequestCallback;
import com.ustc.gry.inews.utils.JsonUtils;
import com.ustc.gry.inews.utils.http.HostType;
import com.ustc.gry.inews.utils.http.RetrofitManager;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/22
 */

public class IVideoListInteractorImpl implements IVideoListInteractor<List<VideoBean>> {
    @Override
    public Subscription requestVideoList(final RequestCallback callback, final String id, int startPage) {
        return RetrofitManager.getInstance(HostType.NETEASE_VIDEO)
                .getVideoListObservable(id,startPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.getMessage());
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            List<VideoBean> videoBeanList= JsonUtils.readJsonVideoBeans(responseBody.string(),id);
                            callback.requestSuccess(videoBeanList);
                            Logger.i("IVideoListInteractorImpl请求成功"+videoBeanList.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }
}

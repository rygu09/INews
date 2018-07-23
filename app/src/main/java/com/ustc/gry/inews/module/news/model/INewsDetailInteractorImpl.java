package com.ustc.gry.inews.module.news.model;

import com.orhanobut.logger.Logger;
import com.ustc.gry.inews.bean.news.NewsDetailBean;
import com.ustc.gry.inews.callback.RequestCallback;
import com.ustc.gry.inews.utils.JsonUtils;
import com.ustc.gry.inews.utils.http.HostType;
import com.ustc.gry.inews.utils.http.RetrofitManager;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/8
 */

public class INewsDetailInteractorImpl implements INewsDetailInteractor<NewsDetailBean> {
    @Override
    public Subscription requestNewsDetail(final RequestCallback callback, final String id) {
        return RetrofitManager.getInstance(HostType.NETEASE_NEWS)
                .getNewsDetailObservable(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.requestError(e.getMessage());
//                        Logger.e("新闻详情请求失败:  "+e.getMessage());
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            NewsDetailBean newsDetailBean= JsonUtils.readJsonNewsDetailBeans(responseBody.string(),id);
                            callback.requestSuccess(newsDetailBean);
                            Logger.i("INewsDetailInteractorImpl请求成功"+newsDetailBean.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}

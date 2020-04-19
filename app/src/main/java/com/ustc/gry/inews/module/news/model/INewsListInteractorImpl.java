package com.ustc.gry.inews.module.news.model;

import com.orhanobut.logger.Logger;
import com.ustc.gry.inews.app.App;
import com.ustc.gry.inews.bean.news.NewsBean;
import com.ustc.gry.inews.callback.RequestCallback;
import com.ustc.gry.inews.utils.JsonUtils;
import com.ustc.gry.inews.utils.http.HostType;
import com.ustc.gry.inews.utils.http.NetUtil;
import com.ustc.gry.inews.utils.http.RetrofitManager;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/5
 */

public class INewsListInteractorImpl implements INewsListInteractor<List<NewsBean>>{

    public Subscription requestNewsList(final RequestCallback<List<NewsBean>> callback, final String type, final String id, final int startPage) {
//        Logger.i("新闻列表：" + type + " " + id+" "+startPage);

         return RetrofitManager.getInstance(HostType.NETEASE_NEWS)
                .getNewsListObservable(type, id, startPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                 @Override
                 public void onCompleted() {

                 }

                 @Override
                 public void onError(Throwable e) {
                     if (callback != null) {
                         String errorMsg = null;
                         if (e instanceof HttpException) {
                             switch (((HttpException) e).code()) {
                                 case 403:
                                     errorMsg = "没有权限访问此链接！";
                                     break;
                                 case 504:
                                     if (!NetUtil.isConnected(App.getContext())) {
                                         errorMsg = "没有联网哦！";
                                     } else {
                                         errorMsg = "网络连接超时！";
                                     }
                                     break;
                                 default:
                                     errorMsg = ((HttpException) e).message();
                                     break;
                             }
                         } else if (e instanceof UnknownHostException) {
                             errorMsg = "不知名主机！";
                         } else if (e instanceof SocketTimeoutException) {
                             errorMsg = "网络连接超时！";
                         }else {
                             errorMsg = "未知异常！";
                         }
                         Logger.e(e.toString());
                         callback.requestError(errorMsg);
                     }
                     Logger.e(type+"   "+id+"   "+startPage+"   "+"新闻列表请求失败:  "+e.getMessage());
                 }

                 @Override
                 public void onNext(ResponseBody responseBody) {
                     try {
                         List<NewsBean> newsBeanList=JsonUtils.readJsonNewsBeans(responseBody.string(),id);
                         callback.requestSuccess(newsBeanList);
                         Logger.i("INewsListInteractorImpl请求成功"+newsBeanList.toString());
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }
                });


    }
}

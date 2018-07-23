package com.ustc.gry.inews.module.news.model;

import com.orhanobut.logger.Logger;
import com.ustc.gry.inews.R;
import com.ustc.gry.inews.app.App;
import com.ustc.gry.inews.callback.RequestCallback;
import com.ustc.gry.inews.greendao.NewsChannelTable;
import com.ustc.gry.inews.greendao.NewsChannelTableDao;
import com.ustc.gry.inews.utils.SharedPreferenceUtil;
import com.ustc.gry.inews.utils.http.Api;

import org.greenrobot.greendao.query.Query;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/3
 */

public class INewsInteractorImpl implements INewsInteractor<List<NewsChannelTable>> {
    @Override
    public Subscription operateChannelDb(final RequestCallback<List<NewsChannelTable>> callback) {
        return Observable.create(new Observable.OnSubscribe<List<NewsChannelTable>>() {
            @Override
            public void call(Subscriber<? super List<NewsChannelTable>> subscriber) {

                //获取数据库
                final NewsChannelTableDao dao = ((App) App.getContext()).getDaoSession()
                        .getNewsChannelTableDao();

                Logger.i("初始化了数据库了吗？ " + SharedPreferenceUtil.readBoolean("initDb"));
                //初始化数据库
                //从SP中读"initDb"的值，true则要求初始化，否则跳过这个if，直接查询
                if (!SharedPreferenceUtil.readBoolean("initDb")) {

                    List<String> channelName = Arrays.asList(App.getContext().getResources()
                            .getStringArray(R.array.news_channel));

                    List<String> channelId = Arrays.asList(App.getContext().getResources()
                            .getStringArray(R.array.news_channel_id));

                    for (int i = 0; i < channelName.size(); i++) {
                        NewsChannelTable table = new NewsChannelTable(i,channelName.get(i),
                                channelId.get(i), Api.getType(channelId.get(i)), i <= 2,
                                // 前三是固定死的，默认选中状态
                                i, i <= 2);
                        dao.insert(table);
                    }
                    SharedPreferenceUtil.writeBoolean("initDb", true);
                    Logger.i("数据库初始化完毕！");
                }

                //查询数据库中select=1的，作为初始的三个新闻列表
                final Query<NewsChannelTable> build = dao.queryBuilder()
                        .where(NewsChannelTableDao.Properties.New_channel_select.eq(true))
                        .orderAsc(NewsChannelTableDao.Properties.New_channel_index).build();

                subscriber.onNext(build.list()); //build.list返回NewsChannelTable实体的list集合
                subscriber.onCompleted();

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(new Action0() {                    //在下一次subscribeOn之前能够做些预处理 Action0：无参无返回
//                    @Override
//                    public void call() {
//                        callback.beforeRequest();
//                    }
//                }).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<NewsChannelTable>>() {
                    @Override
                    public void onCompleted() {
//                        callback.requestComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.getMessage());
                        callback.requestError(e.getLocalizedMessage() + "\n" + e);
                    }

                    @Override
                    public void onNext(List<NewsChannelTable> newsChannels) {
                        try {
                            callback.requestSuccess(newsChannels);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Logger.i("INewsInteractorImpl请求成功");
                    }
                });
    }
}

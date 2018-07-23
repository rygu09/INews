package com.ustc.gry.inews.module.news.model;

import com.ustc.gry.inews.callback.RequestCallback;

import rx.Subscription;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/3
 */

public interface INewsInteractor<T> {

    Subscription operateChannelDb(RequestCallback<T> callback);

}

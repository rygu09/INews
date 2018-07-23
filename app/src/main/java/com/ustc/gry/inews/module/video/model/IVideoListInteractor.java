package com.ustc.gry.inews.module.video.model;

import com.ustc.gry.inews.callback.RequestCallback;

import rx.Subscription;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/5
 */

public interface IVideoListInteractor<T> {

    Subscription requestVideoList(RequestCallback<T> callback, String id, int startPage);

}
package com.ustc.gry.inews.module.picture.model;

import com.ustc.gry.inews.callback.RequestCallback;

import rx.Subscription;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/22
 */

public interface IPictureListInteractor<T> {
    Subscription requestPictureList(RequestCallback<T> callback, String id, int startPage);

}

package com.ustc.gry.inews.base;

import com.ustc.gry.inews.callback.RequestCallback;

import rx.Subscription;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/3
 */

public class BasePresenterImpl<V extends BaseView,T> implements BasePresenter,RequestCallback<T>{

    protected Subscription mSubscription;
    protected V mView;

    public BasePresenterImpl(V view) {
        mView = view;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestory() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mView = null;
    }

//    @Override
//    public void beforeRequest() {
//        mView.showProgress();
//    }

    @Override
    public void requestSuccess(T data) {

    }

    @Override
    public void requestError(String msg) {
    }

//    @Override
//    public void requestComplete() {
//        mView.hideProgress();
//    }
}

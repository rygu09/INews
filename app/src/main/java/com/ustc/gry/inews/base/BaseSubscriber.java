package com.ustc.gry.inews.base;

import android.support.annotation.CallSuper;

import com.orhanobut.logger.Logger;
import com.ustc.gry.inews.app.App;
import com.ustc.gry.inews.callback.RequestCallback;
import com.ustc.gry.inews.utils.http.NetUtil;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/14
 */

public class BaseSubscriber<T> extends Subscriber<T> {

    private RequestCallback<T> mRequestCallback;

    public BaseSubscriber(RequestCallback<T> requestCallback) {
        mRequestCallback = requestCallback;
    }

    @CallSuper
    @Override
    public void onStart() {
        super.onStart();
//        if (mRequestCallback != null) {
//            mRequestCallback.beforeRequest();
//        }
    }

    @CallSuper
    @Override
    public void onCompleted() {
//        if (mRequestCallback != null) {
//            mRequestCallback.requestComplete();
//        }
    }

    @CallSuper
    @Override
    public void onError(Throwable e) {
        if (mRequestCallback != null) {
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
            mRequestCallback.requestError(errorMsg);
        }
    }

    @CallSuper
    @Override
    public void onNext(T t) {
        if (mRequestCallback != null) {
            try {
                mRequestCallback.requestSuccess(t);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

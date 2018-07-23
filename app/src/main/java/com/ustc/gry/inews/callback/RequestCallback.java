package com.ustc.gry.inews.callback;

import java.io.IOException;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/3
 */

public interface RequestCallback<T> {

    /**
     * 请求成功调用
     *
     * @param data 数据
     */
    void requestSuccess(T data) throws IOException;

    /**
     * 请求错误调用
     *
     * @param msg 错误信息
     */
    void requestError(String msg);

}

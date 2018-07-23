package com.ustc.gry.inews.module.news.presenter;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/9
 */

public interface INewsChannelPresenter {
    void onItemAddOrRemove(String channelName, boolean selectState);

    void onItemSwap(int fromPos, int toPos);

    void onDestory();
}

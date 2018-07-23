package com.ustc.gry.inews.module.news.presenter;


import com.ustc.gry.inews.base.BasePresenter;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/3
 */

public interface INewsPresenter extends BasePresenter{

    /**
     * 频道排序或增删变化后调用此方法更新数据库
     */
    void operateChannelDb();

}

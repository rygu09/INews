package com.ustc.gry.inews.module.news.view;

import com.ustc.gry.inews.base.BaseView;
import com.ustc.gry.inews.greendao.NewsChannelTable;

import java.util.List;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/3
 */

public interface INewsView extends BaseView {

    void initViewPager(List<NewsChannelTable> newsChannels);


}

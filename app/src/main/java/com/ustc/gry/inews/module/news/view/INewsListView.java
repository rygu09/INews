package com.ustc.gry.inews.module.news.view;

import com.ustc.gry.inews.bean.news.NewsBean;

import java.util.List;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/5
 */

public interface INewsListView {

    void updateNewsList(List<NewsBean> data,String errorMsg,int type);

    void showProgress();

    void hideProgress();
}

package com.ustc.gry.inews.module.video.view;

import com.ustc.gry.inews.bean.VideoBean;

import java.util.List;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/5
 */

public interface IVideoListView {

    void updateVideoList(List<VideoBean> data, String errorMsg, int type);

    void showProgress();

    void hideProgress();
}

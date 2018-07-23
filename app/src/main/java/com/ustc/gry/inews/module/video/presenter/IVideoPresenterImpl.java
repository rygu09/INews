package com.ustc.gry.inews.module.video.presenter;

import com.ustc.gry.inews.module.video.view.IVideoView;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/22
 */

public class IVideoPresenterImpl {

    private IVideoView mView;


    public IVideoPresenterImpl(IVideoView view) {
        mView=view;
        mView.initViewPager();
    }
}

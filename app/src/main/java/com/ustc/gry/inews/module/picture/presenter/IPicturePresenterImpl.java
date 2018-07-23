package com.ustc.gry.inews.module.picture.presenter;

import com.ustc.gry.inews.module.picture.view.IPictureView;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/22
 */

public class IPicturePresenterImpl {

    private IPictureView mView;


    public IPicturePresenterImpl(IPictureView view) {
        mView=view;
        mView.initViewPager();
    }
}

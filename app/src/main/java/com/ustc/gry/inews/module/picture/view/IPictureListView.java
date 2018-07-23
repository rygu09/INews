package com.ustc.gry.inews.module.picture.view;

import com.ustc.gry.inews.bean.pic.PictureBean;

import java.util.List;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/22
 */

public interface IPictureListView {
    void updatePictureList(List<PictureBean> data, String errorMsg, int type);

    void showProgress();

    void hideProgress();
}

package com.ustc.gry.inews.module.setting;

import android.os.Bundle;

import com.ustc.gry.inews.R;
import com.ustc.gry.inews.base.BaseFragment;
import com.ustc.gry.inews.module.picture.ui.PictureFragment;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/8
 */

public class SettingFragment extends BaseFragment{
    private static final String TAG = "SettingFragment";

    public static SettingFragment newInstance(String title) {
        SettingFragment f = new SettingFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        f.setArguments(args);
        return f;
    }

    @Override
    public int setFragmentLayoutID() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initData() {
        isViewCreated = true;
    }

    @Override
    protected void initView() {

    }


}

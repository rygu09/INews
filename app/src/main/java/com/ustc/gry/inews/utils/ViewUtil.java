package com.ustc.gry.inews.utils;

import android.support.design.widget.TabLayout;
import android.view.View;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/4
 */

public class ViewUtil {


    /**
     * 根据Tab合起来的长度动态修改tab的模式
     *
     * @param tabLayout TabLayout
     */
    public static void dynamicSetTabLayoutMode(TabLayout tabLayout) {
        int tabTotalWidth = 0;
        int screensize_x=MeasureUtil.getScreenSize(tabLayout.getContext()).x;
        int btn_add_px=MeasureUtil.dip2px(tabLayout.getContext(),40);
        for (int i = 0; i < tabLayout.getChildCount(); i++) {
            final View view = tabLayout.getChildAt(i);
            view.measure(0, 0);
            tabTotalWidth += view.getMeasuredWidth();
        }
        if (tabTotalWidth <= screensize_x-btn_add_px) {
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }
}

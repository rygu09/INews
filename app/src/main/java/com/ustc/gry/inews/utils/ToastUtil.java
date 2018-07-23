package com.ustc.gry.inews.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/3
 */

public class ToastUtil {
    public static void make(Context context,String s){
        Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
    }
}

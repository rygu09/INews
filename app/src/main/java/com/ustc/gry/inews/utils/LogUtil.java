package com.ustc.gry.inews.utils;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/7
 */

public class LogUtil {
    public static void i(@NonNull String message){
        Logger.e(message);
    }
}

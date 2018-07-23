package com.ustc.gry.inews.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ustc.gry.inews.R;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/8
 */

public class ImageLoaderUtils {
    public static void display(Context context, ImageView imageView, String url, int placeholder, int error) {
        if(imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url) {
        if(imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context)
                .load(url)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.place_holder_pic)//占位图
                .error(R.drawable.ic_error_outline_black) //出错的占位图
                .centerCrop()//CenterCrop()是一个裁剪技术，即缩放图像让它填充到 ImageView 界限内并且裁剪额外的部分
                .into(imageView);
    }
}

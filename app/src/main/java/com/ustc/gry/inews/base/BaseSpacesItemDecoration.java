package com.ustc.gry.inews.base;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/13
 */

public class BaseSpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;

    public BaseSpacesItemDecoration(int space) {
        this.mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //position
        //如果是两列 左0，右1，左2，右3
        int position = parent.getChildAdapterPosition(view);

        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.top = 0;
        outRect.bottom = mSpace ;

        if (parent.getLayoutManager() instanceof StaggeredGridLayoutManager) {

            //如果是两列 position不一定是 左0，右1，左2，右3 是根据高度随机安排
            // 根据params.getSpanIndex()来判断左右边确定分割线
            final int index = ((StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanIndex();

            if (index % 2 == 0) {
                outRect.right = mSpace / 2 ;
            } else {
                outRect.left = mSpace / 2 ;
            }

            if (position < ((StaggeredGridLayoutManager) parent.getLayoutManager()).getSpanCount()) {
                outRect.top = mSpace;
            }

        } else if (parent.getLayoutManager() instanceof GridLayoutManager) {
            int spanCount=((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
            if(spanCount==2){
                /**
                 * 目的是：有两列，则左、中、右各有一个mSpace
                 */
                if (position % 2 == 0) {
                    // 别忘了 上面定义了这个 outRect.left=mSpace;
                    outRect.right = mSpace / 2 ;
                } else {
                    outRect.left = mSpace / 2 ;
                    // 别忘了 上面定义了这个 outRect.right=mSpace;
                }
                if (position < ((GridLayoutManager) parent.getLayoutManager()).getSpanCount()) {
                    // 保证第一行有相对顶部有高度
                    outRect.top = mSpace;
                }
            }else if(spanCount==4){
                /**
                 * 目的是：有两列，则左、中、右各有一个mSpace
                 */
                if (position % 4 == 0) {
                    outRect.right = mSpace / 2 ;
                } else if(position % 4 == 1 || position % 4 == 2){
                    outRect.left = mSpace / 2 ;
                    outRect.right=mSpace/2;
                }else {
                    outRect.left = mSpace / 2 ;
                }
                if (position < ((GridLayoutManager) parent.getLayoutManager()).getSpanCount()) {
                    // 保证第一行有相对顶部有高度
                    outRect.top = mSpace;
                }
            }
        } else if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            if (position == 0) {
                // 保证第一行有相对顶部有高度
                outRect.top = mSpace;
            }
        }
    }
}

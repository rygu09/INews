package com.ustc.gry.inews.callback;

import android.graphics.Canvas;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/13
 */

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback{

    private final OnMoveAndSwipeListener mAdapter;
    private boolean mIsLongPressDragEnabled = true;


    public SimpleItemTouchHelperCallback(OnMoveAndSwipeListener listener) {
        mAdapter = listener;
    }

    public interface OnMoveAndSwipeListener {
        boolean onItemMove(int fromPosition, int toPosition);

        void onItemDismiss(int position);
    }

    public interface OnStateChangedListener {
        void onItemSelected();

        void onItemClear();
    }


    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            // 如果是GridView样式的RecyclerView
            // 设置拖拽方向为上下左右
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            // 不支持侧滑
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        } else {
            //如果是ListView样式的RecyclerView
            // 设置拖拽方向为上下
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            // 设置侧滑方向为从左到右和从右到左都可以
            final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            // 将方法参数设置进去
            return makeMovementFlags(dragFlags, swipeFlags);
        }
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        // 如果两个item不是一个类型的，我们让他不可以拖拽
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }

        // 回调adapter中的onItemMove方法
        return mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        // 回调adapter中的onItemDismiss方法
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    /**
     * 从静止状态变为拖拽或者滑动的时候会回调该方法，参数actionState表示当前的状态
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        //当前状态不是idel（空闲）状态时，说明当前正在拖拽或者侧滑
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            //看看这个viewHolder是否实现了onStateChangedListener接口
            if (viewHolder instanceof OnStateChangedListener) {
                OnStateChangedListener listener = (OnStateChangedListener) viewHolder;
                //回调ItemViewHolder中的onItemSelected方法来改变item的背景颜色
                listener.onItemSelected();
            }
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    public void setLongPressDragEnabled(boolean enable) {
        mIsLongPressDragEnabled = enable;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return mIsLongPressDragEnabled;
    }

    /**
     * 当用户操作完毕某个item并且其动画也结束后会调用该方法，一般我们在该方法内恢复ItemView的初始状态，防止由于复用而产生的显示错乱问题
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (viewHolder instanceof OnStateChangedListener) {
            OnStateChangedListener listener = (OnStateChangedListener) viewHolder;
            //回调ItemViewHolder中的onItemSelected方法来改变item的背景颜色
            listener.onItemClear();
        }
    }

    /**
     * 我们可以在这个方法内实现我们自定义的交互规则或者自定义的动画效果
     *
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            //根据侧滑的位移来修改item的透明度
            final float alpha = 1 - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setTranslationX(dX);
        }
    }
}

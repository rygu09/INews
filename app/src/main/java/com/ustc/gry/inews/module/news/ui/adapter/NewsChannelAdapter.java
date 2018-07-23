package com.ustc.gry.inews.module.news.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.ustc.gry.inews.R;
import com.ustc.gry.inews.base.BaseRecyclerViewHolder;
import com.ustc.gry.inews.callback.OnItemClickListener;
import com.ustc.gry.inews.callback.SimpleItemTouchHelperCallback;
import com.ustc.gry.inews.greendao.NewsChannelTable;

import java.util.Collections;
import java.util.List;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/13
 */

public class NewsChannelAdapter extends RecyclerView.Adapter<NewsChannelAdapter.NewsChannelHolder>
        implements OnItemClickListener,SimpleItemTouchHelperCallback.OnMoveAndSwipeListener {


    private Context mContext;
    protected List<NewsChannelTable> mData;

    private LayoutInflater mInflater;
    protected OnItemClickListener mClickListener;
    private SimpleItemTouchHelperCallback mItemTouchHelperCallback;

    private OnItemMoveListener mItemMoveListener;


    public NewsChannelAdapter(Context mContext, List<NewsChannelTable> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public NewsChannelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final NewsChannelHolder holder = new NewsChannelHolder(mContext,
                mInflater.inflate(getItemLayoutId(viewType), parent, false));
        if (mClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(v, holder.getLayoutPosition());
                }

            });
        }
        if (mItemTouchHelperCallback != null) {
            holder.itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    // 触摸事件发生的时候，如果是定死频道，直接不给拖拽
                    if (mData.get(holder.getLayoutPosition()).getNew_channel_fixed()) {
                        Logger.e("触摸事件发生的时候，如果是定死频道，直接不给拖拽");
                        mItemTouchHelperCallback.setLongPressDragEnabled(false);
                        return true;
                    } else {
                        mItemTouchHelperCallback.setLongPressDragEnabled(true);
                    }

                    return false;
                }
            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsChannelHolder holder, int position) {
        if(mData.get(position).getNew_channel_fixed()){
            holder.getTextView(R.id.tv_news_channel).setBackgroundColor(mContext.getResources().getColor(R.color.divider));
        }
        holder.getTextView(R.id.tv_news_channel).setText(mData.get(position).getNew_channel_name());
        holder.getTextView(R.id.tv_news_channel).setSelected(mData.get(position).getNew_channel_fixed());
    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    public int getItemLayoutId(int viewType) {
        return R.layout.item_news_channel;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        // 前三固定死不能交换
        if (!mData.get(fromPosition).getNew_channel_fixed() && !mData.get(toPosition)
                .getNew_channel_fixed()) {
            //交换mItems数据的位置
            Collections.swap(mData, fromPosition, toPosition);
            //交换RecyclerView列表中item的位置
            notifyItemMoved(fromPosition, toPosition);

            if (mItemMoveListener != null) {
                mItemMoveListener.onItemMove(fromPosition, toPosition);
            }

            return true;
        }
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        //删除mItems数据
        mData.remove(position);
        //删除RecyclerView列表对应item
        notifyItemRemoved(position);
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    /**
     * 实现长按选中的效果
     */
    static class NewsChannelHolder extends BaseRecyclerViewHolder
            implements SimpleItemTouchHelperCallback.OnStateChangedListener {

        public NewsChannelHolder(Context context, View itemView) {
            super(context, itemView);
        }

        @Override
        public void onItemSelected() {
            // Enable设为false，改变背景颜色
            itemView.setEnabled(false);
        }

        @Override
        public void onItemClear() {
            // Enable设为true，恢复背景颜色
            itemView.setEnabled(true);
        }
    }

    /**
     * 设置item拖拽移动的监听
     * @param itemMoveListener item移动时的监听
     */
    public void setItemMoveListener(OnItemMoveListener itemMoveListener) {
        mItemMoveListener = itemMoveListener;
    }

    public interface OnItemMoveListener {
        void onItemMove(int fromPosition, int toPosition);
    }

    public void setItemTouchHelper(SimpleItemTouchHelperCallback callback1) {
        mItemTouchHelperCallback = callback1;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    public void add(int pos, NewsChannelTable item) {
        mData.add(pos, item);
        notifyItemInserted(pos);
    }

    public void delete(int pos) {
        mData.remove(pos);
        notifyItemRemoved(pos);
    }

    public void addMoreData(List<NewsChannelTable> data) {
        int startPos = mData.size();
        mData.addAll(data);
        notifyItemRangeInserted(startPos, data.size());
    }

    public List<NewsChannelTable> getData() {
        return mData;
    }

    public void setData(List<NewsChannelTable> data) {
        mData = data;
        notifyDataSetChanged();
    }

}

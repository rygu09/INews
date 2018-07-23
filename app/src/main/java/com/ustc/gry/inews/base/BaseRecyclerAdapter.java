package com.ustc.gry.inews.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ustc.gry.inews.R;
import com.ustc.gry.inews.callback.OnEmptyClickListener;
import com.ustc.gry.inews.callback.OnItemClickListener;
import com.ustc.gry.inews.callback.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/9
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder>{

    private List<T> mData;
    private Context mContext;

    public static final int TYPE_HEADER = 1;
    public static final int TYPE_ITEM = 2;
    public static final int TYPE_MORE = 3;
    public static final int TYPE_EMPTY = 4;
    private static final int TYPE_MORE_FAIL = 5;

    protected LayoutInflater mInflater;
    protected OnItemClickListener mClickListener;
    private OnEmptyClickListener mEmptyClickListener;
    private OnLoadMoreListener mOnLoadMoreListener;
    protected boolean mShowEmptyView;
    private Boolean mEnableLoadMore;
    protected boolean mShowLoadMoreView;

    private RecyclerView.LayoutManager mLayoutManager;

    private int mLastPosition = -1;
    private String mExtraMsg;
    private int mMoreItemCount;

    public BaseRecyclerAdapter(Context context, List<T> data) {
        this(context, data, null);
    }

    public BaseRecyclerAdapter(Context context, List<T> data, RecyclerView.LayoutManager layoutManager) {
        mContext = context;
        mLayoutManager = layoutManager;
        mData = data == null ? new ArrayList<T>() : data;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * The new ViewHolder will be used to display items of the adapter using
     * {(onBindViewHolder ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_MORE) {
            return new BaseRecyclerViewHolder(mContext, mInflater.inflate(R.layout.item_load_more, parent, false));
        } else if (viewType == TYPE_MORE_FAIL) {
            final BaseRecyclerViewHolder holder = new BaseRecyclerViewHolder(mContext, mInflater.inflate(R.layout.item_load_more_failed, parent, false));
            if (mOnLoadMoreListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mEnableLoadMore = true;
                        mShowLoadMoreView = true;
                        notifyItemChanged(getItemCount() - 1);//It indicates that any reflection of the data at <code>position</code> is out of date and should be updated.
                        holder.itemView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mOnLoadMoreListener.loadMore();
                            }
                        }, 300);//300ms后loadMore
                    }
                });
            }
            return holder;
        } else if (viewType == TYPE_EMPTY) {
            final BaseRecyclerViewHolder holder = new BaseRecyclerViewHolder(mContext, mInflater.inflate(R.layout.item_empty_view, parent, false));
            if (mEmptyClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mEmptyClickListener.onEmptyClick();
                    }
                });
            }
            return holder;
        } else {
            final BaseRecyclerViewHolder holder = new BaseRecyclerViewHolder(mContext, mInflater.inflate(getItemLayoutId(viewType), parent, false));
            if (mClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.getLayoutPosition() != RecyclerView.NO_POSITION) {
                            try {
                                mClickListener.onItemClick(v, holder.getLayoutPosition());
                            } catch (IndexOutOfBoundsException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
            return holder;
        }
    }
    public abstract int getItemLayoutId(int viewType);

    public abstract void bindData(BaseRecyclerViewHolder holder, int position, T item);

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the ViewHolder itemView to reflect the item at the given
     * position.
     */
    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder holder, int position) {

        if (getItemViewType(position) == TYPE_MORE_FAIL) {

            holder.setText(R.id.tv_failed, mExtraMsg + "请点击重试！");

        } else if (getItemViewType(position) == TYPE_EMPTY) {

            holder.setText(R.id.tv_error, mExtraMsg);

        } else {

            bindData(holder, position, mData.get(position));

        }
        //不展示空view 加载更多监听器不为空 允许加载更多 还没展示更多view 位置=ItemCount()-1 Item数>=mMoreItemCount
        if (!mShowEmptyView && mOnLoadMoreListener != null && (mEnableLoadMore != null && mEnableLoadMore) && !mShowLoadMoreView && position == getItemCount() - 1 && getItemCount() >= mMoreItemCount) {
            mShowLoadMoreView = true;
            holder.itemView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mOnLoadMoreListener.loadMore();
                    notifyItemInserted(getItemCount());
                }
            }, 300);
        }
    }



    @Override
    public void onViewDetachedFromWindow(BaseRecyclerViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    public void add(int pos, T item) {
        mData.add(pos, item);
        notifyItemInserted(pos);
    }

    public void delete(int pos) {
        mData.remove(pos);
        notifyItemRemoved(pos);
    }

    public void addMoreData(List<T> data) {
        int startPos = mData.size();
        mData.addAll(data);
        notifyItemRangeInserted(startPos, data.size());
    }

    public List<T> getData() {
        return mData;
    }

    //初始setData或者refresh
    public void setData(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        if (mShowEmptyView) {
            return TYPE_EMPTY;
        }

        if (mOnLoadMoreListener != null && (mEnableLoadMore != null && mEnableLoadMore) && mShowLoadMoreView && getItemCount() - 1 == position) {
            return TYPE_MORE;
        }

        if (mOnLoadMoreListener != null && !mShowLoadMoreView && (mEnableLoadMore != null && !mEnableLoadMore) && getItemCount() - 1 == position) {
            return TYPE_MORE_FAIL;
        }

        return bindViewType(position);
    }

    @Override
    public int getItemCount() {


        //（加载更多监听器为空 或 允许加载更多为空） 0 ：【（允许加载更多 且 展示更多view）或 （不展示更多view 且 不允许加载更多）？1：0 】
        int i = mOnLoadMoreListener == null || mEnableLoadMore == null ? 0 : (mEnableLoadMore && mShowLoadMoreView) || (!mShowLoadMoreView && !mEnableLoadMore) ? 1 : 0;
        //返回 展示空view ？ 1：【（mData不为空）？mData.size() + i : 0 】
        return mShowEmptyView ? 1 : mData != null ? mData.size() + i : 0;
    }


    protected int bindViewType(int position) {
        return 0;
    }

    public void showEmptyView(boolean showEmptyView, @NonNull String msg) {
        mShowEmptyView = showEmptyView;
        mExtraMsg = msg;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    public void setOnEmptyClickListener(OnEmptyClickListener listener) {
        mEmptyClickListener = listener;
    }

    public void setOnLoadMoreListener(int moreItemCount, @NonNull OnLoadMoreListener onLoadMoreListener) {
        mMoreItemCount = moreItemCount;
        mOnLoadMoreListener = onLoadMoreListener;
        mEnableLoadMore = true;
    }

    public void loadMoreSuccess() {
        mEnableLoadMore = true;
        mShowLoadMoreView = false;
        notifyItemRemoved(getItemCount());
    }

    public void loadMoreFailed(String errorMsg) {
        mEnableLoadMore = false;
        mShowLoadMoreView = false;
        mExtraMsg = errorMsg;
        notifyItemChanged(getItemCount() - 1);
    }

    /**
     * 设置是否开启底部加载
     *
     * @param enableLoadMore true为开启；false为关闭；null为已经全部加载完毕的关闭
     */
    public void enableLoadMore(Boolean enableLoadMore) {
        mEnableLoadMore = enableLoadMore;
    }

}

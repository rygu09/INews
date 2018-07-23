package com.ustc.gry.inews.module.news.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ustc.gry.inews.R;
import com.ustc.gry.inews.bean.news.NewsBean;
import com.ustc.gry.inews.callback.OnItemClickListener;
import com.ustc.gry.inews.utils.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/19
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyViewHoder> {

    Context mContext;
    List<NewsBean> mData;

    private OnItemClickListener mOnItemClickListener;

    public NewsListAdapter(Context mContext) {
        this.mContext = mContext;
        mData =new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_news,parent,false);

        final MyViewHoder holder=new MyViewHoder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v,holder.getLayoutPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoder holder, int position) {
        NewsBean news= mData.get(position);
        holder.tvTitle.setText(news.getTitle());
        holder.tvSrc.setText(news.getSource());
        ImageLoaderUtils.display(mContext, holder.imageView, news.getImgsrc());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<NewsBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public void addMoreData(List<NewsBean> data) {
        int startPos = mData.size();
        mData.addAll(data);
        notifyItemRangeInserted(startPos, data.size());
    }


    public class MyViewHoder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvSrc;
        ImageView imageView;

        public MyViewHoder(View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvSrc=itemView.findViewById(R.id.tvSrc);
            imageView=itemView.findViewById(R.id.imvNews);

        }

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){

        this.mOnItemClickListener=onItemClickListener;
    }

    public NewsBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }
}



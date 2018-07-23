package com.ustc.gry.inews.module.video.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.ustc.gry.inews.R;
import com.ustc.gry.inews.bean.VideoBean;
import com.ustc.gry.inews.callback.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/19
 */

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.MyViewHolder> {

    Context mContext;
    List<VideoBean> mData;

    private OnItemClickListener mOnItemClickListener;

    public VideoListAdapter(Context mContext) {
        this.mContext = mContext;
        mData =new ArrayList<>();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_video, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.jzVideoPlayer.setUp(
                mData.get(position).getMp4_url(), JZVideoPlayer.SCREEN_WINDOW_LIST,
                mData.get(position).getTitle());
        Glide.with(holder.jzVideoPlayer.getContext()).load(mData.get(position).getCover()).into(holder.jzVideoPlayer.thumbImageView);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        JZVideoPlayerStandard jzVideoPlayer;

        public MyViewHolder(View itemView) {
            super(itemView);
            jzVideoPlayer = itemView.findViewById(R.id.videoplayer);
        }
    }

    public void setData(List<VideoBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public void addMoreData(List<VideoBean> data) {
        int startPos = mData.size();
        mData.addAll(data);
        notifyItemRangeInserted(startPos, data.size());
    }
}



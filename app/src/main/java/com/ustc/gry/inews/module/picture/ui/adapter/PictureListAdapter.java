package com.ustc.gry.inews.module.picture.ui.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ustc.gry.inews.R;
import com.ustc.gry.inews.bean.pic.PictureBean;
import com.ustc.gry.inews.callback.OnItemClickListener;
import com.ustc.gry.inews.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/22
 */


public class PictureListAdapter extends RecyclerView.Adapter<PictureListAdapter.MyViewHolder> {

    Context mContext;
    List<PictureBean> mData;

    private MyViewHolder holder;
    private OnItemClickListener mOnItemClickListener;

    public PictureListAdapter(Context mContext) {
        this.mContext = mContext;
        mData =new ArrayList<>();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_picture, parent,
                false));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v,holder.getLayoutPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        PictureBean pictureBean = mData.get(position);
        holder.textView.setText(pictureBean.getTitle());

        String url=pictureBean.getList().get(0).getMiddle();

        GlideUtils.loadIntoUseFitWidth(mContext,url,R.drawable.place_holder_pic,holder.imageView,20);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * holder
     */
    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imvPicture);
            textView=itemView.findViewById(R.id.tvTitle);
        }
    }

    public void setData(List<PictureBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public void addMoreData(List<PictureBean> data) {
        int startPos = mData.size();
        mData.addAll(data);
        notifyItemRangeInserted(startPos, data.size());
    }

    public PictureBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void showDialog(String big_img_url) {
        final Dialog dialog = new Dialog(mContext, R.style.edit_AlertDialog_style);
        dialog.setContentView(R.layout.dialog_picture_big);
        ImageView imageView = dialog.findViewById(R.id.imv_picture_big);
        Glide.with(mContext).load(big_img_url).into(imageView);
        dialog.setCanceledOnTouchOutside(true); // 选择true的话点击其他地方可以使dialog消失，为false的话不会消失
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        imageView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }
}
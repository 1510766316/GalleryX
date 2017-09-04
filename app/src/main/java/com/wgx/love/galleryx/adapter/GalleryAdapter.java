package com.wgx.love.galleryx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wgx.love.galleryx.R;
import com.wgx.love.galleryx.bean.PictureInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wugx on 17-9-4.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private List<PictureInfo> mList;
    private Context mContext;

    public GalleryAdapter(Context context) {
        mList = new ArrayList<>();
        mContext = context;
    }

    public List<PictureInfo> getList() {
        return mList;
    }

    public void addAllAndNotify(List<PictureInfo> list){
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_layout, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mList.get(position).getName());
       // holder.mImageView.setImageDrawable(mList.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.name);
            mImageView = (ImageView) view.findViewById(R.id.cover);
        }
    }
}

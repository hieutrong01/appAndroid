package com.ocr.navigation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ocr.navigation.OOP.Image;
import com.ocr.navigation.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private List<Image> mImageList;
    private Context mContext;

    public ImageAdapter(Context mContext, List<Image> mImageList) {
        this.mContext = mContext;
        this.mImageList = mImageList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Image image = mImageList.get(position);
        if (image != null) {
            Glide.with(mContext).load(image.getImage()).into(holder.imgView);
        }
    }

    @Override
    public int getItemCount() {
        return mImageList != null ? mImageList.size() : 0;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.img_view);
        }
    }
}

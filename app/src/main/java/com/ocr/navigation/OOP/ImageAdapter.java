package com.ocr.navigation.OOP;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ocr.navigation.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{
    private final List<Image> mImageList;

    public ImageAdapter(List<Image> mImageList) {
        this.mImageList = mImageList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_image,parent,false );
        return new ImageViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Image image= mImageList.get( position );
        if (image==null){
            return;
        }
        holder.imgView.setImageResource( image.getResourceID() );

    }

    @Override
    public int getItemCount() {
        if (mImageList!=null){
            return mImageList.size();
        }
        return 0;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imgView;

        public ImageViewHolder(@NonNull View itemView) {
            super( itemView );
            imgView =itemView.findViewById( R.id.img_view );
        }
    }
}

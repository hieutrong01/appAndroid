package com.ocr.navigation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ocr.navigation.OOP.ProductList;
import com.ocr.navigation.R;

import java.util.ArrayList;
import java.util.List;

public class FavouriteAdapter  extends RecyclerView.Adapter<FavouriteAdapter.FavoriteViewHoder> {
    private Context mContext;
    private List<ProductList> mFavoriteList;

    public FavouriteAdapter(ArrayList<ProductList> items) {
    }


    public void FavoriteAdapter(ArrayList<ProductList> favoriteItems) {
        this.mFavoriteList = favoriteItems;
    }
    
    public void setData(List<ProductList> list) {
        this.mFavoriteList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_product_favourite,parent,true );
        return new FavoriteViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHoder holder, int position) {
        ProductList item = mFavoriteList.get(position);
        if (item==null){
            return;
        }
        holder.tvTenProduct.setText( item.getProductName() );
        holder.tvMaProduct.setText( item.getIdProduct() );
        holder.tvGiaProduct.setText( item.getFormattedPrice() );
        holder.imgProduct.setImageResource( item.getResourceImage() );

    }

    @Override
    public int getItemCount() {
        if (mFavoriteList!=null){
            return mFavoriteList.size();
        }
        return 0;
    }

    public class FavoriteViewHoder extends RecyclerView.ViewHolder {
        private ImageView imgProduct;
        private TextView tvTenProduct,tvMaProduct,tvGiaProduct;
        private CardView mCardView;
        public FavoriteViewHoder(@NonNull View itemView) {
            super( itemView );
            imgProduct=itemView.findViewById( R.id.img_product );
            tvTenProduct=itemView.findViewById( R.id.tv_ten_product );
            tvMaProduct=itemView.findViewById( R.id.tv_id_product );
            tvGiaProduct=itemView.findViewById( R.id.tv_cost_product );
        }
    }
}

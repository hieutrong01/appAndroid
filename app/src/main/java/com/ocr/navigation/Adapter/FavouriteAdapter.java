package com.ocr.navigation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ocr.navigation.OOP.Product;
import com.ocr.navigation.R;
import com.ocr.navigation.dataLocal.Database;
import com.ocr.navigation.my_interface.ClickItemProduc;

import java.text.DecimalFormat;
import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavoriteViewHolder> {
    private Context mContext;
    private List<Product> mFavoriteList;
    private ClickItemProduc clickItemProduc;

    private boolean isFavorite=false ;



    public FavouriteAdapter(List<Product> favoriteItems) {
        mFavoriteList = favoriteItems;
    }

    public void setData(List<Product> data, ClickItemProduc listener) {
       this.mFavoriteList = data;
        this.clickItemProduc=listener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_favourite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        Product item = mFavoriteList.get(position);
        if (item == null) {
            return;
        }
        holder.tvTenProduct.setText(item.getName());
        holder.tvMaProduct.setText(String.valueOf(item.getProduct_id()));
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGiaProduct.setText(decimalFormat.format(item.getPrice()) + " VND");
        Glide.with(holder.itemView.getContext()).load(item.getImage()).into(holder.imgProduct);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItemProduc.onItemProductClick( item);
            }
        });

        // Thiết lập hình ảnh yêu thích tương ứng với trạng thái
        List<Product> favoriteProducts = Database.getInstance( mContext ).favouriteDAO().getListFavourite();
        if (favoriteProducts.contains(item)) {
            isFavorite = true;
            holder.imgFavourite.setImageResource(R.drawable.ic_read_favorite);
        } else {
            isFavorite = false;
            holder.imgFavourite.setImageResource(R.drawable.ic_favorite);
        }

        holder.imgFavourite.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavorite) {
                    holder.imgFavourite.setImageResource(R.drawable.ic_favorite);
                    // Xử lý gỡ khỏi yêu thích
                    Database.getInstance( mContext ).favouriteDAO().deleteFavourite( item );

                } else {
                    holder.imgFavourite.setImageResource(R.drawable.ic_read_favorite);
                    // Xử lý thêm vào yêu thích
                    Database.getInstance( mContext).favouriteDAO().insertFavourite( item );
                }
                isFavorite = !isFavorite;
            }
        } );
    }

    @Override
    public int getItemCount() {
        if (mFavoriteList!=null){
            return mFavoriteList.size();
        }
        return 0;
    }


    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgProduct,imgFavourite;
        private TextView tvTenProduct, tvMaProduct, tvGiaProduct;
        private LinearLayout linearLayout;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product);
            tvTenProduct = itemView.findViewById(R.id.tv_ten_product);
            tvMaProduct = itemView.findViewById(R.id.tv_id_product);
            tvGiaProduct = itemView.findViewById(R.id.tv_cost_product);
            linearLayout = itemView.findViewById(R.id.linear_layout);
            imgFavourite=itemView.findViewById( R.id.img_favourite );

            // Xử lý sự kiện khi nhấn vào một sản phẩm trong danh sách yêu thích


        }
    }
}

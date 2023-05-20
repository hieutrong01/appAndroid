package com.ocr.navigation.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ocr.navigation.OOP.Item;
import com.ocr.navigation.OOP.ProductList;
import com.ocr.navigation.R;
import com.ocr.navigation.my_interface.ClickItemMenSearch;
import com.ocr.navigation.my_interface.ClickItemProduc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.ListProductViewHolder> {
    private Context mContext;
    private List<ProductList> mproList;
    private List<ProductList> favoriteItems ;
    private ClickItemProduc clickItemProduc;
    private int selectedPosition = -1;

    private static final String PREFS_NAME = "MyPrefs";
    private static final String FAVORITE_ITEMS_KEY = "favoriteItems";

    public ListProductAdapter(Context mContext) {
        this.mContext = mContext;
        this.favoriteItems = new ArrayList<>();
    }

    public void setData(List<ProductList> list, ClickItemProduc listener){
        this.mproList=list;
        this.clickItemProduc=listener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(  parent.getContext()).inflate( R.layout.item_list_product,parent,false );

        return new ListProductViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ListProductViewHolder holder, int position) {
        ProductList productList = mproList.get( position );
        if (productList ==null){
            return;
        }
        holder.imgListProduct.setImageResource( productList.getResourceImage() );
        holder.tvSex.setText( productList.getGender() );
        holder.tvSize.setText( productList.getSize());
        holder.tvNameProduct.setText( productList.getProductName() );
        holder.tvgiaProduct.setText(productList.getFormattedPrice());
        holder.imgFavourite.setOnClickListener( v -> {
            if (!mproList.get( position ).getFavorite()){
                holder.imgFavourite.setImageResource( R.drawable.ic_read_favorite );
                mproList.get( position ).setFavorite( true );
            } else {
                holder.imgFavourite.setImageResource( R.drawable.heart_plus );
                mproList.get( position ).setFavorite( false );
            }
            clickItemProduc.onClickFavoriteItem( position );
        } );
        holder.tvMaSP.setText( String.valueOf(productList.getIdProduct()) );
        // Kiểm tra xem mục hiện tại có phải là mục được chọn không
       holder.cardView.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               clickItemProduc.onItemProductClick( productList );
           }
       } );
    }
    @Override
    public int getItemCount() {
        if (mproList!=null){
            return mproList.size();
        }
        return 0;
    }

    public class ListProductViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgListProduct;
        private TextView tvSex,tvSize,tvNameProduct,tvgiaProduct,tvMaSP;
        private ImageView imgFavourite;
        private CardView cardView;

        public ListProductViewHolder(@NonNull View itemView) {
            super( itemView );
            imgListProduct=itemView.findViewById( R.id.img_list_product );
            tvSex=itemView.findViewById( R.id.tv_list_sex );
            tvSize=itemView.findViewById( R.id.tv_list_size );
            tvNameProduct=itemView.findViewById( R.id.tv_list_name_product );
            tvgiaProduct=itemView.findViewById( R.id.tv_list_cost_product );
            imgFavourite=itemView.findViewById( R.id.icon_list_favorite );
            tvMaSP=itemView.findViewById( R.id.tv_ma_sp );
            cardView=itemView.findViewById( R.id.card_view_product );

        }
    }
}
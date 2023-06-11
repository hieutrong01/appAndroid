package com.ocr.navigation.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ocr.navigation.OOP.Product;
import com.ocr.navigation.R;
import com.ocr.navigation.my_interface.ClickItemProduc;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.ListProductViewHolder> {
    private Context mContext;
    private List<Product> mproList;

    private ClickItemProduc clickItemProduc;

    // Tạo bản sao của danh sách mục ban đầu
    List<Product> originalItems ;

    public ListProductAdapter(Context mContext) {
        this.mContext = mContext;
       this.originalItems=new ArrayList<>();
    }

    public void setData(List<Product> list, ClickItemProduc listener){
        this.mproList=list;
        this.clickItemProduc=listener;
        this.originalItems=new ArrayList<>(mproList);
        notifyDataSetChanged();
    }

    public void giathapcao() {
        Collections.sort(mproList, new Comparator<Product>() {
            @Override
            public int compare(Product item1, Product item2) {
                return Double.compare(item1.getPrice(), item2.getPrice());
            }
        });
        notifyDataSetChanged();
    }
    public void giaCaoThap(){
        Collections.sort( mproList, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return Double.compare( o2.getPrice(),o1.getPrice() );
            }
        } );
        notifyDataSetChanged();
    }
    public void  tieuBieu(){
        // Gán lại danh sách mục từ bản sao ban đầu vào danh sách hiện tại
        mproList.clear();
        mproList.addAll(originalItems);

        // Cập nhật RecyclerView
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ListProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(  parent.getContext()).inflate( R.layout.item_list_product,parent,false );

        return new ListProductViewHolder( view );
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListProductViewHolder holder, int position) {
        Product product = mproList.get( position );
        if (product ==null){
            return;
        }

        Glide.with( mContext ).load( product.getImage() ).into( holder.imgListProduct );
        holder.tvSex.setText( product.getGender() );
        holder.tvNameProduct.setText( product.getName() );
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        holder.tvgiaProduct.setText( decimalFormat.format (product.getPrice())+ " VND" );
        holder.tvMaSP.setText( String.valueOf(product.getProduct_id()) );
        // Kiểm tra xem mục hiện tại có phải là mục được chọn không
        holder.cardView.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               clickItemProduc.onItemProductClick( product );
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
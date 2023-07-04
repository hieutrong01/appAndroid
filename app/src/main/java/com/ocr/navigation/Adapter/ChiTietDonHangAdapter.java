package com.ocr.navigation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ocr.navigation.OOP.ItemDonHang;
import com.ocr.navigation.R;

import java.util.List;

public class ChiTietDonHangAdapter extends RecyclerView.Adapter<ChiTietDonHangAdapter.ChiTietViewHolder> {
    private List<ItemDonHang> list;
    private Context context;

    public ChiTietDonHangAdapter(List<ItemDonHang> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ChiTietDonHangAdapter.ChiTietViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chi_tiet_don_hang, parent, false);
        return new ChiTietDonHangAdapter.ChiTietViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiTietDonHangAdapter.ChiTietViewHolder holder, int position) {
        ItemDonHang itemDonHang=list.get( position );
        if (itemDonHang==null){
            return;
        }
        holder.tvTenProduct.setText( itemDonHang.getName() );
        holder.tvMaProduct.setText( String.valueOf(itemDonHang.getIdsp()));
        Glide.with(holder.itemView.getContext()).load(itemDonHang.getImage()).into(holder.imgProduct);


    }

    @Override
    public int getItemCount() {
       if (list!=null){
           return list.size();
       }
       return 0;
    }

    public class ChiTietViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgProduct, imgFavourite;
        private TextView tvTenProduct, tvMaProduct, tvGiaProduct;

        public ChiTietViewHolder(@NonNull View itemView) {
            super( itemView );
            imgProduct = itemView.findViewById( R.id.img_product);
            tvTenProduct = itemView.findViewById(R.id.tv_ten_product);
            tvMaProduct = itemView.findViewById(R.id.tv_id_product);
            tvGiaProduct = itemView.findViewById(R.id.tv_cost_product);
        }
    }
}

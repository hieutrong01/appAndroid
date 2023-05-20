package com.ocr.navigation.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ocr.navigation.OOP.Item;

import com.ocr.navigation.R;
import com.ocr.navigation.my_interface.ClickItemMenSearch;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHoder>  {
   private Context mContext;
   private List<Item> mitemList;
    private ClickItemMenSearch clickItemMenSearch;

    public ItemAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<Item> list,ClickItemMenSearch listener){
        this.mitemList=list;
        this.clickItemMenSearch=listener;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ItemViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.item_product,parent,false );

        return new ItemViewHoder( view );
    }
    @Override
    public void onBindViewHolder(@NonNull ItemViewHoder holder, int position) {
        Item item=mitemList.get(position);
        if (item ==null){
            return;
        }
        holder.tvItem.setText( item.getItem());
        holder.cardView.setOnClickListener( v -> {
            clickItemMenSearch.onItemClick(item, position );
        } );
    }

    @Override
    public int getItemCount() {
        if (mitemList!=null){
            return mitemList.size();
        }
        return 0;
    }

    public class ItemViewHoder extends RecyclerView.ViewHolder{
        private TextView tvItem;
        private CardView cardView;

        public ItemViewHoder(@NonNull View itemView) {
            super( itemView );
            tvItem=itemView.findViewById( R.id.tv_item );
            cardView=itemView.findViewById( R.id.layout_item_men );
        }
    }
}

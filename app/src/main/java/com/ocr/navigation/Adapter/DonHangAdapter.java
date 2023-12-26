package com.ocr.navigation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ocr.navigation.OOP.Thanhtoan;
import com.ocr.navigation.R;

import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.DonHangViewHolder> {
    private RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
    private List<Thanhtoan> list;
    private Context context;
    private onClick onClick;
    public interface onClick{
        void onClick(Thanhtoan thanhtoan);
    }

    public DonHangAdapter(List<Thanhtoan> list, Context context, onClick onClick) {
        this.list = list;
        this.context = context;
        this.onClick=onClick;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DonHangAdapter.DonHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_don_hang, parent, false);
        return new DonHangAdapter.DonHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonHangAdapter.DonHangViewHolder holder, int position) {
        Thanhtoan thanhtoan =list.get( position );
        if (thanhtoan==null){
            return;
        }
        holder.tvDonHang.setText( "Đơn Hàng Số: "+thanhtoan.getOrder_id() +" ");
        holder.cvDelete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onClick( thanhtoan );
            }
        } );
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(
        holder.recyclerChitiet.getContext(),LinearLayoutManager.VERTICAL,false
        );
        linearLayoutManager.setInitialPrefetchItemCount( thanhtoan.getItem().size() );

        ChiTietDonHangAdapter chiTietDonHangAdapter = new ChiTietDonHangAdapter( thanhtoan.getItem(),context );
        holder.recyclerChitiet.setLayoutManager( linearLayoutManager );
        holder.recyclerChitiet.setAdapter(  chiTietDonHangAdapter);
        holder.recyclerChitiet.setRecycledViewPool( pool );


    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class DonHangViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDonHang;
        private RecyclerView recyclerChitiet;
        private CardView cvDelete;
        public DonHangViewHolder(@NonNull View itemView) {
            super( itemView );
            tvDonHang=itemView.findViewById( R.id.tv_don_hang );
            recyclerChitiet=itemView.findViewById( R.id.Recycler_chitiet );
            cvDelete=itemView.findViewById( R.id.cv_delete );
        }
    }
}

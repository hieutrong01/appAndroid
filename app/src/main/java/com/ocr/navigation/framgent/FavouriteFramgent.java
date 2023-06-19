package com.ocr.navigation.framgent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nex3z.notificationbadge.NotificationBadge;
import com.ocr.navigation.Adapter.FavouriteAdapter;
import com.ocr.navigation.GioHangActivity;
import com.ocr.navigation.OOP.Product;
import com.ocr.navigation.OOP.ProductList;
import com.ocr.navigation.R;
import com.ocr.navigation.dataLocal.DataLocalManager;
import com.ocr.navigation.utils.Utils;

import java.util.ArrayList;

public class FavouriteFramgent extends Fragment {
    private ImageView imgGioHang, imgNen;
    private TextView tvSoLuong;
    private RecyclerView mRecyclerView;
    private View mView;
    private FavouriteAdapter mItemAdapter;
    private NotificationBadge badge;
    private ArrayList<ProductList> items = new ArrayList<>();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.framgent_favourite, container, false);
        initUI();

        mItemAdapter = new FavouriteAdapter(items);
        mRecyclerView.setAdapter(mItemAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<Product> favouriteProducts = (ArrayList<Product>) DataLocalManager.getFavoriteProducts();
        if (favouriteProducts != null) {
            mItemAdapter.setData(favouriteProducts);
        }

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), linearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        if (mItemAdapter != null && mItemAdapter.getItemCount() == 0) {
            imgNen.setVisibility(View.GONE);
        } else {
            imgNen.setVisibility(View.VISIBLE);
        }

        onClickList();
        return mView;
    }


    public void initUI(){
        imgGioHang=mView.findViewById( R.id.img_gio_hang );
        imgNen=mView.findViewById( R.id.img_nen );
        mRecyclerView = mView.findViewById( R.id.rcv_list_favourite );
        tvSoLuong=mView.findViewById( R.id.tv_so_luong );
        badge=mView.findViewById( R.id.menu_sl );
        if (Utils.manggiohang!=null){
            badge.setText( String.valueOf( Utils.manggiohang.size() ) );
        }

    }
    private void onClickList() {
        imgGioHang.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), GioHangActivity.class);
                startActivity( intent );
            }
        } );

    }

    public void reloadData(){
        Toast.makeText( getActivity(),"reload favorite",Toast.LENGTH_SHORT ).show();
    }

}

package com.ocr.navigation.framgent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nex3z.notificationbadge.NotificationBadge;
import com.ocr.navigation.Adapter.FavouriteAdapter;
import com.ocr.navigation.ChiTietProductActivity;
import com.ocr.navigation.GioHangActivity;
import com.ocr.navigation.OOP.Product;
import com.ocr.navigation.R;
import com.ocr.navigation.dataLocal.Database;
import com.ocr.navigation.my_interface.ClickItemProduc;
import com.ocr.navigation.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFramgent extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private ImageView imgGioHang, imgNen,imvChinhSua ;
    private TextView tvSoLuong;
    private RecyclerView mRecyclerView;
    private View mView;
    private FavouriteAdapter mItemAdapter;
    private NotificationBadge badge;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Product> items = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.framgent_favourite, container, false);
        initUI();

        mItemAdapter = new FavouriteAdapter( items );
        mRecyclerView.setAdapter(mItemAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), linearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        onClickList();
        swipeRefreshLayout.setOnRefreshListener( this );
        return mView;
    }
    public void initUI(){
        imgGioHang=mView.findViewById( R.id.img_gio_hang );
        imvChinhSua = mView.findViewById(R.id.imv_chinh_sua);
        imgNen=mView.findViewById( R.id.img_nen );
        mRecyclerView = mView.findViewById( R.id.rcv_list_favourite );
        tvSoLuong=mView.findViewById( R.id.tv_so_luong );
        badge=mView.findViewById( R.id.menu_sl );
        swipeRefreshLayout=mView.findViewById( R.id.swiperefresh );
        if (Utils.manggiohang!=null){
            badge.setText( String.valueOf( Utils.manggiohang.size() ) );
        }

    }
    private void onClickList() {
        imgGioHang.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(requireActivity(), GioHangActivity.class);
                startActivity( intent );
            }
        } );
    }

    private void onClickgotoChitiet(Product product) {
        Intent intent = new Intent( requireActivity(), ChiTietProductActivity.class );
        Bundle bundle= new Bundle();
        bundle.putSerializable("object_product", product);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
//
    }

    @Override
    public void onRefresh() {
        // Cập nhật dữ liệu cho adapter từ FavoriteProductsManager
        items= Database.getInstance( getActivity() ).favouriteDAO().getListFavourite();
        mItemAdapter.setData( items, new ClickItemProduc() {
            @Override
            public void onItemProductClick(Product product) {
                onClickgotoChitiet( product );
            }

            @Override
            public void onClickFavoriteItem(int pos) {

            }
        } );
        if (mItemAdapter != null && mItemAdapter.getItemCount() == 0) {
            imgNen.setVisibility(View.VISIBLE);

        } else {
            imgNen.setVisibility(View.GONE);
        }
        tvSoLuong.setText( Integer.toString(mItemAdapter.getItemCount()) );
        Handler handler = new Handler();
        handler.postDelayed( new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing( false );
            }
        },2000 );

    }
}

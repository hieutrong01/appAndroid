package com.ocr.navigation.my_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ocr.navigation.Adapter.ListProductAdapter;
import com.ocr.navigation.ChiTietProductActivity;
import com.ocr.navigation.GioHangActivity;
import com.ocr.navigation.OOP.Item;
import com.ocr.navigation.OOP.ProductList;
import com.ocr.navigation.R;
import com.ocr.navigation.my_interface.ClickItemProduc;

import java.util.ArrayList;
import java.util.List;

public class MenListItem extends AppCompatActivity {
    private TextView mtextView;
    private ImageView imgBack;
    private RecyclerView mRecyclerView;
    private ListProductAdapter adapter;
    private ImageView imgGioHang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_men_list_item );
        initUI();
        onClickListener();
        //lấy dữ liệu đổ vào activity từ framgentSearch
        Bundle bundle = getIntent().getExtras();
        if (bundle==null){
            return;
        }
        Item item = (Item) bundle.get( "object_item" );
        int pos = bundle.getInt( "pos" );
        mtextView.setText( item.getItem() ) ;
        //khoi tao Adapter
        GridLayoutManager gridLayoutManager= new GridLayoutManager( this,2 );
        mRecyclerView.setLayoutManager( gridLayoutManager );
        adapter.setData( getListProduct( pos ), new ClickItemProduc() {
            @Override
            public void onItemProductClick(ProductList productList) {
                onClickgotoChitiet(productList);
            }

            @Override
            public void onClickFavoriteItem(int pos) {

            }
        } );
        mRecyclerView.setAdapter( adapter );

    }
    public void  initUI(){
        mtextView = findViewById( R.id.tv_id_item_men );
        mRecyclerView=findViewById( R.id.rcv_list_product );
        imgBack=findViewById( R.id.iv_back );
        imgGioHang=findViewById( R.id.img_gio_hang );
        //khoi tao Adapter
        adapter = new ListProductAdapter( this );
    }

    private void onClickListener() {
        imgBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
        imgGioHang.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MenListItem.this, GioHangActivity.class);
                startActivity( intent );
            }
        } );
    }
    private void onClickgotoChitiet(ProductList productList) {
        Intent intent = new Intent(MenListItem.this, ChiTietProductActivity.class );
        Bundle bundle= new Bundle();
        bundle.putSerializable("object_product", productList);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private List<ProductList> getListProduct(int pos) {
        List<ProductList> list = new ArrayList<>();
        switch (pos){
            case 0: {
                list.add(new ProductList(2345,R.drawable.img1,"Nam","XXL","ÁO THUN DRY-EX CỔ TRÒN NGẮN TAY",399000,false));
                list.add(new ProductList(2346,R.drawable.img1,"Nam","XXL","ÁO THUN DRY-EX CỔ TRÒN NGẮN TAY",599000,false));
                list.add(new ProductList(2347,R.drawable.img1,"Nam","XXL","ÁO THUN DRY-EX CỔ TRÒN NGẮN TAY",499000,false));
                list.add(new ProductList(2348,R.drawable.img1,"Nam","XXL","ÁO THUN DRY-EX CỔ TRÒN NGẮN TAY",699000,false));
                list.add(new ProductList(2349,R.drawable.img1,"Nam","XXL","ÁO THUN DRY-EX CỔ TRÒN NGẮN TAY",299000,false));
                list.add(new ProductList(2350,R.drawable.img1,"Nam","XXL","ÁO THUN DRY-EX CỔ TRÒN NGẮN TAY",499000,false));
                list.add(new ProductList(2351,R.drawable.img1,"Nam","XXL","ÁO THUN DRY-EX CỔ TRÒN NGẮN TAY",399000,false));
                for (ProductList product : list) {
                    String formattedPrice = product.getFormattedPrice();
                }
                break;
            }
            case 1: {
                list.add(new ProductList(2345,R.drawable.img1,"Nam","XXL","ÁO THUN DRY-EX CỔ TRÒN NGẮN TAY",399000,false));
                list.add(new ProductList(2346,R.drawable.img1,"Nam","XXL","ÁO THUN DRY-EX CỔ TRÒN NGẮN TAY",599000,false));
                list.add(new ProductList(2347,R.drawable.img1,"Nam","XXL","ÁO THUN DRY-EX CỔ TRÒN NGẮN TAY",499000,false));
                list.add(new ProductList(2348,R.drawable.img1,"Nam","XXL","ÁO THUN DRY-EX CỔ TRÒN NGẮN TAY",699000,false));
                list.add(new ProductList(2349,R.drawable.img1,"Nam","XXL","ÁO THUN DRY-EX CỔ TRÒN NGẮN TAY",299000,false));
                list.add(new ProductList(2350,R.drawable.img1,"Nam","XXL","ÁO THUN DRY-EX CỔ TRÒN NGẮN TAY",499000,false));
                list.add(new ProductList(2351,R.drawable.img1,"Nam","XXL","ÁO THUN DRY-EX CỔ TRÒN NGẮN TAY",399000,false));
                for (ProductList product : list) {
                    String formattedPrice = product.getFormattedPrice();
                }
            }
        }
        return list;
    }

    }


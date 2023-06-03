package com.ocr.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ocr.navigation.Adapter.GioHangAdapter;
import com.ocr.navigation.Adapter.ItemAdapter;
import com.ocr.navigation.OOP.Item;
import com.ocr.navigation.my_interface.ClickItemMenSearch;
import com.ocr.navigation.utils.Utils;

public class GioHangActivity extends AppCompatActivity {
    private ImageView imgBack;
    private RecyclerView recyclerView;
    private TextView tvSoLuong,tvTongCong,tvTongDon, tvThanhToan;
    private Button btnVoCher,btnThanhToan;
    private GioHangAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_gio_hang );
        initUI();
        onClickListener();

        //khoi tao adpter
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        GioHangAdapter adapter = new GioHangAdapter(getApplicationContext(), Utils.manggiohang);
        recyclerView.setAdapter(adapter);
        //phân ngang giưax các item
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration( recyclerView.getContext(), ((LinearLayoutManager) layoutManager).getOrientation() );
        recyclerView.addItemDecoration( dividerItemDecoration );

    }


    public void  initUI(){
        imgBack=findViewById( R.id.iv_back );
        recyclerView=findViewById( R.id.rcv_gio_hang );
        tvSoLuong=findViewById( R.id.tv_so_luong );
        tvTongCong=findViewById( R.id.tv_tong_cong );
        tvTongDon=findViewById( R.id.tv_tongdon );
        tvThanhToan=findViewById( R.id.tv_tong_than_toan );
        btnVoCher=findViewById( R.id.btn_vocher );
        btnThanhToan=findViewById( R.id.btn_thanh_toan );
    }
    private void onClickListener() {
        imgBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
    }

}
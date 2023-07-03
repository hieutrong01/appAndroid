package com.ocr.navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ocr.navigation.Adapter.DonHangAdapter;
import com.ocr.navigation.OOP.DonHangResponse;
import com.ocr.navigation.OOP.Thanhtoan;
import com.ocr.navigation.OOP.UserManager;
import com.ocr.navigation.my_interface.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LichSuDonHangActivity extends AppCompatActivity {

    private List<Thanhtoan> list;
    private RecyclerView recyclerView;
    private DonHangAdapter donHangAdapter;
    private ImageView imgBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lich_su_don_hang );
        initUI();
        onClickLister();
        int userId = Integer.parseInt(UserManager.getInstance().getCurrentUser().getUser_id());
        APIService.apiService.xemDonHang(userId).enqueue(new Callback<DonHangResponse>() {
            @Override
            public void onResponse(Call<DonHangResponse> call, Response<DonHangResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    DonHangResponse donHangResponse = response.body();
                    if (donHangResponse.isSuccess()) {
                        list = donHangResponse.getResult();
                        donHangAdapter=new DonHangAdapter( list,LichSuDonHangActivity.this );
                        recyclerView.setAdapter( donHangAdapter );
                    }
                }
            }
            @Override
            public void onFailure(Call<DonHangResponse> call, Throwable t) {
                // Xử lý lỗi khi gọi API thất bại
            }
        });
    }



    private void initUI() {
        recyclerView=findViewById( R.id.recycler_view );
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager( this );
        recyclerView.setLayoutManager( linearLayoutManager );
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration( recyclerView.getContext(),linearLayoutManager.getOrientation() );
        recyclerView.addItemDecoration( dividerItemDecoration );
        imgBack=findViewById( R.id.iv_back );

    }
    private void onClickLister() {
        imgBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
    }
}
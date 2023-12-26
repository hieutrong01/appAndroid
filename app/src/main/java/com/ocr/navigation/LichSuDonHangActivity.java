package com.ocr.navigation;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ocr.navigation.Adapter.DonHangAdapter;
import com.ocr.navigation.OOP.DonHangResponse;
import com.ocr.navigation.OOP.ResponePost;
import com.ocr.navigation.OOP.Thanhtoan;
import com.ocr.navigation.OOP.UserManager;
import com.ocr.navigation.my_interface.APIService;
import com.ocr.navigation.retrofit.ApiInterface;
import com.ocr.navigation.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LichSuDonHangActivity extends AppCompatActivity {

    private List<Thanhtoan> reversedList;
    private RecyclerView recyclerView;
    private DonHangAdapter donHangAdapter;
    private ImageView imgBack;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lich_su_don_hang );
        reversedList = new ArrayList<>();
        initUI();
        onClickLister();
        callAPIListHistroy();
    }



    private void initUI() {
        apiInterface= RetrofitClient.getInstance().create( ApiInterface.class );
        recyclerView=findViewById( R.id.recycler_view );
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager( this );
        recyclerView.setLayoutManager( linearLayoutManager );
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration( recyclerView.getContext(),linearLayoutManager.getOrientation() );
        recyclerView.addItemDecoration( dividerItemDecoration );
        imgBack=findViewById( R.id.iv_back );

    }
    private void showDialogView(Thanhtoan thanhtoan) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn xóa đơn hàng này?")
                .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        callAPIDelete(thanhtoan);
                        dialog.dismiss();
                        donHangAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Xử lý khi người dùng chọn "Hủy"
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }
    private void callAPIListHistroy(){
        int userId = Integer.parseInt( UserManager.getInstance().getCurrentUser().getUser_id());
        APIService.apiService.xemDonHang(userId).enqueue( new Callback<DonHangResponse>() {
            @Override
            public void onResponse(Call<DonHangResponse> call, Response<DonHangResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    DonHangResponse donHangResponse = response.body();
                    if (donHangResponse.isSuccess()) {
                        List<Thanhtoan> originalList = donHangResponse.getResult();
                        for (int i = originalList.size() - 1; i >= 0; i--) {
                            Thanhtoan item = originalList.get(i);
                            reversedList.add(item);
                        }
                        donHangAdapter=new DonHangAdapter( reversedList, LichSuDonHangActivity.this, new DonHangAdapter.onClick() {
                            @Override
                            public void onClick(Thanhtoan thanhtoan) {
                                showDialogView(thanhtoan);
                            }
                        } );
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

    private void callAPIDelete(Thanhtoan thanhtoan) {
        apiInterface.deleteBill( thanhtoan.getOrder_id()).enqueue( new Callback<ResponePost>() {
            @Override
            public void onResponse(Call<ResponePost> call, Response<ResponePost> response) {
                if (response.body() != null) {
                    if (response.body().isSuccess()) {
                        Toast.makeText( LichSuDonHangActivity.this, "Bạn đã xóa đơn hàng", Toast.LENGTH_SHORT ).show();
                        reversedList.remove(thanhtoan);
                        donHangAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponePost> call, Throwable t) {
                Toast.makeText( LichSuDonHangActivity.this, "Không thể xóa đơn hà", Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    private void onClickLister() {
        imgBack.setOnClickListener( v -> onBackPressed() );
    }

}
package com.ocr.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ocr.navigation.Adapter.ThanhToanAdapter;
import com.ocr.navigation.OOP.ResponePost;
import com.ocr.navigation.OOP.UserManager;
import com.ocr.navigation.retrofit.ApiInterface;
import com.ocr.navigation.retrofit.RetrofitClient;
import com.ocr.navigation.utils.Utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThanhToanActivity extends AppCompatActivity {
    private ImageView btnBack;
    private Button btnDatHang;
    private RecyclerView recyclerView;
    private ThanhToanAdapter adapter;
    CompositeDisposable compositeDisposable= new CompositeDisposable();
    private ApiInterface apiInterface;
    private TextView tvEdit, tvNameUser,tvSDT,tvAdress,tvTongCong,tvTong, tvTongTien,tvNgayThang;
    private RadioGroup radioGroup;
    private RadioButton button1, button2;
    private EditText edtNote;
    private String note = "";
    private String selectedOption="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_thanh_toan );
        initUI();
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ThanhToanAdapter(this,Utils.manggiohang );
        recyclerView.setAdapter(adapter);
        tinhTongTien();

        // Lấy ngày tháng hiện tại
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDate = dateFormat.format(calendar.getTime());

        // Cộng thêm 5 ngày
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        String futureDate = dateFormat.format(calendar.getTime());

        // Nối ngày tháng hiện tại với ngày tháng sau 5 ngày
        String result = currentDate + " - " + futureDate;

        // Hiển thị kết quả lên TextView
        tvNgayThang.setText(result);
        onClickListen();
    }


    private void initUI() {
        apiInterface= RetrofitClient.getInstance().create( ApiInterface.class );
        btnBack = findViewById(R.id.iv_back);
        recyclerView=findViewById( R.id.recycler );
        tvNameUser=findViewById( R.id.tv_name_user );
        tvSDT=findViewById( R.id.tv_sdt );
        tvAdress=findViewById( R.id.tv_dia_chi );
        tvTongCong=findViewById( R.id.tv_tong_cong );
        tvTong=findViewById( R.id.tv_tong );
        tvTongTien=findViewById( R.id.tv_tongdon );
        tvNgayThang=findViewById( R.id.tv_date_data );
        btnDatHang=findViewById( R.id.btn_dathang );
        radioGroup=findViewById( R.id.radio_group );
        button1=findViewById( R.id.radio_nhan_hang );
        button2=findViewById( R.id.radio_zalopay );
        edtNote=findViewById( R.id.edt_note );
        tvEdit=findViewById( R.id.tv_edit );
        if (button1.isChecked()){
            selectedOption = "1";
        } else {
            selectedOption = "2";
        }

    }
    private void tinhTongTien() {
        int sum=0;
        int total=0;
        for (int i=0; i<Utils.manggiohang.size();i++){
            sum+=Utils.manggiohang.get(i).getPrice()*Utils.manggiohang.get( i ).getSoluong();
        }
        total+=sum+50000;
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        tvTongCong.setText( decimalFormat.format( sum )+" VND" );
        tvTong.setText( decimalFormat.format( total )+" VND" );
        tvTongTien.setText( decimalFormat.format( total )+" VND" );

    }
    private void onClickListen() {
        tvNameUser.setText( UserManager.getInstance().getCurrentUser().getUsername() );
        tvSDT.setText( UserManager.getInstance().getCurrentUser().getPhoneNumber() );
        tvAdress.setText( UserManager.getInstance().getCurrentUser().getAddress() +", "+
                            UserManager.getInstance().getCurrentUser().getCity());




        radioGroup.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (button1.isChecked()){
                    selectedOption = "1";
                } else {
                    selectedOption = "2";
                }

            }
        } );
        edtNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Lấy giá trị từ EditText và gán vào biến userInput
                note = editable.toString();
            }
        });

        btnBack.setOnClickListener( v->{
            onBackPressed();
        } );
        btnDatHang.setOnClickListener( v -> {
            // Lấy ngày tháng hiện tại
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            String currentDate = dateFormat.format(calendar.getTime());


            int id = Integer.parseInt(UserManager.getInstance().getCurrentUser().getUser_id());
            String sdt = UserManager.getInstance().getCurrentUser().getPhoneNumber();
            String diachi = UserManager.getInstance().getCurrentUser().getAddress() + ", " + UserManager.getInstance().getCurrentUser().getCity();

            int sum = 0;
            int totalItem = 0;
            int tongtien=0;
            String status = "1";
            String email = UserManager.getInstance().getCurrentUser().getEmail();

            for (int i = 0; i < Utils.manggiohang.size(); i++) {
                sum =sum+ Utils.manggiohang.get(i).getPrice() * Utils.manggiohang.get(i).getSoluong();
           totalItem += Utils.manggiohang.get(i).getSoluong();
            }
            tongtien=sum+50000;

            String chitiet = new Gson().toJson( Utils.manggiohang );


            // Lưu ý: cần thay thế `apiInterface` bằng đối tượng đã khởi tạo từ Retrofit.
            apiInterface.createOder( id, totalItem, tongtien, status, selectedOption, note, email,sdt,diachi, currentDate,chitiet  )
                    .enqueue( new Callback<ResponePost>() {
                        @Override
                        public void onResponse(Call<ResponePost> call, Response<ResponePost> response) {
                            if (response.body() != null) {
                                if (response.body().isSuccess()){
                                    Toast.makeText( ThanhToanActivity.this, "Success", Toast.LENGTH_SHORT ).show();
                                    Utils.manggiohang.clear();
                                    Intent intent =new Intent(ThanhToanActivity.this, MainActivity.class);
                                    finishAffinity();
                                    startActivity( intent );
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponePost> call, Throwable t) {
                            Log.d( "TAGa", "onFailure: " + t.getMessage() );
                            Toast.makeText( ThanhToanActivity.this, "Fail" + t.getMessage(), Toast.LENGTH_SHORT ).show();
                        }
                    } );
        } );
        tvEdit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThanhToanActivity.this,UpDateMyProfile.class);
                startActivity( intent );
            }
        } );


    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        compositeDisposable.clear();
        super.onPause();
    }
}
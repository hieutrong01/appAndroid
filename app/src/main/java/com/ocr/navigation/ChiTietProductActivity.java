package com.ocr.navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ocr.navigation.OOP.Item;
import com.ocr.navigation.OOP.ProductList;

public class ChiTietProductActivity extends AppCompatActivity {
    private boolean isFavorite = false;
    private ImageButton btnFavorite;
    private TextView tvTenSP,tvGia,tvMaSP,tvMoTa,tvBangKichThuoc;
    private ImageView imgSP, imgBack, imgGioHang;
    private Spinner spnSoLuong,spnKichCo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_chi_tiet_product );
        isUnit();
        onClickListner();
        //lấy dữ liệu đổ vào activity từ framgentSearch
        Bundle bundle = getIntent().getExtras();
        if (bundle==null){
            return;
        }
        ProductList productList = (ProductList) bundle.get( "object_product" );
        imgSP.setImageResource(productList.getResourceImage()  );
        tvTenSP.setText( productList.getProductName() );
        tvGia.setText( productList.getFormattedPrice() );
        tvMaSP.setText( String.valueOf(productList.getIdProduct()) );
    }

    public void isUnit(){
        btnFavorite =findViewById( R.id.btn_them_favorite );
        tvTenSP=findViewById( R.id.tv_chitiet_product );
        tvGia=findViewById( R.id.tv_gia );
        tvMaSP=findViewById( R.id.tv_ma_product );
        tvMoTa=findViewById( R.id.tv_mo_ta );
        imgSP=findViewById( R.id.img_product );
        imgBack=findViewById( R.id.iv_back );
        imgGioHang=findViewById( R.id.gio_hang_a );
        spnKichCo=findViewById( R.id.spn_kich_co );
        String[] kicthuoc = new String[]{"S","M","L","XL","XXL"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>( this, me.relex.circleindicator.R.layout.support_simple_spinner_dropdown_item,kicthuoc );
        spnKichCo.setAdapter( arrayAdapter );
        spnKichCo.setBackgroundResource(R.drawable.spinner_bg_shadow);
        spnSoLuong=findViewById( R.id.spn_so_luong);
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>( this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item ,soluong);
        spnSoLuong.setAdapter( adapter );
        spnSoLuong.setBackgroundResource(R.drawable.spinner_bg_shadow);


    }

    private void onClickListner() {

        imgBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }

        } );
        imgGioHang.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietProductActivity.this,GioHangActivity.class );
                startActivity( intent );
            }
        } );

        btnFavorite.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = new ScaleAnimation(1f, 0.9f, 1f, 0.9f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim.setDuration(100);
                anim.setFillAfter(true);
                btnFavorite.startAnimation(anim);
                if (isFavorite) {
                    btnFavorite.setImageResource(R.drawable.ic_favorite);
                    //sử lý thêm vào yêu thích

                } else {
                    btnFavorite.setImageResource(R.drawable.ic_read_favorite);
                    //sử lý gỡ khỏi yêu thích

                }
                isFavorite = !isFavorite;
            }
        } );
    }
}
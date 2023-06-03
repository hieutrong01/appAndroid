package com.ocr.navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.nex3z.notificationbadge.NotificationBadge;
import com.ocr.navigation.OOP.GioHang;
import com.ocr.navigation.OOP.Item;
import com.ocr.navigation.OOP.ProductList;
import com.ocr.navigation.my_activity.KidsListProductActivity;
import com.ocr.navigation.my_activity.MenListItem;
import com.ocr.navigation.my_activity.WomenListProductActivity;
import com.ocr.navigation.utils.Utils;

public class ChiTietProductActivity extends AppCompatActivity {
    private boolean isFavorite = false;
    private ImageButton btnFavorite;
    private Button btnThem;
    private TextView tvTenSP,tvGia,tvMaSP,tvMoTa,tvBangKichThuoc;
    private ImageView imgSP, imgBack, imgGioHang;
    private Spinner spnSoLuong,spnKichCo;
    private NotificationBadge badge;
    ProductList productList;


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
        productList = (ProductList) bundle.get( "object_product" );
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
        btnThem=findViewById( R.id.btn_them_gio_hang );
        spnKichCo=findViewById( R.id.spn_kich_co );
        badge=findViewById( R.id.menu_sl );
        String[]  kichco = new String[]{"S","M","L","XL","XXL"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>( this,R.layout.simple_spinner_dropdown_item,kichco );
        spnKichCo.setAdapter( adapter1 );
        spnSoLuong=findViewById( R.id.spn_so_luong );
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>( this, R.layout.simple_spinner_dropdown_item ,soluong);
        spnSoLuong.setAdapter( adapter );
        if (Utils.manggiohang!=null){
            int totalItem=0;
            for (int i =0; i<Utils.manggiohang.size();i++){
                totalItem=totalItem + Utils.manggiohang.get(i).getSoluong();
            }
            badge.setText( String.valueOf( totalItem ) );
        }
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
        btnThem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themGioHang();
            }
        } );
    }

    private void themGioHang() {
        int soluong = Integer.parseInt(spnSoLuong.getSelectedItem().toString());
        boolean flag = false;

        for (int i = 0; i < Utils.manggiohang.size(); i++) {
            GioHang gioHang = Utils.manggiohang.get(i);



            if (gioHang.getIdProduct() == productList.getIdProduct() && gioHang.getSize().equals(productList.getSize())) {
                gioHang.setSoluong(soluong + gioHang.getSoluong());
                gioHang.setPrice(productList.getPrice());
                flag = true;
                break;
            }
        }

        if (!flag) {
            String kichco = spnKichCo.getSelectedItem().toString();

            //int gia = productList.getPrice() * soluong;

            GioHang gioHang = new GioHang();
            gioHang.setPrice(productList.getPrice());
            gioHang.setSoluong(soluong);
            gioHang.setIdProduct(productList.getIdProduct());
            gioHang.setResourceImage(productList.getResourceImage());
            gioHang.setSize(kichco);
            gioHang.setProductName( productList.getProductName() );
            gioHang.setGender( productList.getGender() );

            Utils.manggiohang.add(gioHang);
        }

        int totalItem=0;
        for (int i =0; i<Utils.manggiohang.size();i++){
            totalItem=totalItem + Utils.manggiohang.get(i).getSoluong();
        }
        badge.setText( String.valueOf( totalItem ) );
    }

}
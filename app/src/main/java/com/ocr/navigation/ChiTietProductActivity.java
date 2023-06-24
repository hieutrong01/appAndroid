package com.ocr.navigation;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.nex3z.notificationbadge.NotificationBadge;
import com.ocr.navigation.OOP.GioHang;
import com.ocr.navigation.OOP.Product;
import com.ocr.navigation.dataLocal.Database;
import com.ocr.navigation.utils.Utils;

import java.text.DecimalFormat;
import java.util.List;

public class ChiTietProductActivity extends AppCompatActivity {
    private boolean isFavorite=false ;
    private ImageButton btnFavorite;
    private Button btnThem;
    private TextView tvTenSP,tvGia,tvMaSP,tvMoTa,tvGiaSale,tvPhanTramSale,tvSaleOff;
    private ImageView imgSP, imgBack, imgGioHang;
    private Spinner spnSoLuong,spnKichCo;
    private NotificationBadge badge;
    private   Product productList;
    private boolean isAnimationRunning = false;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_chi_tiet_product );

        //lấy dữ liệu đổ vào activity từ framgentSearch
        Bundle bundle = getIntent().getExtras();
        if (bundle==null){
            return;
        }
        productList = (Product) bundle.getSerializable( "object_product" );
        isUnit();


        Glide.with( getApplicationContext() ).load( productList.getImage() ).into( imgSP );
        tvTenSP.setText( productList.getName() );
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        int giaSale = productList.getPrice() - (productList.getPrice() * productList.getSale() / 100);
        if (productList.getSale() == 0) {
            tvGiaSale.setVisibility(View.GONE);
            tvPhanTramSale.setVisibility( View.GONE );
            tvSaleOff.setVisibility( View.GONE );
            // Xóa gạch ngang khi không có giá sale
            tvGia.setPaintFlags(tvGia.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            tvGia.setTextColor( Color.BLACK);

        } else {
            tvGiaSale.setVisibility(View.VISIBLE);
            tvGiaSale.setText(decimalFormat.format(giaSale) + " VND");
            tvSaleOff.setVisibility( View.VISIBLE );
            tvPhanTramSale.setVisibility( View.VISIBLE );
            tvPhanTramSale.setText("-"+ decimalFormat.format( productList.getSale() )+"%" );
            // Gạch ngang tvgiaProduct
            tvGia.setPaintFlags(tvGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tvGia.setTextColor(Color.GRAY);

        }
        tvGia.setText( decimalFormat.format( productList.getPrice() )+"VND"  );
        tvMaSP.setText( String.valueOf(productList.getProduct_id()) );
        tvMoTa.setText( productList.getDescription() );

        // Kiểm tra xem sản phẩm có trong danh sách yêu thích hay không
        List<Product> favoriteProducts = Database.getInstance( this ).favouriteDAO().getListFavourite();

        if (favoriteProducts.contains(productList)) {
            isFavorite = true;
            btnFavorite.setImageResource(R.drawable.ic_read_favorite);
        } else {
            isFavorite = false;
            btnFavorite.setImageResource(R.drawable.ic_favorite);
        }
        onClickListner();



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
        tvGiaSale=findViewById( R.id.tv_cost_sale );
        tvSaleOff=findViewById( R.id.tv_sale_off );
        tvPhanTramSale=findViewById( R.id.tvphantramSale );
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>( this,R.layout.simple_spinner_dropdown_item,productList.getKichco() );
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

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAnimationRunning) {
                    return;
                }

                isAnimationRunning = true;
                animateFavoriteButton();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isFavorite) {
                            btnFavorite.setImageResource(R.drawable.ic_favorite);
                            // Xử lý gỡ khỏi yêu thích
                            deleteFavorite();


                        } else {
                            btnFavorite.setImageResource(R.drawable.ic_read_favorite);
                            // Xử lý thêm vào yêu thích
                            addFavorite();

                        }

                        isFavorite = !isFavorite;
                        isAnimationRunning = false;
                    }
                }, 100);
            }
        });
        btnThem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themGioHang();
            }
        } );
    }

    private void deleteFavorite() {
        Database.getInstance( this ).favouriteDAO().deleteFavourite( productList );
    }

    private void addFavorite() {
        Database.getInstance( this ).favouriteDAO().insertFavourite( productList );
    }

    private void animateFavoriteButton() {
        Animation anim = new ScaleAnimation(1f, 0.9f, 1f, 0.9f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(100);
        anim.setFillAfter(true);
        btnFavorite.startAnimation(anim);
    }


    private void themGioHang() {
        int soluong = Integer.parseInt(spnSoLuong.getSelectedItem().toString());
        String kichco = spnKichCo.getSelectedItem().toString();
        boolean flag = false;

        for (int i = 0; i < Utils.manggiohang.size(); i++) {
            GioHang gioHang = Utils.manggiohang.get(i);

            if (gioHang.getProduct_id() == productList.getProduct_id() && gioHang.getKichco().equals(kichco)) {
                Log.d("Taaa", "aaa" + kichco + " - " + gioHang.getKichco());
                gioHang.setSoluong(soluong + gioHang.getSoluong());
                gioHang.setPrice(productList.getPrice());
                flag = true;
                break;
            }
        }

        if (!flag) {
            GioHang gioHang = new GioHang();
            int giaSale = productList.getPrice() - (productList.getPrice() * productList.getSale() / 100);
            gioHang.setPrice(giaSale);
            gioHang.setSoluong(soluong);
            gioHang.setProduct_id(productList.getProduct_id());
            gioHang.setImage(productList.getImage());
            gioHang.setKichco(kichco);
            gioHang.setName(productList.getName());
            gioHang.setGender(productList.getGender());

            Utils.manggiohang.add(gioHang);
        }

        int totalItem = 0;
        for (GioHang gioHang : Utils.manggiohang) {
            totalItem += gioHang.getSoluong();
        }
        badge.setText(String.valueOf(totalItem));
    }

}
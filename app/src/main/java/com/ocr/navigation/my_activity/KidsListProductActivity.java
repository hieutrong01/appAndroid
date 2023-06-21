package com.ocr.navigation.my_activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nex3z.notificationbadge.NotificationBadge;
import com.ocr.navigation.Adapter.ListProductAdapter;
import com.ocr.navigation.ChiTietProductActivity;
import com.ocr.navigation.GioHangActivity;
import com.ocr.navigation.OOP.DataProduct;
import com.ocr.navigation.OOP.Item;
import com.ocr.navigation.OOP.Product;
import com.ocr.navigation.R;
import com.ocr.navigation.my_interface.APIService;
import com.ocr.navigation.my_interface.ClickItemProduc;
import com.ocr.navigation.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KidsListProductActivity extends AppCompatActivity {
    private TextView mtextView, tvSoLuong;
    private ImageView imgBack;
    private RecyclerView mRecyclerView;
    private ListProductAdapter adapter;
    private ImageView imgGioHang;
    private LinearLayout  layoutSapXep;
    private NotificationBadge badge;
    private Disposable disposable;
    private List<Product>  mList;

    private int selectedRadioButtonId = -1;

    private boolean isFavorite=false ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_kids_list_product );
        initUI();
        mList= new ArrayList<>();
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
            public void onItemProductClick(Product product) {
                onClickgotoChitiet(product);
            }

            @Override
            public void onClickFavoriteItem(int pos) {
            }
        } );
        mRecyclerView.setAdapter( adapter );

        int itemCount = adapter.getItemCount();
        tvSoLuong.setText( Integer.toString(itemCount) );


    }
 public void  initUI(){
        mtextView = findViewById( R.id.tv_id_item_men );
        mRecyclerView=findViewById( R.id.rcv_list_product );
        imgGioHang=findViewById( R.id.img_gio_hang );
        imgBack=findViewById( R.id.iv_back );
        tvSoLuong=findViewById( R.id.tv_so_luong );
        layoutSapXep=findViewById( R.id.linea_sap_xep );
        badge=findViewById( R.id.menu_sl );
        if (Utils.manggiohang!=null){
            badge.setText( String.valueOf( Utils.manggiohang.size() ) );
        }
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
                Intent intent= new Intent(KidsListProductActivity.this, GioHangActivity.class);
                startActivity( intent );
            }
        } );
        layoutSapXep.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogSapXep();
            }
        } );
    }

    private void showDialogSapXep() {
        Dialog dialog = new Dialog( this );
        dialog.setContentView( R.layout.dialog_sap_xep );
        RadioGroup radioGroup =dialog.findViewById( R.id.radio_group );
        RadioButton radioTieuBieu= dialog.findViewById( R.id.radio_tieu_bieu );
        RadioButton radioCaoThap =dialog.findViewById( R.id.radio_cao_thap );
        RadioButton radioThapCao= dialog.findViewById( R.id.radio_thap_cao );
        radioTieuBieu.setChecked( true );
        radioThapCao.setChecked( selectedRadioButtonId == R.id.radio_thap_cao );
        radioCaoThap.setChecked( selectedRadioButtonId == R.id.radio_cao_thap );

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedRadioButtonId = checkedId;
                if (selectedRadioButtonId == R.id.radio_thap_cao) {
                    adapter.giathapcao();
                    dialog.dismiss();

                } else if (selectedRadioButtonId == R.id.radio_cao_thap) {
                    adapter.giaCaoThap();
                    dialog.dismiss();
                }else {
                    adapter.tieuBieu();
                    dialog.dismiss();
                }
            }
        });
        // Thiết lập vị trí dialog ở góc dưới màn hình
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(window.getAttributes());
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT; // Chiều ngang dàn ra hết màn hình
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            layoutParams.gravity = Gravity.BOTTOM; // Đặt dialog ở góc dưới màn hình
            window.setAttributes(layoutParams);
        }
        dialog.show();
    }


    private void onClickgotoChitiet(Product product) {
        Intent intent = new Intent(KidsListProductActivity.this, ChiTietProductActivity.class );
        Bundle bundle= new Bundle();
        bundle.putSerializable("object_product", product);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    private List<Product> getListProduct(int pos) {
        List<Product> list = new ArrayList<>();
        switch (pos){
            case 0: {
                callApiAoWomen();

                break;
            }
            case 1: {

               break;
            }
        }
        return list;
    }

    private void callApiAoWomen() {
        APIService.apiServiceKids.getLisAoKids().enqueue( new Callback<DataProduct>() {
            @Override
            public void onResponse(Call<DataProduct> call, Response<DataProduct> response) {
                if (response.body() == null) return;
                DataProduct data = response.body();
                mList = data.getData();
                adapter.setData( mList, new ClickItemProduc() {
                    @Override
                    public void onItemProductClick(Product product) {
                        onClickgotoChitiet(product);
                    }

                    @Override
                    public void onClickFavoriteItem(int pos) {

                    }
                } );
                mRecyclerView.setAdapter( adapter );

                int itemCount = adapter.getItemCount();
                tvSoLuong.setText( Integer.toString(itemCount) );
            }

            @Override
            public void onFailure(Call<DataProduct> call, Throwable t) {
                Toast.makeText( KidsListProductActivity.this, "Call api fall", Toast.LENGTH_SHORT ).show();

            }
        } );
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable!=null){
            disposable.dispose();
        }
    }
}
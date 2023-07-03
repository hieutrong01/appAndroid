package com.ocr.navigation;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ocr.navigation.framgent.FavouriteFramgent;
import com.ocr.navigation.framgent.HomeFramgent;
import com.ocr.navigation.framgent.PesonFramgent;
import com.ocr.navigation.framgent.QRFramgent;
import com.ocr.navigation.framgent.SearchFramgent;
import com.ocr.navigation.framgent.ViewPageAdapter;
import com.ocr.navigation.my_interface.IntegerCallBack;
import com.ocr.navigation.utils.Utils;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements IntegerCallBack {
    private DrawerLayout mdrawerLayout;
    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;
    private MainViewModel mainViewModel;

    private ViewPageAdapter adapter;

    private SearchFramgent searchFramgent;



    private long backTime;
    private Toast mToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        init();

        HomeFramgent homeFramgent= new HomeFramgent(this::integerCallBack);
        PesonFramgent pesonFramgent= new PesonFramgent();
        searchFramgent = new SearchFramgent();
        QRFramgent qrFramgent= new QRFramgent();
        FavouriteFramgent favouriteFramgent= new FavouriteFramgent();

        //bắt sự kiện navigation
        adapter= new ViewPageAdapter( getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
         adapter.addFragment( homeFramgent );
          adapter.addFragment( searchFramgent );
          adapter.addFragment( favouriteFramgent );
          adapter.addFragment( qrFramgent );
          adapter.addFragment( pesonFramgent );
        mViewPager.setAdapter( adapter );
        mViewPager.setOffscreenPageLimit( 4 );

        //tắt chức năng vuốt băngf onTouch
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return true;
            }
        });



        //vuốt sang trái phải thanh navigation bên dưới
        mViewPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                  switch (position)
                  {
                      case 0:
                          mBottomNavigationView.getMenu().findItem( R.id.toolbar_trangchu ).setChecked( true );
                          break;
                      case 1:
                          mBottomNavigationView.getMenu().findItem( R.id.toolbar_search ).setChecked( true );
                          break;
                      case 2:
                          mBottomNavigationView.getMenu().findItem( R.id.toolbar_favorite ).setChecked( true );

                          break;
                      case 3:
                          mBottomNavigationView.getMenu().findItem( R.id.toolbar_QR).setChecked( true );
                          break;
                      case 4:
                          mBottomNavigationView.getMenu().findItem( R.id.toolbar_person).setChecked( true );
                          break;
                  }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        } );
        //ấn nút thay vi vuốt sang ngang
        mBottomNavigationView.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.toolbar_trangchu:
                        mViewPager.setCurrentItem( 0);
                        break;
                    case R.id.toolbar_search:
                        mViewPager.setCurrentItem( 1);
                        break;
                    case R.id.toolbar_favorite:
                        mViewPager.setCurrentItem( 2);
                        break;
                    case R.id.toolbar_QR:
                        mViewPager.setCurrentItem( 3);
                        break;
                    case R.id.toolbar_person:
                        mViewPager.setCurrentItem( 4);
                        break;
                }
                return false;
            }
        } );

        if (isConnect( this )){
            Toast.makeText( this, "kết nối", Toast.LENGTH_SHORT ).show();
        }else {
            Toast.makeText( this, "không có kết nối internet", Toast.LENGTH_SHORT ).show();
        }
    }
    private void init() {
        mdrawerLayout = findViewById(R.id.drawer_layout);
        mViewPager = findViewById(R.id.content_frame);
        mBottomNavigationView = findViewById(R.id.bottom_nav);
        if (Utils.manggiohang == null) {
            Utils.manggiohang = new ArrayList<>();
        }

    }
    @Override
    public void onBackPressed() {
        if (backTime+2000 > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else {
            mToast = Toast.makeText( MainActivity.this, "Chạm lại để thoát", Toast.LENGTH_SHORT );
            mToast.show();
        } backTime=System.currentTimeMillis();
    }
    private boolean isConnect (Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService( Context.CONNECTIVITY_SERVICE );
        NetworkInfo wifi = connectivityManager.getNetworkInfo( ConnectivityManager.TYPE_WIFI );
        NetworkInfo mobile = connectivityManager.getNetworkInfo( ConnectivityManager.TYPE_MOBILE );
        if (wifi!=null&& wifi.isConnected()||mobile!=null&&mobile.isConnected()){
            return true;
        }else
            return false;
    }
    @Override
    public void integerCallBack(int i) {
        mViewPager.setCurrentItem( 1 );
        mBottomNavigationView.setSelectedItemId( R.id.toolbar_search );
            if (searchFramgent != null){
                searchFramgent.setCurrentPage( i - 1);
            }
    }

}

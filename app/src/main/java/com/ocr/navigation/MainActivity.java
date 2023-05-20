package com.ocr.navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ocr.navigation.framgent.FavouriteFramgent;
import com.ocr.navigation.framgent.HomeFramgent;
import com.ocr.navigation.framgent.PesonFramgent;
import com.ocr.navigation.framgent.QRFramgent;
import com.ocr.navigation.framgent.SearchFramgent;
import com.ocr.navigation.framgent.ViewPageAdapter;


public class MainActivity extends AppCompatActivity  {
    private DrawerLayout mdrawerLayout;
    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private BottomNavigationView mBottomNavigationView;
    private long backTime;
    private Toast mToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        init();
        //bắt sự kiện navigation
        ViewPageAdapter adapter= new ViewPageAdapter( getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter( adapter );

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
                        HomeFramgent homeFramgent= (HomeFramgent) mViewPager.getAdapter().instantiateItem( mViewPager,0 );
                       // homeFramgent.reloadData();
                        break;
                    case R.id.toolbar_search:
                        mViewPager.setCurrentItem( 1);
                        SearchFramgent searchFramgent =(SearchFramgent) mViewPager.getAdapter().instantiateItem( mViewPager,1 );
                        //searchFramgent.reloadData();
                        break;
                    case R.id.toolbar_favorite:
                        mViewPager.setCurrentItem( 2);
                        FavouriteFramgent favouriteFramgent=(FavouriteFramgent) mViewPager.getAdapter().instantiateItem( mViewPager,2 ) ;
                       // favouriteFramgent.reloadData();
                        break;
                    case R.id.toolbar_QR:
                        mViewPager.setCurrentItem( 3);
                        QRFramgent qrFramgent=(QRFramgent) mViewPager.getAdapter().instantiateItem( mViewPager,3 );
                      //  qrFramgent.reloadData();
                        break;
                    case R.id.toolbar_person:
                        mViewPager.setCurrentItem( 4);
                      PesonFramgent pesonFramgen=(PesonFramgent)  mViewPager.getAdapter().instantiateItem( mViewPager,4 );
                        break;
                }
                return false;
            }
        } );
    }
    private void init() {
        mdrawerLayout = findViewById( R.id.drawer_layout );
        mViewPager=findViewById( R.id.content_frame );
        mViewPager = findViewById( R.id.content_frame);
        mBottomNavigationView=findViewById( R.id.bottom_nav);
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


}

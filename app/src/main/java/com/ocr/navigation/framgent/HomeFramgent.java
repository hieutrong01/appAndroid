package com.ocr.navigation.framgent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.Toast;
import com.google.android.material.tabs.TabLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.ocr.navigation.GioHangActivity;
import com.ocr.navigation.R;
import com.ocr.navigation.framgentHome.HomeViewPageAdapter;

public class HomeFramgent extends Fragment {
    private TabLayout mTableLayout;
    private ViewPager mViewPager;
    private View mView;
    private ImageView imgGioHang;
    private SearchView searchView;
    @Override
    public void onResume() {
        super.onResume();
        Log.e( "Check","reload framgent home" );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView= inflater.inflate( R.layout.framgent_home,container,false );
       mTableLayout = mView.findViewById( R.id.tab_top);
       mViewPager=mView.findViewById( R.id.view_home );
       HomeViewPageAdapter adapter=new HomeViewPageAdapter( getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT );
       mViewPager.setAdapter( adapter );
       mTableLayout.setupWithViewPager( mViewPager );
       initUI();
        oncClickListner();
       searchView.setOnClickListener( v -> Toast.makeText( requireContext(), "abc", Toast.LENGTH_SHORT ).show() );
        return mView;
    }
    public void initUI(){
         searchView = mView.findViewById(R.id.search_view );
         imgGioHang=mView.findViewById( R.id.img_gio_hang );
    }
    public void oncClickListner(){
        imgGioHang.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), GioHangActivity.class);
                startActivity( intent );
            }
        } );

    }




}

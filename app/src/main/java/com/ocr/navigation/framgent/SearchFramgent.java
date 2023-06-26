package com.ocr.navigation.framgent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.ocr.navigation.R;
import com.ocr.navigation.SearchActivity;
import com.ocr.navigation.framgentSearch.KidsSearchFragment;
import com.ocr.navigation.framgentSearch.MenSearchFragment;
import com.ocr.navigation.framgentSearch.ViewPageAdapterSearch;
import com.ocr.navigation.framgentSearch.WomenSearchFramgent;

public class SearchFramgent extends Fragment {
    private TabLayout mTableLayout;
    private ViewPager mViewPager;
    private View mView;
    private LinearLayout linearLayoutSerch;
    @Override
    public void onResume() {
        super.onResume();
        Log.e( "Check","reload framgent search" );
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       mView= inflater.inflate( R.layout.framgent_search,container,false );
        initUI();

        WomenSearchFramgent womenFragment = new WomenSearchFramgent();
        MenSearchFragment menFragment = new MenSearchFragment();
        KidsSearchFragment kidsSearchFragment= new KidsSearchFragment();
        ViewPageAdapterSearch adapterSearch= new ViewPageAdapterSearch(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT  );

        adapterSearch.addFragment( menFragment );
        adapterSearch.addFragment( womenFragment );
        adapterSearch.addFragment( kidsSearchFragment );
        mViewPager.setAdapter( adapterSearch );
        mTableLayout.setupWithViewPager( mViewPager );
        oncClickListner();

        return mView;
    }

    public void setCurrentPage(int i){
        mViewPager.setCurrentItem( i );
        mTableLayout.setScrollPosition( i, 0f, true );
    }
    private void initUI() {
        mTableLayout = mView.findViewById( R.id.tab_search);
        mViewPager=mView.findViewById( R.id.view_search );
        linearLayoutSerch=mView.findViewById( R.id.linea_search );

    }
    public void oncClickListner(){
        linearLayoutSerch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), SearchActivity.class );
                startActivity( intent );
            }
        } );

    }




}

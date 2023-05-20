package com.ocr.navigation.framgentHome;

import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ocr.navigation.OOP.Image;
import com.ocr.navigation.OOP.ImageAdapter;
import com.ocr.navigation.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;


public class TopFragment extends Fragment {
    private ViewPager2 mViewPager2;
    private CircleIndicator3 mIndicator3;
    private List<Image> imageList;
    private Handler mHandler= new Handler( Looper.getMainLooper());
    private Runnable mRunnable= new Runnable() {
        @Override
        public void run() {
            int troi =  mViewPager2.getCurrentItem();
             if (troi ==imageList.size()-1){
                 mViewPager2.setCurrentItem( 0 );
             }else {
                 mViewPager2.setCurrentItem( troi+1 );
             }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_top, container, false );

        //setting img view_page
        mViewPager2= view.findViewById( R.id.view_page_top );
        mIndicator3= view.findViewById( R.id.circleIndicator3 );
        mViewPager2.setOffscreenPageLimit( 3 );
        mViewPager2.setClipToPadding( false );
        mViewPager2.setClipChildren( false );
        imageList=getListImage();
        ImageAdapter imageAdapter= new ImageAdapter( imageList );
        mViewPager2.setAdapter( imageAdapter );
        mIndicator3.setViewPager( mViewPager2 );
        mViewPager2.registerOnPageChangeCallback( new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected( position );
                mHandler.removeCallbacks( mRunnable );
                mHandler.postDelayed( mRunnable,3000 );
            }
        } );
        return view;
    }

    private List<Image> getListImage() {
        List<Image> list =new ArrayList<>();
        list.add( new Image( R.drawable.img1) );
        list.add( new Image( R.drawable.img1) );
        list.add( new Image( R.drawable.img1) );
        return list;
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks( mRunnable );
    }

    @Override
    public void onResume() {
        super.onResume();
        mHandler.postDelayed( mRunnable,3000 );
    }
}
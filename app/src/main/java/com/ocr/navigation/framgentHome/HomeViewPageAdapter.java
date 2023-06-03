package com.ocr.navigation.framgentHome;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;



public class HomeViewPageAdapter extends FragmentStatePagerAdapter {


    public HomeViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super( fm, behavior );
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TopFragment();
            case 1:
                return new WomenFragment();
            case 2:
                return new MenFragment();
            case 3:
                return new KidsFragment();
            default:
                return new TopFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "TOP";
            case 1:
                return "WOMEN";
            case 2:
                return "MEN";
            case 3:
                return "KIDS";
            default:return "TOP";
        }

    }
}


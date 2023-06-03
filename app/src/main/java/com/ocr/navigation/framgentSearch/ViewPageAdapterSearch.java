package com.ocr.navigation.framgentSearch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class ViewPageAdapterSearch extends FragmentStatePagerAdapter {


    public ViewPageAdapterSearch(@NonNull FragmentManager fm, int behavior) {
        super( fm, behavior );
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MenSearchFragment();
            case 1:
                return new WomenSearchFramgent();
            case 2:
                return new KidsSearchFragment();
            default:
                return new MenSearchFragment();
        }
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "MEN";
            case 1:
                return "WOMEN";
            case 2:
                return "KIDS";

            default:return "MEN";
        }

    }
}


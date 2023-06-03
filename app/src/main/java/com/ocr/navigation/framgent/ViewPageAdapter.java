package com.ocr.navigation.framgent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class ViewPageAdapter extends FragmentStatePagerAdapter {

    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super( fm, behavior );
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: return new HomeFramgent();
            case 1: return new SearchFramgent();
            case 2: return new FavouriteFramgent();
            case 3: return new QRFramgent();
            case 4: return new PesonFramgent();
            default:return new HomeFramgent();
        }
    }
    @Override
    public int getCount() {
        return 5;
    }
}

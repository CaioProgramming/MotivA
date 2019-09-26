package com.ilustris.motiv.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ilustris.motiv.fragments.HomeFragment;
import com.ilustris.motiv.fragments.PicsFragment;

public class HomePager extends FragmentPagerAdapter {
    public HomePager(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return  new HomeFragment();
        }else{
            return new PicsFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}

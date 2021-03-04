package com.mac_available.available;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SearchTabAdapter extends FragmentPagerAdapter {

    Fragment[] fragments = new Fragment[3];
    String[] titles = new String[]{"For Sale", "Neighborhood Info", "User"};

    public SearchTabAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

        fragments[0] = new SaleFragment();
        fragments[1] = new InfoFragment();
        fragments[2] = new UserFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    //Tab버튼에 보여질 글씨 리턴 해주는 메소드
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

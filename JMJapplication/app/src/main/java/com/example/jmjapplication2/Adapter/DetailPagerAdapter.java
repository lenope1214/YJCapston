package com.example.jmjapplication2.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.jmjapplication2.Fragment.InfoFragment;
import com.example.jmjapplication2.Fragment.MenuFragment;
import com.example.jmjapplication2.Fragment.ReviewFragment;

public class DetailPagerAdapter extends FragmentPagerAdapter {
    private static int PAGE_NUMBER = 3;

    public DetailPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return MenuFragment.newInstance();
            case 1:
                return InfoFragment.newInstance();
            case 2:
                return ReviewFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "메뉴";
            case 1:
                return "상세정보";
            case 2:
                return "리뷰";
            default:
                return null;
        }
    }

}

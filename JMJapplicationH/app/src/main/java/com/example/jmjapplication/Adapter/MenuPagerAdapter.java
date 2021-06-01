package com.example.jmjapplication.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.jmjapplication.Fragment.*;

public class MenuPagerAdapter extends FragmentPagerAdapter {
    private static int PAGE_NUMBER = 9;

    public MenuPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return PageOneFragment.newInstance();
            case 1:
                return PageTwoFragment.newInstance();
            case 2:
                return PageThreeFragment.newInstance();
            case 3:
                return PageFourFragment.newInstance();
            case 4:
                return PageFiveFragment.newInstance();
            case 5:
                return PageSixthFragment.newInstance();
            case 6:
                return PageSevenFragment.newInstance();
            case 7:
                return PageEightFragment.newInstance();
            case 8:
                return PageNineFragment.newInstance();
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
                return "한식";
            case 1:
                return "일식";
            case 2:
                return "중식";
            case 3:
                return "고기";
            case 4:
                return "분식";
            case 5:
                return "술집";
            case 6:
                return "패스트푸드";
            case 7:
                return "찜.탕";
            case 8:
                return "디저트.카페";
            default:
                return null;
        }
    }
}
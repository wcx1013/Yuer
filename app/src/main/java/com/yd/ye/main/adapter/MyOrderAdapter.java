package com.yd.ye.main.adapter;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MyOrderAdapter  extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;
    private List<String> mTitles;

    public MyOrderAdapter(FragmentManager fm, List<Fragment> list, List<String> titles) {
        super(fm);
        mFragments = list;
        mTitles = titles;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mFragments.get(position);
//        Bundle bundle = new Bundle();
//        bundle.putInt("pos", position);
//        fragment.setArguments(bundle);
        return fragment;
    }
}

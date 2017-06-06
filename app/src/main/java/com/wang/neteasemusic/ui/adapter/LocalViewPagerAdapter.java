package com.wang.neteasemusic.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2017/6/6.
 */

public class LocalViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments=new ArrayList<>();

    private List<String> list_tiles=new ArrayList<>();



    public LocalViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> list_tiles) {
        super(fm);
        this.fragments = fragments;
        this.list_tiles = list_tiles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return list_tiles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list_tiles.get(position);
    }
}

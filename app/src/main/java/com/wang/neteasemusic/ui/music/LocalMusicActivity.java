package com.wang.neteasemusic.ui.music;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.wang.neteasemusic.R;
import com.wang.neteasemusic.ui.adapter.LocalViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class LocalMusicActivity extends AppCompatActivity {
    private TabLayout mTableLayout;
    private ViewPager mViewPager;
    private LocalViewPagerAdapter adapter;
    private List<Fragment> fragments;
    private List<String> list_tiles;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);
        initView();
        setData();
        setTableLayout();


    }

    private void setData() {
        LocalMusicFragment musicFragment1 = new LocalMusicFragment();
        LocalMusicFragment musicFragment2 = new LocalMusicFragment();
        LocalMusicFragment musicFragment3 = new LocalMusicFragment();
        fragments = new ArrayList<>();
        fragments.add(musicFragment1);
        fragments.add(musicFragment2);
        fragments.add(musicFragment3);
        list_tiles = new ArrayList<>();
        list_tiles.add("单曲");
        list_tiles.add("歌手");
        list_tiles.add("专辑");
    }


    private void initView() {
        mTableLayout = (TabLayout) findViewById(R.id.local_table);
        mViewPager = (ViewPager) findViewById(R.id.vp_local);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("本地音乐");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    private void setTableLayout() {
        adapter = new LocalViewPagerAdapter(getSupportFragmentManager(), fragments, list_tiles);
        mViewPager.setAdapter(adapter);
        mTableLayout.setupWithViewPager(mViewPager);

    }

}

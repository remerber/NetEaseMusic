package com.wang.neteasemusic.ui.music;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.bilibili.magicasakura.widgets.TintToolbar;
import com.wang.neteasemusic.R;
import com.wang.neteasemusic.ui.BaseActivity;
import com.wang.neteasemusic.ui.adapter.MenuAdapter;
import com.wang.neteasemusic.ui.album.AlbumFragment;
import com.wang.neteasemusic.ui.widget.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity {
    private TintToolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBar actionBar;
    private CustomViewPager mViewPager;
    private List<Fragment> fragments = new ArrayList<>();
    private ImageView barNet, barMusic;
    private List<ImageView> tabs = new ArrayList<>();
    private ListView drawerListview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setToolbar();
        setUpDrawer();
        setCustomViewPager();

    }


    private void initView() {
        mToolbar = (TintToolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mViewPager = (CustomViewPager) findViewById(R.id.main_viewpager);
        barNet = (ImageView) findViewById(R.id.bar_net);
        barMusic = (ImageView) findViewById(R.id.bar_music);
        drawerListview = (ListView) findViewById(R.id.id_lv_left_menu);

    }


    private void setToolbar() {
        setSupportActionBar(mToolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setTitle("");


    }


    private void setUpDrawer() {
        LayoutInflater inflater = LayoutInflater.from(this);
        drawerListview.addHeaderView(inflater.inflate(R.layout.nav_header_main, drawerListview, false));
        drawerListview.setAdapter(new MenuAdapter(this));

    }

    private void setCustomViewPager() {
        AlbumFragment albumFragment = new AlbumFragment();
        AlbumFragment musicFragment = new AlbumFragment();
        fragments.add(albumFragment);
        fragments.add(musicFragment);

        tabs.add(barNet);
        tabs.add(barMusic);

        mViewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new MyViewPagerListener());
        mViewPager.setCurrentItem(1);
        barMusic.setSelected(true);


        barNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
            }
        });

        barMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(1);
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    public class MyViewPagerAdapter extends FragmentPagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    public class MyViewPagerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            changeTab(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private void changeTab(int position) {

        for (int i = 0; i < tabs.size(); i++) {
            if (position == i) {
                tabs.get(i).setSelected(true);
            } else {
                tabs.get(i).setSelected(false);
            }
        }
    }


}

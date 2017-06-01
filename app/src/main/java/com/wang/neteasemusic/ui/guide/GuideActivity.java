package com.wang.neteasemusic.ui.guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wang.neteasemusic.R;
import com.wang.neteasemusic.ui.BaseActivity;

import java.util.ArrayList;

/**
 * Created by HP on 2017/6/1.  引导页
 */

public class GuideActivity extends BaseActivity {

    private ViewPager mViewPager;
    private Button btn_start;
    private ImageView image_doto1, image_doto2, image_doto3;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initData();
        initView();
    }

    private void initData() {
        fragments = new ArrayList<>();

        GuideFragment guideFragment1 = new GuideFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("index", 1);
        guideFragment1.setArguments(bundle1);
        fragments.add(guideFragment1);

        GuideFragment guideFragment2 = new GuideFragment();
        Bundle bundle2 = new Bundle();
        bundle1.putInt("index", 2);
        guideFragment2.setArguments(bundle2);
        fragments.add(guideFragment2);

        GuideFragment guideFragment3 = new GuideFragment();
        Bundle bundle3 = new Bundle();
        bundle1.putInt("index", 3);
        guideFragment3.setArguments(bundle3);
        fragments.add(guideFragment3);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp);
        btn_start = (Button) findViewById(R.id.bt_start);
        image_doto1 = (ImageView) findViewById(R.id.iv1);
        image_doto2 = (ImageView) findViewById(R.id.iv2);
        image_doto3 = (ImageView) findViewById(R.id.iv3);

        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new MyPagerListener());

    }


    public class MyPageAdapter extends FragmentPagerAdapter {


        public MyPageAdapter(FragmentManager fm) {
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

    public class MyPagerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            btn_start.setVisibility(View.GONE);
            //切换图标
            image_doto1.setImageResource(R.mipmap.dot_normal);
            image_doto2.setImageResource(R.mipmap.dot_normal);
            image_doto3.setImageResource(R.mipmap.dot_normal);
            if (position == 0) {
                image_doto1.setImageResource(R.mipmap.dot_focus);
            } else if (position == 1) {
                image_doto2.setImageResource(R.mipmap.dot_focus);
            } else {
                image_doto3.setImageResource(R.mipmap.dot_focus);
                btn_start.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}

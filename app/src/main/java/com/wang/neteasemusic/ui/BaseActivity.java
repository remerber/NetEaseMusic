package com.wang.neteasemusic.ui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wang.neteasemusic.service.MusicPlayerManager;
import com.wang.neteasemusic.service.MusicServiceHelper;

import static com.wang.neteasemusic.MusicApplication.context;

/**
 * Created by HP on 2017/6/1. Activity的基类
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //默认屏幕不能横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        MusicServiceHelper.get(getApplication()).initService();
        //服务开启
        MusicPlayerManager.startServiceIfNecessary(getApplication());
    }

    /**
     * Snackbar的显示
     *
     * @param view
     * @param text
     */
    public void showSnackBar(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }

    public void showSnackBar(View view, int resID) {
        Snackbar.make(view, resID, Snackbar.LENGTH_SHORT).show();
    }

    public void startActivity(Class activity) {
        Intent intent = new Intent();
        intent.setClass(this, activity);
        startActivity(intent);

    }
}

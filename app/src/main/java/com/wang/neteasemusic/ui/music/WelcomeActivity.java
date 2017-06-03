package com.wang.neteasemusic.ui.music;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.wang.neteasemusic.R;
import com.wang.neteasemusic.ui.BaseActivity;
import com.wang.neteasemusic.ui.guide.GuideActivity;

/**
 * Created by HP on 2017/6/1.
 */

public class WelcomeActivity extends BaseActivity {
    private ImageView im_logo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  //全屏

        setContentView(R.layout.activity_welcome);
        initView();
        startAnimation();
    }

    private void initView() {
        im_logo = (ImageView) findViewById(R.id.logo);
    }


    /**
     * 启动页动画
     */
    private void startAnimation() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(im_logo, "scaleX", 0f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(im_logo, "scaleY", 0f, 1f);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(im_logo, "alpha", 0f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(alphaAnimator).with(scaleX).with(scaleY);
        animatorSet.setDuration(1500);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.start();


        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //1.当App有用户后，可以在此判断是否登录
                //2.需要跳转界面

                startActivity(GuideActivity.class);
                finish();
            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            return true;
        }
        return false;
    }
}

package com.wang.neteasemusic.ui.widget;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by HP on 2017/6/1. 自定义播放控件
 */

public class CustomVideoView extends VideoView {
    public CustomVideoView(Context context) {
        super(context);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    /**
     * 播放视频
     */
    public void playVideo(Uri uri) {

        if (uri == null) {
            throw new IllegalArgumentException("uri can not be null");
        }
        /** 设置播放路径 **/
        setVideoURI(uri);
        /** 开始播放 **/
        start();

        setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);//设置循环播放
            }
        });
        setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return true;
            }
        });


    }
}

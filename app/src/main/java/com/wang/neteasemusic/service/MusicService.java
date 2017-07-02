package com.wang.neteasemusic.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;

public class MusicService extends Service {
    public static MediaPlayer mediaPlayer=new MediaPlayer();
    public   Binder mBinder = new MyBinder();

    public class MyBinder extends Binder {
        public MusicService getMusicService() {
            return MusicService.this;
        }
    }


    public MusicService() {

    }

    /**
     * 停止服务
     */
    public void stopService() {
        stopSelf();
    }

    /**
     * 播放音乐
     *
     * @param path
     */
    public void play(String path) {
        mediaPlayer.reset();//重置
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();//播放
            }
        });
    }

    /**
     * 暂停
     */
    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    /**
     * 停止音乐
     */
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    /**
     * 继续播放音乐
     */
    public void continueMusic() {
        mediaPlayer.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}

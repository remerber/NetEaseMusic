package com.wang.neteasemusic.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;

//好多地方都需要这个服务，那我每次都不能总写那么多吧，写一个Service的帮助类 初始化
public class MusicService extends Service {
    public static MediaPlayer mediaPlayer = new MediaPlayer();
    public Binder mBinder = new MyBinder();
    private MediaSessionCompat mediaSession;
    private MusicPlayerManager playerManager;
    private PlaybackStateCompat mState;


    public class MyBinder extends Binder {
        public MusicService getMusicService() {
            return MusicService.this;
        }
    }


    public MusicService() {

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //服务的入口
        MediaButtonReceiver.handleIntent(mediaSession, intent);
        return super.onStartCommand(intent, flags, startId);
    }


    public void setUp() {
        playerManager = MusicPlayerManager.from(this);
        setUpMediaSession();
    }

    /***
     * 使用MediaButtonReceiVer来兼容api21之前的版本
     */
    private void setUpMediaSession() {
        ComponentName componentName = new ComponentName(getPackageName(), MediaButtonReceiver.class.getName());
        mediaSession = new MediaSessionCompat(this, "fd", componentName, null);
        //设置处理media button的flag
        mediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
        //设置回调
        mediaSession.setCallback(new MediaSessionCallback());
        setState(PlaybackStateCompat.STATE_NONE);


    }

    private void setState(int state) {

        mState = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE |
                                PlaybackStateCompat.ACTION_PAUSE |
                                PlaybackStateCompat.ACTION_SKIP_TO_NEXT |
                                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                                PlaybackStateCompat.ACTION_STOP |
                                PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID |
                                PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH |
                                PlaybackStateCompat.ACTION_SEEK_TO |
                                PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM
                )
                .setState(state, PlaybackStateCompat.PLAYBACK_POSITION_UNKNOWN,
                        1.0f, SystemClock.elapsedRealtime())
                .build();
        mediaSession.setPlaybackState(mState);
        mediaSession.setActive(state != PlaybackStateCompat.STATE_NONE && state != PlaybackStateCompat.STATE_STOPPED);

    }

    /***
     * 获取播放状态
     * @return
     */
    public int getState() {
        return mState.getState();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /**
     * 停止服务
     */
    public void stopService() {
        stopSelf();
    }

    public class MediaSessionCallback extends MediaSessionCompat.Callback {

        @Override
        public void onPlay() {
            super.onPlay();
        }

        @Override
        public void onPause() {
            super.onPause();
        }

        @Override
        public void onSkipToNext() {
            super.onSkipToNext();
        }

        @Override
        public void onSkipToPrevious() {
            super.onSkipToPrevious();
        }

        @Override
        public void onStop() {
            super.onStop();
        }

        @Override
        public void onSeekTo(long pos) {
            super.onSeekTo(pos);
        }


    }

}

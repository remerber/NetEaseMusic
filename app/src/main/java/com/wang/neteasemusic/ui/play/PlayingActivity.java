package com.wang.neteasemusic.ui.play;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.wang.neteasemusic.R;
import com.wang.neteasemusic.common.utils.LocalMusicLibrary;
import com.wang.neteasemusic.data.Song;
import com.wang.neteasemusic.service.MusicService;
import com.wang.neteasemusic.ui.BaseActivity;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by HP on 2017/7/2.
 */

public class PlayingActivity extends BaseActivity implements View.OnClickListener {
    private TextView time_current, time_duration;
    private SeekBar seekBar;
    private ImageView im_back, im_play, im_next;
    private MusicService musicService;
    private ServiceConnection connection;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private List<Song> allSongs;
    int index;


    private class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MyBinder myBinder = (MusicService.MyBinder) service;
            musicService = myBinder.getMusicService();
            mediaPlayer = musicService.mediaPlayer;
            if (mediaPlayer != null) {
                //播放音乐
                playMusic(0);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        playMusic(1);
                    }
                });
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
            time_current.setText(formatChange(mediaPlayer.getCurrentPosition()));
            mHandler.postDelayed(mRunnable, 100);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        //开启服务
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
        //绑定服务
        connection = new MyServiceConnection();
        bindService(intent, connection, BIND_AUTO_CREATE);
        initView();
        init();
        mHandler.removeCallbacks(mRunnable);
        mHandler.post(mRunnable);
    }


    private void initView() {

        time_current = (TextView) findViewById(R.id.music_duration_played);
        time_duration = (TextView) findViewById(R.id.music_duration);

        seekBar = (SeekBar) findViewById(R.id.play_seek);

        im_back = (ImageView) findViewById(R.id.play_back);
        im_back.setOnClickListener(this);
        im_play = (ImageView) findViewById(R.id.play_start);
        im_play.setOnClickListener(this);
        im_next = (ImageView) findViewById(R.id.play_next);
        im_next.setOnClickListener(this);

    }

    private void init() {
        allSongs = LocalMusicLibrary.getAllSongs(this);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               if(fromUser){
                   if(mediaPlayer!=null){
                       mediaPlayer.seekTo(progress);
                   }
               }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    /**
     * 根据歌曲下标播放音乐
     *
     * @param index
     */
    private void playMusic(int index) {
        if (allSongs.size() > 0) {
            musicService.play(allSongs.get(index).getPath());
            time_duration.setText(formatChange(allSongs.get(index).getDuration()));
        }
        seekBar.setMax(mediaPlayer.getDuration());

    }

    /***
     * 对歌曲长度的格式进行转换
     *
     * @param millSeconds
     */
    public String formatChange(int millSeconds) {
        int seconds = millSeconds / 1000;
        int second = seconds % 60;
        int minute = (seconds - second) / 60;
        DecimalFormat decimalFormat = new DecimalFormat("00");
        return decimalFormat.format(minute) + ":" + decimalFormat.format(second);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_back:
                playMusicByStatus(2);
                break;
            case R.id.play_start:
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        musicService.pause();
                    } else {
                        musicService.continueMusic();
                    }
                }
                break;
            case R.id.play_next:
                playMusicByStatus(1);
                break;
        }
    }

    private void playMusicByStatus(int status) {
        switch (status) {
            case 0:
                break;
            case 1:
                //播放下一首
                index++;
                if (index == allSongs.size()) {
                    index = 0;
                }

                break;
            case 2:
                //播放上一首
                index--;
                if (index == -1) {
                    index = allSongs.size() - 1;
                }
                break;
        }
        playMusic(index);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}

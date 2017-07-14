package com.wang.neteasemusic.ui.play;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.wang.neteasemusic.R;
import com.wang.neteasemusic.common.utils.ImageUtils;
import com.wang.neteasemusic.data.Song;
import com.wang.neteasemusic.service.MusicPlayerManager;
import com.wang.neteasemusic.service.OnSongchangeListener;
import com.wang.neteasemusic.ui.BaseActivity;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by HP on 2017/7/2.
 */

public class PlayingActivity extends BaseActivity implements OnSongchangeListener {


    /**
     * 播放seekbar
     **/
    @BindView(R.id.play_seek)
    SeekBar mPlaySeek;
    /**
     * 歌曲的长度
     **/
    @BindView(R.id.music_duration)
    TextView mMusicDuration;

    /**
     * 上一首
     **/
    @BindView(R.id.playing_pre)
    ImageView mPlayingPre;

    /**
     * 播放按钮
     **/

    @BindView(R.id.playing_play)
    ImageView mPlayingPlay;

    /**
     * 播放下一首
     **/
    @BindView(R.id.playing_next)
    ImageView mPlayingNext;

    @BindView(R.id.coverImage)
    ImageView mCoverImage;
    @BindView(R.id.music_duration_played)
    TextView mMusicDurationPlayed;
    private Toolbar toolbar;
    private Song song;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        ButterKnife.bind(this);
        MusicPlayerManager.get().registerListener(this);
        initData();
        updateProgress();
        updateData();


    }


    private void initData() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        song = MusicPlayerManager.get().getPlayingSong();
        if (song == null) {
            finish();
        }

        mPlaySeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    MusicPlayerManager.get().seekTo(progress);
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

    /***
     * 更新进度条,进度显示,歌曲长度
     */
    private void updateProgress() {
        Observable.interval(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mPlaySeek.setMax(MusicPlayerManager.get().getCurrentMaxDuration());
                        mPlaySeek.setProgress(MusicPlayerManager.get().getCurrentPosition());
                        mMusicDuration.setText(formatChange(
                                MusicPlayerManager
                                        .get()
                                        .getCurrentMaxDuration()));
                        mMusicDurationPlayed.setText(formatChange(
                                MusicPlayerManager
                                        .get()
                                        .getCurrentPosition()));

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }


    /***
     * 更新数据:封面,标题,图标
     */
    private void updateData() {
        //歌曲的封面
        String coverUrl = song.getCoverUrl();
        ImageUtils.GlideWith(this, coverUrl, R.drawable.ah1, mCoverImage);

        toolbar.setTitle(song.getTitle());

        if (MusicPlayerManager.get().getPlayingSong() != null) {
            mPlayingPlay.setImageResource(R.drawable.play_rdi_btn_pause);
        }
    }

    @Override
    public void onSongChanged(Song song) {
        this.song = song;
        updateData();
    }

    @Override
    public void onPlayBackStateChanged(PlaybackStateCompat state) {

    }

    @OnClick({R.id.playing_pre, R.id.playing_play, R.id.playing_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.playing_pre:
                MusicPlayerManager.get().playPrev();
                break;
            case R.id.playing_play:
                if (MusicPlayerManager.get().getState() == PlaybackStateCompat.STATE_PLAYING) {
                    MusicPlayerManager.get().pause();
                    mPlayingPlay.setImageResource(R.drawable.play_rdi_btn_play);
                } else if (MusicPlayerManager.get().getState() == PlaybackStateCompat.STATE_PAUSED) {
                    MusicPlayerManager.get().play();
                    mPlayingPlay.setImageResource(R.drawable.play_rdi_btn_pause);
                }


                break;
            case R.id.playing_next:
                MusicPlayerManager.get().playNext();
                break;
        }
    }


    /***
     * 对歌曲长度的格式进行转换
     *
     * @param millSeconds
     */
    public String formatChange(int millSeconds) {
        int seconds = millSeconds / 1000;
        int second = seconds % 60;
        int munite = (seconds - second) / 60;
        DecimalFormat decimalFormat = new DecimalFormat("00");
        return decimalFormat.format(munite) + ":" + decimalFormat.format(second);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MusicPlayerManager.get().unregisterListener(this);
    }

    public static void open(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, PlayingActivity.class);
        context.startActivity(intent);
    }


}

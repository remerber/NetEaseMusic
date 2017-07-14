package com.wang.neteasemusic.service;

import android.support.v4.media.session.PlaybackStateCompat;

import com.wang.neteasemusic.data.Song;


/**
 * 歌曲改变的接口监听
 *
 */
public interface OnSongchangeListener {

    //歌曲改变的回调
    void onSongChanged(Song song);

    //歌曲后台改变的回调
    void onPlayBackStateChanged(PlaybackStateCompat state);



}

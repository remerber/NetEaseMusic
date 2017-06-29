package com.wang.neteasemusic.ui.music;

import android.content.Context;

import com.wang.neteasemusic.common.utils.LocalMusicLibrary;
import com.wang.neteasemusic.data.Song;
import com.wang.neteasemusic.model.LocalIview;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by HP on 2017/6/27.
 */

public class LocalLibraryPresenter {
    private LocalIview.LocalMusic localMusic;
    private Context mContext;

    public LocalLibraryPresenter(LocalIview.LocalMusic localMusic, Context mContext) {
        this.localMusic = localMusic;
        this.mContext = mContext;
    }

    public void requestMusic() {
        Observable.create(new Observable.OnSubscribe<List<Song>>() {
            @Override
            public void call(Subscriber<? super List<Song>> subscriber) {
                List<Song> songs = LocalMusicLibrary.getAllSongs(mContext);
                subscriber.onNext(songs);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<Song>>() {
            @Override
            public void call(List<Song> songs) {
                if (localMusic != null) {
                    localMusic.getLocalMusic(songs);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });

    }
}

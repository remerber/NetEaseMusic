package com.wang.neteasemusic.model;

import com.wang.neteasemusic.data.Song;

import java.util.List;

/**
 * Created by HP on 2017/6/27.
 */

public interface LocalIview {
    //本地音乐
    interface LocalMusic {
        void getLocalMusic(List<Song> songs);
    }

    //唱片
    interface LocakAlbum {
        void getLocalAlbum(List<Song> albums);
    }
}

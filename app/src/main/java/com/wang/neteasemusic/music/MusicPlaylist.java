package com.wang.neteasemusic.music;

import com.wang.neteasemusic.data.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2017/6/29.
 * 播放列表
 * 对应歌曲存放和查找
 */

public class MusicPlaylist {
    private List<Song> queue;
    private Song curSong;
    private long albumID;
    private String title;


    public MusicPlaylist() {
        queue = new ArrayList<>();
    }


    public void setQueue(List<Song> songs) {


    }

    /**
     * 添加歌曲队列
     *
     * @param songs
     */
    public void addQueue(List<Song> songs, boolean head) {
        if (!head) {
            queue.addAll(songs);
        } else {
            queue.addAll(0, songs);
        }

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getAlbumID() {
        return albumID;
    }

    public void setAlbumID(long albumID) {
        this.albumID = albumID;
    }

    public String getTitle() {
        return title;
    }


}

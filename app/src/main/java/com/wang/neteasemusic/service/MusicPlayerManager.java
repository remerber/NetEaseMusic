package com.wang.neteasemusic.service;

import android.content.Context;

/**
 * Created by HP on 2017/7/10.
 * 为什么要有这个类?????
 */

public class MusicPlayerManager {
    private final static MusicPlayerManager mInstance = new MusicPlayerManager();
    private Context mContext;
    private MusicService mService;

    public static MusicPlayerManager get() {
        return mInstance;
    }

    /**
     * 设置Context和Service
     * @param musicService
     * @return
     */
    public static MusicPlayerManager from(MusicService musicService) {
        return MusicPlayerManager.get().setContext(musicService).setService(musicService);
    }

    public MusicPlayerManager setContext(Context context) {
        this.mContext = context;
        return this;
    }

    public MusicPlayerManager setService(MusicService service) {

        this.mService = service;
        return this;
    }

}

package com.wang.neteasemusic.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by HP on 2017/7/4.
 */

public class MusicServiceHelper {

    private Context mContext;
    private static MusicServiceHelper musicServiceHelper = new MusicServiceHelper();

    public static MusicServiceHelper get(Context context) {
        musicServiceHelper.mContext = context;
        return musicServiceHelper;
    }

    MusicService musicService;

    /**
     * 初始化服务
     */
    public void initService() {
        if (musicService == null) {
            Intent intent = new Intent(mContext, MusicService.class);
            ServiceConnection conn = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    MusicService.MyBinder binder = (MusicService.MyBinder) service;
                    musicService = binder.getMusicService();
                    musicService.setUp();

                }

                @Override
                public void onServiceDisconnected(ComponentName name) {

                }
            };

            mContext.startService(intent);
            mContext.bindService(intent, conn, Context.BIND_AUTO_CREATE);

        }

    }

    public MusicService getMusicService() {
        return musicService;
    }
}

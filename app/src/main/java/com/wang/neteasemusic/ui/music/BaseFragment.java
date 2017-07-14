package com.wang.neteasemusic.ui.music;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.wang.neteasemusic.R;
import com.wang.neteasemusic.service.MusicPlayerManager;
import com.wang.neteasemusic.ui.play.PlayingActivity;

/**
 * Created by HP on 2017/7/13.
 */

public class BaseFragment extends Fragment {
    /**
     * snackbar的显示
     *
     * @param toast
     */
    public void showSnackBar(String toast) {
        Snackbar.make(getActivity().getWindow().getDecorView(), toast, Snackbar.LENGTH_SHORT).show();
    }

    public void showToast(int toastRes) {
        Toast.makeText(getActivity(), getString(toastRes), Toast.LENGTH_SHORT).show();
    }

    public boolean gotoSongPlayerActivity() {
        if (MusicPlayerManager.get().getPlayingSong() == null) {
            showToast(R.string.music_playing_none);
        }
        PlayingActivity.open(getActivity());
        return true;
    }
}

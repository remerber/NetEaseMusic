package com.wang.neteasemusic.ui.guide;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wang.neteasemusic.R;
import com.wang.neteasemusic.ui.widget.CustomVideoView;

/**
 * Created by HP on 2017/6/1.
 */

public class GuideFragment extends Fragment {

    private CustomVideoView mVideoView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mVideoView = new CustomVideoView(getContext());
        Uri uri;
        int index = getArguments().getInt("index");
        if (index == 1) {
            uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.guide_1);
        } else if (index == 2) {
            uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.guide_2);


        } else {
            uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.guide_3);
        }
        mVideoView.playVideo(uri);
        return mVideoView;
    }
}

package com.wang.neteasemusic.ui.guide;

import android.net.Uri;

import com.wang.neteasemusic.R;
import com.wang.neteasemusic.ui.widget.CustomVideoView;

/**
 * Created by HP on 2017/6/1.
 */

public class GuideFragment extends LoadFragment {

    private CustomVideoView mVideoView;

    @Override
    protected void lazyLoad() {
        mVideoView = findViewById(R.id.customVideoView);
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
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_guide;
    }



    @Override
    protected void stopLoad() {
        super.stopLoad();
        if (mVideoView != null) {
            mVideoView.stopPlayback();
        }
    }
}

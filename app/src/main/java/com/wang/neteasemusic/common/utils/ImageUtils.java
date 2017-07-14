package com.wang.neteasemusic.common.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by HP on 2017/7/14.
 */

public class ImageUtils {

    public static void GlideWith(Context context, String coverUrl, int resID, final ImageView imageView) {

        Glide.with(context).load(coverUrl).placeholder(resID).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                imageView.setImageDrawable(resource);
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                imageView.setImageDrawable(errorDrawable);
            }
        });

    }
}

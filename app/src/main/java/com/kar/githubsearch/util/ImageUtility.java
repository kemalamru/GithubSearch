package com.kar.githubsearch.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by Kemal Amru Ramadhan on 1/17/18.
 */

public class ImageUtility {

    public static void setImageUrl(ImageView imageView, String imageUrl) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(imageUrl)
                .into(imageView);
    }

    public static void setImageCircleUrl(ImageView imageView, String imageUrl) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(imageUrl)
                .apply(bitmapTransform(new CircleCrop()))
                .into(imageView);
    }
}

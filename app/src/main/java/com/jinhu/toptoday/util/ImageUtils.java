package com.jinhu.toptoday.util;

import android.widget.ImageView;

import com.jinhu.toptoday.R;

import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/4/12  18:36
 */

public class ImageUtils {
    public static void initImage(ImageView imageView, String iconUrl, boolean isCircluar) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setCircular(isCircluar)
                .setCrop(true)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .build();
        x.image().bind(imageView, iconUrl, imageOptions);
    }
}

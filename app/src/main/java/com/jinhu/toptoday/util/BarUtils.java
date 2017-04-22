package com.jinhu.toptoday.util;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/4/13  9:51
 */

public class BarUtils {
    public static void setStatusBar(Activity activity, String color) {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setStatusBarColor(Color.parseColor(color));
        }
    }
}

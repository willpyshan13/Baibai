package com.baibai.tools;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * 手机屏幕属性
 *
 * @author LJ
 * @since 2013-7-14
 */

public class ScreenProperties {

    public static final int WIDTH_NUM = 4;
    public static final int HEIGHT_NUM = 7;
    private static DisplayMetrics dm;

    public ScreenProperties(Activity activity) {
        initScreenProperties(activity);
    }

    public static void initScreenProperties(Activity activity) {
        dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        if (dm == null) {
            initScreenProperties(activity);
        }
    }

    public static int getScreenHeight() {
        return dm.heightPixels;
    }

    public static int getScreenWidth() {
        return dm.widthPixels;
    }

    public static float getScreenDensity() {
        return dm.density;
    }

    public static final float getXdpi() {
        return dm.xdpi;
    }

    public static final float getYdpi() {
        return dm.ydpi;
    }

}

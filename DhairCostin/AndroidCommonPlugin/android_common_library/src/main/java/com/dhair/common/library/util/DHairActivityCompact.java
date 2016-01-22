package com.dhair.common.library.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

/**
 * Creator: dengshengjin on 16/1/22 12:53
 * Email: deng.shengjin@zuimeia.com
 */
public class DHairActivityCompact extends ActivityOptionsCompat {
    public static void startActivityWithCustomAnim(Activity activity, Intent intent, int enterResId, int exitResId) {
        if (Build.VERSION.SDK_INT < 16) {
            activity.startActivity(intent);
            activity.overridePendingTransition(enterResId, exitResId);
        } else {
            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(activity, enterResId, exitResId);
            ActivityCompat.startActivity(activity, intent, activityOptionsCompat.toBundle());
        }
    }

    public static void startActivityWithScaleUpAnim(Activity activity, Intent intent, View source, int startX, int startY, int startWidth, int startHeight) {
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(source, startX, startY, startWidth, startHeight);
        ActivityCompat.startActivity(activity, intent, activityOptionsCompat.toBundle());
    }

}

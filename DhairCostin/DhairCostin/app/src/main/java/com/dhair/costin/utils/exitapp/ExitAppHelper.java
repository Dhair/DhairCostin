package com.dhair.costin.utils.exitapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;

import com.dhair.costin.injection.context.ActivityContext;
import com.dhair.costin.ui.home.HomeActivity;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

/**
 * Creator: dengshengjin on 16/1/11 14:35
 * Email: deng.shengjin@zuimeia.com
 */
public class ExitAppHelper {
    private Context mContext;
    private ExitReceiver mExitReceiver;
    private boolean mReceiverRegistered;
    private static final String EXIT_APP_RECEIVER = "ExitAppReceiver";

    @Inject
    public ExitAppHelper(@ActivityContext Context context) {
        if (!(context instanceof Activity)) {
            throw new InstanceNotActivityException();
        }
        mContext = context.getApplicationContext();
        mExitReceiver = new ExitReceiver((Activity) context);
    }

    public void registerReceiver() {
        if (!mReceiverRegistered) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(EXIT_APP_RECEIVER);
            mContext.registerReceiver(mExitReceiver, filter);
            mReceiverRegistered = true;
        }
    }

    public void unregisterReceiver() {
        if (mReceiverRegistered) {
            mReceiverRegistered = false;
            mContext.unregisterReceiver(mExitReceiver);
        }
    }

    public static void exitAppFinally(Context mContext) {
        Intent intent = new Intent(mContext, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);

        final Intent exitAppIntent = new Intent(EXIT_APP_RECEIVER);
        mContext.sendBroadcast(exitAppIntent);
        new Handler(Looper.getMainLooper()).postDelayed(() -> mContext.sendBroadcast(exitAppIntent), 300);
    }

    public static class ExitReceiver extends BroadcastReceiver {
        WeakReference<Activity> mActivityWeakReference;

        public ExitReceiver(Activity activity) {
            mActivityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (mActivityWeakReference != null) {
                Activity activity = mActivityWeakReference.get();
                if (activity != null) {
                    activity.finish();
                }
            }
        }
    }


    public static class InstanceNotActivityException extends RuntimeException {
        public InstanceNotActivityException() {
            super("Please deliver the Activity's Context");
        }
    }
}

package com.dhair.costin.utils.exitapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;

import com.dhair.costin.ui.splash.SplashActivity;

import java.lang.ref.WeakReference;

/**
 * Creator: dengshengjin on 16/1/11 14:35
 * Email: deng.shengjin@zuimeia.com
 */
public class ExitAppHelper {
    private Activity mActivity;
    private ExitReceiver mExitReceiver;
    private boolean mReceiverRegistered;
    private static final String EXIT_APP_RECEIVER = "ExitAppReceiver";

    public ExitAppHelper(Activity activity) {
        mActivity = activity;
        mExitReceiver = new ExitReceiver(mActivity);
    }

    public void registerReceiver() {
        if (!mReceiverRegistered) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(EXIT_APP_RECEIVER);
            mActivity.registerReceiver(mExitReceiver, filter);
            mReceiverRegistered = true;
        }
    }

    public void unregisterReceiver() {
        if (mReceiverRegistered) {
            mReceiverRegistered = false;
            mActivity.unregisterReceiver(mExitReceiver);
        }
    }

    public static void exitAppFinally(Context mContext) {
        Intent intent = new Intent(mContext, SplashActivity.class);
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
}

package com.dhair.hotfix;

import android.content.Context;
import android.util.Log;

import com.dhair.hotfix.utils.AssetUtils;
import com.dhair.hotfix.utils.DexUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by jixin.jia on 15/10/31.
 */
public class DhairHotFix {

    private static final String TAG = DhairHotFix.class.getSimpleName();
    private static final String HACK_DEX = "dhair_hack.apk";

    private static final String DEX_DIR = "dhair";
    private static final String DEX_OPT_DIR = "dhair_opt";


    public static void init(Context context) {
        File dexDir = new File(context.getFilesDir(), DEX_DIR);
        dexDir.mkdir();
        String dexPath = null;
        try {
            dexPath = AssetUtils.copyAsset(context, HACK_DEX, dexDir);
        } catch (IOException e) {
            Log.e(TAG, "copy " + HACK_DEX + " failed");
            e.printStackTrace();
        }

        loadPatch(context, dexPath);
    }

    public static void loadPatch(Context context, String dexPath) {

        if (context == null) {
            Log.e(TAG, "context is null");
            return;
        }
        if (!new File(dexPath).exists()) {
            Log.e(TAG, dexPath + " is null");
            return;
        }
        File dexOptDir = new File(context.getFilesDir(), DEX_OPT_DIR);
        dexOptDir.mkdir();
        try {
            DexUtils.injectDexAtFirst(dexPath, dexOptDir.getAbsolutePath());
        } catch (Exception e) {
            Log.e(TAG, "inject " + dexPath + " failed");
            e.printStackTrace();
        }
    }
}

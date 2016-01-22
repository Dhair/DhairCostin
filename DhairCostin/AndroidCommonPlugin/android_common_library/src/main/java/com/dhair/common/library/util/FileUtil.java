package com.dhair.common.library.util;

import android.content.Context;
import android.text.format.Formatter;

/**
 * Creator: dengshengjin on 16/1/22 11:52
 * Email: deng.shengjin@zuimeia.com
 */
public class FileUtil {
    public static String getFileSize(Context context, long sizeBytes) {
        return Formatter.formatFileSize(context, sizeBytes);
    }

    public static String getShortFileSize(Context context, long sizeBytes) {
        return Formatter.formatShortFileSize(context, sizeBytes);
    }
}

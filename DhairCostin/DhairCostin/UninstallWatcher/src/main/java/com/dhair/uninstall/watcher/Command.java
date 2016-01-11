package com.dhair.uninstall.watcher;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Creator: dengshengjin on 16/1/8 20:02
 * Email: deng.shengjin@zuimeia.com
 */
public class Command {
    private static final String TAG = Command.class.getSimpleName();

    public static final String BIN_DIR_NAME = "bin";

    /**
     * copy file to destination
     */
    private static void copyFile(File file, InputStream is, String mode)
            throws IOException, InterruptedException {
        final String abspath = file.getAbsolutePath();
        final FileOutputStream out = new FileOutputStream(file);
        byte buf[] = new byte[1024];
        int len;
        while ((len = is.read(buf)) > 0) {
            out.write(buf, 0, len);
        }

        out.close();
        is.close();

        Runtime.getRuntime().exec("chmod " + mode + " " + abspath).waitFor();
    }

    /**
     * copy file in assets into destination file
     *
     * @param context        context
     * @param assetsFilename file name in assets
     * @param file           the file to copy to
     * @param mode           mode of file
     * @throws IOException
     * @throws InterruptedException
     */
    public static void copyAssets(Context context, String assetsFilename, File file, String mode)
            throws IOException, InterruptedException {
        AssetManager manager = context.getAssets();
        final InputStream is = manager.open(assetsFilename);
        copyFile(file, is, mode);
    }

    /**
     * Install specified binary into destination directory.
     *
     * @param context  context
     * @param destDir  destionation directory
     * @param filename filename of binary
     * @return true if install successfully, otherwise return false
     */
    @SuppressWarnings("deprecation")
    public static boolean install(Context context, String destDir, String filename) {
        String abi = Build.CPU_ABI;
        if (!abi.startsWith("arm")) {
            return false;
        }

        try {
            File f = new File(context.getDir(destDir, Context.MODE_PRIVATE), filename);
            if (f.exists()) {
                Log.d(TAG, "binary has existed");
                return false;
            }

            copyAssets(context, filename, f, "0755");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "installBinary failed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Install specified binary into /data/data/pakage-name/app-bin directory.
     *
     * @param context  context
     * @param filename file name
     */
    public static void install(Context context, String filename) {
        install(context, BIN_DIR_NAME, filename);
    }
}

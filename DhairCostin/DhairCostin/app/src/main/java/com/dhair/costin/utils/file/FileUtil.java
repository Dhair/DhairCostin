package com.dhair.costin.utils.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Creator: dengshengjin on 16/2/28 21:04
 * Email: deng.shengjin@zuimeia.com
 */
public class FileUtil {
    public static void zip(String[] files, String zipFile) throws IOException {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
        byte data[] = new byte[1024];
        try {
            for (int i = 0; i < files.length; i++) {
                FileInputStream fileInputStream = new FileInputStream(files[i]);
                BufferedInputStream origin = new BufferedInputStream(fileInputStream, 1024);
                try {
                    ZipEntry entry = new ZipEntry(files[i].substring(files[i].lastIndexOf("/") + 1));
                    zipOutputStream.putNextEntry(entry);
                    int count;
                    while ((count = origin.read(data, 0, 1024)) != -1) {
                        zipOutputStream.write(data, 0, count);
                    }
                } finally {
                    if (origin != null) {
                        origin.close();
                    }
                }
            }
        } finally {
            zipOutputStream.close();
        }

    }

    public static void unzip(String zipFile, String location) throws IOException {
        File file = new File(location);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFile));
        try {
            ZipEntry zipEntry = null;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                String path = location + zipEntry.getName();
                if (zipEntry.isDirectory()) {
                    File unzipFile = new File(path);
                    if (!unzipFile.isDirectory()) {
                        unzipFile.mkdirs();
                    }
                } else {
                    FileOutputStream fileOutputStream = new FileOutputStream(path, false);
                    try {
                        for (int c = zipInputStream.read(); c != -1; c = zipInputStream.read()) {
                            fileOutputStream.write(c);
                        }
                        zipInputStream.closeEntry();
                    } finally {
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    }
                }
            }
        } finally {
            if (zipInputStream != null) {
                zipInputStream.close();
            }
        }

    }
}

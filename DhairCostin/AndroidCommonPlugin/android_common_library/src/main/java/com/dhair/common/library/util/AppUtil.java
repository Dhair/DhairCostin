package com.dhair.common.library.util;

/**
 * Creator: dengshengjin on 16/1/19 11:21
 * Email: deng.shengjin@zuimeia.com
 */
public class AppUtil {
    static int i = 0;

    public static void main(String[] args) {
        String s="sdfsfsf\nsdfdsf";
        System.out.println(s+","+AppUtil.class.getCanonicalName()+","+AppUtil.class.getName()+","+AppUtil.class.getSimpleName());
        new Thread() {
            @Override
            public void run() {
                super.run();
                for (int j = 0; j < 2; j++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }

            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                super.run();
                for (int j = 0; j < 2; j++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i--;
                }
            }
        }.start();

    }
}

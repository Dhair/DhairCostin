package com.dhair.common.library.util;

import java.lang.reflect.Method;

/**
 * Creator: dengshengjin on 16/2/22 14:10
 * Email: deng.shengjin@zuimeia.com
 */
public class DecalringClass {
    public static void main(String[] args) {

        // class object associated with class Long
        Class cls = Long.class;

        Method[] m = cls.getMethods();
        for (int i = 0; i < m.length; i++) {
            // returns te declaring class
            Class dec = m[i].getDeclaringClass();
            // displays all methods
            System.out.println("Method = " + m[i].toString());
            System.out.println(" Declaring class: " + dec.toString());
        }
    }
}

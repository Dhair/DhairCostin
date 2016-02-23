package com.dhair.datastructs.algorithm.container;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Creator: dengshengjin on 16/2/18 10:22
 * Email: deng.shengjin@zuimeia.com
 *
 * @see ArrayList
 */
public class DHairArrayList implements DHairList, Serializable {
    Object[] mData;
    int size;
    ArrayList mArrayList;

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public boolean add(Object o) {
        mData[size++] = o;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return true;
    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public boolean set(int index, Object o) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public static void main(String[] args) {
        String[] strings = {};
        strings = Arrays.copyOf(strings, 1);
        strings[0] = "A";
        for (String str : strings) {
            System.out.println("str:" + str);
        }
    }
}

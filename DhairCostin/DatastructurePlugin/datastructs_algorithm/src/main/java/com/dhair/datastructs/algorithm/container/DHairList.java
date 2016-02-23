package com.dhair.datastructs.algorithm.container;

import java.util.List;

/**
 * Creator: dengshengjin on 16/2/18 10:46
 * Email: deng.shengjin@zuimeia.com
 */
public interface DHairList {
    List list = null;
    int indexOf(Object o);

    boolean add(Object o);

    boolean remove(Object o);

    Object get(int index);

    boolean set(int index, Object o);

    boolean isEmpty();
}

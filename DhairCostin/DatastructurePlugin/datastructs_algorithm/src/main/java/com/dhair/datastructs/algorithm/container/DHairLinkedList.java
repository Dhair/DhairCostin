package com.dhair.datastructs.algorithm.container;

import java.io.Serializable;

/**
 * Creator: dengshengjin on 16/2/18 11:15
 * Email: deng.shengjin@zuimeia.com
 */
public class DHairLinkedList implements DHairList, Serializable {
    private transient Node mFirst;
    private transient Node mLast;
    //双向链表

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
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

    private static class Node {
        Node mNext;
        Node mPre;
    }
}

package com.dhair.datastructs.algorithm.mutilthread;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Creator: dengshengjin on 16/1/26 23:59
 * Email: deng.shengjin@zuimeia.com
 */
public class ProducerConsumer {
    public static final String INTENT_EXTRA = ProducerConsumer.class.getName();
    public static class Producer extends Thread {

    }

    public static class Consumer extends Thread {

    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedBlockingQueue<>();
        System.out.println(INTENT_EXTRA+","+(198111%1000));

    }
}

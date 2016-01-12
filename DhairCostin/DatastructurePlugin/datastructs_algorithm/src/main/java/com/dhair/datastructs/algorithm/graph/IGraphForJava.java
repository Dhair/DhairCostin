package com.dhair.datastructs.algorithm.graph;

import java.util.Queue;

/**
 * Creator: dengshengjin on 16/1/12 10:30
 * Email: deng.shengjin@zuimeia.com
 */
public interface IGraphForJava {

    void resetVisited();

    void depthSearch(int index);

    void broadSearch(Queue<Integer> queue);
}

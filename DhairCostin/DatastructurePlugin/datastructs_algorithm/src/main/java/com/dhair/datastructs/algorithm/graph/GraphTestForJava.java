package com.dhair.datastructs.algorithm.graph;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Creator: dengshengjin on 16/1/12 12:44
 * Email: deng.shengjin@zuimeia.com
 */
public class GraphTestForJava {
    public static void main(String[] args) {
        String[] nodes = {"A", "B", "C", "D", "E"};
        int[][] mMatrix = new int[5][5];
        //AB
        mMatrix[0][1] = 1;
        mMatrix[1][0] = 1;
//        //BC
//        mMatrix[1][2] = 1;
//        mMatrix[2][1] = 1;
        //BD
        mMatrix[1][3] = 1;
        mMatrix[3][1] = 1;
        //CD
        mMatrix[2][3] = 1;
        mMatrix[3][2] = 1;
        //DE
        mMatrix[3][4] = 1;
        mMatrix[4][3] = 1;

        GraphForJava graphForJava = new GraphForJava(mMatrix, nodes);
        graphForJava.resetVisited();
        graphForJava.depthSearch(0);
        System.out.println();
        graphForJava.resetVisited();
        Queue<Integer> queue = new LinkedBlockingQueue<>();
        queue.offer(0);
        graphForJava.broadSearch(queue);
    }
}

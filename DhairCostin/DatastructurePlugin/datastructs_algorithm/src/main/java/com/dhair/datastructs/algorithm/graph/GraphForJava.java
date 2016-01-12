package com.dhair.datastructs.algorithm.graph;

import java.util.Queue;

/**
 * Creator: dengshengjin on 16/1/12 12:43
 * Email: deng.shengjin@zuimeia.com
 */
public class GraphForJava implements IGraphForJava {
    private int[][] mMatrix;
    private String[] mNodes;
    private int mVertex;

    public GraphForJava(int[][] matrix, String[] nodes) {
        mMatrix = matrix;
        mNodes = nodes;
        mVertex = nodes.length;
    }

    @Override
    public void resetVisited() {
        for (int j = 0; j < mVertex; j++) {
            mMatrix[j][j] = 0;
        }
    }

    @Override
    public void depthSearch(int index) {
        if (mMatrix[index][index] == 1) {
            return;
        }
        mMatrix[index][index] = 1;
        System.out.print(mNodes[index] + ",");
        for (int j = 0; j < mVertex; j++) {
            if (index == j) {
                continue;
            }
            if (mMatrix[index][j] == 1) {
                depthSearch(j);
            }
        }
    }

    @Override
    public void broadSearch(Queue<Integer> queue) {
        if (queue == null || queue.isEmpty()) {
            return;
        }
        int index = queue.poll().intValue();
        if (mMatrix[index][index] == 1) {
            broadSearch(queue);
            return;
        }
        mMatrix[index][index] = 1;
        System.out.print(mNodes[index] + ",");
        for (int j = 0; j < mVertex; j++) {
            if (index == j) {
                continue;
            }
            if (mMatrix[index][j] == 1 && mMatrix[j][j] != 1) {
                queue.offer(j);
            }
        }
        broadSearch(queue);
    }
}

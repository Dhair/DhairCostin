package com.dhair.datastructs.algorithm.tree;

/**
 * Creator: dengshengjin on 16/1/12 10:15
 * Email: deng.shengjin@zuimeia.com
 */
public class TreeNode {
    TreeNode mLeftNode;
    TreeNode mRightNode;
    int mValue;
    TreeType mTreeType;

    public TreeNode(int value) {
        mValue = value;
    }

    enum TreeType {
        Left, Right, Root
    }
}

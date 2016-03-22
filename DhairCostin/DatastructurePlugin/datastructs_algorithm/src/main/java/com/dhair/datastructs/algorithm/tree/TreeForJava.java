package com.dhair.datastructs.algorithm.tree;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Creator: dengshengjin on 16/1/12 11:34
 * Email: deng.shengjin@zuimeia.com
 */
public class TreeForJava implements ITreeForJava {
    private TreeNode mTreeNode;

    @Override
    public TreeNode createTree(int[] intArr) {
        for (int intValue : intArr) {
            insert(intValue);
        }
        return mTreeNode;
    }

    private void insert(int intValue) {
        if (mTreeNode == null) {
            mTreeNode = new TreeNode(intValue);
            mTreeNode.mTreeType = TreeNode.TreeType.Root;
        } else {
            TreeNode currNode = mTreeNode;
            while (true) {
                if (intValue < currNode.mValue) {
                    TreeNode tmpNode = currNode;
                    currNode = currNode.mLeftNode;
                    if (currNode == null) {
                        tmpNode.mLeftNode = new TreeNode(intValue);
                        tmpNode.mLeftNode.mTreeType = TreeNode.TreeType.Left;
                        break;
                    }
                } else {
                    TreeNode tmpNode = currNode;
                    currNode = currNode.mRightNode;
                    if (currNode == null) {
                        tmpNode.mRightNode = new TreeNode(intValue);
                        tmpNode.mRightNode.mTreeType = TreeNode.TreeType.Right;
                        break;
                    }
                }

            }
        }
    }

    @Override
    public void preSearch(TreeNode treeNode) {

    }

    @Override
    public void middleSearch(TreeNode treeNode) {

    }

    @Override
    public void postSearch(TreeNode treeNode) {

    }

    @Override
    public void levelSearch(TreeNode treeNode) {
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.offer(treeNode);
        while (!queue.isEmpty()) {
            TreeNode tmpNode = queue.poll();
            System.out.print(tmpNode.mValue + ",");
            if (tmpNode.mLeftNode != null) {
                queue.offer(tmpNode.mLeftNode);
            }
            if (tmpNode.mRightNode != null) {
                queue.offer(tmpNode.mRightNode);
            }
        }
    }

    @Override
    public int getLeafNode(TreeNode treeNode) {
        return 0;
    }

    @Override
    public int getNode(TreeNode treeNode) {
        return 0;
    }

    @Override
    public int getWeight(TreeNode treeNode) {
        return 0;
    }
}

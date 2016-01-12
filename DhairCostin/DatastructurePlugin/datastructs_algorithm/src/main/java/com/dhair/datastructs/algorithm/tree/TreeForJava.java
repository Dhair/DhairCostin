package com.dhair.datastructs.algorithm.tree;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Creator: dengshengjin on 16/1/12 11:34
 * Email: deng.shengjin@zuimeia.com
 */
public class TreeForJava implements ITreeForJava {
    private TreeNode mRootNode;

    @Override
    public TreeNode createTree(int[] intArr) {
        for (int intValue : intArr) {
            insertTree(intValue);
        }
        return mRootNode;
    }

    private void insertTree(int value) {
        if (mRootNode == null) {
            mRootNode = new TreeNode(value);
            mRootNode.mTreeType = TreeNode.TreeType.Root;
        } else {
            TreeNode currNode = mRootNode;//Warning
            while (true) {
                if (value < currNode.mValue) {
                    TreeNode tmpNode = currNode;
                    currNode = currNode.mLeftNode;
                    if (currNode == null) {
                        tmpNode.mLeftNode = new TreeNode(value);
                        tmpNode.mLeftNode.mTreeType = TreeNode.TreeType.Left;
                        break;
                    }
                } else {
                    TreeNode tmpNode = currNode;
                    currNode = currNode.mRightNode;
                    if (currNode == null) {
                        tmpNode.mRightNode = new TreeNode(value);
                        tmpNode.mRightNode.mTreeType = TreeNode.TreeType.Right;
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void preSearch(TreeNode treeNode) {
        if (treeNode != null) {
            System.out.print(+treeNode.mValue + ",");
            preSearch(treeNode.mLeftNode);
            preSearch(treeNode.mRightNode);
        }
    }

    @Override
    public void middleSearch(TreeNode treeNode) {
        if (treeNode != null) {
            middleSearch(treeNode.mLeftNode);
            System.out.print(+treeNode.mValue + ",");
            middleSearch(treeNode.mRightNode);
        }
    }

    @Override
    public void postSearch(TreeNode treeNode) {
        if (treeNode != null) {
            postSearch(treeNode.mLeftNode);
            postSearch(treeNode.mRightNode);
            System.out.print(+treeNode.mValue + ",");
        }
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
        if (treeNode == null) {
            return 0;
        }
        int leftNode = getLeafNode(treeNode.mLeftNode);
        int rightNode = getLeafNode(treeNode.mRightNode);
        if (treeNode.mLeftNode == null && treeNode.mRightNode == null) {
            return leftNode + rightNode + 1;
        } else {
            return leftNode + rightNode;
        }
    }

    @Override
    public int getNode(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        return getNode(treeNode.mLeftNode) + getNode(treeNode.mRightNode) + 1;
    }

    @Override
    public int getWeight(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        int leftWeight = getWeight(treeNode.mLeftNode);
        int rightWeight = getWeight(treeNode.mRightNode);
        int weight = Math.max(leftWeight, rightWeight);
        return weight + 1;
    }
}

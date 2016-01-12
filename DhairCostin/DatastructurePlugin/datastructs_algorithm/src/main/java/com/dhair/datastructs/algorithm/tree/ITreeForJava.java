package com.dhair.datastructs.algorithm.tree;

/**
 * Creator: dengshengjin on 16/1/12 10:14
 * Email: deng.shengjin@zuimeia.com
 */
public interface ITreeForJava {

    TreeNode createTree(int[] intArr);

    void preSearch(TreeNode treeNode);

    void middleSearch(TreeNode treeNode);

    void postSearch(TreeNode treeNode);

    void levelSearch(TreeNode treeNode);

    int getLeafNode(TreeNode treeNode);

    int getNode(TreeNode treeNode);

    int getWeight(TreeNode treeNode);

}

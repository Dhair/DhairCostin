package com.dhair.datastructs.algorithm.tree;

/**
 * Creator: dengshengjin on 16/1/12 11:35
 * Email: deng.shengjin@zuimeia.com
 */
public class TreeTestForJava {
    public static void main(String[] args) {
        int[] intArr = {18, 7, 6, 19, 45, 12, 78, 67, 78};
        TreeForJava treeForJava = new TreeForJava();
        TreeNode treeNode = treeForJava.createTree(intArr);
        System.out.print("preSearch:");
        treeForJava.preSearch(treeNode);
        System.out.println();

        System.out.print("middleSearch:");
        treeForJava.middleSearch(treeNode);
        System.out.println();

        System.out.print("postSearch:");
        treeForJava.postSearch(treeNode);
        System.out.println();

        System.out.print("levelSearch:");
        treeForJava.levelSearch(treeNode);
        System.out.println();

        System.out.println("leaf:" + treeForJava.getLeafNode(treeNode));
        System.out.println("node:" + treeForJava.getNode(treeNode));
        System.out.println("weight:" + treeForJava.getWeight(treeNode));
    }
}

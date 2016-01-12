package com.dhair.datastructs.algorithm.sort;

/**
 * Creator: dengshengjin on 16/1/12 10:33
 * Email: deng.shengjin@zuimeia.com
 */
public class SortTestForJava {
    public static void main(String[] args) {
        int[] intArr = {18, 7, 6, 19, 45, 12, 78, 67, 78};
        int length = intArr.length;
        SortForJava sortForJava = new SortForJava();
        System.out.println("source:");
        sortForJava.print(intArr);

        int[] bubbleArr = intArr.clone();
        sortForJava.bubbleSort(bubbleArr);
        System.out.println("bubble:");
        sortForJava.print(bubbleArr);

        int[] quickArr = intArr.clone();
        sortForJava.quickSort(quickArr, 0, length - 1);
        System.out.println("quick:");
        sortForJava.print(quickArr);

        int[] insertArr = intArr.clone();
        sortForJava.insertSort(insertArr);
        System.out.println("insert:");
        sortForJava.print(insertArr);

        int[] selectArr = intArr.clone();
        sortForJava.selectSort(selectArr);
        System.out.println("select:");
        sortForJava.print(selectArr);

        int[] shellArr = intArr.clone();
        sortForJava.shellSort(shellArr);
        System.out.println("shell:");
        sortForJava.print(shellArr);

        int[] mergeArr = intArr.clone();
        sortForJava.mergeSort(mergeArr, 0, length - 1);
        System.out.println("merge:");
        sortForJava.print(mergeArr);

        int[] radixArr = intArr.clone();
        sortForJava.radixSort(radixArr, 100);
        System.out.println("radix:");
        sortForJava.print(radixArr);

        int destValue=78;
        System.out.println("find "+destValue+" with binary:" + sortForJava.binarySearch(radixArr, destValue, 0, length));
    }
}

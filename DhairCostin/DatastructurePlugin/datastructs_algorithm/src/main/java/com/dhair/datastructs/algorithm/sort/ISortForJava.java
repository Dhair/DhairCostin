package com.dhair.datastructs.algorithm.sort;

/**
 * Creator: dengshengjin on 16/1/11 17:37
 * Email: deng.shengjin@zuimeia.com
 */
public interface ISortForJava {
    void print(int[] intArr);

    void bubbleSort(int[] intArr);

    void quickSort(int[] intArr, int low, int high);

    void insertSort(int[] intArr);

    void selectSort(int[] intArr);

    void shellSort(int[] intArr);

    void mergeSort(int[] intArr, int left, int right);

    void radixSort(int[] intArr, int maxDigit);

    void heapSort(int[] intArr);

    int binarySearch(int[] intArr, int destValue, int left, int right);

}

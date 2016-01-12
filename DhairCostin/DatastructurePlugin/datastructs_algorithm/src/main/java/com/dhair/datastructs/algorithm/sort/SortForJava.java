package com.dhair.datastructs.algorithm.sort;

/**
 * Creator: dengshengjin on 16/1/11 20:43
 * Email: deng.shengjin@zuimeia.com
 */
public class SortForJava implements ISortForJava {
    @Override
    public void print(int[] intArr) {
        for (int intValue : intArr) {
            System.out.print(intValue + ",");
        }
        System.out.println("\n");
    }

    @Override
    public void bubbleSort(int[] intArr) {
        int length = intArr.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (intArr[i] > intArr[j]) {
                    int tmp = intArr[i];
                    intArr[i] = intArr[j];
                    intArr[j] = tmp;
                }
            }
        }

    }

    @Override
    public void quickSort(int[] intArr, int low, int high) {
        if (low < high) {
            int middle = getMiddle(intArr, low, high);
            quickSort(intArr, low, middle - 1);
            quickSort(intArr, middle + 1, high);
        }
    }

    private int getMiddle(int[] intArr, int low, int high) {
        int tmp = intArr[low];
        while (low < high) {
            while (low < high && tmp <= intArr[high]) {
                high--;
            }
            intArr[low] = intArr[high];
            while (low < high && tmp >= intArr[low]) {
                low++;
            }
            intArr[high] = intArr[low];
        }
        intArr[low] = tmp;
        return low;
    }

    @Override
    public void insertSort(int[] intArr) {
        int length = intArr.length;
        for (int i = 0; i < length; i++) {
            for (int j = i; j > 0 && intArr[j] < intArr[j - 1]; j--) {
                int tmp = intArr[j];
                intArr[j] = intArr[j - 1];
                intArr[j - 1] = tmp;
            }
        }
    }

    @Override
    public void selectSort(int[] intArr) {
        int length = intArr.length;
        for (int i = 0; i < length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < length; j++) {
                if (intArr[j] < intArr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int tmp = intArr[min];
                intArr[min] = intArr[i];
                intArr[i] = tmp;
            }
        }
    }

    @Override
    public void shellSort(int[] intArr) {
        int length = intArr.length;
        int middle = intArr.length;
        while (true) {
            middle = (int) Math.ceil(middle / 2);
            for (int i = 0; i < middle; i++) {
                for (int j = i + middle; j < length; j = j + middle) {
                    for (int k = j; k >= middle && intArr[k] < intArr[k - middle]; k = k - middle) {
                        int tmp = intArr[k];
                        intArr[k] = intArr[k - middle];
                        intArr[k - middle] = tmp;
                    }
                }
            }

            if (middle == 1) {
                break;
            }
        }
    }

    @Override
    public void mergeSort(int[] intArr, int left, int right) {
        if (left < right) {
            int middle = (left + right) >> 1;
            mergeSort(intArr, left, middle);
            mergeSort(intArr, middle + 1, right);
            merge(intArr, left, middle, right);
        }
    }

    private void merge(int[] intArr, int left, int middle, int right) {
        int[] tmp = new int[intArr.length];
        int leftIndex = left;
        int rightIndex = middle + 1;
        int k = 0;
        while (leftIndex <= middle && rightIndex <= right) {
            if (intArr[leftIndex] < intArr[rightIndex]) {
                tmp[k++] = intArr[leftIndex++];
            } else {
                tmp[k++] = intArr[rightIndex++];
            }
        }
        while (rightIndex <= right) {
            tmp[k++] = intArr[rightIndex++];
        }
        while (leftIndex <= middle) {
            tmp[k++] = intArr[leftIndex++];
        }
        for (int i = 0; i < k; i++) {
            intArr[left + i] = tmp[i];
        }
    }

    @Override
    public void radixSort(int[] intArr, int maxDigit) {
        int n = 1;
        int length = intArr.length;
        if (length < 10) {
            length = 10;
        }
        while (n <= maxDigit) {
            int[][] bucket = new int[10][length];
            int[] order = new int[length];
            for (int intValue : intArr) {
                int digit = (intValue / n) % 10;
                bucket[digit][order[digit]] = intValue;
                order[digit]++;
            }
            int k = 0;
            for (int i = 0; i < 10; i++) {
                if (order[i] != 0) {
                    for (int j = 0; j < order[i]; j++) {
                        intArr[k] = bucket[i][j];
                        k++;
                    }
                }
            }
            n = n * 10;
        }
    }

    @Override
    public void heapSort(int[] intArr) {

    }

    @Override
    public int binarySearch(int[] intArr, int destValue, int left, int right) {
        if (left <= right) {
            int middle = (left + right) >> 1;
            if (destValue == intArr[middle]) {
                return middle;
            } else if (destValue < intArr[middle]) {
                return binarySearch(intArr, destValue, left, middle - 1);
            } else if (destValue > intArr[middle]) {
                return binarySearch(intArr, destValue, middle + 1, right);
            }
        }
        return -1;
    }
}

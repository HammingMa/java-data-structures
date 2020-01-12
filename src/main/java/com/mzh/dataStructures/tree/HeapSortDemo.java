package com.mzh.dataStructures.tree;

import java.util.Arrays;

public class HeapSortDemo {
    public static void main(String[] args) {
        int[] array = {4, 6, 8, 5, 9};

        heapSort(array);

        System.out.println(Arrays.toString(array));

    }

    public static void heapSort(int[] array) {
//        adjustHeap(array, 1, array.length); // 4 9 8 5 6
//        System.out.println(Arrays.toString(array));
//
//        adjustHeap(array, 0, array.length); // 4 9 8 5 6
//        System.out.println(Arrays.toString(array));

        int temp = 0;
        //将无序的堆调整为有序的堆
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }

        for (int i = array.length - 1; i >= 0; i--) {
            temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            adjustHeap(array, 0, i);
        }


    }

    /**
     * @param array  操作的数组
     * @param i      第一个非叶子节点的下标
     * @param length 要排序的数的个数
     */
    public static void adjustHeap(int[] array, int i, int length) {
        int temp = array[i];

        //定位 第一个非叶子节点的 i的左子节点 n =2*i+1 然后循环取下面的节点
        for (int n = i * 2 + 1; n < length; n = n * 2 + 1) {
            //右子节点不为空，判断判断右子节点是否比左子节点大
            if ((n + 1) < length && array[n] < array[n + 1]) {
                //n保存两个字节点中值较大的下标
                n++;
            }

            if (array[n] > temp) {
                array[i] = array[n];
                //用i记录交换的值得下标， 用于最终将 顶值 写入
                i = n;
            } else {
                break;
            }
        }

        array[i] = temp;

    }
}

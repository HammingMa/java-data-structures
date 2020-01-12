package com.mzh.dataStructures.tree;

import java.util.Arrays;
import java.util.Date;

public class HeapSortDemo2 {


    public static void main(String[] args) {
//        int[] array = {4, 6, 8, 5, 9,-1,10,-99,100,0};
//
//        heapSort(array);
//
//        System.out.println(Arrays.toString(array));

        int size = 8000000;

        int array[] = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * size * 10);
        }

        if (size <= 10) {
            System.out.println("排序前");
            System.out.println(Arrays.toString(array));
        }

        long time1 = System.currentTimeMillis();
        System.out.println(new Date(time1).toString());


        heapSort(array);


        long time2 = System.currentTimeMillis();
        System.out.println(new Date(time2).toString());

        System.out.println("排序耗时：" + (time2 - time1) + "毫秒");
        if (size <= 10) {
            System.out.println("排序后");
            System.out.println(Arrays.toString(array));
        }
    }

    public static void heapSort(int[] array) {
        System.out.println("进入堆排序");
        int temp = 0;

        //从第一个非叶子节点，开始 调整成大顶堆 第一个最终的大顶堆
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjust(array, i, array.length);
        }

        //头尾交换，循环 root节点大顶堆
        for (int i = array.length - 1; i >= 0; i--) {
            temp = array[i];
            array[i] = array[0];
            array[0] = temp;

            adjust(array, 0, i);
        }
    }

    /**
     * @param array  数组
     * @param index  非叶子节点的编号
     * @param length 要调整的数据个数
     */
    public static void adjust(int[] array, int index, int length) {
        int temp = array[index];

        //循环子节点 从左子节点开始  i*2+1 为左子节点
        for (int j = index * 2 + 1; j < length; j = j * 2 + 1) {
            //判断左子节点和右子节点的大小 取最大的索引
            if ((j + 1) < length && array[j] < array[j + 1]) {
                //保存左右节点最大值得下标
                j++;
            }

            //最大的子节点大于 父节点
            if (array[j] > temp) {
                array[index] = array[j];
                //记住交换的数据的下标
                index = j;
            }else {
                break;
            }

        }

        array[index] = temp;
    }
}



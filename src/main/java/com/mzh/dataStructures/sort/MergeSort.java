package com.mzh.dataStructures.sort;

import java.util.Arrays;
import java.util.Date;

public class MergeSort {

    private static int count = 0 ;

    public static void main(String[] args) {
//        int array[] = {8, 4, 5, 7, 1, 3, 6, 2};
//
//        int[] temp = new int[array.length];
//        mergeSort(array, 0, array.length-1, temp);
//
//        System.out.println(Arrays.toString(array));
//
//
//        System.out.println(count);


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


        int[] temp = new int[array.length];
        mergeSort(array, 0, array.length - 1, temp);


        long time2 = System.currentTimeMillis();
        System.out.println(new Date(time2).toString());

        System.out.println("排序耗时：" + (time2 - time1) + "毫秒");
        if (size <= 10) {
            System.out.println("排序后");
            System.out.println(Arrays.toString(array));
        }

        System.out.println(count);

    }

    public static void mergeSort(int[] array, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //左拆分
            mergeSort(array, left, mid, temp);
            //右拆分
            mergeSort(array, mid + 1, right, temp);
            //合并
            merge(array, left, right, mid, temp);
        }


    }

    public static void merge(int[] array, int left, int right, int mid, int[] temp) {
        count++;
        //排序
        int leftIndex = left;
        int rightIndex = mid + 1;
        int tempIndex = left;

        while (leftIndex <= mid && rightIndex <= right) {
            if (array[leftIndex] <= array[rightIndex]) {
                temp[tempIndex] = array[leftIndex];
                leftIndex++;
            } else {
                temp[tempIndex] = array[rightIndex];
                rightIndex++;
            }
            tempIndex++;
        }

        //有剩余拷贝
        while (leftIndex <= mid) {
            temp[tempIndex] = array[leftIndex];
            leftIndex++;
            tempIndex++;
        }

        while (rightIndex <= right) {
            temp[tempIndex] = array[rightIndex];
            rightIndex++;
            tempIndex++;
        }

        //拷贝到源数组
        for (int i = left; i <= right; i++) {
            array[i] = temp[i];
        }

    }

}

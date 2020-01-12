package com.mzh.dataStructures.sort;

import java.util.Arrays;
import java.util.Date;

public class RadixSort {
    public static void main(String[] args) {
//        int[] array = {53, 3, 542, 748, 14, 214};
//
//        radixSort(array);
//        System.out.println(Arrays.toString(array));
//
//
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
        radixSort(array);


        long time2 = System.currentTimeMillis();
        System.out.println(new Date(time2).toString());

        System.out.println("排序耗时：" + (time2 - time1) + "毫秒");
        if (size <= 10) {
            System.out.println("排序后");
            System.out.println(Arrays.toString(array));
        }

    }

    public static void radixSort(int[] array) {
        int[][] bucket = new int[10][array.length];
        int[] bucketElementCounts = new int[10];

        //求出最大数的位数
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        int maxBitCount = Integer.toString(max).length();

        //按照最长位数循环， 每次循环多一个取余数
        for (int i = 0, n = 1; i < maxBitCount; i++, n *= 10) {
            //取数放到桶里
            for (int j = 0; j < array.length; j++) {
                int digit = array[j] / n % 10;
                bucket[digit][bucketElementCounts[digit]] = array[j];
                bucketElementCounts[digit]++;
            }

            //把桶的的数据取出放回源数组
            int index = 0;
            while (index < array.length) {
                for (int j = 0; j < bucketElementCounts.length; j++) {
                    for (int k = 0; k < bucketElementCounts[j]; k++) {
                        array[index] = bucket[j][k];
                        index++;
                    }
                }
            }

            for (int j = 0; j < bucketElementCounts.length; j++) {
                bucketElementCounts[j] = 0;
            }


        }

    }


}

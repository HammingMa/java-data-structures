package com.mzh.dataStructures.sort;

import java.util.Arrays;
import java.util.Date;

public class ShellSort {
    public static void main(String[] args) {

        //int array[] = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};


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


        shellSortByMovePosition(array);


        long time2 = System.currentTimeMillis();
        System.out.println(new Date(time2).toString());

        System.out.println("排序耗时：" + (time2 - time1) + "毫秒");
        if (size <= 10) {
            System.out.println("排序后");
            System.out.println(Arrays.toString(array));
        }

    }

    public static void shellSort(int array[]) {
        int temp = 0;
        int count = 0;
        //分组
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            //从每一组的第二个开始和把前面的当成有序列表进行比较
            for (int i = gap; i < array.length; i++) {
                //System.out.println("i = " + i);
                for (int j = i - gap; j >= 0; j -= gap) {
                    //System.out.println("j = " + j);
                    if (array[j] > array[j + gap]) {
                        temp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = temp;
                    }
                }
            }
            count++;
            //System.out.println("第" + count + "次排序的结果为");
            //System.out.println(Arrays.toString(array));


        }

    }

    public static void shellSort2(int[] array) {
        int temp = 0;
        for (int gap = array.length / 2; gap >= 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (array[j] > array[j + gap]) {
                        temp = array[j + gap];
                        array[j + gap] = array[j];
                        array[j] = temp;
                    }
                }
            }
            System.out.println(Arrays.toString(array));
        }
    }

    public static void shellSort3(int[] array) {
        int temp = 0;
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (array[j] > array[j + gap]) {
                        temp = array[j + gap];
                        array[j + gap] = array[j];
                        array[j] = temp;
                    }
                }
            }
            System.out.println(Arrays.toString(array));
        }
    }

    //移动位置的希尔排序
    public static void shellSortByMovePosition(int[] array) {
        //分组
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            //第二个位置开始移位
            for (int i = gap; i < array.length; i++) {
                int insertValue = array[i];
                int insertIndex = i - gap;
                while (insertIndex >= 0 && array[insertIndex] > insertValue) {
                    array[insertIndex + gap] = array[insertIndex];
                    insertIndex -= gap;
                }
                if (insertIndex + gap != i) {
                    array[insertIndex + gap] = insertValue;
                }
            }
        }
    }
}

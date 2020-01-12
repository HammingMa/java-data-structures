package com.mzh.dataStructures.sort;

import java.util.Arrays;
import java.util.Date;

public class SelectSort {
    public static void main(String[] args) {
        //int array[] = {10, 34, 119,2, 1};


        int array[] = new int[80000];

        for (int i = 0; i < 80000; i++) {
            array[i] = (int) (Math.random() * 800000);
        }

        long time1 = System.currentTimeMillis();
        System.out.println(new Date(time1).toString());
        //System.out.println("排序前");
        //System.out.println(Arrays.toString(array));

        selectSort2(array);

        //System.out.println("排序后");
        //System.out.println(Arrays.toString(array));
        long time2 = System.currentTimeMillis();
        System.out.println(new Date(time2).toString());

        System.out.println("排序耗时：" + (time2 - time1) + "毫秒");
    }

    //选择排序，每一轮选择最小的放在该放的位置
    public static void selectSort(int array[]) {
        int minValue = 0;
        for (int i = 0; i < array.length - 1; i++) {
            minValue = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < minValue) {
                    minValue = array[j];
                    array[j] = array[i];
                    array[i] = minValue;
                }
            }
            //array[i] = minValue;

            //System.out.println("第" + (i + 1) + "次排序的结果为");
            //System.out.println(Arrays.toString(array));
        }
    }

    //选择排序，每一轮选择最小的放在该放的位置
    public static void selectSort2(int array[]) {
        int minValue = 0;
        int minIndex = 0;
        for (int i = 0; i < array.length - 1; i++) {
            minValue = array[i];
            minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < minValue) {
                    minValue = array[j];
                    minIndex = j;
                }
            }

            if (i != minIndex) {
                array[minIndex] = array[i];
                array[i] = minValue;
            }
            //System.out.println("第" + (i + 1) + "次排序的结果为");
            //System.out.println(Arrays.toString(array));
        }
    }

}

package com.mzh.dataStructures.sort;


import java.util.Arrays;
import java.util.Date;


public class BubbleSort {
    //冒泡排序
    public static void main(String[] args) {
        //int array[] = { 3, 9, -1, 10, -2};


        int array[] = new int[80000];

        for (int i = 0; i < 80000; i++) {
            array[i] = (int) (Math.random() * 800000);
        }

        long time1 = System.currentTimeMillis();
        System.out.println(new Date(time1).toString());
        //System.out.println("排序前");
        //System.out.println(Arrays.toString(array));

        bubble(array);

        //System.out.println("排序后");
        //System.out.println(Arrays.toString(array));
        long time2 = System.currentTimeMillis();
        System.out.println(new Date(time2).toString());

        System.out.println("排序耗时：" + (time2 - time1) + "毫秒");


    }

    public static void bubble(int array[]) {
        int temp = 0;
        boolean flag;
        for (int i = 0; i < array.length - 1; i++) {
            flag = true;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    flag = false;
                }
            }
            //System.out.println("第" + (i + 1) + "次排序的结果为");
            //System.out.println(Arrays.toString(array));

            if (flag) {
                System.out.println("排序提前完成！！！");
                break;
            }
        }
    }
}

package com.mzh.dataStructures.sort;

import java.util.Arrays;
import java.util.Date;

public class InsertSort {
    public static void main(String[] args) {
        //int array[] = {101, 34, 119, 2};


        int array[] = new int[80000];

        for (int i = 0; i < 80000; i++) {
            array[i] = (int) (Math.random() * 800000);
        }

        long time1 = System.currentTimeMillis();
        System.out.println(new Date(time1).toString());
        //System.out.println("排序前");
        //System.out.println(Arrays.toString(array));

        insertSort(array);

        //System.out.println("排序后");
        //System.out.println(Arrays.toString(array));
        long time2 = System.currentTimeMillis();
        System.out.println(new Date(time2).toString());

        System.out.println("排序耗时：" + (time2 - time1) + "毫秒");
    }

    //将需要插入的数据插入到前面的有序列表中
    public static void insertSort(int array[]) {

        int insertValue = 0;
        int insertIndex = 0;

        for (int i = 1; i < array.length; i++) {
            insertValue = array[i];
            insertIndex = i - 1;

            while (insertIndex >= 0 && insertValue < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }

            if (insertIndex + 1 != i) {
                array[insertIndex + 1] = insertValue;
            }
            //System.out.println("第" + i + "次排序的结果为");
            //System.out.println(Arrays.toString(array));

        }
    }
}

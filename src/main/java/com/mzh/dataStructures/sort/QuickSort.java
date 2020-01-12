package com.mzh.dataStructures.sort;

import java.util.Arrays;
import java.util.Date;

public class QuickSort {
    public static void main(String[] args) {

        //int array[] = {0,-9, 0, 78, 0, 23, 0, -20, -567, 70,0,-3,-5};

        //int array[] = {1, 3, 3, 4, 5, 6, 7, 9, 8, 1};
        // 1,3,3,4,5,6,7,9,8,1
        //1,3,3,4,1,6,7,9,8,5
        //1,3,3,4,1,5,7,9,8,6

//        quickSort2(array, 0, array.length - 1);
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


        quickSort2(array, 0, array.length - 1);


        long time2 = System.currentTimeMillis();
        System.out.println(new Date(time2).toString());

        System.out.println("排序耗时：" + (time2 - time1) + "毫秒");
        if (size <= 10) {
            System.out.println("排序后");
            System.out.println(Arrays.toString(array));
        }



    }

    public static void quickSort(int[] array, int left, int right) {
        int l = left;
        int r = right;
        int pivot = array[(left + right) / 2];
        int temp = 0;


        while (l < r) {
            //向后找直到倒找 大于等于 pivot的值  可能会找到pivot
            while (array[l] < pivot) {
                l++;
            }

            //向前找直到找到 小于等于 pivot的值 可能会找到 pivot
            while (array[r] > pivot) {
                r--;
            }

            //如果 l大于r证明 找完完了 可以结束循环了
            if (l >= r) {
                break;
            }

            //交换
            temp = array[r];
            array[r] = array[l];
            array[l] = temp;

            System.out.println("交换");
            System.out.println("l = " + l);
            System.out.println((left + right) / 2);
            System.out.println("r = " + r);

            //如果出现和中间相同的数 会反复交换，成为死循环，这要加上判断
            if (array[l] == pivot) {
                r--;
            }

            if (array[r] == pivot) {
                l++;
            }
        }

        System.out.println(Arrays.toString(array));
        System.out.println("l = " + l);
        System.out.println((left + right) / 2);
        System.out.println("r = " + r);

        if (l == r) {
            l++;
            r--;
        }

        if (left < r) {
            quickSort(array, left, r);
        }

        if (l < right) {
            quickSort(array, l, right);
        }

    }

    public static void quickSort2(int[] array, int left, int right) {
        int l = left;
        int r = right;
        int pivot = array[(left + right) / 2];

        int temp = 0;

        while (l < r) {
            while (array[l] < pivot) {
                l++;
            }

            while (array[r] > pivot) {
                r--;
            }

            if (l >= r) {
                break;
            }

            temp = array[r];
            array[r] = array[l];
            array[l] = temp;

            if (array[r] == pivot) {
                l++;
            }

            if (array[l] == pivot) {
                r--;
            }
        }

        if (l == r) {
            l++;
            r--;
        }

        if (left < r) {
            quickSort2(array, left, r);
        }

        if (l < right) {
            quickSort2(array, l, right);
        }

    }

}

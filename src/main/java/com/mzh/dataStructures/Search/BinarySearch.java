package com.mzh.dataStructures.Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {-2, -2, -2, -2, 3, 4, 4, 6, 11, 11, 11, 89, 101, 101, 101};

        int findIndex = binarySearch(array, 0, array.length - 1, -3);

        if (findIndex == -1) {
            System.out.println("没有找到！");

        }

        System.out.println("查找的下标为：" + findIndex);

        List<Integer> indexs = binarySearch2(array, 0, array.length - 1, 5);
        System.out.println("查找的下标为："+ indexs);



    }

    public static int binarySearch(int[] array, int left, int rigth, int findvalue) {
        System.out.println("二分查找");
        int findIndex = -1;
        if (left > rigth) {
            return findIndex;
        }

        int midIndex = (left + rigth) / 2;

        if (array[midIndex] > findvalue) {
            findIndex = binarySearch(array, left, midIndex-1, findvalue);
        } else if (array[midIndex] < findvalue) {
            findIndex = binarySearch(array, midIndex + 1, rigth, findvalue);
        } else {
            findIndex = midIndex;
        }
        return findIndex;
    }


    public static List<Integer> binarySearch2(int[] array, int left, int rigth, int findvalue) {
        List<Integer> resultList = null;

        if (left > rigth) {
            return resultList;
        }

        int midIndex = (left + rigth) / 2;

        if (array[midIndex] > findvalue) {
            resultList = binarySearch2(array, left, midIndex-1, findvalue);
        } else if (array[midIndex] < findvalue) {
            resultList = binarySearch2(array, midIndex + 1, rigth, findvalue);
        } else {
            resultList = new ArrayList<>();
            resultList.add(midIndex);

            int index = midIndex + 1;
            while (index < array.length && array[index] == findvalue) {
                resultList.add(index);
                index++;
            }

            index = midIndex - 1;
            while (index >= 0 && array[index] == findvalue) {
                resultList.add(index);
                index--;
            }
        }
        return resultList;
    }

}

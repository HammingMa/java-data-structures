package com.mzh.dataStructures.Search;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] array = new int[100];

        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        int index = insertValueSearch(array, 0, array.length - 1, 89);

        System.out.println(index);

        int index2 = BinarySearch.binarySearch(array, 0, array.length - 1, 89);
        System.out.println(index2);

    }

    public static int insertValueSearch(int[] array, int left, int right, int findValue) {
        System.out.println("差值查找");
        int reslut = -1;
        if (left > right || findValue < array[left] || findValue > array[right]) {
            return reslut;
        }

        int mid = left + (right - left) * (findValue - array[left]) / (array[right] - array[left]);
        int midValue = array[mid];

        if (findValue > midValue) {
            reslut = insertValueSearch(array, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            reslut = insertValueSearch(array, left, mid - 1, findValue);
        } else {
            reslut = mid;
        }


        return reslut;
    }
}

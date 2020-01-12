package com.mzh.algorithm.binarysearch;

public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] array = {1, 3, 8, 10, 11, 67, 100};

        int res = binarySearchNoRecur(array, 1001);
        System.out.println(res);
    }

    public static int binarySearchNoRecur(int[] array, int searchValue) {
        int result = -1;

        int left = 0;
        int right = array.length - 1;
        int mid = 0;

        while (left <= right) {
            mid = (left + right) / 2;

            if (array[mid] > searchValue) {
                right = mid - 1;
            } else if (array[mid] < searchValue) {
                left = mid + 1;
            } else if (array[mid] == searchValue) {
                result = mid;
                break;
            }
        }


        return result;
    }
}

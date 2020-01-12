package com.mzh.dataStructures.Search;

public class SeqSearch {
    public static void main(String[] args) {
        int[] array = {3, 5, 7, 11, -1, 9, 6};

        int result = seqSearch(array, 6);
        if (result == -1) {
            System.out.println("没有找到!");
            return;
        }

        System.out.println("查找的结果下标为："+result);

    }

    public static int seqSearch(int[] array, int value) {
        int result = -1;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                result = i;
            }
        }

        return result;
    }

}

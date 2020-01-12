package com.mzh.dataStructures.Search;

import java.util.Arrays;

public class FibonacciSearch {

    private static int maxSize = 20;

    public static int[] fib() {
        int[] fib = new int[maxSize];

        fib[0] = 1;
        fib[1] = 1;

        for (int i = 2; i < maxSize; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        return fib;
    }

    public static int fibSearch(int[] array, int findValue) {
        int index = -1;
        int low = 0;
        int higth = array.length - 1;
        int mid = 0;

        int k = 0;

        int[] f = fib();

        System.out.println(Arrays.toString(f));

        while (higth > f[k] - 1) {
            k++;
        }

        int[] temp = Arrays.copyOf(array, f[k]);

        for (int i = higth + 1; i < f[k]; i++) {
            temp[i] = temp[higth];
        }

        while (low <= higth) {
            System.out.println("low = "+low +" hight = "+higth+" k = "+k);
            // 1 1 2 3
            //为了计算黄金分割数
            mid = low + f[k - 1] - 1;

            if (findValue < temp[mid]) {
                higth = mid - 1;
                k -= 1;
            } else if (findValue > temp[mid]) {
                low = mid + 1;
                //在此查找由于 f(k) = f(k-1) + f(k-2) 所以剩下的数只有f(k-2)个
                k -= 2;
            } else {
                if (higth > mid) {
                    index = mid;
                } else {
                    index = higth;
                }
                break;
            }

        }

        return index;
    }

    public static void main(String[] args) {
        int[] array = {-1, 7, 10, 20, 29, 53, 61, 63, 64, 99};

        int index = fibSearch(array, 62);

        System.out.println(index);
    }
}

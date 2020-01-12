package com.mzh.algorithm.kmp;

import java.util.Arrays;

public class KmpAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        //String str2 = "ABCDABD";

        String str2 = "AB";

        int[] kmpNext = getKmpNext(str2);

        System.out.println(Arrays.toString(kmpNext));

        int result = kmp(str1, str2, kmpNext);
        System.out.println(result);

    }

    public static int kmp(String src, String dest, int[] kmpNext) {

        for (int i = 0, j = 0; i < src.length(); i++) {

            while (j > 0 && src.charAt(i) != dest.charAt(j)) {
                j = kmpNext[j - 1];
            }

            if (src.charAt(i) == dest.charAt(j)) {
                j++;
            }

            if (j == dest.length()) {
                return i - j + 1;
            }

        }

        return -1;
    }

    public static int[] getKmpNext(String dest) {
        int[] next = new int[dest.length()];

        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {

            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }

        return next;
    }
}

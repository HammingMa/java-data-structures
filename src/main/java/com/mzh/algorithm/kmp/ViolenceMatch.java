package com.mzh.algorithm.kmp;

public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";

        System.out.println(violenceMatch(str1, str2));
    }

    public static int violenceMatch(String str1, String str2) {

        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        int s1Len = str1.length();
        int s2Len = str2.length();

        int i = 0;
        int j = 0;

        while (i < s1Len && j < s2Len) {
            if (chars1[i] == chars2[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }


        if(j==s2Len){
            return i-(j-1)-1;
        }

        return -1;
    }
}

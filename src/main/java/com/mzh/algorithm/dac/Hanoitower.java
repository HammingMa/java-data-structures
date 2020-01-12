package com.mzh.algorithm.dac;

public class Hanoitower {
    public static void main(String[] args) {
        hanoitower(5,'A','B','C');
    }

    //汉罗塔
    public static void hanoitower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("将第" + num + "个盘从 " + a + "->" + c);
        }else{
            hanoitower(num-1,a,c,b);
            System.out.println("将第" + num + "个盘从 " + a + "->" + c);
            hanoitower(num-1,b,a,c);
        }
    }
}

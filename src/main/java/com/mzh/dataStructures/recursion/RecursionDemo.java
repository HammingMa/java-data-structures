package com.mzh.dataStructures.recursion;

public class RecursionDemo {
    public static void main(String[] args) {
        test(4);
        test2(5);

        System.out.println(factorial(4));
    }

    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println(n);
    }

    public static void test2(int n) {
        if (n > 2) {
            test2(n - 1);
        }else {
            System.out.println(n);
        }
    }

    public static int factorial(int n){
        if(n==1){
            return 1;
        }else {
            return n*factorial(n-1);
        }
    }
}

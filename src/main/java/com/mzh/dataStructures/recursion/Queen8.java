package com.mzh.dataStructures.recursion;

public class Queen8 {

    private int maxCount = 8;

    private int[] array = new int[maxCount];

    public int printCount = 0;
    public int judgeCount = 0;

    public void print() {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
        printCount++;
    }

    public Boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            if (array[n] == array[i] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }

        return true;
    }

    private void check(int n) {
        if (n == maxCount) {
            print();
            return;
        }


        for (int i = 0; i < maxCount; i++) {
            array[n] = i;
            if (judge(n)) {
                check(n + 1);
            }
        }


    }


    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);

        System.out.println("一共有" + queen8.printCount + "种放法");
        System.out.println("一共检测了" + queen8.judgeCount + "次");
    }


}

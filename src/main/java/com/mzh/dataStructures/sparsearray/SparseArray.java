package com.mzh.dataStructures.sparsearray;

//稀疏数组压缩 五子棋存盘
public class SparseArray {
    public static void main(String[] args) {
        int chessArray1[][] = new int[11][11];
        chessArray1[1][2] = 1;
        chessArray1[2][3] = 2;
        chessArray1[6][5] = 1;

        System.out.println("原始的数组");
        for (int[] rows : chessArray1) {
            for (int item : rows) {
                System.out.printf("%d\t", item);
            }
            System.out.println();
        }


        int sum = 0;
        for (int[] rows : chessArray1) {
            for (int item : rows) {
                if (item != 0) {
                    sum++;
                }
            }
        }

        int sparseArray[][] = new int[sum + 1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        int count = 0;
        for (int i = 0; i < chessArray1.length; i++) {
            for (int j = 0; j < chessArray1[i].length; j++) {
                if (chessArray1[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray1[i][j];
                }
            }
        }

        System.out.println("压缩后的稀疏数组");
        for (int[] rows : sparseArray) {
            System.out.printf("%d\t%d\t%d", rows[0], rows[1], rows[2]);
            System.out.println();
        }

        int chessArray2[][] = new int[sparseArray[0][0]][sparseArray[0][1]];

        for (int i = 1; i < sparseArray.length; i++) {
            chessArray2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        System.out.println("恢复后的数组");
        for (int[] rows : chessArray2) {
            for (int item : rows) {
                System.out.printf("%d\t", item);
            }
            System.out.println();
        }
    }
}

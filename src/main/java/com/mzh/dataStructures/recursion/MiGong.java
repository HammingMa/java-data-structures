package com.mzh.dataStructures.recursion;

public class MiGong {


    public static void main(String[] args) {
        int map[][] = new int[7][8];

        for (int i = 0; i < 7; i++) {
            map[i][0] = 1;
            map[i][7] = 1;
        }

        for (int i = 0; i < 8; i++) {
            map[0][i] = 1;
            map[6][i] = 1;
        }

        map[3][2] = 1;
        map[3][1] = 1;
        map[2][2] = 1;

        for (int[] rows : map) {
            for (int i : rows) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

        getWay(map,1,1);

        System.out.println();
        for (int[] rows : map) {
            for (int i : rows) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

    }

    public static Boolean getWay(int map[][], int i, int j) {
        if (map[5][6] == 2) {
            return true;
        } else {
            if(map[i][j]==0) {
                map[i][j] = 2;
                if (getWay(map, i + 1, j)) {
                    return true;
                } else if (getWay(map, i, j + 1)) {
                    return true;
                } else if (getWay(map, i - 1, j)) {
                    return true;
                } else if (getWay(map, i, j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            }else {
                return false;
            }
        }
    }
}


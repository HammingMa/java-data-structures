package com.mzh.algorithm.dynamic;

//背包问题
public class KnapsackProblem {
    public static void main(String[] args) {
        //物品的重量
        int[] w = {1, 4, 3};
        //物品的价值
        int[] val = {1500, 3000, 2000};
        //背包的容量
        int n = 4;
        //物品的个数
        int m = w.length;
        //存放最优的组合
        int[][] v = new int[m + 1][n + 1];

        //记录最优时存放的商品
        int[][] path = new int[m + 1][n + 1];

        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }

        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }


        //循环物品(的重量)循环一次加一个物品
        for (int i = 1; i <= m; i++) {
            //从1循环背包的的容量，
            for (int j = 1; j <= n; j++) {
                //当前的容量小于当前商品的重量
                if (j < w[i - 1]) {
                    v[i][j] = v[i - 1][j];
                } else if (j >= w[i - 1]) { // 当前的容量大于当前商品的质量
                    //上一格 所有组合的价值
                    int v1 = v[i - 1][j];
                    //添加上 当前行商品的 价值
                    // w[i-1] 为当前商品的重量
                    // j-w[i-1] 为放进当前商品背包的剩余空间
                    //v[i - 1][j - w[i - 1] i - 1未放进当前商品的  剩余空间为 j - w[i - 1] 最优价值
                    int v2 = val[i - 1] + v[i - 1][j - w[i - 1]];
                    if (v1 >= v2) {
                        v[i][j] = v1;

                    } else {
                        v[i][j] = v2;
                        path[i][j] = 1;
                    }
                }
            }
        }


        for (int[] rows : v) {
            for (int i : rows) {
                System.out.print(i + " ");
            }
            System.out.println();
        }


        int i = path.length-1;
        int j = path[0].length-1;
        while (i > 0 && j > 0) {
            if(path[i][j]==1){
                System.out.println("第"+i+"件商品放进背包");
                //背包剩余的空间
                j = j-w[i-1];
            }
            i--;
        }

    }
}

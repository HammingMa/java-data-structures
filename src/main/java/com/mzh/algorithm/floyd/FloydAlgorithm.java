package com.mzh.algorithm.floyd;

import java.util.Arrays;

public class FloydAlgorithm {
    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        int[][] matrix = new int[7][];
        final int N = 65535;
        matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
        matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
        matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
        matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
        matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
        matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
        matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };

        Graph graph = new Graph(vertexs.length, vertexs, matrix);
        graph.floyd();
        graph.show();
    }
}


class Graph {
    private char[] vertexs;
    private int[][] dis;
    private int[][] pre;

    public Graph(int length, char[] vertexs, int[][] dis) {
        this.vertexs = vertexs.clone();
        this.dis = dis.clone();
        pre = new int[length][length];

        for (int i = 0; i < pre.length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    public void show() {
        for (int i = 0; i < vertexs.length; i++) {

            for (int j = 0; j < pre[0].length; j++) {
                System.out.printf("%10c", vertexs[pre[i][j]]);
            }

            System.out.println();
            for (int j = 0; j < dis[0].length; j++) {
                System.out.printf("%3c->%c:%d", vertexs[i], vertexs[j], dis[i][j]);
            }
            System.out.println();
        }
    }

    public void floyd() {
        int len = 0;

        //k为中间节点的下标
        for (int k = 0; k < dis.length; k++) {
            //i为出发节点的下标
            for (int i = 0; i < dis.length; i++) {
                //j为到达节点的下标的
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k] + dis[k][j];
                    if (len < dis[i][j]) {
                        dis[i][j] = len;
                        pre[i][j] = k;
                    }
                }
            }
        }

    }
}

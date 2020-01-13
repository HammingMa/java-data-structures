package com.mzh.algorithm.kruskal;

import java.util.Arrays;

public class KruskalCase {

    int edgeOfNums = 0;
    char[] vertexs;
    int[][] edges;

    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {

        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        int[][] matrix = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}
        };

        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);

        kruskalCase.print();

        System.out.println(Arrays.toString(kruskalCase.getEdatas()));
        System.out.println(Arrays.toString(kruskalCase.edataSort(kruskalCase.getEdatas())));

        kruskalCase.kroskal();

    }

    public void kroskal() {
        int index = 0;

        int[] ends = new int[vertexs.length]; //保存每个节点的终点

        Edata[] rest = new Edata[vertexs.length - 1];

        Edata[] edatas = getEdatas();

        edataSort(edatas);

        for (int i = 0; i < edatas.length; i++) {
            char start = edatas[i].start;
            char end = edatas[i].end;

            int p1 = getPostion(start);
            int p2 = getPostion(end);

            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);

            if (m != n) {
                ends[m] = n;
                rest[index++] = edatas[i];
            }

        }

        System.out.println("克鲁斯卡尔最小生成树为");
        for (Edata edata : rest) {
            System.out.println(edata);
        }

    }

    public KruskalCase(char[] vertexs, int[][] edges) {
        this.vertexs = vertexs.clone();
        this.edges = edges.clone();

        for (int i = 0; i < edges.length; i++) {
            for (int j = i + 1; j < edges[0].length; j++) {
                if (edges[i][j] != INF) {
                    edgeOfNums++;
                }
            }
        }

    }

    public void print() {
        System.out.println("临接矩阵为：");
        System.out.print(" ");
        for (char vertex : vertexs) {
            System.out.printf("%12c", vertex);
        }
        System.out.println();
        for (int i = 0; i < edges.length; i++) {
            System.out.printf("%c", vertexs[i]);
            for (int j = 0; j < edges[0].length; j++) {
                System.out.printf("%12d", edges[i][j]);
            }
            System.out.println();
        }
    }

    public Edata[] edataSort(Edata[] edatas) {
        for (int i = 0; i < edatas.length - 1; i++) {
            for (int j = 0; j < edatas.length - 1 - i; j++) {
                if (edatas[j].weight > edatas[j + 1].weight) {
                    Edata temp = edatas[j];
                    edatas[j] = edatas[j + 1];
                    edatas[j + 1] = temp;
                }
            }
        }
        return edatas;
    }

    public int getPostion(char c) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public Edata[] getEdatas() {
        int index = 0;
        Edata[] edatas = new Edata[edgeOfNums];
        for (int i = 0; i < edges.length; i++) {
            for (int j = i + 1; j < edges[0].length; j++) {
                if (edges[i][j] < INF) {
                    edatas[index++] = new Edata(vertexs[i], vertexs[j], edges[i][j]);
                }
            }
        }
        return edatas;
    }

    public int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }

        return i;
    }

}

class Edata {
    char start;
    char end;
    int weight;

    public Edata(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "{" + start +
                "<-->" + end +
                "," + weight +
                '}';
    }
}

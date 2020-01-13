package com.mzh.algorithm.dijkstra;

import java.util.Arrays;

public class DijkstraAlgorithm {


    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        int[][] matrix = new int[7][];
        final int N = 65535;//
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};

        Grap grap = new Grap(vertexs, matrix);
        grap.print();

        grap.dijkstra(6);

        grap.showDijkstra();


    }

}

class Grap {
    char[] vertexs;
    int[][] edges;
    VisitedVertex visitedVertex;

    public Grap(char[] vertexs, int[][] edges) {
        this.vertexs = vertexs.clone();
        this.edges = edges.clone();
    }

    public void print() {
        System.out.println("临接矩阵为：");
        System.out.print(" ");
        for (char vertex : vertexs) {
            System.out.printf("%8c", vertex);
        }
        System.out.println();
        for (int i = 0; i < edges.length; i++) {
            System.out.printf("%c", vertexs[i]);
            for (int j = 0; j < edges[0].length; j++) {
                System.out.printf("%8d", edges[i][j]);
            }
            System.out.println();
        }
    }

    public void dijkstra(int index) {
        visitedVertex = new VisitedVertex(vertexs.length, index);
        update(index);
        for (int i = 1; i < vertexs.length; i++) {
            index = visitedVertex.updateArr();
            update(index);
        }


    }

    public void update(int index) {

        for (int i = 0; i < edges[index].length; i++) {
            int len = visitedVertex.dis[index] + edges[index][i];
            if (!visitedVertex.in(i) && len < visitedVertex.dis[i]) {
                //visitedVertex.alreadArr[i] = true;
                visitedVertex.updateDis(i, len);
                visitedVertex.updatePre(i, index);
            }
        }
    }

    public void showDijkstra(){
        visitedVertex.show();
    }
}

class VisitedVertex {
    //存放节点是否访问过数组
    boolean[] alreadArr;
    //存放访问前驱的数组
    int[] preVisitorArr;
    //存放fang问最短的距离
    int[] dis;

    /**
     * @param length 顶点个数
     * @param index  从哪个顶点开始
     */
    public VisitedVertex(int length, int index) {
        alreadArr = new boolean[length];
        preVisitorArr = new int[length];
        dis = new int[length];

        Arrays.fill(dis, 65535);
        dis[index] = 0;
        alreadArr[index]=true;
    }

    /**
     * 顶点是否被访问过
     *
     * @param index 顶点的下标
     * @return
     */
    public boolean in(int index) {
        return alreadArr[index];
    }

    /**
     * 更新访问距离
     *
     * @param index 要更新的顶点下标
     * @param len   更新的值
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新前驱节点
     *
     * @param index    要更新的顶点下标
     * @param preIndex 新的前驱节点的下标
     */
    public void updatePre(int index, int preIndex) {
        preVisitorArr[index] = preIndex;
    }


    public int updateArr() {
        int min = 65535;
        int index = 0;

        for (int i = 0; i < alreadArr.length; i++) {
            if (!alreadArr[i] && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }

        alreadArr[index] = true;
        return index;
    }

    public void show(){
        System.out.println();
        System.out.println("最短路径");

        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (char vertex : vertexs) {
            System.out.printf("%5c",vertex);
        }

        System.out.println();
        for (boolean b : alreadArr) {
            System.out.printf("%5b",b);
        }
        System.out.println();
        for (int i : preVisitorArr) {
            System.out.printf("%5d",i);
        }
        System.out.println();
        for (int di : dis) {
            System.out.printf("%5d",di);
        }
    }
}

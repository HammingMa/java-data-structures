package com.mzh.dataStructures.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {

    //存储定点的集合
    private List<String> vertexList;
    //存储图对应的邻接矩阵
    private int[][] edges;
    //存储边的个数
    private int numOfEdges;


    public static void main(String[] args) {

        String[] vertexs = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(5);

        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        System.out.println(graph.getNumOfVertex());
        System.out.println(graph.getNumOfEdges());
        graph.showGraph();

        System.out.println("深度优先遍历");
        graph.dfs();
        System.out.println("广度优先遍历");
        graph.dfs();


    }

    public Graph(int n) {
        vertexList = new ArrayList<String>();
        edges = new int[n][n];
        numOfEdges = 0;

    }


    //查找第一个临接节点
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < edges.length; i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }

        return -1;
    }

    //根据当前临接节点返回下一个临接节点
    public int getNextNeighbor(int index, int v2) {
        for (int i = v2 + 1; i < edges.length; i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    private void dfs(boolean[] isVisited, int index) {
        System.out.print(vertexList.get(index) + "->");
        isVisited[index] = true;

        int v2 = getFirstNeighbor(index);

        while (v2 > -1) {
            if (!isVisited[v2]) {
                dfs(isVisited, v2);
            }

            v2 = getNextNeighbor(index, v2);
        }
    }

    //深度优先遍历
    public void dfs() {
        //存储是否被访问 过
        boolean[] isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
        System.out.println();
    }

    //广度优先遍历
    public void bfs(boolean[] isVisited, int index) {
        System.out.println(vertexList.get(index) + "->");
        isVisited[index] = true;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(index);

        while (queue.isEmpty()) {
            Integer v1 = queue.removeFirst();
            int v2 = getFirstNeighbor(v1);
            while (v2 != -1) {
                if (isVisited[v2]) {
                    System.out.println(vertexList.get(v2) + "->");
                    isVisited[v2] = true;
                    queue.addLast(v2);
                }
                v2 = getNextNeighbor(v1, v2);
            }
        }

    }

    public void bfs(){
        //存储是否被访问 过
        boolean[] isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
        System.out.println();
    }


    public String getVertex(int index) {
        return vertexList.get(index);
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    public int getNumOfVertex() {
        return vertexList.size();
    }

    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public void showGraph() {
        for (int[] rows : edges) {
            for (int vertex : rows) {
                System.out.print(vertex + " ");
            }
            System.out.println();
        }
    }


    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

}

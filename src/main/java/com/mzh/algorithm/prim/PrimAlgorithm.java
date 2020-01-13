package com.mzh.algorithm.prim;

public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] weight = {
                {Integer.MAX_VALUE, 5, 7, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2},
                {5, Integer.MAX_VALUE, Integer.MAX_VALUE, 9, Integer.MAX_VALUE, Integer.MAX_VALUE, 3},
                {7, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 8, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 9, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 4, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 8, Integer.MAX_VALUE, Integer.MAX_VALUE, 5, 4},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 4, 5, Integer.MAX_VALUE, 6},
                {2, 3, Integer.MAX_VALUE, Integer.MAX_VALUE, 4, 6, Integer.MAX_VALUE}
        };
        Grap grap = new Grap(data.length);

        MinGrap minGrap = new MinGrap();

        minGrap.createMinGrap(grap, data.length, data, weight);

        minGrap.showGrap(grap);

        minGrap.prim(grap,3);
    }
}

class MinGrap {
    public void createMinGrap(Grap grap, int verxs, char[] data, int[][] weight) {
        for (int i = 0; i < verxs; i++) {
            grap.getData()[i] = data[i];
            for (int j = 0; j < verxs; j++) {
                grap.getWeight()[i][j] = weight[i][j];
            }
        }
    }

    public void showGrap(Grap grap) {
        for (int i = 0; i < grap.getVerxs(); i++) {
            for (int j = 0; j < grap.getVerxs(); j++) {
                System.out.print(grap.getWeight()[i][j] + " ");
            }
            System.out.println();
        }
    }


    /**
     * 普利姆算法 最小生成树
     *
     * @param grap  图
     * @param index 开始的顶点
     */
    public void prim(Grap grap, int index) {
        boolean[] isVisited = new boolean[grap.getVerxs()];

        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i] = false;
        }

        isVisited[index] = true;

        //记录最小的边
        int minWeight = Integer.MAX_VALUE;
        //记录最小边已访问过的顶点
        int h1 = -1;
        //记录最小边未访问的顶点
        int h2 = -1;
        //循环生成边 n个顶点生成 n-1个边
        for (int i = 1; i < grap.getVerxs(); i++) {
            minWeight = Integer.MAX_VALUE;
            //遍历isVisited数组中已经访问过的顶点，取已经访问的到未访问的最小值
            for (int j = 0; j < grap.getVerxs(); j++) {
                if (isVisited[j]) {
                    for (int l = 0; l < grap.getVerxs(); l++) {
                        if (!isVisited[l] && grap.getWeight()[j][l] < minWeight) {
                            minWeight = grap.getWeight()[j][l];
                            h1 = j;
                            h2 = l;
                        }
                    }
                }
            }

            isVisited[h2] = true;
            System.out.println("从" + grap.getData()[h1] + "点到" + grap.getData()[h2] + "点最小的权值为" + grap.getWeight()[h1][h2]);

        }

    }

}


class Grap {
    private int verxs;
    private char[] data;
    private int[][] weight;

    public int getVerxs() {
        return verxs;
    }

    public void setVerxs(int verxs) {
        this.verxs = verxs;
    }

    public char[] getData() {
        return data;
    }

    public void setData(char[] data) {
        this.data = data;
    }

    public int[][] getWeight() {
        return weight;
    }

    public void setWeight(int[][] weight) {
        this.weight = weight;
    }

    public Grap(int verxs) {
        this.verxs = verxs;
        this.data = new char[verxs];
        this.weight = new int[verxs][verxs];
    }
}

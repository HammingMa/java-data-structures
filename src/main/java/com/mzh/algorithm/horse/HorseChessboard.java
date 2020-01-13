package com.mzh.algorithm.horse;


import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class HorseChessboard {
    //列
    private static int X;
    //行
    private static int Y;

    private static boolean[] isVisited;

    private static boolean finished;


    public static void main(String[] args) {
        System.out.println("开始执行");

        X = Y = 10;
        int row = 1;
        int column = 1;

        int[][] chessboard = new int[X][Y];
        isVisited = new boolean[X * Y];

        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();

        System.out.println("共耗时" + (end - start) + "毫秒");

        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step+"\t");
            }
            System.out.println();
        }
    }

    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        isVisited[row * X + column] = true;

        List<Point> ps = getNext(new Point(column, row));

        sort(ps);

        while (!ps.isEmpty()) {
            Point p = ps.remove(0);
            if (!isVisited[p.y * X + p.x]) {
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }
        //System.out.println(step +" "+X*Y);

        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            isVisited[row * X + column] = false;
        } else {
            finished = true;
        }



    }

    public static List<Point> getNext(Point curPoint) {
        List<Point> points = new ArrayList<Point>();

        Point p = new Point();

        //走5
        if ((p.x = curPoint.x - 2) >= 0 && (p.y = curPoint.y - 1) >= 0) {
            points.add(new Point(p));
        }
        //走6
        if ((p.x = curPoint.x - 1) >= 0 && (p.y = curPoint.y - 2) >= 0) {
            points.add(new Point(p));
        }
        //走7
        if ((p.x = curPoint.x + 1) < X && (p.y = curPoint.y - 2) >= 0) {
            points.add(new Point(p));
        }
        //走0
        if ((p.x = curPoint.x + 2) < X && (p.y = curPoint.y - 1) >= 0) {
            points.add(new Point(p));
        }
        //走1
        if ((p.x = curPoint.x + 2) < X && (p.y = curPoint.y + 1) < Y) {
            points.add(new Point(p));
        }
        //走2
        if ((p.x = curPoint.x + 1) < X && (p.y = curPoint.y + 2) < Y) {
            points.add(new Point(p));
        }
        //走3
        if ((p.x = curPoint.x - 1) >= 0 && (p.y = curPoint.y + 2) < Y) {
            points.add(new Point(p));
        }
        //走4
        if ((p.x = curPoint.x - 2) >= 0 && (p.y = curPoint.y + 1) < Y) {
            points.add(new Point(p));
        }

        return points;
    }



    public static void sort(List<Point> points){
        points.sort((Point p1,Point p2)->{
            int s1 = getNext(p1).size();
            int s2 = getNext(p2).size();

            if(s1<s2){
                return -1;
            }else if(s1>s2){
                return 1;
            }else {
                return 0;
            }
        });
    }





}

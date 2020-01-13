//package com.mzh.algorithm.horse;
//
//import java.awt.*;
//import java.util.List;
//
//public class t2 {
//
//    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
//        chessboard[row][column] = step;
//        isVisited[row * X + column] = true;
//
//        List<Point> ps = getNext(new Point(column, row));
//
//        while(!ps.isEmpty()) {
//            Point p = ps.remove(0);
//            if(!isVisited[p.y * X + p.x]) {
//                traversalChessboard(chessboard, p.y, p.x, step + 1);
//            }
//        }
//        if(step < X * Y && !finished) {
//            chessboard[row][column] = 0;
//            isVisited[row * X + column] = false;
//        } else {
//            finished = true;
//        }
//    }
//}

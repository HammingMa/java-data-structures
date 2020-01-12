package com.mzh.dataStructures.queue;


import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {

        ArrayQueue queue = new ArrayQueue(3);

        char key = ' ';
        Boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("s(Show)：显示队列");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：获取队列的元素");
            System.out.println("h(head)：查看队列头元素");
            System.out.println("e(exit)：退出应用程序");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入数据：");
                    int n = scanner.nextInt();
                    queue.addQueue(n);
                    break;
                case 'g':
                    try{
                        int i = queue.getQueue();
                        System.out.println("获取的数据是："+i);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try{
                        int i = queue.headQueue();
                        System.out.println("队列的头是："+i);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 'e':
                    loop= false;
                    scanner.close();
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~");

    }
}

class ArrayQueue {
    int array[];
    int maxSiez;
    int front; //队列头 指向队列头元素的前一个位置 初始为-1
    int rear; //队列尾 指向队列的尾元素初始化为-1

    public ArrayQueue(int maxSiez) {
        this.maxSiez = maxSiez;
        array = new int[maxSiez];
        front = -1;
        rear = -1;
    }

    public Boolean isFull() {
        return rear == maxSiez - 1;
    }

    public Boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满，不能添加！");
            return;
        }
        rear++;
        array[rear] = n;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空不能出队列！");
        }

        return array[++front];
    }

    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空不能出队列！");
        }

        return array[front + 1];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空不能查看队列");
            return;
        }

        for (int i : array) {
            System.out.printf("%d\t", i);
        }

        System.out.println();
    }
}

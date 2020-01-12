package com.mzh.dataStructures.queue;


import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {

        CircleArrayQueue queue = new CircleArrayQueue(3);

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
                    try {
                        int i = queue.getQueue();
                        System.out.println("获取的数据是：" + i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try {
                        int i = queue.headQueue();
                        System.out.println("队列的头是：" + i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'e':
                    loop = false;
                    scanner.close();
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~");

    }
}

//数组模拟环形队列要素
//1. 队头指队头 队尾指队尾下一个
//2. 队头队尾初始0
//3. 判空 队头=队尾
//4. 判满 队尾加一余上长度 等于 队头
//5. 有效数据为 队尾加长度减队头 余上 长度

class CircleArrayQueue {
    int array[];
    int maxSiez;
    int front; //队列头 指向队列头元素位置 初始为0
    int rear; //队列尾 指向队列的尾元素的下一个位置初始化为0

    public CircleArrayQueue(int maxSiez) {
        this.maxSiez = maxSiez + 1;
        array = new int[this.maxSiez];
        front = 0;
        rear = 0;
    }

    public Boolean isFull() {
        return front == (rear + 1) % maxSiez;
    }

    public Boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满，不能添加！");
            return;
        }
        int value = this.rear;
        array[value] = n;

        rear = (rear + 1) % maxSiez;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空不能出队列！");
        }
        int i = array[front];
        front = (front + 1) % maxSiez;
        return i;
    }

    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空不能出队列！");
        }

        return array[front];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空不能查看队列");
            return;
        }

        for (int i = front; i < (front+size()); i++) {
            System.out.printf("%d\t", array[i%maxSiez]);
        }

        System.out.println();
    }

    public int size() {
        return (rear + maxSiez - front) % maxSiez;
    }
}

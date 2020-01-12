package com.mzh.dataStructures.stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("push:入栈");
            System.out.println("pop:出栈");
            System.out.println("list:遍历栈");
            System.out.println("exit:退出应用程序");

            key = scanner.nextLine();

            switch (key) {
                case "push":
                    System.out.println("请输入数字：");
                    int num = scanner.nextInt();
                    stack.push(num);
                    break;
                case "pop":
                    try {
                        int i = stack.pop();
                        System.out.println("出栈的数据为：" + i);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "list":
                    stack.list();
                    break;
                case "exit":
                    loop = false;
                    scanner.close();
                    break;
            }

        }

        System.out.println("退出应用程序！");

    }
}

class ArrayStack {
    private int maxSize;
    private int stack[];
    private int top;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
        top = -1;
    }

    public Boolean isFull() {
        return top == (maxSize - 1);
    }

    public Boolean isEmpty() {
        return top == -1;
    }

    public void push(int num) {
        if (isFull()) {
            System.out.println("栈满不能入栈！！！");
            return;
        }

        stack[++top] = num;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，不能出栈");
        }

        return stack[top--];
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空不能遍历！");
            return;
        }

        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d   ", i, stack[i]);
        }

        System.out.println();
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，不能出栈");
        }

        return stack[top];
    }



}


